package com.zues.base.array;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ArrayTest {

    @Test
    void test1() {
        int[] s = new int[10];
        for (int i = 0; i < 10; i++) {
            s[i] = 2 * i + 1;
            System.out.println("s[i] = " + s[i]);
        }
    }

    @Test
    void test2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生人数：");
        int number = scanner.nextInt();

        int[] student = new int[number];
        System.out.println("请输入" + number + "个成绩");
        int maxScore = 0;
        for (int i = 0; i < student.length; i++) {
            Scanner scanner1 = new Scanner(System.in);
            int aaa = scanner1.nextInt();
            student[i] = aaa;
            if (maxScore < student[i]) {
                maxScore = student[i];
            }
        }
        System.out.println("最高分为 = " + maxScore);
        for (int i = 0; i < student.length; i++) {
            System.out.println("student" + i + "score is" + student[i] + "grade is" + getGrade(student[i], maxScore));
        }

    }

    private String getGrade(int score, int maxScore) {
        if(score >= maxScore - 10){
            return "A";
        }else if(score >= maxScore - 20){
            return "B";
        }else if (score >= maxScore - 30){
            return "C";
        }else{
            return "D";
        }
    }


}
