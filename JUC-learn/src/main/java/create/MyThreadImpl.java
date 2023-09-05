package create;

/**
 * 实现Runnable的方式创建线程
 * java有多实现方式，使用这种方式创建线程，可以同时实现其他业务接口
 *
 * @author zeus
 * @date 2023-09-05 14:39
 **/
public class MyThreadImpl implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
