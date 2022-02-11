package com.mec.mutiFileTransfer.prepare.distribute;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;
import com.mec.mutiFileTransfer.prepare.common.OffsetLength;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileSectionInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/4 下午10:26
 */
public class DefaultDistribute implements IResourceStrategyDistribute {

    private int maxSize;

    public DefaultDistribute() {
        this.maxSize = DEFAULT_MAX_SIZE;
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public List<ResourceFileSectionInfo> distribute(ResourceStructor resourceStructor, int senderCount) {
        long curSize = 0;
        int index = 0;

        List<ResourceFileSectionInfo> resourceFileSectionInfoList = new ArrayList<>();

        for (int i = 0; i < senderCount; i++) {
            resourceFileSectionInfoList.add(new ResourceFileSectionInfo());
        }

        while (resourceStructor.hasNext()) {
            ResourceFileInfo resourceFileInfo = resourceStructor.next();
            curSize = resourceFileInfo.getFileSize();

            if (curSize > this.maxSize) {
                while(curSize != 0) {
                    int distributeSize = curSize > maxSize ? maxSize: (int)curSize;

                    FileSectionHead fileSectionHead = new FileSectionHead(resourceFileInfo.getFileNo()
                                            ,resourceFileInfo.getFileSize() - curSize,distributeSize);

                    resourceFileSectionInfoList.get(index++ % senderCount).addFileSection(fileSectionHead);
                    curSize -= distributeSize;
                }
            } else {
                // 为什么这里能够进行强制转换呢,因为已经判断他小于8M了
                OffsetLength offsetLength = new OffsetLength(0, (int) resourceFileInfo.getFileSize());
                FileSectionHead fileSectionHead = new FileSectionHead(resourceFileInfo.getFileNo(),offsetLength);

                resourceFileSectionInfoList.get(index++ % senderCount).addFileSection(fileSectionHead);
            }
        }
        return resourceFileSectionInfoList;
    }
}
