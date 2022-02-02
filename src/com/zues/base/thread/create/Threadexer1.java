package com.zues.base.thread.create;

/**
 * 练习：创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 *
 *
 * @author zues
 * @create 2019-02-13 下午 2:16
 */
public class Threadexer1 {

    @SuppressWarnings("")
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 ==0){
                        System.out.println(Thread.currentThread().getName() + "-" + i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 !=0){
                        System.out.println(Thread.currentThread().getName() + "-" + i);
                    }
                }
            }
        }.start();

    }


}
