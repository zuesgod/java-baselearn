package com.zues.base.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 复习lock
 * 第一步  创建资源类，定义属性和操作方法
 */
class LTicket {
    //票数
    private int number = 30;
    //创建可重入锁
    private Lock lock = new ReentrantLock();

    public void sale() {
        try {
            //上锁
            lock.lock();
            //判断是否有票
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖出了：" + (number--) + " 还剩：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //解锁
            lock.unlock();
        }
    }

}


public class LSaleTIcket {
    public static void main(String[] args) {
        //创建资源类对象
        LTicket ticket = new LTicket();
        //创建三个线程
        new Thread(() ->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"AA").start();

        new Thread(() ->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"BB").start();

        new Thread(() ->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"CC").start();

    }
}
