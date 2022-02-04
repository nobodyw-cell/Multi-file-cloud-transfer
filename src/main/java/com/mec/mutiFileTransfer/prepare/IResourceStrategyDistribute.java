package com.mec.mutiFileTransfer.prepare;

import java.util.List;

public interface IResourceStrategyDistribute {
    public List<ResourceFileSectionInfo> distribute(ResourceStructor resourceStructor, int senderCount);
}
