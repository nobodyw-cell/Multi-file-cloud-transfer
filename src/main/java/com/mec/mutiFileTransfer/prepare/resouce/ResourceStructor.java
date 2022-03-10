package com.mec.mutiFileTransfer.prepare.resouce;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 资源结构的整体描述
 *
 * 含有
 * 1. 资源的绝对路径
 * 2. 资源id
 * 3. 资源的目录结构 (相对路径)
 * 4. 资源文件名称以及结构 (相对路径)
 *
 *
 * @Author wfh
 * @Date 2022/2/3 下午9:56
 */
public class ResourceStructor {
    public final static String DEFAULT_ABSOLUTE_PATH = "/home/wfh/Pictures";
    private String ResourthId;
    private String absolutePath;
    private List<String> resourcesDirectories;
    private List<ResourceFileInfo> fileList;

    /**
     * 对文件做一个编号
     */
    private static int temIndex;

    /**
     * 游标
     */
    private int index;

    public ResourceStructor(ResourceStructor resourceStructor) {
        this.ResourthId = new String(resourceStructor.getResourthId());
        this.resourcesDirectories = resourceStructor.getResourcesDirectories();
        this.fileList = new LinkedList<>();

        for (ResourceFileInfo resourceFileInfo : resourceStructor.getFileList()) {
            ResourceFileInfo rfi = new ResourceFileInfo(resourceFileInfo);

            this.fileList.add(rfi);
        }
    }

    public List<String> getResourcesDirectories() {
        return resourcesDirectories;
    }

    public List<ResourceFileInfo> getFileList() {
        return fileList;
    }

    public ResourceStructor() {
        this.absolutePath = DEFAULT_ABSOLUTE_PATH;
        this.resourcesDirectories = new ArrayList<String>();
        this.fileList = new ArrayList<ResourceFileInfo>();
        this.index = 0;
    }

    public String getAbsolutePath() {
        return this.absolutePath;
    }

    public ResourceFileInfo getResourceFileInfo(int fileNo) {
        return this.fileList.get(fileNo);
    }

    public boolean hasNext() {
        if (this.fileList.size() > this.index) {
            return true;
        }

         this.index = 0;
        return false;
    }

    public ResourceFileInfo next() {
        if (hasNext()) {
            return this.fileList.get(this.index++);
        }
        this.index = 0;
        // TODO 越界异常
        return null;
    }

    public void setResourthId(String resourthId) {
        ResourthId = resourthId;
    }

    public String getResourthId() {
        return ResourthId;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    /**
     * 扫描绝对路径得到资源结构.
     */
    public void scanAbsolutePath() {
        File rootFile = new File(this.absolutePath);

        if (!rootFile.exists()) {
            // TODO  抛异常
            return;
        }

        ResourceStructor.temIndex = 0;

        scanAbsolutePath(rootFile);
    }

    public void scanAbsolutePath(File curFile) {
        File[] files = curFile.listFiles();
        if (files.length <= 0) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                String dirPath = file.getAbsolutePath();
                this.resourcesDirectories.add(dirPath.replace(this.absolutePath,""));
                scanAbsolutePath(file);
            } else {
                ResourceFileInfo resourceFileInfo = new ResourceFileInfo();
                resourceFileInfo.setFileName(file.getAbsolutePath().replace(this.absolutePath,""));
                resourceFileInfo.setFileSize(file.length());
                resourceFileInfo.setFileNo(ResourceStructor.temIndex++);
                this.fileList.add(resourceFileInfo);
            }
        }
    }
}
