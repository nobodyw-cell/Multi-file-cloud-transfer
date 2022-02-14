package com.mec.mutiFileTransfer.util.nio.client;

import com.mec.mutiFileTransfer.util.nio.common.INetMessageProcessor;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/14 下午2:29
 */
public abstract class NioClientConversation implements INetMessageProcessor {
    @Override
    public void dealNetMessage(byte[] message) {
        if (isASKMessage(message)) {
            dealMessage(message);
        }
    }

    public abstract void dealMessage(byte[] message);


    /**
     * 每一个消息都做这样一个措施的话,会不会效率太低.
     * 能不能像以前一样做消息头
     * @param message
     * @return
     */
    private boolean isASKMessage(byte[] message) {
        if (message.length != 4) {
            return false;
        }

        for (int index = 0; index < 4; index++) {
            if (message[index] != (byte)0xFF) {
                return false;
            }
        }

        return true;
    }
}
