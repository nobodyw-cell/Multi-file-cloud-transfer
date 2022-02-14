package com.mec.mutiFileTransfer.util.nio.common;

import com.mec.mutiFileTransfer.util.nio.common.BaseComunication;
import com.mec.mutiFileTransfer.util.nio.common.INetMessageProcessor;

import java.io.IOException;
import java.net.Socket;

/**
 * 启动线程去监听通信端口
 *
 * @Author wfh
 * @Date 2022/2/12 下午2:04
 */
public class BIOComunication extends BaseComunication implements Runnable{
    private volatile boolean goon;
    private INetMessageProcessor processor;

    public void setProcessor(INetMessageProcessor processor) {
        this.processor = processor;
    }

    public BIOComunication(Socket socket) throws IOException {
        super(socket);
        this.goon = false;
    }

    @Override
    public void close() {
        this.goon = false;
        super.close();
    }

    public void startUp() {
        if (this.goon = false) {
            // TODO
            return;
        }

        if (this.processor == null) {
            //TODO
            return;
        }

        this.goon = true;

        new Thread(this).start();
    }

    @Override
    public void run() {
        byte[] message = null;
        while (this.goon) {
            try {
                message = receive();
                this.processor.dealNetMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                if (this.goon == true) {
                    this.processor.peerAbnormalDrop();
                    this.goon = false;
                }
            }
        }
    }
}
