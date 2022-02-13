package com.mec.mutiFileTransfer.util.nio;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  不要可不可以
 *
 * @Author wfh
 * @Date 2022/2/12 下午10:28
 */
public class NioClientPool implements Runnable{
    public static final int DEFAULT_MAX_COUNT = 10;
    private List<NIOComunication> clientList;
    private ThreadPoolExecutor threadPoolExecutor;
    private volatile boolean goon;
    private int maxCount;
    private int count;

    public NioClientPool() {
        this.clientList = new LinkedList<>();
        this.maxCount = DEFAULT_MAX_COUNT;
        this.count = 0;
    }

    /**
     * 设置每几次轮询过后做心跳检测
     * @param maxCount
     */
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public void addClient(NIOComunication comunication) {
        comunication.setThreadPool(this.threadPoolExecutor);
        this.clientList.add(comunication);
    }

    public void removeClient(NIOComunication comunication) {
        this.clientList.remove(comunication);
    }

    public void scan() {
        if (this.goon == true) {
            return;
        }

        this.goon = true;

        this.threadPoolExecutor.execute(this);
    }

    public void stopScan() {
        this.goon = false;
    }

    /**
     * 用这个看是不是在线?  avalibale引发异常说明对端不在线
     * 这个既可以处理消息有可以心跳检测(不行拔掉网线是检测不出来的)
     */
    @Override
    public void run() {
        while (this.goon) {
            if (this.clientList.isEmpty()) {
                continue;
            }

            Iterator<NIOComunication> iterator = this.clientList.iterator();

            while (iterator.hasNext()) {
                NIOComunication client = iterator.next();

                try {
                    client.receiveAndDeal();
                } catch (IOException e) {
                    iterator.remove();
                }
            }
        }
    }
}

// tcp 到底知不知道自己的消息对方接收到了没有,是send的异常能不能检测到对端掉线的基础