package subsidiary;

import java.util.concurrent.CountDownLatch;

/**
 * 演示计数器 CountDownLatch
 *
 * 六个同学陆续离开教室后值班同学才可以关门
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {
        //创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);


        //六个同学陆续离开教室
        for (int i = 1; i <= 6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
                //计数器减1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //等待
        countDownLatch.await();
        //如果不使用CountDownLatch，则班长可能会在同学未离开教室之前就锁门了。即主线程没有等待上面线程执行完就执行了
        System.out.println(Thread.currentThread().getName() + " 班长锁门走人了");
    }


}
