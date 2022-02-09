package com.mec.mutiFileTransfer.prepare.read;

import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileSectionInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/8 下午9:40
 */
public class SendClient{
    public static final int DEFAULT_PORT = 54188;
    public static final String DEFAULT_SERVER_IP = "127.0.0.1";
    private int port;
    private ResourceFileSectionInfo resourceFileSectionInfo;
    private ResourceStructor resourceStructor;
    private String serverIp;

    public SendClient() {
        this.port = DEFAULT_PORT;
        this.serverIp = DEFAULT_SERVER_IP;
    }

    public SendClient(ResourceFileSectionInfo resourceFileSectionInfo,ResourceStructor resourceStructor) {
        this.resourceFileSectionInfo = resourceFileSectionInfo;
        this.port = DEFAULT_PORT;
        this.serverIp = DEFAULT_SERVER_IP;
    }

    public void setResourceStructor(ResourceStructor resourceStructor) {
        this.resourceStructor = resourceStructor;
    }

    public void setResourceFileSectionInfo(ResourceFileSectionInfo resourceFileSectionInfo) {
        this.resourceFileSectionInfo = resourceFileSectionInfo;
    }

    public void connectToServer() {
        try {
            Socket socket = new Socket(this.serverIp,this.port);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            FileSender fileSender = new FileSender(resourceFileSectionInfo,resourceStructor,dos);
            fileSender.send();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
