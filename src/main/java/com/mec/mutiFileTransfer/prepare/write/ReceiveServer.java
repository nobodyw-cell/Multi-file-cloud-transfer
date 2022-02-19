package com.mec.mutiFileTransfer.prepare.write;

import com.mec.mutiFileTransfer.prepare.distribute.DefaultDistribute;
import com.mec.mutiFileTransfer.prepare.distribute.IResourceStrategyDistribute;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileSectionInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/8 下午9:41
 */
public class ReceiveServer implements Runnable {
    public static final int DEFAULT_RECEIVE_SERVER_PORT = 54188;
    private IResourceStrategyDistribute distribute;
    private ResourceStructor resourceStructor;
    private int port;
    private ServerSocket serverSocket;
    private int senderCount;

    public ReceiveServer(ResourceStructor resourceStructor) {
        this.resourceStructor = resourceStructor;
        this.port = DEFAULT_RECEIVE_SERVER_PORT;
        this.distribute = new DefaultDistribute();
    }

    public void setResourceStructor(ResourceStructor resourceStructor) {
        this.resourceStructor = resourceStructor;
    }

    public void setDistribute(IResourceStrategyDistribute distribute) {
        this.distribute = distribute;
    }

    public void startServer(int senderCount) throws IOException {
        this.serverSocket = new ServerSocket(this.port);
        this.senderCount = senderCount;
        new Thread(this).start();
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 这里应该考虑到超时问题
     */
    @Override
    public void run() {
        int sender = 0;
        List<ResourceFileSectionInfo> resourceFileSectionInfoList = this.distribute.distribute(resourceStructor,senderCount);

        while (sender++ < senderCount) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket sender = serverSocket.accept();
                        DataInputStream dis = new DataInputStream(sender.getInputStream());

                        FileReceiver fileReceiver = new FileReceiver(dis,resourceStructor);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
