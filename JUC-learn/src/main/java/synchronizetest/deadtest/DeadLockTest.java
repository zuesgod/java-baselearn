package synchronizetest.deadtest;

/**
 * 死锁测试
 *
 * 验证是否是死锁：
 *  1. jps 类似Linux中 ps -ef
 *  2. jstack  jvm自带堆栈跟踪工具
 *
 */
public class DeadLockTest {
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        new Thread() {
            @Override
            public void run() {
                synchronized (s1) {

                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");

                        System.out.println("s1 = " + s1);
                        System.out.println("s2 = " + s2);
                    }
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                synchronized (s2) {

                    s1.append("c");
                    s2.append("2");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    synchronized (s1) {
                        s1.append("d");
                        s2.append("1");

                        System.out.println("s1 = " + s1);
                        System.out.println("s2 = " + s2);
                    }
                }
            }
        }.start();

    }

}
