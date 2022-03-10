package com.mec.mutiFileTransfer.prepare.resouce;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 将整个资源根据n个发送方分成n份
 * 每份的表现形式就是这个类的一个对象
 *
 * 包含
 * 1. 资源id
 * 2. 需要发送的内容(文件以及文件片段的集合)
 *
 * @Author wfh
 * @Date 2022/2/4 下午9:43
 */
public class ResourceFileSectionInfo {
    /**
     * 所属资源的编号
     */
    private String resourceId;

    /**
     * 文件以及文件片段的集合
     */
    private List<FileSectionHead> fileSectionHeads;

    /**
     * 游标
     */
    private int index;

    public ResourceFileSectionInfo() {
        this.fileSectionHeads = new ArrayList<>();
    }

    /**
     * 根据文件编号将文件(文件片段)做一个排序
     */
    public void sort() {
        this.fileSectionHeads.sort(
                new Comparator<FileSectionHead>() {
                    @Override
                    public int compare(FileSectionHead f1, FileSectionHead f2) {
                        if (f1.getFileNo() > f2.getFileNo()) {
                            return 1;
                        } else if (f1.getFileNo() < f2.getFileNo()) {
                            return -1;
                        }
                        return 0;
                    }
                }
        );
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void addFileSection(FileSectionHead fileSection) {
        fileSectionHeads.add(fileSection);
    }

    public boolean hasNext() {
        return this.fileSectionHeads.size() > this.index;
    }

    public FileSectionHead next() {
        return this.fileSectionHeads.get(this.index++);
    }

    @Override
    public String toString() {
        return "ResourceFileSectionInfo{" +
                "fileSectionHeads=" + fileSectionHeads +
                '}' + '\n';
    }
}
