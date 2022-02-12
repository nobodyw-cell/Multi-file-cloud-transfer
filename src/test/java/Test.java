import com.mec.mutiFileTransfer.prepare.common.OffsetLength;
import com.mec.mutiFileTransfer.prepare.common.UnrecivedFileSection;
import com.mec.mutiFileTransfer.prepare.distribute.DefaultDistribute;
import com.mec.mutiFileTransfer.prepare.resouce.ResourceStructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/5 下午6:01
 */
public class Test {

    @org.junit.Test
    public void testForWhile() {
        byte[] bytes = null;

        for (int i = 0; i <10; i++) {
            bytes = new byte[10];
        }

    }

    @org.junit.Test
    public void testForIterator() {
        List<String> strs = new LinkedList<>();

        strs.add("1");
        strs.add("2");
        strs.add("3");
        strs.add("4");
        strs.add("5");

        Iterator iterator = strs.iterator();

        while(iterator.hasNext()) {
            String str = (String) iterator.next();

            if (str.equals("3")) {
                iterator.remove();
            }
        }

        System.out.println(strs);
    }

    @org.junit.Test
    public void testForDistribute() {
        DefaultDistribute defaultDistribute = new DefaultDistribute();
        ResourceStructor resourceStructor = new ResourceStructor();
        resourceStructor.scanAbsolutePath();

        System.out.println(defaultDistribute.distribute(resourceStructor, 2));
    }

    @org.junit.Test
    public void test2() {
        int index = 0;
        int sendCount = 2;
        for (int i = 0; i < 19; i++) {
            System.out.println(index++ % sendCount);
        }
    }

    @org.junit.Test
    public void test() {
        UnrecivedFileSection unrecivedFileSection = new UnrecivedFileSection(30);
        unrecivedFileSection.receive(new OffsetLength(0,30));
        System.out.println(unrecivedFileSection.getUnreceivedlist());
    }

    public void testForCreateDic() {
        File file = new File("/home/wfh/Pictures/test");

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void main(String[] args) {
        File file1 = new File("/home/wfh/Pictures/2021-12-30 20-07-27 的屏幕截图.png");
        File file2 = new File("/home/wfh/Pictures/test/test.png");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            RandomAccessFile rafRead = new RandomAccessFile(file1,"r");
            RandomAccessFile rafWriter = new RandomAccessFile(file2,"rw");
            byte[] bytes = new byte[(int) rafRead.length()];
            rafRead.read(bytes,64, (int)rafRead.length() - 64);
            rafWriter.write(bytes,64,(int) rafRead.length() - 64);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
