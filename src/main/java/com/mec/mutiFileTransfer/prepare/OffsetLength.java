package com.mec.mutiFileTransfer.prepare;

/**
 * //TODO add class commment here
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
}
