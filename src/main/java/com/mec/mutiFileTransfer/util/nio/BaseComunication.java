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
 * @Date 2022/2/12 下午9:26
 */
public class BaseComunication {
    protected Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    public BaseComunication(Socket socket) throws IOException {
        this.socket = socket;
        this.dis = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
    }

    public String receiveString() throws IOException {
        byte[] message = receive();

        return String.valueOf(message);
    }

    protected int available() {
        try {
            return this.dis.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public byte[] receive() throws IOException {
        byte[] messageLenBytes = new byte[4];
        this.dis.read(messageLenBytes);
        int messageLen = TypeParser.bytesToInt(messageLenBytes);

        byte[] message = new byte[messageLen];

        int offset = 0;
        int curentlen = messageLen;

        while (curentlen > 0) {
            int readLen =  dis.read(message,offset,curentlen);
            offset += readLen;
            curentlen -= readLen;
        }

        return message;
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

}
