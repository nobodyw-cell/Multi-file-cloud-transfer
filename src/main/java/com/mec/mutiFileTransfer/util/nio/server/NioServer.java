package com.mec.mutiFileTransfer.util.nio.server;

import com.mec.mutiFileTransfer.util.IListener;
import com.mec.mutiFileTransfer.util.ISpeaker;
import com.mec.mutiFileTransfer.util.nio.common.INetMessageProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/12 下午1:10
 */
public class NioServer implements Runnable, ISpeaker {
    private ServerSocket server;
    private volatile boolean goon;
    private INetMessageProcessor processor;
    private int port;
    private List<IListener> listeners;
    private ThreadPoolExecutor threadPool;
    private NioClientPool clientPool;

    public NioServer() {
        this.goon = false;
        this.listeners = new ArrayList<>();
        this.clientPool = new NioClientPool();
//        this.threadPool = new ThreadPoolExecutor(10,20,200,);
    }

    public void startUp() throws IOException {
        if (this.goon == true) {
            speak("NIO 服务器已启动不可重复启动");
            return;
        }

        speak("正在启动NIO服务器");
        this.goon = true;

        this.server = new ServerSocket(this.port);
        this.clientPool.scan();
        speak("NIO 服务器启动成功,正在监听端口请求");
        new Thread(this).start();
    }

    public NioServer(int port) {
        this();
        this.port = port;
    }

    public void setProcessor(INetMessageProcessor processor) {
        this.processor = processor;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (this.goon) {
            try {
                Socket client = this.server.accept();

                String clientIp = client.getInetAddress().getHostAddress();

                speak("客户端[" + clientIp + "]请求连接");

                NIOComunication comunication = new NIOComunication(client);
                comunication.setProcessor(this.processor);
                comunication.setThreadPool(this.threadPool);

                this.clientPool.addClient(comunication);
            } catch (IOException e) {
                if (this.goon) {
                    speak("NIO 服务器异常关闭");
                    this.goon = false;
                } else {
                    speak("NIO 服务器关闭成功");
                }
            }
        }

        this.clientPool.stopScan();
    }

    @Override
    public void speak(String message) {
        for (IListener listener : this.listeners) {
            listener.messageFromSpeaker(message);
        }
    }

    @Override
    public void addListenner(IListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeListenner(IListener listener) {
        this.listeners.remove(listener);
    }
}
