package com.mec.mutiFileTransfer.prepare;

import java.util.LinkedList;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/3 下午10:58
 */
public class UnrecivedFileSection {
    private LinkedList<OffsetLength> unreceivedlist;

    public UnrecivedFileSection(int fileSize) {
        this.unreceivedlist = new LinkedList<>();
        this.unreceivedlist.add(new OffsetLength(0,fileSize));
    }

    public LinkedList<OffsetLength> getUnreceivedlist() {
        return unreceivedlist;
    }

    public void receive(OffsetLength receivedSection) {
        int unReceiveSectionIndex = findSection((int) receivedSection.getOffset());
        OffsetLength unReceiveSection = this.unreceivedlist.get(unReceiveSectionIndex);

        this.unreceivedlist.remove(unReceiveSectionIndex);

        OffsetLength section1 = new OffsetLength(unReceiveSection.getOffset()
                                        ,unReceiveSection.getLength());
        OffsetLength section2 = new OffsetLength(unReceiveSection.getOffset()
                                        ,unReceiveSection.getLength());

        section1.setLength((int)(receivedSection.getOffset() - unReceiveSection.getOffset()));
        section2.setOffset(receivedSection.getOffset() + receivedSection.getLength());
        section2.setLength((int)(unReceiveSection.getLength() + unReceiveSection.getOffset() -section2.getOffset()));


        if (section2.getLength() != 0) {
            this.unreceivedlist.add(unReceiveSectionIndex,section2);
        }

        if (section1.getLength() != 0) {
            this.unreceivedlist.add(unReceiveSectionIndex,section1);
        }
    }

    private int findSection(int offset) {
        int index;

        for (index = 0; index < unreceivedlist.size(); index++) {
            OffsetLength section = unreceivedlist.get(index);

            if (offset < section.getOffset() + section.getLength()) {
                return index;
            }
        }

        return -1;
    }

    /**
     *
     以下16个片段 0 : 16 表示

     0123456789ABCDEF

     接收前 0 16
     0123456789ABCDEF
     接收片段 1 2
     接收后   0 1 和 3 13
      --
     0123456789ABCDEF


     接收前 0 1 和 3 13
      --
     0123456789ABCDEF
     接收片段 6 3
     接收后
      --   ---
     0123456789ABCDEF
     变成了 0 1和3 3和9 7

     归纳总结:
     首先 找出接收片段所在的那一节
     就拿第二个例子来说
     接收片段是6 3
     由于没有区间会重合
     所以我们只需要找出6在那个区间就好了
     我们只要保持list有序就可以很轻松的找出这个区间
     方法是 如果 offset + len > 6就行了

     找到所需要的section所在的下标以后我们这样做

     6 : 10

     7 : 5

     offset不变  len变为 offset - offset
     第二个来说就是 len 不变offset为 接收片段的offset + len要是刚好等于未变之前的就不用再添加了


     */

}
