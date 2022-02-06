package com.zues.exer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 1、List接口框架
 *
 *|----Collection接口: 单列集合，用来存储一个一个的对象
 *      |----List接口: 存储有序的、可重复的数据。  -》“动态”数组，替换原有的数组
 *          |----ArrayList:作为List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData存储
 *          |----LinkedList: 对于频繁的插入、删除操作，使用此类效率比ArrayList高，底层使用双向链表存储
 *          |----Vector: 作为List接口的古老实现类；线程安全的，效率低；底层使用Object[] elementData存储
 *
 * 2、ArrayList的源码分析：
 *  2.1  jdk 7情况下
 *      List list = new ArrayList(); 底层创建了长度是10的Object[] 数组elementData
 *      list.add(123);  //elementData[0] = new Integer(123);
 *      ...
 *      list.add(11); //如果此次的添加导致底层elementData数组容量不够，则扩容。
 *      默认情况下，扩容为原来的容量的1.5倍，同时需要将原有的数组中的数据复制到新的数组中。
 *
 *      结论：建议开发中使用带参的构造器：List list = new ArrayList(int initialCapacity)
 *  2.2 jdk 8中的变化
 *      List list = new ArrayList(); 底层Object[] elementData初始化为{},并没有创建长度为10的数组
 *
 *      list.add(123);//第一次调用add()时，底层才创建了长度为10的数组，并将数据123添加到elementData[0]
 *      ...
 *      后续的添加和扩容操作与jdk 7 无异
 *  2.3 小结：jdk7中的ArrayList的对象创建类似于单例模式的饿汉式，而jdk8中的ArrayList的对象创建
 *           则类似于懒汉式，延迟了数组的创建，节省了内存。
 *
 * 3、LinkedList的源码分析：
 *      List list = new LinkedList(); 内部声明了Node类型的first和last属性，默认值为null；
 *      list.add(123); //将123封装到Node中，创建了Node对象
 *
 *      其中，Node定义为：  体现了LinkedList的双向链表的说法
 *     private static class Node<E> {
 *         E item;
 *         Node<E> next;
 *         Node<E> prev;
 *
 *         Node(Node<E> prev, E element, Node<E> next) {
 *             this.item = element;
 *             this.next = next;
 *             this.prev = prev;
 *         }
 *     }
 *
 *
 * 4、Vector的源码分析： jdk7 和 jdk8中通过Vector()构造器创建对象时，底层都创建了长度为10的数组。
 *                     在扩容方面，默认扩容为原来数组长度的2倍
 *
 * 面试题：ArrayList、LinkedList、Vector三者的异同？
 * 同：三个类都是实现了List接口，存储数据的特点相同：存储有序的，可重复的数据
 * 不同：见上
 *
 *
 *
 *
 * @author zeus
 * @date 2022-02-06 11:20 上午
 **/
public class ListTest {

    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(121);
        list.add(325);
        list.add(847);

        //方式一：使用迭代器
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("--------------------------");

        //方式二：使用普通for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("--------------------------");

        //方式三：使用增强for循环
        for (Integer val : list) {
            System.out.println(val);
        }
        System.out.println("--------------------------");

        //方式四：java8新增的foreach方法
        list.forEach(System.out::println);

    }


    @Test
    public void test2(){
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(121);
        list.add(325);
        list.add(847);
        list.add(121);
        //int indexOf(Object obj): 返回obj在集合中首次出现的位置，如果不存在，返回-1
        System.out.println("list.indexOf(121) = " + list.indexOf(121));
        //int lastIndexOf(Object obj): 返回obj在当前集合中末次出现的位置，如果不存在，返回-1
        System.out.println("list.lastIndexOf(121) = " + list.lastIndexOf(121));
        //Object remove(int index): 移除指定index位置的元素，并返回此元素
        Integer removeObj = list.remove(0);
        System.out.println("removeObj = " + removeObj);
        System.out.println("list = " + list);
        //Object set(int dex,Object ele): 设置指定index位置的元素为ele
        list.set(1,222);
        System.out.println("list = " + list);
        //List subList(int fromIndex,int toIndex): 返回从fromIndex到toIndex位置的左闭右开区间的子集合
        List<Integer> subList = list.subList(2, 4);
        System.out.println("subList = " + subList);
    }


    @Test
    public void test3(){
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(121);
        list.add(325);
        list.add(847);
        list.add(121);

        System.out.println("list = " + list);

        list.add(1,555);
        System.out.println("list = " + list);

        List<Integer> list1 = Arrays.asList(2, 8, 999, 555, 121);
        list.addAll(list1);
//        list.add(list1);
        System.out.println("list = " + list);

        System.out.println("list.get(0) = " + list.get(5));

    }

}
