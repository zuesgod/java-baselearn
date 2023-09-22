package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示线程池三种常用分类
 *
 *
 * @author zeus
 * @date 2023-09-22 9:10
 **/
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        //一池n线程  五个窗口
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        
        //一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        //一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        //10顾客请求
        try {
            for (int i = 1; i <= 5; i++) {
                //执行
//                threadPool1.execute(() -> {
//                    System.out.println(Thread.currentThread().getName() + " 办理业务");
//                });

//                threadPool2.execute(() -> {
//                    System.out.println(Thread.currentThread().getName() + " 办理业务");
//                });

                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            threadPool1.shutdown();
        }
    }


}
