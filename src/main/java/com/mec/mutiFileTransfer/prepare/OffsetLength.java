package com.mec.mutiFileTransfer.prepare;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/2 下午4:09
 */
public class OffsetLength {
    private long offset;
    private long length;

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public OffsetLength(long offset, long length) {
        this.offset = offset;
        this.length = length;
    }
}
