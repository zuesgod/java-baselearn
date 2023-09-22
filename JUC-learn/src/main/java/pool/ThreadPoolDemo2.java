package pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * ThreadPool七个参数：
 *       int corePoolSize：常驻线程数量
 *       int maximumPoolSize：线程池中最大支持多少线程数量
 *       long keepAliveTime：超过常驻的线程存活时间
 *       TimeUnit unit：存活时间单位
 *       BlockingQueue<Runnable> workQueue：阻塞队列
 *       ThreadFactory threadFactory：线程工厂，用于创建线程
 *       RejectedExecutionHandler handler: 拒绝策略
 * @author zeus
 * @date 2023-09-22 11:37
 **/
public class ThreadPoolDemo2 {


    public static void main(String[] args) {
        //创建自定义线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        try {
            for (int i = 1; i <= 5; i++) {
                //执行
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            threadPool.shutdown();
        }

    }
}
