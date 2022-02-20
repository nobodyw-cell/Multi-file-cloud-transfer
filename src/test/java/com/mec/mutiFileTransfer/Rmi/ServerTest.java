package com.mec.mutiFileTransfer.Rmi;

import com.mec.mutiFileTransfer.util.rmi.RmiServer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/20 下午3:11
 */
public class ServerTest {
    public static void main(String[] args) {
        RmiServer rmiServer = new RmiServer(54188,new ThreadPoolExecutor(10,
                20,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()));

        rmiServer.startUp();
    }
}
