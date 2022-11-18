package com.binbini.imall.demo;

import java.util.Scanner;

/**
 * @Author: BinBin
 * @Date: 2022/11/14/18:10
 * @Description:
 */
public class Change {
    java.util.Stack<String> stack = new java.util.Stack<String>();
    public int getResult(int num,int rank) {
        return num/rank;
    }
    public int getRemain(int num,int rank) {
        return num%rank;
    }
    public void run(int num,int rank) {
        int result=getResult(num, rank);
        if(result==0) {
            stack.push(getRemain(num, rank)+"");
        }else {
            stack.push(getRemain(num, rank)+"");
            run(result, rank);
        }
    }
    public static void main(String[] args) {
        int[] changeNum = {2, 8, 16};
        String[] changeString = {"二进制", "八进制", "十六进制"};
        Change change=new Change();
        System.out.println("请输入一个十进制数字:");
        Scanner sca = new Scanner(System.in);
        int num = sca.nextInt();
        for (int i = 0; i < 3; i++) {
            System.out.println(changeString[i]);
            change.run(num, changeNum[i]);
            String Result = "";
            while(!change.stack.isEmpty()) {
                Result += change.stack.pop();
            }
            System.out.println(Result);
        }

    }
}