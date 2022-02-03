package com.mec.mutiFileTransfer.prepare;

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
}