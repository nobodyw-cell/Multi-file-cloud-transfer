package com.mec.mutiFileTransfer.prepare.common;

import com.mec.mutiFileTransfer.prepare.distribute.DefaultDistribute;
import com.mec.mutiFileTransfer.prepare.distribute.IResourceStrategyDistribute;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileSectionInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.io.File;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.Iterator;
import java.util.List;

/**
 * 负责资源接收前的准备工作
 * 1. 创建目录
 * 2. 根据现有资源进行分发
 *
 * @Author wfh
 * @Date 2022/2/11 下午9:00
 */
public class ResourcePrepare {
    /**
     * 本地机的资源描述,通过checkoutHostResource设置进来set,他不应该破坏原有的资源描述
     */
    private ResourceStructor resourceStructor;
    /**
     * 本机绝对路径
     */
    private String absolutePath;
    private int senderCount;
    private IResourceStrategyDistribute distribute;
    private int index;

    private List<ResourceFileSectionInfo> resourceFileSectionInfoList;

    public ResourcePrepare() {
    }
    
    /**
     * 接受之前的准备,需要完全的资源结构
     * @param orgResourceStructor
     */
    public void prepare(String absolutePath,ResourceStructor orgResourceStructor) {
        this.absolutePath = absolutePath;
        creatDir(orgResourceStructor);
        checkOutHostResource(orgResourceStructor);
    }

    public void setDistribute(IResourceStrategyDistribute distribute) {
        this.distribute = distribute;
    }

    public void distribute(int senderCount) {
        this.senderCount = senderCount;

         IResourceStrategyDistribute distribute = this.distribute == null ? new DefaultDistribute() : this.distribute;

         this.resourceFileSectionInfoList = distribute.distribute(this.resourceStructor,senderCount);
         this.index = 0;
    }

    public boolean hasNext() {
        if (this.index < this.resourceFileSectionInfoList.size()) {
            return true;
        }
        this.index = 0;
        return false;
    }

    public ResourceFileSectionInfo next() {
        return this.resourceFileSectionInfoList.get(this.index);
    }

    /**
     * 检查本地资源,删除掉传输好的file
     * @param orgResourceStructor
     */
    private void checkOutHostResource(ResourceStructor orgResourceStructor) {
        this.resourceStructor = new ResourceStructor(orgResourceStructor);

        List<ResourceFileInfo> fileList = this.resourceStructor.getFileList();

        Iterator<ResourceFileInfo> iterator = fileList.iterator();
        if (iterator.hasNext()) {
            ResourceFileInfo resourceFileInfo = iterator.next();
            File file = new File(this.absolutePath + resourceFileInfo.getFileName());
            if (file.exists()) {
                iterator.remove();
            }
        }
    }


    private void creatDir(ResourceStructor orgResourceStructor) {
        List<String> dirs = orgResourceStructor.getResourcesDirectories();

        for (String dir : dirs) {
            File dirFile = new File(this.absolutePath + dir);

            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
        }
    }

}
