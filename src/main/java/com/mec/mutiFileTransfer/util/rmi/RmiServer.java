package com.mec.mutiFileTransfer.util.rmi;

import com.mec.mutiFileTransfer.util.common.ArgumentPackager;
import com.mec.mutiFileTransfer.util.nio.common.BaseComunication;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 接受客户端申请
 * 接受客户端发送的信息
 * 调用方法
 * 发送返回值
 *
 * @Author wfh
 * @Date 2022/2/20 下午2:42
 */
public class RmiServer implements Runnable{
    private int port;
    private volatile boolean goon;
    private ServerSocket serverSocket;
    private ThreadPoolExecutor threadPoolExecutor;

    public RmiServer(int port,ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.goon = false;
        this.port = port;
    }

    public void startUp() {
        if (this.goon == true) {
            return;
        }

        this.goon = true;

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (this.goon == true) {
            try {
                Socket clientSocket = this.serverSocket.accept();
                this.threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BaseComunication comunication = new BaseComunication(clientSocket);
                            String arguJson = new String(comunication.receive());

                            ArgumentPackager argumentPackager = ArgumentPackager.gson.fromJson(arguJson,ArgumentPackager.class);

                            Method method = argumentPackager.getMethod();
                            Object[] argus = argumentPackager.getObjects();
                            Object o = argumentPackager.getObject();

                            Object result = method.invoke(o,argus);

                            comunication.send(ArgumentPackager.gson.toJson(result).getBytes(StandardCharsets.UTF_8));
                        } catch (IOException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对线程池的使用还不够熟练,比如说这里想将所有的rmi任务全部删除掉怎么办
     */
    public void close() {
        this.goon = false;

        if (this.serverSocket != null &&!this.serverSocket.isClosed()) {
            try {
                this.serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.serverSocket = null;
            }
        }
    }
}
