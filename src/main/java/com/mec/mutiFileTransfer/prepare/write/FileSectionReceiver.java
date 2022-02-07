package com.mec.mutiFileTransfer.prepare.write;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/2 下午6:59
 */
public class FileSectionReceiver {
    private DataInputStream dis;
    private FileSectionHead fileSectionHead;
    private byte[] fileSection;

    public FileSectionReceiver(DataInputStream dis) {
        this.dis = dis;
    }

    public void setFileSectionHead(FileSectionHead fileSectionHead) {
        this.fileSectionHead = fileSectionHead;
    }

    public void receive() {
        byte[] headBytes = new byte[16];

        try {
            this.dis.read(headBytes);

            this.fileSectionHead = new FileSectionHead(headBytes);

            this.fileSection = new byte[this.fileSectionHead.getOffsetLength().getLength()];

            this.dis.read(fileSection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
