package communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//创建资源类
class Share3{
    //定义属性和方法

    //定义标志位
    private int flag = 1;  //1  AA  2 BB  3CC

    private Lock lock = new ReentrantLock();
    //创建三个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印5次
    public void print5(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try{
            //判断
            while(flag != 1){
                //等待
                c1.await();
            }
            //干活
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + ":轮数：" + loop);
            }
            //修改标志位2
            flag = 2;
            //通知
            c2.signal();
        }finally {
            //解锁
            lock.unlock();
        }
    }

    //打印10次
    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }
            for (int i = 1; i <= 10 ; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + ":轮数：" + loop);
            }

            flag = 3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }


    //打印15次
    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try{
            while(flag != 3){
                c3.await();
            }
            for (int i = 1; i <= 15 ; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + ":轮数：" + loop);
            }
            flag = 1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }

}

public class ThreadDemo3 {
    public static void main(String[] args) {
        Share3 share3 = new Share3();
        new Thread(()->{
            try {
                for (int i = 1; i <= 10 ; i++) {
                    share3.print5(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"AA").start();

        new Thread(()->{
            try {
                for (int i = 1; i <= 10 ; i++) {
                    share3.print10(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"BB").start();

        new Thread(()->{
            try {
                for (int i = 1; i <= 10 ; i++) {
                    share3.print15(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"CC").start();
    }
}
