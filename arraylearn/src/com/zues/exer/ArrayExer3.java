package com.zues.exer;

import org.junit.jupiter.api.Test;

/**
 * 数组的练习
 */
public class ArrayExer3 {
    /**
     * 使用简单数组
     * （1）创建测试类，声明array1和array2两个变量，他们是int[]类型的数组。
     * （2）使用大括号{}，把array1初始化为8个素数：2,3,5,7,11,13,17,19.
     * （3）显示array1的内容。
     * （4）赋值array2变量等于array1，修改array2中的偶索引元素，使其等于索引值（如array[0] = 0,array[2] = 2）。打印出array1。
     * <p>
     * 思考：array1和array2是什么关系？
     * 拓展：修改题目，实现array2对array1数组的复制
     */
    @Test
    public void test1() {
        //（1）
        int[] array1, array2;
        array1 = new int[]{2, 3, 5, 7, 11, 13, 17, 19};
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i]);
        }
        System.out.println();
        //不能称作数组的复制，赋予地址值，相当于创建快捷方式
        array2 = array1;
        //修改array2中的偶索引元素，使其等于索引值（如array[0] = 0,array[2] = 2）。打印出array1。
        for (int i = 0; i < array2.length; i++) {
            if (i % 2 == 0) {
                array2[i] = i;
            }
            //这时发现array1也变了，其实堆空间中只有一个数组存在，array1与array2地址值一样
            System.out.print(array1[i]);
        }
        System.out.println();
        System.out.println(array2 == array1);

        //数组复制
        array2 = new int[array1.length];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = array1[i];
        }

        for (int i = 0; i < array2.length; i++) {
            if (i % 2 == 0) {
                array2[i] = i;
            }
            System.out.print(array2[i]);
        }
        System.out.println();
        System.out.println(array2 == array1);
    }

    /**
     * 数组的复制与反转
     */
    @Test
    public void test2() {
        String[] arr = {"JJ", "DD", "MM", "BB", "GG", "AA", "CC"};

        //复制
        //数组的复制（区别于数组变量的赋值： arr1 = arr）
        String[] arr1 = new String[arr.length];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = arr[i];
            System.out.print(arr1[i]);
        }

        //反转  自己的方法
//        for (int i = 1; i < arr1.length / 2; i++) {
//            String temp = arr1[arr1.length - i];
//            arr1[arr1.length - i] = arr1[i - 1];
//            arr1[i - 1] = temp;
//        }
        //尚硅谷康师傅的方法一：
//        for (int i = 0; i < arr1.length / 2; i++) {
//            String temp = arr1[i];
//            arr1[i] = arr1[arr1.length - i - 1];
//            arr1[arr1.length - i - 1] = temp;
//        }
        //方式二：
        for (int i = 0, j = arr1.length - 1; i < j; i++, j--) {
            String temp = arr1[i];
            arr1[i] = arr1[j];
            arr1[j] = temp;
        }
        System.out.println();
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]);
        }
    }

    /**
     * 查找（或搜索）
     */
    @Test
    public void test3() {
//        String[] arr = {"JJ", "DD", "MM", "BB", "GG", "AA", "CC"};
//        //线性查找：
//        String dest = "BB";
//        dest = "EE";
//        boolean isFind = true;
//        for (int i = 0; i < arr.length; i++) {
//            if (dest.equals(arr[i])) {
//                System.out.println("找到了，位置为：" + i);
//                isFind = false;
//                break;
//            }
//        }
//        if (isFind) {
//            System.out.println("抱歉，没有找到！");
//        }

        //二分法查找
        //前提：所要查找的数组必须有序
        int[] arr2 = {-98, -34, 2, 34, 54, 66, 78, 98, 105, 210, 333};
        int dest2 = 34;
        int head = 0;
        int end = arr2.length - 1;
        while (head <= end) {
            int middle = (head + end) / 2;
            if (dest2 == arr2[middle]) {
                System.out.println("找到了，位置为：" + middle);
                break;
            } else if (dest2 > arr2[middle]) {
                head = middle + 1;
            } else {
                //dest2 < arr2[middle]
                end = middle - 1;
            }
        }

    }
}




