package com.zues.base.juc.communicate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        //创建资源类对象
        DataLock data = new DataLock();

        //创建四个线程进行测试
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "DD").start();
    }
}


//资源类
class DataLock {
    //公共资源
    private int number = 10;

    //可重入锁
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    //+1
    public void increment() {
        lock.lock();
        try {
            //判断
            while (number != 0) {
                condition.await();
            }

            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //-1
    public void decrement() {
        lock.lock();
        try {
            //判断
            while (number == 0) {
                condition.await();
            }

            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
