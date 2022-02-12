package com.mec.mutiFileTransfer.util.nio;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/2/12 下午3:20
 */
public interface INetMessageProcessor {
    void dealNetMessage(byte[] message);
    void peerAbnormalDrop();
}
