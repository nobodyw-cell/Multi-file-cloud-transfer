package com.mec.mutiFileTransfer.util.nio;

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
    private List<NIOComunication> clientList;
    private ThreadPoolExecutor threadPoolExecutor;
    private volatile boolean goon;

    public NioClientPool() {
        this.clientList = new LinkedList<>();
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
        if (!goon) {
            return;
        }

        this.goon = false;
    }

    @Override
    public void run() {
        while (this.goon) {
            if (this.clientList.isEmpty()) {
                continue;
            }

            for (NIOComunication nioComunication : this.clientList) {
                  
            }
        }
    }
}