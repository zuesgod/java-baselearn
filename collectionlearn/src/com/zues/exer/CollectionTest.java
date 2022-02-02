package com.zues.exer;

import com.zues.entity.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * collection接口测试
 *
 * 结论：
 *      向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals()
 *
 * @author zeus
 * @date 2022-02-02 8:30 下午
 **/
public class CollectionTest {

    @Test
    public void test1() {
        Collection<Object> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        list.add(new String("Tom"));
        list.add(false);
        list.add(new Person("小明", 22));
        //contains()
        //true 使用其对象中的equals方法进行比较
        System.out.println(list.contains(new Person("小明", 22)));

        //containsAll()
        Collection<Object> list2 = Arrays.asList(123, 456);
        System.out.println("list.containsAll(list2) = " + list.containsAll(list2));
        //remove  返回是否移除成功
        System.out.println(list.remove(456));
        System.out.println(list.toString());
        //removeAll 一次性移除多个,移除参数集合与调用集合的交集（差集）部分
        System.out.println(list.removeAll(list2));
        System.out.println(list.toString());

        Collection<Object> list3 = new ArrayList<>();
        list3.add(123);
        list3.add(456);
        list3.add(new String("Tom"));
        list3.add(false);
        list3.add(new Person("小明", 22));
        //挨个比较元素，如果是无序集合不需比较顺序
        System.out.println("list3.equals(list) = " + list3.equals(list));

        Collection<Object> list4 = Arrays.asList(123, 456);
        //retainAll() :交集：获取当前集合和参数集合中的交集，并设置给调用者集合
        System.out.println("list3.retainAll(list4) = " + list3.retainAll(list4));
        System.out.println("list3.toString() = " + list3.toString());
    }

    @Test
    public void test2(){
        Collection<Object> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        list.add(new String("Tom"));
        list.add(false);
        list.add(new Person("小明", 22));
        //hashCode()  获取哈希值
        System.out.println("list.hashCode() = " + list.hashCode());
        //toArray() 转换集合为数组  对应Arrays类的asList()
        Object[] list2 = list.toArray();
        System.out.println("Arrays.toString(list2) = " + Arrays.toString(list2));
    }
}
