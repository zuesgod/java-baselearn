package base.io;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 测试FileInputStream和FileOutputStream的使用
 * <p>
 * 结论：
 * 1. 对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
 * 2. 对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
 *
 * @author zues
 * @create 2021 下午 19:19
 */
public class FileInputOutputStreamTest {

    /**
     * 使用字节流FileInputStream处理文本文件，可能出现乱码。
     */
    @Test
    public void testFileInputStream() {
        FileInputStream inputStream = null;
        try {
            //1.造文件
            File file = new File("hello.txt");
            //2.造流
            inputStream = new FileInputStream(file);
            //3.读写操作
            byte[] buffer = new byte[5];
            int len;//记录每次读取的字节的个数
            while ((len = inputStream.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                //4.关资源
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 实现对图片的复制操作
     */
    @Test
    public void testFileInputOutputStream() {
        String srcPath = "美女图片.png";
        String distPath="美女图片_copy.png";
        copyFile(srcPath,distPath);
    }


    public void copyFile(String srcPath,String distPath){
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            File file = new File(srcPath);
            File fileCopy = new File(distPath);


            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(fileCopy);


            byte[] buffer = new byte[1024];
            int len;

            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            System.out.println("文件拷贝成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
