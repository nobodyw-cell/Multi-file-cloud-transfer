package com.mec.mutiFileTransfer.prepare.common;

/**
 *  描述文件片段在文件中的偏移量以及长度.
 *
 * @Author wfh
 * @Date 2022/2/2 下午4:09
 */
public class OffsetLength {
    private long offset;
    private int length;

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public OffsetLength(long offset, int length) {
        this.offset = offset;
        this.length = length;
    }

    @Override
    public String toString() {
        return offset + ":" + length;
    }
}
