package com.mec.mutiFileTransfer.prepare.resouce;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/4 下午9:43
 */
public class ResourceFileSectionInfo {
    private String resourceId;
    private List<FileSectionHead> fileSectionHeads;
    private int index;

    public ResourceFileSectionInfo() {
        this.fileSectionHeads = new ArrayList<>();
    }

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
}
