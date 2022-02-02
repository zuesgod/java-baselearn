package base.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 一、流的分类：
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 * <p>
 * 二、流的体系结构
 * 抽象基类         节点流（或文件流）                               缓冲流（处理流的一种）
 * InputStream     FileInputStream   (read(byte[] buffer))        BufferedInputStream (read(byte[] buffer))
 * OutputStream    FileOutputStream  (write(byte[] buffer,0,len)  BufferedOutputStream (write(byte[] buffer,0,len) / flush()
 * Reader          FileReader (read(char[] cbuf))                 BufferedReader (read(char[] cbuf) / readLine())
 * Writer          FileWriter (write(char[] cbuf,0,len)           BufferedWriter (write(char[] cbuf,0,len) / flush()
 *
 * @author zues
 * @create 2021 下午 20:37
 */
public class FileReaderWriterTest {

    /**
     * 将Moudle下的hello.txt文件内容读入程序中，并输出到控制台
     * <p>
     * 说明点：
     * 1. read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
     * 2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
     * 3. 读入的文件一定要存在，否则就会报FileNotFoundException。
     */
    @Test
    public void testFileReader() {
        //1、实例化File类的对象，指明要操作的文件
        File file = new File("hello.txt");
        FileReader fileReader = null;
        try {
            //2、提供具体的流
            fileReader = new FileReader(file);
            int data;
            //3、数据的读入
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileReader) {
                    //4、流的关闭操作
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对read()操作升级，使用read的重载方法
     */
    @Test
    public void testFileReader1() {
        //1.Flie的实例化
        FileReader fileReader = null;
        try {
            File file = new File("hello.txt");
            //2.FileReader的实例化
            fileReader = new FileReader(file);
            //read(char[] cbuf):返回每次读入cbuf数组中的字符的个数。如果达到文件末尾，返回-1
            char[] cbuf = new char[1024];
            int len;
            //3.读入的操作
            while ((len = fileReader.read(cbuf)) != -1) {
                //方式一：
                //错误的写法
//                for(int i = 0;i < cbuf.length;i++){
//                    System.out.print(cbuf[i]);
//                }
                //正确的写法
//                for(int i = 0;i < len;i++){
//                    System.out.print(cbuf[i]);
//                }
                //方式二：
                //错误的写法,对应着方式一的错误的写法
//                String str = new String(cbuf);
//                System.out.print(str);
                //正确的写法
                String str = new String(cbuf, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    //4.关闭资源
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从内存中写出数据到硬盘的文件里。
     * <p>
     * 说明：
     * 1. 输出操作，对应的File可以不存在的。并不会报异常
     * 2.
     * File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
     * File对应的硬盘中的文件如果存在：
     * 如果流使用的构造器是：FileWriter(file,false) / FileWriter(file):对原有文件的覆盖
     * 如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件基础上追加内容
     */
    @Test
    public void testFileWriter() {
        FileWriter fileWriter = null;
        try {
            //1.File类的实例化或设置需要读出的内容
            File file = new File("helloWrite.txt");
            String str = "这是我要写出的内容";
            //2.创建流
            fileWriter = new FileWriter(file);
            //3.写操作
            fileWriter.write(str + "\n");
            fileWriter.write("这是第二行！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    //4.资源关闭
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试边读编写，相当于文件复制
     */
    @Test
    public void testFileReaderAndWriter() {
        FileReader reader = null;
        FileWriter writer = null;
        try {
            //1.创建文件实例
            File file = new File("hello.txt");
            File fileCopy = new File("hello1.txt");
            //2.创建所需流(此处文本文件需要使用字符流)
            //不能使用字符流来处理图片等字节数据
            reader = new FileReader(file);
            writer = new FileWriter(fileCopy);

            char[] chuf = new char[1024];
            int len;
            //3.读写操作
            while ((len = reader.read(chuf)) != -1) {
                writer.write(chuf, 0, len);
            }
            System.out.println("文件拷贝成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源关闭
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
