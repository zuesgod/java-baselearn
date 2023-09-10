package communication;

/**
 * 案例： 使用两个线程打印 1-100  线程1，线程2 交替打印
 */
class PrintNumber implements Runnable {

    private int number = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                //执行此方法，就会唤醒被wait()的线程中优先级最高的那一个线程  （如果被wait()的多个线程的优先级相同，随机唤醒一个）
//                notify();
                //执行此方法，会唤醒所有被wait的线程
                notifyAll();
                if (number <= 100) {
                    try {
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName() + ":" + number);
                        number++;
                        //线程一旦执行此方法，就进入等待状态，同时也会释放对同步监视器的调用
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    break;
                }
            }
        }
    }
}


public class PrintNumberTest {

    public static void main(String[] args) {
        PrintNumber p = new PrintNumber();

        Thread t1 = new Thread(p, "线程1");
        Thread t2 = new Thread(p, "线程2");

        t1.start();
        t2.start();

    }
}
