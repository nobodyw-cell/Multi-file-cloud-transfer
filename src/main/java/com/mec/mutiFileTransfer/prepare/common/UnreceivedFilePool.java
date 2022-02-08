package com.mec.mutiFileTransfer.prepare.common;

import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.util.HashMap;
import java.util.Map;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/8 下午10:59
 */
public class UnreceivedFilePool {
    private Map<Integer,UnrecivedFileSection> filePool;
    private ResourceStructor resourceStructor;

    public UnreceivedFilePool(ResourceStructor resourceStructor) {
        this.filePool = new HashMap<>();
        this.resourceStructor = resourceStructor;
        ininPool();
    }

    public void ininPool() {
        while(this.resourceStructor.hasNext()) {
            ResourceFileInfo resourceFileInfo = this.resourceStructor.next();
            this.filePool.put(resourceFileInfo.getFileNo(),new UnrecivedFileSection((int) resourceFileInfo.getFileSize()));
        }
    }

    public UnrecivedFileSection getUnReceivedFileSection(int fileNo) {
        return this.filePool.get(fileNo);
    }

    public boolean isReceiveAll() {
        return this.filePool.size() == 0;
    }

    public void remove(int fileNo) {
        this.filePool.remove(fileNo);
    }
}
