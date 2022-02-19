package com.mec.mutiFileTransfer.ResourceDiscovery;

import java.util.List;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/2/19 下午3:28
 */
public interface IResourceRequester {
    List<ResourceHolder> getAddressList(String resourceId);
    void deleteResourceRecord(ResourceHolder resourceHolder);
}
