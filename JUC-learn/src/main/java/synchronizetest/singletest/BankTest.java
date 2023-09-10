package synchronizetest.singletest;

/**
 * 实现线程安全的懒汉式
 */
public class BankTest {

    static Bank b1 = null;
    static Bank b2 = null;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                b2 = Bank.getInstance();
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);
        System.out.println(b1 == b2);
    }

}


class Bank {
    public Bank() {
    }

    //为了避免指令重排，需要添加volatile关键字
    private static volatile Bank instance = null;

    //线程安全的方式一 同步方法
//    public static synchronized Bank getInstance(){
//        if (instance == null){
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//            instance = new Bank();
//        }
//        return instance;
//    }

    //线程安全的方式二 同步代码块
//    public static Bank getInstance(){
//        synchronized (Bank.class) {
//            if (instance == null){
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//                instance = new Bank();
//            }
//            return instance;
//        }
//    }


    //线程安全的方式三
    public static Bank getInstance() {
        //当第一个线程创建Bank对象后，后续线程可以直接使用不用等锁  效率更高
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    instance = new Bank();
                }

            }
        }
        return instance;
    }

}