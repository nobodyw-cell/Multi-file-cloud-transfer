package com.mec.mutiFileTransfer.prepare.resouce;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * //TODO add class commment here
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

    private static int temIndex;

    private int index;

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
        return this.fileList.size() > this.index;
    }

    public ResourceFileInfo next() {
        return this.fileList.get(this.index++);
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
