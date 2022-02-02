package com.zues.base;

import com.zues.entity.Person;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射测试
 *
 * @author zues
 * @date 2022/01/25 20:17
 **/
public class ReflectionTest {


    //反射之前对于Person的操作
    @Test
    public void test1() {
        //1.创建对象
        Person tom = new Person("Tom", 12);

        //2.通过对象调用其内部的属性、方法
        tom.age = 10;
        System.out.println("tom = " + tom);
        tom.show();
        //在Person类外部，不可以通过Person类的对象调用其内部私有结构
        //比如: name、 showNation()以及私有的构造器
    }

    //反射之后，对于Person的操作
    @Test
    public void test2() throws Exception {
        Class<Person> clazz = Person.class;
        //1.通过反射，创建Person对象
        Constructor<Person> constructor = clazz.getConstructor(String.class, Integer.class);
        Person tom = constructor.newInstance("Tom", 12);
        System.out.println("tom = " + tom);
        //2.通过反射，调用对象制定的属性、方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(tom, 10);
        System.out.println("tom = " + tom);
        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(tom);
        System.out.println("===================================================");
        //通过反射，可以调用Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有构造器
        Constructor<Person> cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person person = cons1.newInstance("jerry");
        System.out.println("person = " + person);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, "郭美美");
        System.out.println("person = " + person);
        //调用私有方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        //相当于person.showNation("China");并接受返回值
        String nation = (String) showNation.invoke(person, "China");
        System.out.println("nation = " + nation);
    }


    /**
     * 疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用哪个？
     * 建议：直接new的方式
     * 什么时候会使用反射的方式。 反射的特征：动态性   在编译的时候无法确认需要new什么对象，可以使用反射。比如controller中使用url找到对应对象并调用其方法
     * 疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
     * 不矛盾。封装性主要是建议我们不要调用内部属性，方法。    反射决定的是我们具体能不能调用的问题和技术。
     * <p>
     * 关于java.lang.Class类的理解
     * 1.类的加载过程：
     * 程序在经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。此过程称之为编译。
     * 接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。此过程就称为类的加载。加载到内存中的类，
     * 我们就称为运行时类，此运行时类，就作为Class的一个实例。
     * <p>
     * 2.换句话说，Class的实例就对应着一个运行时类。
     * 3.加载到内存中的运行时类，会缓存一定的事件。在此时间之内，我们可以通过不同的方式来获取此运行时类
     */
    @Test
    public void test3() throws ClassNotFoundException {
        //获取Class的实例的方式  (前三种方式需要掌握)
        //方式一：调用运行时类的属性
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);

        //方式二：通过运行时类的对象,调用getClass()
        Person p2 = new Person();
        Class<? extends Person> clazz2 = p2.getClass();
        System.out.println("clazz2 = " + clazz2);

        //方式三：调用Class的静态方法：forName(String classPath) 使用的最多，在编译时期不会报错，运行时才知道此类能否找到
        Class<?> clazz3 = Class.forName("com.zues.entity.Person");
        System.out.println("clazz3 = " + clazz3);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);

        //方式四：使用类的加载器 ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("com.zues.entity.Person");
        System.out.println("clazz4 = " + clazz4);
        System.out.println(clazz3 == clazz4);
    }

    /**
     * Class实例可以使哪些结构的说明
     */
    @Test
    public void test4() {
        Class<Object> c1 = Object.class;
        Class<Comparable> c2 = Comparable.class;
        Class<String[]> c3 = String[].class;
        Class<int[][]> c4 = int[][].class;
        Class<ElementType> c5 = ElementType.class;
        Class<Override> c6 = Override.class;
        Class<Integer> c7 = int.class;
        Class<Void> c8 = void.class;
        Class<Class> c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        //只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }
}
