package collects;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 演示 list集合线程不安全
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        /*
            演示ArrayList线程不安全问题
         */
        //会报错：线程的并发修改异常
//        List<String> list = new ArrayList<>();
        //使用Vector解决，太老不推荐
//        List<String> list = new Vector<>();

        //Collections解决
//        List<String> list = Collections.synchronizedList( new ArrayList<String>());

        //写时复制技术
//        List<String> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                //向集合中添加内容
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }



        /*
            演示HashSet线程不安全问题
         */
//        Set<String> set = new HashSet<>();
//        Set<String> set = new CopyOnWriteArraySet<>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                //向集合中添加内容
//                set.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(set);
//            },String.valueOf(i)).start();
//        }


         /*
            演示HashMap线程不安全问题
         */
//        Map<String,String> map = new HashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                //向集合中添加内容
                map.put(key,UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

}
