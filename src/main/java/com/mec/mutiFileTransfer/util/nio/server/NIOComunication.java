package com.mec.mutiFileTransfer.util.nio.server;

import com.mec.mutiFileTransfer.util.nio.common.BaseComunication;
import com.mec.mutiFileTransfer.util.nio.common.INetMessageProcessor;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/12 下午9:38
 */
public class NIOComunication extends BaseComunication implements Runnable{
    private INetMessageProcessor processor;
    private ThreadPoolExecutor threadPool;

    public NIOComunication(Socket socket) throws IOException {
        super(socket);
    }

    public void setThreadPool(ThreadPoolExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public void setProcessor(INetMessageProcessor processor) {
        this.processor = processor;
    }

    /**
     * 如果有消息传来就开启一个线程接受并处理信息,否则不,加个线程池会更好一些.自己写的也行
     *
     * @return
     */
    public boolean receiveAndDeal() throws IOException {
        int contentLen =available();
        if (contentLen > 0) {
            if (this.threadPool == null) {
                new Thread(this).start();
            } else {
                this.threadPool.execute(this);
            }
            return true;
        }

        return false;
    }

    @Override
    public void run() {
        try {
            byte[] message = receive();
            this.processor.dealNetMessage(message);
        } catch (IOException e) {
            // 接收的时候对端掉线
            this.processor.peerAbnormalDrop();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
