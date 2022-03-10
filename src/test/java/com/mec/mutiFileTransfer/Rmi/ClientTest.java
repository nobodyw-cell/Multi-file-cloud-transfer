package com.mec.mutiFileTransfer.Rmi;

import com.mec.mutiFileTransfer.util.rmi.RMIClient;
import com.mec.mutiFileTransfer.util.rmi.RMIServer;
import com.mec.mutiFileTransfer.util.rmi.RmiClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * port是可以指定的
 *
 * @Author wfh
 * @Date 2022/2/20 下午3:11
 */
public class ClientTest {
    public static void main(String[] args) {
        RMIClient rmiClient = new RMIClient();

        IRMIMath rmiMath = rmiClient.getProxy("compute");

        try {
            System.out.println(rmiMath.add(3,7));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
