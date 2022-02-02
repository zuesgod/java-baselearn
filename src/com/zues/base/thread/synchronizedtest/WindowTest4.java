package com.zues.base.thread.synchronizedtest;

/**
 * 使用同步方法处理继承Thread类的方式中的线程安全问题
 *
 * @author zues
 * @create 2021-12-22 21:28
 */
class Window4 extends Thread {


    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            show();
            if (ticket == 0) {
                break;
            }
        }
    }
    //同步监视器：Window4.class
    private static synchronized void show() {
//    private synchronized void show(){ //同步监视器：t1,t2,t3。此种解决方式是错误的
        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
            ticket--;
        }
    }
}

public class WindowTest4 {
    public static void main(String[] args) {
        Window4 t1 = new Window4();
        Window4 t2 = new Window4();
        Window4 t3 = new Window4();


        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
