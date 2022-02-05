package com.mec.mutiFileTransfer.prepare;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/4 下午10:26
 */
public class DefaultDistribute implements IResourceStrategyDistribute{

    private int maxSize;

    public DefaultDistribute() {
        this.maxSize = DEFAULT_SIZE;
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
                    int distributeSize = curSize > maxSize ? (int)maxSize: (int)curSize;

                    FileSectionHead fileSectionHead = new FileSectionHead(resourceFileInfo.getFileNo()
                                            ,resourceFileInfo.getFileSize() - curSize,distributeSize);

                    index = index++ % senderCount;

                    resourceFileSectionInfoList.get(index).addFileSection(fileSectionHead);
                    curSize -= distributeSize;
                }

            } else {
                OffsetLength offsetLength = new OffsetLength(0, (int) resourceFileInfo.getFileSize());
                FileSectionHead fileSectionHead = new FileSectionHead(resourceFileInfo.getFileNo(),offsetLength);

                index = index++ % senderCount;

                resourceFileSectionInfoList.get(index).addFileSection(fileSectionHead);
            }
        }
        return resourceFileSectionInfoList;
    }
}
