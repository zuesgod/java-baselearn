package threadtest;


import create.MyThreadExtends;
import create.MyThreadImpl;
import org.junit.Test;

/**
 * 多线程练习1
 *
 * @author zeus
 * @date 2023-09-05 14:27
 **/
public class ThreadTest {

    //测试线程的创建方式
    public static void main(String[] args) {
        //继承的方式创建线程
        new MyThreadExtends().start();

        //实现Runnable接口的方式创建线程
        new Thread(new MyThreadImpl()).start();

    }

    /**
     * 注意：这里多线程的测试在Junit的测试方法中无法打印测试内容，只有
     * main方法才可以
     */
    @Test
    public void test1() {
        //继承的方式创建线程
//        new MyThreadExtends().start();

        //实现Runnable接口的方式创建线程
//        new Thread(new MyThreadImpl()).start();

    }

}
