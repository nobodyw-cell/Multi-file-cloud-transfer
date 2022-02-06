package com.mec.mutiFileTransfer.prepare;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/5 下午5:52
 */
public class FIleSectionWriter {
    private FileSectionHead fileSectionHead;
    private RandomAccessFile rafWrite;
    private byte[] fileSection;

    public void setFileSectionHead(FileSectionHead fileSectionHead) {
        this.fileSectionHead = fileSectionHead;
    }

    public void setRafWrite(RandomAccessFile rafWrite) {
        this.rafWrite = rafWrite;
    }

    public void setFileSection(byte[] fileSection) {
        this.fileSection = fileSection;
    }

    public void write() throws IOException {
        this.rafWrite.seek(fileSectionHead.getOffsetLength().getOffset());
        this.rafWrite.write(this.fileSection);
    }
}
