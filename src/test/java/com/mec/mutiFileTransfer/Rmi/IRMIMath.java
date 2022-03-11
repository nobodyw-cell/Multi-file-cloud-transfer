package com.mec.mutiFileTransfer.Rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/3/10 下午7:23
 */
public interface IRMIMath extends Remote {
    public ArrayList<Double> add(int a, int b) throws RemoteException;
}
