package synchronizetest.lock_8;

import java.util.concurrent.TimeUnit;

/**
 * 共享资源类 phone
 */
class Phone {

    public static synchronized void sendSMS() throws Exception {
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("--------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("--------sendEmail");
    }

    public void getHello(){
        System.out.println("--------getHello");
    }
}

/**
 * 演示线程的8锁问题
 * synchronized锁的为当前对象(默认为this)，static为当前类Class
 * JAVA中每一个对象都可以作为锁，具体表现为以下3中形式：
 * 对于普通的同步方法：锁的是当前实例对象
 * 对于静态同步方法：锁的是当前类的class对象
 * 对于同步代码块：锁的是synchronized括号里配置的对象
 *
 * 1. 标准访问，先打印短信还是邮件
 * --------sendSMS
 * --------sendEmail
 * 2. 停4秒在短信方法内，先打印短信还是邮件
 * --------sendSMS
 * --------sendEmail
 * 3. 新增普通的hello方法，是先打印短信还是hello
 * --------getHello
 * --------sendSMS
 * 4. 现在有两部手机，先打印短信还是邮件
 * --------sendEmail
 * --------sendSMS
 * 5. 两个静态同步方法，1部手机，先打印短信还是邮件
 * --------sendSMS
 * --------sendEmail
 * 6. 两个静态同步方法，2部手机，先打印短信还是邮件
 * --------sendSMS
 * --------sendEmail
 * 7. 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 * --------sendEmail
 * --------sendSMS
 * 8. 1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 * --------sendEmail
 * --------sendSMS
 * @author zeus
 * @date 2023-09-18 11:37
 **/
public class Lock_8 {

    public static void main(String[] args) throws InterruptedException {
        Phone xiaomi = new Phone();
        Phone apple = new Phone();

        new Thread(()->{
            try {
                xiaomi.sendSMS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },"AA").start();

        Thread.sleep(100);


        new Thread(()->{
            try {
//                xiaomi.sendEmail();
//                xiaomi.getHello();
                apple.sendEmail();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },"BB").start();
    }

}
