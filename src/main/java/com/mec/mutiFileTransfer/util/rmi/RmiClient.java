package com.mec.mutiFileTransfer.util.rmi;

import com.mec.mutiFileTransfer.ResourceDiscovery.INodeAddress;
import com.mec.mutiFileTransfer.util.nio.common.BaseComunication;

import java.net.Socket;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/19 下午10:28
 */
public class RmiClient {
    private INodeAddress nodeAddress;
    private Socket server;
    private BaseComunication comunication;

    public RmiClient() {
    }

    public RmiClient(INodeAddress nodeAddress) {
        this.nodeAddress = nodeAddress;
    }

    public void setNodeAddress(INodeAddress nodeAddress) {
        this.nodeAddress = nodeAddress;
    }

    public INodeAddress getNodeAddress() {
        return nodeAddress;
    }

    private void connectToServer() throws Exception {
        if (this.nodeAddress == null) {
            throw new Exception("未设置服务器地址");
        }

        this.server = new Socket(nodeAddress.getIp(),nodeAddress.getPort());

        this.comunication = new BaseComunication(server);
    }

    public void get

}
