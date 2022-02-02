package com.zues.exer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 数组工具类测试
 *
 * @author zeus
 * @date 2022-01-31 9:50 下午
 **/
public class ArraysTest {

    @Test
    public void test1(){
        int[] arr = {23, 321, 542, 544, 12, 54, 34, 12, 767, 323, 43};
        //数组复制
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        //数组打印
        System.out.println("Arrays.toString(copyArr) = " + Arrays.toString(copyArr));
        //数组排序，底层为快排
        Arrays.sort(arr);
        System.out.println("Arrays.toString(copyArr) = " + Arrays.toString(arr));

        //二分法查找
        System.out.println("Arrays.binarySearch(arr,24) = " + Arrays.binarySearch(arr, 323));
    }
}
