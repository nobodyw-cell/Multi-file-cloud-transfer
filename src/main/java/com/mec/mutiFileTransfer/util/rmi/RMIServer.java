package com.mec.mutiFileTransfer.util.rmi;

import com.mec.mutiFileTransfer.util.common.IListener;
import com.mec.mutiFileTransfer.util.common.ISpeaker;

import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * 先用原生写个凑和能用的吧.
 *
 * 端口可以指定不过默认是1099
 * 肯定不能用默认啊
 * @Author wfh
 * @Date 2022/3/10 下午7:54
 */
public class RMIServer implements ISpeaker {
    public static final int DEFALUT_RMI_PORT = 1099;
    private int port;
    private boolean goon;
    private List<IListener> listeners;
    private Registry registry;

    public RMIServer() {
        this.goon = false;
        this.port = DEFALUT_RMI_PORT;
        this.listeners = new ArrayList<>();
    }

    public boolean isStartUp() {
        return this.goon;
    }


    public void setPort(int port) {
        this.port = port;
    }

    public void startUp() {
        if (this.goon == true) {
            speak("服务器不可重复启动!!!");
            return;
        }

        this.goon = true;

        try {
            this.registry = LocateRegistry.createRegistry(this.port);
            speak("RMIServer启动成功,负责负责监听的端口号为" + this.port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void terminate() {
        if(this.goon == false) {
            speak("服务器没开啊怎么关啊,宝贝");
            return;
        }

        this.goon = false;

        try {
            UnicastRemoteObject.unexportObject(this.registry,true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    public void bind(String s, Remote remote) {
        if (this.goon == false) {
            speak("服务没开启啊,宝贝");
            return;
        }

        try {
            this.registry.bind(s,remote);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
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
