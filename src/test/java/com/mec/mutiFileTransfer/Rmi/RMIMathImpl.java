package com.mec.mutiFileTransfer.Rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/3/10 下午7:26
 */
public class RMIMathImpl extends UnicastRemoteObject implements IRMIMath {

    public RMIMathImpl() throws RemoteException {
    }

    @Override
    public ArrayList<Double> add(int a, int b) throws RemoteException {
        ArrayList<Double> arrayList = new ArrayList<>();
        int sum = a + b;

        System.out.println("结果是" + sum);

        arrayList.add((double) sum);

        return arrayList;
    }
}
