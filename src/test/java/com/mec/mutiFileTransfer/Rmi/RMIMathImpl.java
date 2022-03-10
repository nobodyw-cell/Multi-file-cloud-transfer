package com.mec.mutiFileTransfer.Rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/3/10 下午7:26
 */
public class RMIMathImpl extends UnicastRemoteObject implements IRMIMath {

    protected RMIMathImpl() throws RemoteException {
    }

    @Override
    public double add(int a, int b) throws RemoteException {
        int sum = a + b;

        System.out.println("结果是" + sum);


        return sum;
    }
}
