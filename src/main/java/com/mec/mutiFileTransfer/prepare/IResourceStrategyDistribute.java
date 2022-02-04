package com.mec.mutiFileTransfer.prepare;

import java.util.List;

public interface IResourceStrategyDistribute {
    public static final int DEFAULT_SIZE = 1 << 15;
    public List<ResourceFileSectionInfo> distribute(ResourceStructor resourceStructor, int senderCount);
}
