package com.mec.mutiFileTransfer.prepare;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/2 下午6:52
 */
public class FileSection {
    private FileSectionHead fileSectionHead;
    private byte[] fileSection;

    public FileSection(FileSectionHead fileSectionHead) {
        this.fileSectionHead = fileSectionHead;
    }

    public FileSection() {
    }

    public void setFileSectionHead(FileSectionHead fileSectionHead) {
        this.fileSectionHead = fileSectionHead;
    }
}
