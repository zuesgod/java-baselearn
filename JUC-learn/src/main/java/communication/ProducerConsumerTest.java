package communication;

/**
 * 生产者 && 消费者
 * 生产者(Producer)将产品交给店员(Clerk),而消费者(Customer)从店员处取走产品，店员一次只能持有固定
 * 数量的产品（比如：20），如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店内有空位放产品了再通知
 * 生产者继续生产；如果店中没有产品了，店员会告知消费者等一下，如果店中有产品了再通知消费者来取走产品
 * <p>
 * 分析：
 * 1.是否是多线程问题：是, 生产者、消费者
 * 2.是否有共享数据： 是  产品
 * 3.是否有线程安全问题： 有 因为有共享数据
 * 4。使用同步机制处理线程安全问题
 * 5.使用wait与notify实现线程通信问题
 */
public class ProducerConsumerTest {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        new Producer(clerk, "生产者1").start();
        new Consumer(clerk, "消费者1").start();
        new Consumer(clerk, "消费者2").start();
    }

}

/**
 * 店员
 */
class Clerk extends Thread {

    //产品的数量
    private int productNum = 0;


    //增加产品数量
    public synchronized void addProduct() {

        if (productNum >= 20) {
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            productNum++;
            System.out.println(Thread.currentThread().getName() + "生产了第" + productNum + "个产品");

            //唤醒消费者
            notifyAll();
        }
    }


    //减少产品数量
    public synchronized void minusProduct() {

        if (productNum <= 0) {
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "消费了第" + productNum + "个产品");
            productNum--;

            //唤醒生产者
            notifyAll();
        }
    }

}

/**
 * 生产者
 */
class Producer extends Thread {

    private Clerk clerk;

    public Producer(Clerk clerk, String name) {
        super(name);
        this.clerk = clerk;
    }

    @Override
    public void run() {

        while (true) {
            System.out.println("生产者开始生产产品...");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            clerk.addProduct();
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {

    private Clerk clerk;

    public Consumer(Clerk clerk, String name) {
        super(name);
        this.clerk = clerk;
    }

    @Override
    public void run() {

        while (true) {
            System.out.println("消费者开始消费产品...");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            clerk.minusProduct();
        }
    }
}
