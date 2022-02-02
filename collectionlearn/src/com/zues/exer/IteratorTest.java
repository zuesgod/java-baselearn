package com.zues.exer;

import com.zues.entity.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Iterator测试  迭代器设计模式的使用
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

}
