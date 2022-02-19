package com.mec.mutiFileTransfer.ResourceDiscovery;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/19 下午10:29
 */
public class NodeAddress implements INodeAddress{
    private String ip;
    private int port;

    public NodeAddress(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public NodeAddress() {
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }
}
