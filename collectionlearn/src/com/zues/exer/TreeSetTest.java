package com.zues.exer;

import com.zues.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet测试
 *
 * @author zeus
 * @date 2022-02-06 2:19 下午
 **/
public class TreeSetTest {

    /**
     * 1、向TreeSet中添加的数据，要求是相同的类的对象。
     * 2、两种排序方式：自然排序（实现Comparable接口）和定制排序（Comparator）
     *
     * 3、自然排序中，比较两个对象是否相同的标准为：compareTo()返回0,不再是equals()。
     * 4、定制排序中，比较两个对象是否相同的标准为：compare()返回0,不再是equals()。
     */
    @Test
    public void test1(){
        Set set = new TreeSet();
        //举例一：
//        set.add(213);
//        set.add(233);
//        set.add(223);
//        set.add(253);
        //举例二：
        set.add(new User("Tom",11));
        set.add(new User("Tom",18));
        set.add(new User("Mark",6));
        set.add(new User("Tom",55));

        set.forEach(System.out::println);
    }

    @Test
    public void test2(){
        Comparator com = new Comparator() {
            //按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }else{
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };

        Set set = new TreeSet(com);
        set.add(new User("Tom",11));
        set.add(new User("Tom",18));
        set.add(new User("Tom",18));
        set.add(new User("Mark",6));
        set.add(new User("Tom",55));

        set.forEach(System.out::println);
    }


}
