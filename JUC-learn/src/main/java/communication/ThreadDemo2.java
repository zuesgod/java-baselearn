package communication;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//创建资源类，定义属性和方法
class Share2 {
    private int number = 0;

    //创建Lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //+1
    public void incr() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //判断
            while (number != 0) {
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + " :: " + number);
            //通知
            condition.signalAll();
        } finally {
            //解锁
            lock.unlock();
        }
    }

    //-1
    public void decr() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //判断
            while (number == 0) {
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + " :: " + number);
            //通知
            condition.signalAll();
        } finally {
            //解锁
            lock.unlock();
        }
    }


}

/**
 * 多线程通信-lock方案
 */
public class ThreadDemo2 {

    public static void main(String[] args) {
        Share2 share2 = new Share2();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share2.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }, "AA").start();


        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share2.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share2.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }, "CC").start();


        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share2.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }, "DD").start();


    }
}
