package com.mec.mutiFileTransfer.ResourceDiscovery;

import com.mec.mutiFileTransfer.util.common.IListener;
import com.mec.mutiFileTransfer.util.common.ISpeaker;
import com.mec.mutiFileTransfer.util.rmi.RMIServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * //TODO add class commment here
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
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.listeners = new ArrayList<>();
        this.server.addListenner(this);
    }

    public void setRMIPort(int port) {
        this.server.setPort(port);
    }

    @Override
    public void messageFromSpeaker(String message) {
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
 */
