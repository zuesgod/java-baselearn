package com.zues.exer;

import org.junit.jupiter.api.Test;

/**
 * 数组异常测试
 *
 * @author zeus
 * @date 2022-02-01 8:30 下午
 **/
public class ArrayExceptionExer {


    @Test
    public void test1(){
        //空指针异常
        //情况一
        int[] arr1 = {1, 2, 3};
        arr1 = null;
        System.out.println(arr1[0]);
        //情况二
        int[][] arr2 = new int[4][];
        System.out.println("arr2[0][0] = " + arr2[0][0]);

        //情况三：
        String[] arr3 = {"AA", "BB", "CC"};
        arr3[0] = null;
        System.out.println(arr3[0].toString());
    }
}
