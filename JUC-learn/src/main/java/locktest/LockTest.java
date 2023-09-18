package locktest;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }


}

class Window extends Thread {

    static int ticket = 100;

    /**
     * 1.创建lock实例，一定要确保多个线程共用同一个lock锁！ 需要考虑将此对象声明为static final
     *
     * ReentrantLock(boolean fair): fair如果为true,则线程依次使用，为公平锁，默认为false：非公平锁
     */
    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                //2.执行lock方法，锁定共享资源
                lock.lock();
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + "售票，票号为" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                //2.执行unlock方法，释放共享资源
                lock.unlock();
            }
        }
    }
}