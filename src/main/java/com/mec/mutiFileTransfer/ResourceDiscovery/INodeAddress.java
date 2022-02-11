package com.mec.mutiFileTransfer.ResourceDiscovery;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/2/11 下午8:48
 */
public interface INodeAddress {
    void setIp(String ip);
    void setPort(int port);

    int getPort();
    String getIp();
}
