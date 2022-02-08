package com.mec.mutiFileTransfer.prepare.common;

import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/6 下午9:39
 */
public class RandomAccessFilePool {
    private Map<Integer, RandomAccessFile> rafPool;
    private ResourceStructor resourceStructor;

    public RandomAccessFilePool(ResourceStructor resourceStructor) {
        this.resourceStructor = new ResourceStructor();
    }


    public RandomAccessFile getRaf(int fileNO) throws FileNotFoundException {
        return getRaf(fileNO,null);
    }

    public boolean isExists(int fileNo) {
        return this.rafPool.get(fileNo) != null;
    }

    public void close(int fileNo) {
        RandomAccessFile raf = this.rafPool.remove(fileNo);
        try {
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RandomAccessFile getRaf(int fileNo,String mod) throws FileNotFoundException {
        RandomAccessFile raf = this.rafPool.get(fileNo);
        ResourceFileInfo resourceFileInfo = this.resourceStructor.getResourceFileInfo(fileNo);

        if (raf == null) {
            synchronized (rafPool) {
                raf = this.rafPool.get(fileNo);
                if (raf == null) {
                    raf = new RandomAccessFile(this.resourceStructor.getAbsolutePath() + resourceFileInfo
                            .getFileName(),mod);
                    this.rafPool.put(fileNo,raf);
                }
            }
        }

        return raf;
    }
}
//    private  Map<Integer, RandomAccessFile> rafPool;
//    private ResourceStructor resourceStructor;
//
//    public void setResourceStructor(ResourceStructor resourceStructor) {
//        this.resourceStructor = resourceStructor;
//    }
//
//    static {
//        rafPool = new HashMap<>();
//    }
//
//    public void ininRafFilePool() {
//
//    }
//
//    public static RandomAccessFile getRaf(int fileNo) throws FileNotFoundException {
//        return getRaf(fileNo,null,null);
//    }
//
//    public static RandomAccessFile getRaf(int fileNo,String filePath, String mod) throws FileNotFoundException {
//        RandomAccessFile raf = rafPool.get(fileNo);
//
//        if (raf == null) {
//            synchronized (rafPool) {
//                raf = getRaf(fileNo);
//                if (raf == null) {
//                    raf = new RandomAccessFile(filePath,mod);
//                    rafPool.put(fileNo,raf);
//                }
//            }
//        }
//        return raf;
//    }
//
//    public boolean isFileExists(int fileNo) {
//        return rafPool.get(fileNo) == null;
//    }
//
//    public void close(int fileNo) {
//        RandomAccessFile raf = rafPool.remove(fileNo);
//        try {
//            raf.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

