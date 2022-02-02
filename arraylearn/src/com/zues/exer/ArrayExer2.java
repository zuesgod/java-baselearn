package com.zues.exer;

import org.junit.jupiter.api.Test;

/**
 * 二维数组的练习,理解二维数组
 */
public class ArrayExer2 {
    /**
     * 计算数组中值的和
     */
    @Test
    public void test1(){
        int[][] arr = {{3, 5, 8}, {12, 9}, {7, 0, 6, 4}};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("sum = " + sum);
    }

    /**
     * 练习杨辉三角
     * 使用二维数组打印一个10行的杨辉三角
     * 提示
     * 1.第一行有1个元素，第n行有n个元素
     * 2.每一行的第一个元素和最后一个元素都是1
     * 3.从第三行开始，对于非第一个元素和最后一个元素的元素。即：
     * yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j]
     */
    @Test
    public void test2(){
        //1.声明并初始化二维数组
        int[][] yanghui = new int[10][];

        //2.给数组的元素赋值
        for (int i = 0; i < yanghui.length; i++) {
            yanghui[i] = new int[i+1];
            //2.1 给首末元素赋值
            yanghui[i][0] = 1;
            yanghui[i][i] = 1;
            //2.2 给每行的非首末元素赋值
            for (int j = 1; j < yanghui[i].length -1; j++) {
                yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
            }
        }

        //3.遍历打印
        for (int i = 0; i < yanghui.length; i++) {
            for (int j = 0; j < yanghui[i].length; j++) {
                System.out.print(yanghui[i][j] + " ");
            }
            System.out.println();
        }
    }
}
