package com.zues.create.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试泛型用entity  自定义泛型类
 * 声明构造器时不能带上泛型
 *
 * @author zues
 * @date 2022/01/22 20:02
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Order<T> {

    private Integer id;

    private String name;

    //类的内部结构就可以使用类的泛型
    private T orderT;

    //静态方法中不能使用类的泛型
//    public static void show(T orderT){
//        System.out.println("orderT = " + orderT);
//    }

    //泛型方法： 在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。
    //换句话说，泛型方法所属的类是不是泛型类都没有关系
    public <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for (E e : arr) {
            list.add(e);
        }
        return list;
        //上述方法可以写在一行如下所示
        //return new ArrayList<>(Arrays.asList(arr));
    }



}
