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
                // 开始分割
            } else {
                // 直接添加
            }
        }
        return resourceFileSectionInfoList;
    }
}
