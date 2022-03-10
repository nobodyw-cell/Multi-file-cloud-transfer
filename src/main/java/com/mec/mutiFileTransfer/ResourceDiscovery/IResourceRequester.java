package com.mec.mutiFileTransfer.ResourceDiscovery;

import java.rmi.Remote;
import java.util.List;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/2/19 下午3:28
 */
public interface IResourceRequester extends Remote {
    List<ResourceHolder> getAddressList(String resourceId);
    void deleteResourceRecord(ResourceHolder resourceHolder);
}
