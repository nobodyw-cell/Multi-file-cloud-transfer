package com.mec.mutiFileTransfer.prepare.write;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;
import com.mec.mutiFileTransfer.prepare.common.RandomAccessFilePool;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.io.DataInputStream;
import java.io.IOException;
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
        FileSectionReceiver fileSectionReceiver = new FileSectionReceiver(this.dis);

        try {
            int readLen = fileSectionReceiver.receive();

            while (readLen > 0) {
                int fileNo = fileSectionReceiver.getFileNo();
                RandomAccessFile raf = this.randomAccessFilePool.getRaf(fileNo,"rw");

                FIleSectionWriter fIleSectionWriter = new FIleSectionWriter();
                fIleSectionWriter.setFileSection(fileSectionReceiver.getFileSection());
                fIleSectionWriter.setRafWrite(raf);

                fIleSectionWriter.write();

                readLen = fileSectionReceiver.receive();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
