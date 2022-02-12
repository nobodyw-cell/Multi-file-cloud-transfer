package com.mec.mutiFileTransfer.util.nio;

import com.mec.mutiFileTransfer.util.TypeParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/12 下午2:04
 */
public class Comunication {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    private INetReader netReader;
    private volatile boolean goon;

    public Comunication(Socket socket) throws IOException {
        this.socket = socket;
        this.dis = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
        this.goon = false;
    }

    /**
     * 开启线程接受和处理消息
     */
    public void startReading() {
        if (netReader == null) {
            return;
        }

        this.goon = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] message = null;
                while (goon) {
                    byte[] messageLenBytes = new byte[4];

                    try {
                        dis.read(messageLenBytes);
                        int messageLen = TypeParser.bytesToInt(messageLenBytes);

                        message = new byte[messageLen];

                        int offset = 0;
                        int curentlen = messageLen;

                        while (curentlen > 0) {
                            int readLen =  dis.read(message,offset,curentlen);
                            offset += readLen;
                            curentlen -= readLen;
                        }
                        netReader.dealNetMessage(message);
                    } catch (IOException e) {
                        netReader.peerAbnormalDrop();
                    }
                }
            }
        }).start();
    }

    private void setNetReader(INetReader netReader) {
        this.netReader = netReader;
    }

    public void send(String message) {
        send(message.getBytes());
    }

    public void send(byte[] message) {
        int messageLen = message.length;

        byte[] lenBytes = TypeParser.toBytes(messageLen);

        try {
            this.dos.write(lenBytes);
            this.dos.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        if (this.dis != null) {
            try {
                this.dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.dis = null;
            }
        }


        if (this.dos != null) {
            try {
                this.dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.dos = null;
            }
        }

        if (this.socket != null && !this.socket.isClosed()) {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.socket = null;
            }
        }
    }

    class NetReader {

    }



}
