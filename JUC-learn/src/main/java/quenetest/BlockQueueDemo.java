package quenetest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示阻塞队列
 */
public class BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建阻塞队列
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        //第一组
//        System.out.println(queue.add("1"));
//        System.out.println(queue.add("2"));
//        System.out.println(queue.add("3"));
////        System.out.println(queue.element());
////        System.out.println(queue.add("4"));
//
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());


        //第二组
//        System.out.println(queue.offer("A"));
//        System.out.println(queue.offer("B"));
//        System.out.println(queue.offer("C"));
////        System.out.println(queue.offer("D"));
//
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());


        //第三组
//        queue.put("A");
//        queue.put("B");
//        queue.put("C");
////        queue.put("D");
//
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());

    }

}
