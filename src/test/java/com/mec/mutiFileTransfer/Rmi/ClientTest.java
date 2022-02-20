package com.mec.mutiFileTransfer.Rmi;

import com.mec.mutiFileTransfer.ResourceDiscovery.NodeAddress;
import com.mec.mutiFileTransfer.util.rmi.RmiClient;
import com.mec.mutiFileTransfer.util.view.ISay;
import com.mec.mutiFileTransfer.util.view.Say;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/20 下午3:11
 */
public class ClientTest {
    public static void main(String[] args) {
        RmiClient rmiClient = new RmiClient(new NodeAddress("127.0.0.1",54188));
        ISay say = rmiClient.getRmiProxy(Say.class);

        System.out.println(say.say());
    }
}
