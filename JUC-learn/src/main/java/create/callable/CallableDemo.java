package create.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable接口创建线程
 *
 * @author zeus
 * @date 2023-09-20 11:28
 **/
public class CallableDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //普通创建方式
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread1());

        //lam表达式
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(() -> {
            System.out.println(Thread.currentThread().getName() + "come in callable");
            return 300;
        });

        /*
         *  FutureTask 使用场景，未来任务
         * 1、老师上课，口渴了，去买水不合适，不干预讲课线程
         *    单开启线程找班上班长帮忙买水，需要的时候直接get
         *
         * 2、4个同学， 1同学 1+2...5,  2同学 10+11+12...50,  3同学 60+61+62， 4同学 100+200
         *   第2个同学计算量较大
         *   单开启线程给2同学计算，先汇总1 3 4，最后等2同学计算完成，统一汇总
         *
         * 3、考试，先做会做的题，最后看不会做的题目
         *
         * 以上任务只需要汇总一次，第二轮时不用再进行重复计算
         */

        new Thread(futureTask1,"lucy").start();
        new Thread(futureTask,"lucy1").start();

        while (!futureTask1.isDone()){
            System.out.println("wait.....");
        }

        //调用FutureTask的get方法
        System.out.println(futureTask1.get());

        System.out.println(futureTask.get());

        System.out.println(Thread.currentThread().getName() + "come over");

    }



}

/**
 *  实现Callable
 */
class MyThread1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 200;
    }
}
