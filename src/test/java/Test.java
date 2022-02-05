import com.mec.mutiFileTransfer.prepare.OffsetLength;
import com.mec.mutiFileTransfer.prepare.UnrecivedFileSection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/5 下午6:01
 */
public class Test {

    @org.junit.Test
    public void test() {
        UnrecivedFileSection unrecivedFileSection = new UnrecivedFileSection(30);
        unrecivedFileSection.receive(new OffsetLength(0,5));
        unrecivedFileSection.receive(new OffsetLength(16,2));
        System.out.println(unrecivedFileSection.getUnreceivedlist());
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
