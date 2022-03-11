package com.mec.mutiFileTransfer.Rmi;

import com.mec.mutiFileTransfer.ResourceDiscovery.ResourceAddressPool;
import com.mec.mutiFileTransfer.ResourceDiscovery.ResourceHolder;
import com.mec.mutiFileTransfer.util.common.IListener;
import com.mec.mutiFileTransfer.util.rmi.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 分析和自己比如何
 *
 * 这里的取舍真的是一种很棒的设计
 * 1. 没有过于严格的要求用户
 * 2. 减少开发难度
 * 3. 增加了灵活性
 *
 *
 * 不过要是自己真的用spring做一个rmi肯定会更好用一些.
 *
 * @Author wfh
 * @Date 2022/2/20 下午3:11
 */
public class ServerTest {
    public static void main(String[] args) {
        RMIServer rmiServer = new RMIServer();

        IListener listener = new IListener() {
            @Override
            public void messageFromSpeaker(String message) {
                System.out.println(message);
            }
        };

        rmiServer.addListenner(listener);

        rmiServer.startUp();

        try {
            rmiServer.bind("compute",new RMIMathImpl());


            ResourceAddressPool resourceAddressPool = new ResourceAddressPool();
            ResourceHolder resourceHolder = new ResourceHolder();

            resourceHolder.setIp("local");
            resourceHolder.setPort(102343);
            resourceAddressPool.RegistResourceAddress("com.linux",resourceHolder);

            rmiServer.bind("com",resourceAddressPool);


            rmiServer.bind("IResourceRequester",new ResourceAddressPool());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
