package com.mec.mutiFileTransfer.prepare.distribute;

import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileSectionInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.util.List;

/**
 * 资源分配策略接口
 *
 * 为什么要将这里做成一个接口
 * 1. 降低程序的耦合性,方便以后改变分配策略
 */
public interface IResourceStrategyDistribute {
    /**
     * 分的时候分大一点,8M就很不错.
     * 1024*1024*8
     */
    public static final int DEFAULT_MAX_SIZE = 1 << 23;
    public void setMaxSize(int maxSize);

    public List<ResourceFileSectionInfo> distribute(ResourceStructor resourceStructor, int senderCount);
}
