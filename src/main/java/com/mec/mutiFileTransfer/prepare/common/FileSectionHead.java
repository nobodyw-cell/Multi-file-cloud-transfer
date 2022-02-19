package com.mec.mutiFileTransfer.prepare.common;

import com.mec.mutiFileTransfer.util.common.TypeParser;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/2 下午4:07
 */
public class FileSectionHead {
    /**
     * 为什么这里要用 1<<15 作为传输单位
     * 首先我们先来看1 << 15是多少
     * 1<<15 = (1<<10) * (1<<5) = 1024 * 32 = 32K
     *
     * 我们传输数据用的传输层协议是tcp协议,tcp基于ip协议进行路由,ip协议包最大是64k,
     * 考虑到数据报本身就有自己的头所以我们就给,32k就好
     * 避免频繁分包.
     *
     */
    public static final int DEFAULT_SECTION_LEN = 1 << 15;
    public static final int HEAD_LEN = 4 + 4 +8;
    private int fileNo;
    private OffsetLength offsetLength;

    public FileSectionHead() {
    }


    public FileSectionHead(int fileNo, OffsetLength offsetLength) {
        this.fileNo = fileNo;
        this.offsetLength = offsetLength;
    }

    public FileSectionHead(int fileNo,long offset,int length) {
        this.fileNo = fileNo;
        this.offsetLength = new OffsetLength(offset,length);
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

    public void setLength(int len) {
        this.offsetLength.setLength(len);
    }

    public void setOffset(long offset) {
        this.offsetLength.setOffset(offset);
    }

    @Override
    public String toString() {
        return "FileSectionHead{" +
                "fileNo=" + fileNo +
                ", offsetLength=" + offsetLength +
                '}' + '\n';
    }
}
