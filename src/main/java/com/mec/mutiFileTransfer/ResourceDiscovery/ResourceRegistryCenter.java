package com.mec.mutiFileTransfer.ResourceDiscovery;

import com.mec.mutiFileTransfer.util.common.IListener;
import com.mec.mutiFileTransfer.util.common.ISpeaker;
import com.mec.mutiFileTransfer.util.rmi.RMIServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源注册中心
 *
 * @Author wfh
 * @Date 2022/3/10 下午6:54
 */
public class ResourceRegistryCenter implements IListener, ISpeaker {
    private RMIServer server;
    private ResourceAddressPool resourceAddressPool;

    private List<IListener> listeners;

    public ResourceRegistryCenter() {
        this.server = new RMIServer();
        try {
            this.resourceAddressPool = new ResourceAddressPool();
            // TODO delete
            ResourceHolder resourceHolder = new ResourceHolder();
            resourceHolder.setPort(432);
            resourceHolder.setIp("localhost");
            this.resourceAddressPool.RegistResourceAddress("com.xulinux.wang",resourceHolder);
            System.out.println("注册成功一个" + resourceHolder);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.listeners = new ArrayList<>();
        this.server.addListenner(this);
    }

    public void startUp() {
        this.server.startUp();

        this.server.bind("IResourceRequester",this.resourceAddressPool);
    }

    public void setRMIPort(int port) {
        this.server.setPort(port);
    }

    @Override
    public void messageFromSpeaker(String message) {
        System.out.println(message);
    }

    @Override
    public void speak(String message) {
        for (IListener listener : listeners) {
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

/**
 * 47 2
 *
 * 用名字找对象很接近ioc了
 *
 * today
 * todo list
 * 1. 把这个项目再好好看看
 * 2. 学拉普拉斯变换
 * 3. 看会儿算法
 */
