package threadtest;

import synchronizetest.TicketThread;

public class SyncTest {

    public static void main(String[] args) {
        //测试线程安全问题，synchronized
        TicketThread ticketThread = new TicketThread();
        Thread t1 = new Thread(ticketThread, "窗口1");
        Thread t2 = new Thread(ticketThread, "窗口2");
        Thread t3 = new Thread(ticketThread, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
