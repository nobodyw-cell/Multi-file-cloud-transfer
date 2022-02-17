package com.mec.mutiFileTransfer.prepare.read;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 根据设置进去的FileSectioHead读取数据
 *
 * 这里的设计感觉有点冗余
 * 1. FileSectionReader这个东西可不可重用要是可以重用的话直接构造方法里边加东西不就完了.
 * 2. rafRead 和 fileSectionHead 和fileSection都不应该作为成员出现
 * 3. 也就是说这个类其实可以浓缩为一个方法就行了
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
