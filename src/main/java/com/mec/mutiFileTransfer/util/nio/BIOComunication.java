package com.mec.mutiFileTransfer.util.nio;

import com.mec.mutiFileTransfer.util.TypeParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * //TODO add class commment here
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
        this.goon = true;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (this.goon) {
            try {
                receive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
