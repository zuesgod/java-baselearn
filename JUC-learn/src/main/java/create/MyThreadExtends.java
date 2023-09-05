package create;

/**
 * 继承Thread方式创建线程
 * 单继承性，使用了此方式就无法继承其他类
 *
 * @author zeus
 * @date 2023-09-05 14:28
 **/
public class MyThreadExtends extends Thread {

    public MyThreadExtends() {
    }

    public MyThreadExtends(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
//            if(i % 20 == 0){
//                Thread.yield();
//            }
        }
    }
}
