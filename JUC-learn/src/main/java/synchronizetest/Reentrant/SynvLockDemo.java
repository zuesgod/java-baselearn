package synchronizetest.Reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示可重入锁
 *
 * @author zeus
 * @date 2023-09-20 9:13
 **/
public class SynvLockDemo {

    public synchronized void add() {
        add();
    }

    public static void main(String[] args) {
        //可重入锁会造成循环递归调用，造成栈溢出异常
        //new SynvLockDemo().add();

        //synchronized
//        Object o = new Object();
//        new Thread(() -> {
//            synchronized (o) {
//                System.out.println(Thread.currentThread().getName() + "最外层");
//
//                synchronized (o) {
//                    System.out.println(Thread.currentThread().getName() + "中层");
//
//                    synchronized (o) {
//                        System.out.println(Thread.currentThread().getName() + "内层");
//                    }
//                }
//            }
//        }, "t1").start();


        /**
         * 使用lock演示可重入锁
         */
        ReentrantLock lock = new ReentrantLock();

        new Thread(()->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "外层");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "中层");
                }finally {
                    //这里不释放会造成其他使用这个锁的线程阻塞
//                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"t1").start();

        //创建新线程
        new Thread(()->{
            lock.lock();
            System.out.println("1111");
            lock.unlock();
        },"aa").start();

    }
}
