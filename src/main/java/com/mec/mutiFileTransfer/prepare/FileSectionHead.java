package com.mec.mutiFileTransfer.prepare;

import com.mec.mutiFileTransfer.util.TypeParser;

import java.io.File;
import java.util.Arrays;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/2 下午4:07
 */
public class FileSectionHead {
    private static final int HEAD_LEN = 4 + 8 +8;
    private int fileNo;
    private OffsetLength offsetLength;

    public FileSectionHead(int fileNo, OffsetLength offsetLength) {
        this.fileNo = fileNo;
        this.offsetLength = offsetLength;
    }

    public FileSectionHead(int fileNo,long offset,long length) {
        this.fileNo = fileNo;
        this.offsetLength.setOffset(offset);
        this.offsetLength.setLength(length);
    }

    public FileSectionHead(byte[] bytes) {
        byte[] fileNoBytes = TypeParser.subBytes(bytes,0,4);
        byte[] offsetBytes = TypeParser.subBytes(bytes,4,8);
        byte[] lengthBytes = TypeParser.subBytes(bytes,12,8);

        this.fileNo = TypeParser.bytesToInt(fileNoBytes);
        this.offsetLength.setOffset(TypeParser.bytesToLong(offsetBytes));
        this.offsetLength.setLength(TypeParser.bytesToLong(lengthBytes));
    }

    public byte[] toBytes() {
        byte[] bytes = new byte[HEAD_LEN];

        byte[] fileNoBytes = TypeParser.toBytes(fileNo);
        byte[] offsetBytes = TypeParser.toBytes(offsetLength.getOffset());
        byte[] lengthBytes = TypeParser.toBytes(offsetLength.getLength());

        TypeParser.setByteAt(bytes,0,fileNoBytes);
        TypeParser.setByteAt(bytes,4,fileNoBytes);
        TypeParser.setByteAt(bytes,12,fileNoBytes);

        return bytes;
    }

    public int getFileNo() {
        return fileNo;
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public OffsetLength getOffsetLength() {
        return offsetLength;
    }

    public void setOffsetLength(OffsetLength offsetLength) {
        this.offsetLength = offsetLength;
    }
}
