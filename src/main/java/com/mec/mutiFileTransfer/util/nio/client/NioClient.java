package com.mec.mutiFileTransfer.util.nio.client;

import com.mec.mutiFileTransfer.util.nio.common.BIOComunication;

import java.io.IOException;
import java.net.Socket;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/14 下午1:16
 */
public class NioClient{
    private int port;
    private Socket socket;
    private String ip;
    private NioClientConversation conversation;
    private Communication comunication;
    private boolean connectted;


    public NioClient() {
        this.connectted = false;
    }

    public void setProcessor(NioClientConversation conversation) {
        this.conversation = conversation;
    }

    public void connectToServer() throws IOException {
        if (this.connectted == true) {
            return;
        }

        System.out.println("正在连接至服务器" + this.ip + ":" + this.port);
        this.socket = new Socket(ip, port);
        System.out.println(this.socket);
        this.comunication = new Communication(socket);
        this.comunication.setProcessor(this.conversation);
        this.connectted = true;
        this.comunication.startUp();
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void close() {
        if (this.comunication != null) {
            this.comunication.close();}
    }

    /**
     * 通过内部类进行提权
     */
    class Communication extends BIOComunication{

        public Communication(Socket socket) throws IOException {
            super(socket);
        }

        @Override
        public void close() {
            super.close();
        }
    }

}