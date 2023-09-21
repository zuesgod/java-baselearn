package subsidiary;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 演示信号灯
 *
 * 6辆汽车，停3个停车位
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //创建Semaphore 设置许可数量
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6 ; i++) {
            new Thread(()->{
                //抢占
                try {
                    //获得许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到了车位");
                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "-------------离开了了车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    //释放许可
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }


    }

}
