package com.mec.mutiFileTransfer.prepare.read;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/2 下午6:59
 */
public class FileSectionSender {
    private DataOutputStream dos;
    private FileSectionHead fileSectionHead;
    private byte[] fileSection;

    public FileSectionSender(DataOutputStream dataOutputStream) {
        this.dos = dataOutputStream;
    }

    public void setFileSectionHead(FileSectionHead fileSectionHead) {
        this.fileSectionHead = fileSectionHead;
    }

    public void setFileSection(byte[] fileSection) {
        this.fileSection = fileSection;
    }

    public void sendFileSection() {
        try {
            this.dos.write(fileSectionHead.toBytes());

            this.dos.write(fileSection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEnd() {
        FileSectionHead fileSectionHead = new FileSectionHead(-1,0,0);
        try {
            this.dos.write(fileSectionHead.toBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}