package com.mec.mutiFileTransfer.prepare.write;

import com.mec.mutiFileTransfer.prepare.common.RandomAccessFilePool;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.io.DataInputStream;
import java.io.RandomAccessFile;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/7 下午8:55
 */
public class FileReceiver {
    private DataInputStream dis;
    private ResourceStructor resourceStructor;
    private RandomAccessFilePool randomAccessFilePool;


    public FileReceiver(DataInputStream dis,
                        ResourceStructor resourceStructor) {
        this.dis = dis;
        this.resourceStructor = resourceStructor;
        this.randomAccessFilePool = new RandomAccessFilePool(resourceStructor);
    }

    public void receive() {

    }
}
