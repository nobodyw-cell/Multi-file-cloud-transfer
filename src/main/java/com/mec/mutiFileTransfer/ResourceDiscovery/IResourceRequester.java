package com.mec.mutiFileTransfer.ResourceDiscovery;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/2/19 下午3:28
 */
public interface IResourceRequester extends Remote {
    ArrayList<ResourceHolder> getAddressList(String resourceId) throws RemoteException;
    void deleteResourceRecord(ResourceHolder resourceHolder) throws RemoteException;
}
