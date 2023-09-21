package communication;


//第一步  创建资源类。定义属性和操作方法
class Share {
    //初始值
    private int number = 0;

    //+1的方法
    public synchronized void incr() throws InterruptedException {
        //第二步  判断  干活 通知
        while(number != 0){//放在while中，处理通信时的虚假唤醒问题
            //如果不是0，等待
            this.wait();//在哪里睡，在哪里醒
        }
        //如果是0，就+1操作
        number++;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        //通知 其他线程
        this.notifyAll();
    }


    //-1的方法
    public synchronized void decr() throws InterruptedException {
        //判断 干活  通知
        while(number == 0){//放在while中，处理通信时的虚假唤醒问题
            //如果是0则等待
            this.wait();
        }
        //如果不是0，则-1操作
        number--;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        //通知其他线程
        this.notifyAll();
    }

}

/**
 * 多线程编程步骤：
 *      第一步: 创建资源类，在资源类创建属性和操作方法
 *      第二步: 在资源类创建操作方法
 *          ①判断
 *          ②干活
 *          ③通知
 *      第三步: 创建多个线程，调用资源类的操作方法
 *      第四步: 防止虚假唤醒的问题
 */
public class ThreadDemo1 {

    //创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    //+1
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"AA").start();


        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    //-1
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    //+1
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"CC").start();


        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    //-1
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"DD").start();

    }
}
