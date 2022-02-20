package com.mec.mutiFileTransfer.util.rmi;

import com.mec.mutiFileTransfer.ResourceDiscovery.INodeAddress;
import com.mec.mutiFileTransfer.util.nio.common.BaseComunication;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
    private RmiProxyImpl rmiProxy;

    public RmiClient() {
        this.rmiProxy = new DefaultRmiProxyimpl();
        this.rmiProxy.setRmiClient(this);
    }

    public RmiClient(INodeAddress nodeAddress) {
        this();
        this.nodeAddress = nodeAddress;
    }

    public void setNodeAddress(INodeAddress nodeAddress) {
        this.nodeAddress = nodeAddress;
    }

    /**
     * 用包管理权限代理友元,但也只能模仿权限不会有性能的改善
     * 其实用包管理权限也是不合适的
     * @return
     */
    void sendArgu(String argu) {
        try {
            this.comunication.send(argu.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String receiveResult() {
        try {
            return  new String(this.comunication.receive());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public INodeAddress getNodeAddress() {
        return nodeAddress;
    }

    public void connectToServer() throws Exception {
        if (this.nodeAddress == null) {
            throw new Exception("未设置服务器地址");
        }

        this.server = new Socket(nodeAddress.getIp(),nodeAddress.getPort());

        this.comunication = new BaseComunication(server);
    }

    public <T> T getRmiProxy(Class<T> klass) {
        return (T) Proxy.newProxyInstance(
                klass.getClassLoader()
                , klass.getInterfaces()
                , this.rmiProxy);
    }

    public void close() {
        this.comunication.close();
    }

}
