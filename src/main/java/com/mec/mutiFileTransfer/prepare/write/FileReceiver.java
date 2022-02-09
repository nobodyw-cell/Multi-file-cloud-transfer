package com.mec.mutiFileTransfer.prepare.write;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;
import com.mec.mutiFileTransfer.prepare.common.RandomAccessFilePool;
import com.mec.mutiFileTransfer.prepare.common.UnreceivedFilePool;
import com.mec.mutiFileTransfer.prepare.common.UnrecivedFileSection;
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
    private UnreceivedFilePool unreceivedFilePool;


    public FileReceiver(DataInputStream dis,
                        ResourceStructor resourceStructor) {
        this.dis = dis;
        this.resourceStructor = resourceStructor;
        this.randomAccessFilePool = new RandomAccessFilePool(resourceStructor);
        this.unreceivedFilePool = new UnreceivedFilePool(resourceStructor);
    }

    public void receive() {
        FileSectionReceiver fileSectionReceiver = new FileSectionReceiver(this.dis);

        try {
            int readLen = fileSectionReceiver.receive();

            while (readLen > 0) {
                FileSectionHead fileSectionHead = fileSectionReceiver.getFileSectionHead();

                int fileNo = fileSectionHead.getFileNo();

                UnrecivedFileSection unrecivedFileSection = this.unreceivedFilePool.getUnReceivedFileSection(fileNo);
                RandomAccessFile raf = this.randomAccessFilePool.getRaf(fileNo,"rw");

                FileSectionWriter fIleSectionWriter = new FileSectionWriter();
                fIleSectionWriter.setFileSection(fileSectionReceiver.getFileSection());
                fIleSectionWriter.setRafWrite(raf);
                fIleSectionWriter.write();

                unrecivedFileSection.receive(fileSectionHead.getOffsetLength());

                if (unrecivedFileSection.isReceiveAll()) {
                    this.randomAccessFilePool.close(fileNo);
                    this.unreceivedFilePool.remove(fileNo);
                }

                readLen = fileSectionReceiver.receive();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        if  (this.unreceivedFilePool.isReceiveAll()) {
            // TODO 接受完毕
            if (this.dis != null) {
                try {
                    this.dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    this.dis = null;
                }
            }
        }
    }
}
