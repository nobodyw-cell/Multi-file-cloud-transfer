package com.mec.mutiFileTransfer.prepare.common;

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
    private static Map<Integer, RandomAccessFile> rafPool;
    private ResourceStructor resourceStructor;

    public void setResourceStructor(ResourceStructor resourceStructor) {
        this.resourceStructor = resourceStructor;
    }

    static {
        rafPool = new HashMap<>();
    }

    public static RandomAccessFile getRaf(int fileNo) throws FileNotFoundException {
        return getRaf(fileNo,null,null);
    }

    public static RandomAccessFile getRaf(int fileNo,String filePath, String mod) throws FileNotFoundException {
        RandomAccessFile raf = rafPool.get(fileNo);

        if (raf == null) {
            synchronized (rafPool) {
                raf = getRaf(fileNo);
                if (raf == null) {
                    raf = new RandomAccessFile(filePath,mod);
                    rafPool.put(fileNo,raf);
                }
            }
        }
        return raf;
    }

    public boolean isFileExists(int fileNo) {
        return rafPool.get(fileNo) == null;
    }

    public void close(int fileNo) {
        RandomAccessFile raf = rafPool.remove(fileNo);
        try {
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
