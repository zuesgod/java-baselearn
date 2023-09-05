package synchronizetest;

/**
 * synchronized 同步监视器测试
 */
public class TicketThread implements Runnable {

    int ticket = 100;

    @Override
    public void run() {
        while (true) {
            //同步代码块
            synchronized (TicketThread.class) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "出售了第" + ticket + "张票...");
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
