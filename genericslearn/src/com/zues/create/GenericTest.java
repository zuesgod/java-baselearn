package com.zues.create;

import com.zues.create.entity.Order;
import com.zues.create.entity.Person;
import com.zues.create.entity.SubOrder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义创建并使用泛型的测试
 * 如何自定义泛型结构：泛型类、泛型接口、泛型方法
 *
 * @author zues
 * @date 2022/01/22 20:06
 **/
public class GenericTest {

    @Test
    public void test1() {
        //如果定义了泛型，实例化没有指明类的泛型，则认为此泛型类型为Object类型
        //要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型。
//        Order order = new Order();     此处上下写法其实是一样的，只不过写了泛型后阿里巴巴检测不会报黄色警告
        Order<Object> order = new Order<Object>();
        order.setId(1)
                .setName("测试者1")
                .setOrderT("测试泛型1");
        System.out.println("order = " + order);

        //建议实例化时指明类的泛型
        Order<String> order1 = new Order<>(22, "orderAA", "测试者2");
        System.out.println("order1 = " + order1);

        //由于子类在继承带泛型的父类时，指明了泛型类型。则实例化子类对象时，不再需要指明泛型
        SubOrder subOrder = new SubOrder();
        subOrder.setOrderT(213123);
    }

    @Test
    public void test3(){
        ArrayList<String> list1 = null;
        ArrayList<Integer> list2 = null;
        //泛型不同的引用不能相互赋值  理解：等于破坏泛型的类型约束
//        list1 = list2;
        Person p1 = null;
        Person p2 = null;
        p1 = p2;
    }

    //测试使用泛型方法
    @Test
    public void test4(){
        Order<String> order = new Order<>();
        Integer[] arr = new Integer[]{1,2,3,4};
        List<Integer> list = order.copyFromArrayToList(arr);
        System.out.println("list = " + list);

        String[] arr1 = new String[]{"asd","das","das"};
        List<String> list2 = order.copyFromArrayToList(arr1);
        System.out.println("list2 = " + list2);
    }

}
