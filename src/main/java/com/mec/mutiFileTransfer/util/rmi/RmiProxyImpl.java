package com.mec.mutiFileTransfer.util.rmi;

import java.lang.reflect.InvocationHandler;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/2/19 下午10:59
 */
public interface RmiProxyImpl extends InvocationHandler {
    void setRmiClient(RmiClient rmiClient);
}
