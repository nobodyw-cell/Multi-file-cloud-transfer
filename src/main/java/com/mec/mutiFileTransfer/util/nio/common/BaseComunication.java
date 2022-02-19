package com.mec.mutiFileTransfer.util.nio.common;

import com.mec.mutiFileTransfer.util.common.TypeParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 怎么不做成真的nio,有什么区别呢,教主的这种和真的有什么区别呢
 *
 * 如果阻塞不耗时维护这个线程又如何
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

    protected int available() throws IOException {
        return this.dis.available();
    }

    protected byte[] receive() throws IOException {
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

    public void send(byte[] message) throws IOException {
        int messageLen = message.length;

        byte[] lenBytes = TypeParser.toBytes(messageLen);

        this.dos.write(lenBytes);
        this.dos.write(message);

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

/**
 * 传统的BIO里面socket.read()，如果TCP RecvBuffer里没有数据，函数会一直阻塞，直到收到数据，返回读到的数据。
 * 对于NIO，如果TCP RecvBuffer有数据，就把数据从网卡读到内存，并且返回给用户；反之则直接返回0，永远不会阻塞。
 * AIO(Async I/O)里面会更进一步：不但等待就绪是非阻塞的，就连数据从网卡到内存的过程也是异步的。
 */
