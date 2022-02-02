package com.zues.base.thread.create;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程的方式四：使用线程池
 * <p>
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于线程管理
 * corePoolSize：核心池的大小
 * maximumPoolSize：最大线程数
 * keepAliveTime：线程没有任务时最多保持多长时间后会终止
 * <p>
 * <p>
 * 面试题：创建多线程有几种方式？四种！
 *
 * @author zues
 * @create 2021-12-25 09:04
 */
class NumberThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}


class NumberThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        //1.提供制定线程数量的线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) pool;
        //2.设置线程池的属性
        System.out.println(pool.getClass());
//        poolExecutor.setCorePoolSize(15);
//        poolExecutor.setKeepAliveTime();
        //3.执行制定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        pool.execute(new NumberThread());
        pool.execute(new NumberThread1());
        //4.关闭线程池
        poolExecutor.shutdown();
    }
}
