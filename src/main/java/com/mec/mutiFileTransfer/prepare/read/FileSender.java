package com.mec.mutiFileTransfer.prepare.read;

import com.mec.mutiFileTransfer.prepare.common.FileSectionHead;
import com.mec.mutiFileTransfer.prepare.common.RandomAccessFilePool;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceFileSectionInfo;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/7 下午8:55
 */
public class FileSender {
    private ResourceFileSectionInfo resourceFileSectionInfo;
    private DataOutputStream dos;

    public FileSender(DataOutputStream dos) {
        this.dos = dos;
    }

    public void setResourceFileInfo(ResourceFileSectionInfo resourceFileSectionInfo) {
        this.resourceFileSectionInfo = resourceFileSectionInfo;
    }

    /**
     * 这段代码需要使用面向对象的思想再次重构 (继承或者组合)
     * @throws IOException
     */
    public void send() throws IOException {
        // 会不会给个池子更好一点
        byte[] fileSection = new byte[FileSectionHead.DEFAULT_SECTION_LEN];

        while (resourceFileSectionInfo.hasNext()) {
            FileSectionHead fileSectionHead = resourceFileSectionInfo.next();
            // TODO 对rafpool的处理还不够好
            RandomAccessFile raf = RandomAccessFilePool.getRaf(fileSectionHead.getFileNo());
            FileSectionReader fileSectionReader = new FileSectionReader();
            FileSectionSender fileSectionSender = new FileSectionSender(this.dos);

            int sendLen = 0;
            int curLen = fileSectionHead.getOffsetLength().getLength();
            int defaultSendSize = FileSectionHead.DEFAULT_SECTION_LEN;
            int fileNo = fileSectionHead.getFileNo();
            long offset = fileSectionHead.getOffsetLength().getOffset();
            long curOffset = offset;
            /*
             为什么要有空构造这里就很清楚了
             */
            FileSectionHead sendSectionHead = new FileSectionHead();

            while (curLen != 0) {
                sendLen = curLen > defaultSendSize ? defaultSendSize : curLen;
                sendSectionHead.setFileNo(fileNo);
                sendSectionHead.setLength(sendLen);
                sendSectionHead.setOffset(curOffset);
                curLen -= sendLen;
                offset +=sendLen;

                fileSectionReader.setFileSection(fileSection);
                fileSectionReader.setRafRead(raf);
                fileSectionReader.setFileSectionHead(sendSectionHead);

                fileSectionReader.read();

                fileSectionSender.setFileSection(fileSectionReader.getFileSection());
                fileSectionSender.setFileSectionHead(fileSectionReader.getFileSectionHead());
                fileSectionSender.sendFileSection();
            }

        }
    }
}
