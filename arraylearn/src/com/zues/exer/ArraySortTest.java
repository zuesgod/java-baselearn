package com.zues.exer;

import org.junit.jupiter.api.Test;

/**
 * 练习：数组的排序算法
 *
 * @author zues
 */
public class ArraySortTest {

    /**
     * 冒泡排序
     *
     * @author zeus
     * @date 2022/1/31 8:16 下午
     */
    @Test
    public void bubbleSortTest() {
        int[] arr = {23, 321, 542, 544, 12, 54, 34, 12, 767, 323, 43};

        for (int i = 0; i < arr.length - 1; i++) {
            //内存循环开始时需要排除上一大轮已经排好序的元素
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
                    swap(arr,j,j+1);
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    /**
     * 快速排序
     * 通过一趟排序将待排序记录分割成独立的两部分，其中一部分记录的关键字均比另一部分关键字小
     * 则分别对这两部分继续进行排序，知道整个序列有序
     *
     * @author zeus
     * @date 2022/1/31 8:43 下午
     */
    @Test
    public void fastSortTest() {
        int[] arr = {23, 321, 542, 544, 12, 54, 34, 12, 767, 323, 43};
        //
        subSort(arr, 0, arr.length - 1);
        //打印结果
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    /**
     * 数组当前元素的反转
     *
     * @param data 当前数组
     * @param i    数组中遍历的当前元素
     * @param j    数组中遍历的后一个元素
     * @author zeus
     * @date 2022/1/31 9:03 下午
     */
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void subSort(int[] data, int start, int end) {
        if (start < end) {
            int base = data[start];
            int low = start;
            int high = end + 1;
            while (true) {
                while (low < end && data[++low] - base <= 0)
                    ;
                while (high > start && data[--high] - base >= 0)
                    ;
                if (low < high) {
                    swap(data, low, high);
                } else {
                    break;
                }
                swap(data, start, high);
                subSort(data, start, high - 1); //递归调用
                subSort(data, high + 1, end);
            }
        }
    }
}
