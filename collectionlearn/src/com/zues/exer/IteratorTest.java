package com.zues.exer;

import com.zues.entity.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator测试  迭代器设计模式的使用
 * 集合元素的遍历操作，使用迭代器Iterator接口
 * 1、内部的方法： hasNext() 和 next()
 * 2、集合对象每次调用iterator()方法都得到一个全新的迭代器对象。
 *    默认游标都在集合的第一个元素之前
 * 3、内部定义了remove(),可以在遍历的时候，删除集合中的元素，此方法不同于集合直接调用remove()
 *
 *
 * @author zeus
 * @date 2022-02-02 9:11 下午
 **/
public class IteratorTest {

    @Test
    public void test1(){
        Collection<Object> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        list.add(new String("Tom"));
        list.add(false);
        list.add(new Person("小明", 22));

        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println("iterator.next() = " + iterator.next());
        }
    }

    //测试Iterator中的remove()
    //如果还未调用next()或在上一次带哦用next方法之后已经调用了remove方法，
    //再调用remove都会报IllegalStateException
    @Test
    public void test2(){
        List<Object> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        list.add(new String("Tom"));
        list.add(false);
        list.add(new Person("小明", 22));

        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            if("Tom".equals(obj)){
                iterator.remove();
            }
        }

        //遍历集合
        list.forEach(System.out::println);

    }

}
