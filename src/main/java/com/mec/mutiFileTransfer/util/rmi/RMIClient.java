package com.mec.mutiFileTransfer.util.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/3/10 下午8:45
 */
public class RMIClient {
    public static final String DEFAULT_RMI_SERVER_IP = "localhost";
    public static final int DEFAULT_PORT = 1099;
    private String ip;
    private int port;
    private Registry registry;

    public RMIClient() {
        this.ip = DEFAULT_RMI_SERVER_IP;
        this.port = DEFAULT_PORT;

        try {
            this.registry = LocateRegistry.getRegistry(this.ip,this.port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public RMIClient(String ip, int port) {
        this.ip = ip;
        this.port = port;

        try {
            this.registry = LocateRegistry.getRegistry(this.ip,this.port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public <T> T getProxy(String s) {
        try {
             return (T)this.registry.lookup(s);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
