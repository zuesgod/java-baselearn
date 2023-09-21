package subsidiary;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏测试
 *
 * 集齐7颗龙珠就可以召唤神龙
 *
 */
public class CycliBarrierDemo {

    //创建固定值
    private static final int NUMBER = 7;


    public static void main(String[] args) {
        //创建CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,()->{
            System.out.println("--------集齐7颗龙珠就可以召唤神龙");
        });

        //集齐7颗龙珠的过程
        for (int i = 1; i <= 7 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 星龙珠被收集到了");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }
    }

}
