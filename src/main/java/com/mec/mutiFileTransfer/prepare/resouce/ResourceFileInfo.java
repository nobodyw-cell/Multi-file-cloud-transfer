package com.mec.mutiFileTransfer.prepare.resouce;

import java.util.Objects;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/3 下午9:55
 */
public class ResourceFileInfo {
    public ResourceFileInfo() {
    }

    /**
     * 从零开始的连续文件编号
     */
    private int fileNo;
    /**
     * 文件名称含有路径但路径不含绝对根
     */
    private String fileName;
    /**
     * 文件长度
     */
    private long fileSize;

    public int getFileNo() {
        return fileNo;
    }

    void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceFileInfo that = (ResourceFileInfo) o;
        return fileNo == that.fileNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileNo);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "ResourceFileInfo{" +
                "fileNo=" + fileNo +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
