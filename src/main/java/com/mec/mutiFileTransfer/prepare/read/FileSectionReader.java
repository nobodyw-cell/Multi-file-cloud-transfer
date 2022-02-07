package com.mec.mutiFileTransfer.prepare.read;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/5 下午5:51
 */
public class FileSectionReader {
    private FileSectionHead fileSectionHead;
    private RandomAccessFile rafRead;
    private byte[] fileSection;

    public void setFileSection(byte[] fileSection) {
        this.fileSection = fileSection;
    }


    public FileSectionHead getFileSectionHead() {
        return fileSectionHead;
    }

    public void setFileSectionHead(FileSectionHead fileSectionHead) {
        this.fileSectionHead = fileSectionHead;
    }

    public void setRafRead(RandomAccessFile rafRead) {
        this.rafRead = rafRead;
    }

    public void read() throws IOException {
        long offset = fileSectionHead.getOffsetLength().getOffset();
        int len = fileSectionHead.getOffsetLength().getLength();

        this.fileSection = new byte[len];

        this.rafRead.seek(offset);
        this.rafRead.read(this.fileSection);
    }

    public byte[] getFileSection() {
        return fileSection;
    }
}
