package create;

/**
 * 继承Thread方式创建线程
 * 单继承性，使用了此方式就无法继承其他类
 *
 * @author zeus
 * @date 2023-09-05 14:28
 **/
public class MyThreadExtends extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("继承Thread的线程:" + i);
        }
    }
}
