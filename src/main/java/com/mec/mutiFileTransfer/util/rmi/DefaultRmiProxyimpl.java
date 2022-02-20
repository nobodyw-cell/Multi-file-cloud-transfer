package com.mec.mutiFileTransfer.util.rmi;

import com.mec.mutiFileTransfer.ResourceDiscovery.INodeAddress;
import com.mec.mutiFileTransfer.util.common.ArgumentPackager;
import com.mec.mutiFileTransfer.util.view.Say;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/19 下午10:52
 */
public class DefaultRmiProxyimpl implements RmiProxyImpl {
    private RmiClient rmiClient;

    public void setRmiClient(RmiClient rmiClient) {
        this.rmiClient = rmiClient;
    }

    /**
     * 各有各的好处吧,这样降低了耦合性,增加了可维护性,
     * 但是不用内部类使得有一些属性想暴露也暴露不了,c++用了友元类解决了这个问题,我们的内部类看来不够灵活啊
     *
     * 1. 首先连接至server
     * 2. 发送调用请求
     * 3. 接收调用返回
     *
     * @param o
     * @param method
     * @param objects
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        this.rmiClient.connectToServer();
        ArgumentPackager argumentPackager = new ArgumentPackager(o,method,objects);
        System.out.println(argumentPackager);
        this.rmiClient.sendArgu(ArgumentPackager.gson.toJson(argumentPackager));

        String result = this.rmiClient.receiveResult();

        Object object = ArgumentPackager.gson.fromJson(result,o.getClass());
        return object;
    }
}

/**
 * 为什么Method的hashcode不考虑重载
 */