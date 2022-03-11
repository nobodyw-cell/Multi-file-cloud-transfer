package com.mec.mutiFileTransfer.Rmi;

import com.mec.mutiFileTransfer.ResourceDiscovery.IResourceRequester;
import com.mec.mutiFileTransfer.util.rmi.RMIClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/3/11 上午11:50
 */
public class CenterRequestTest {
    public static void main(String[] args) {
        RMIClient rmiClient = new RMIClient();

        IResourceRequester resourceRequester = rmiClient.getProxy("IResourceRequester");

        try {
            System.out.println(resourceRequester.getAddressList("com.xulinux.wang"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
