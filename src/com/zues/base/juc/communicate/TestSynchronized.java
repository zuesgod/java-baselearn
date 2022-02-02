package com.zues.base.juc.communicate;

/**
 * 经典的线程通信问题：生产者消费者问题
 * 题目：现在四个线程，可以操作初始值为0的一个变量
 * 实现两个线程对该变量+1，两个线程对该变量-1
 * 实现交替10次
 * <p>
 * <p>
 * 诀窍：
 * 1、高内聚低耦合的前提下，线程操作资源类
 * 2、判断、干活、通知
 * 3、多线程交互中，必须要防止多线程的虚假唤醒，也即（判断不能用if，只能用while）
 */
public class TestSynchronized {
    public static void main(String[] args) {
        //创建资源类对象
        DataSyn data = new DataSyn();

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
class DataSyn {
    //公共资源
    private int number = 10;

    //+1
    public synchronized void increment() {
        //判断
        while (number != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //通知
        this.notifyAll();
    }


    //-1
    public synchronized void decrement() {
        //判断
        while (number == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //通知
        this.notifyAll();
    }

}
