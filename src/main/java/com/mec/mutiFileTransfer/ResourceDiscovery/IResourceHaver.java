package com.mec.mutiFileTransfer.ResourceDiscovery;

import java.rmi.Remote;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/2/19 下午3:28
 */
public interface IResourceHaver {
    void resourceRegistry(String resourceId,ResourceHolder address);
    void resourceDestroy(ResourceHolder address);
}
