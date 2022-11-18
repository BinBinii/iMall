package com.binbini.imall.demo;

import java.util.Scanner;

/**
 * @Author: BinBin
 * @Date: 2022/11/14/18:19
 * @Description:
 */
public class BF {
    public static int BF(String str,String sub,int pos) {
        int lenStr = str.length();
        int lenSub = sub.length();
        if (pos < 0 || pos > lenStr) {
            return -1;
        }
        int i = pos;
        int j = 0;
        while (i < lenStr && j < lenSub) {
            if (str.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j >= sub.length()) {
            return i - j;
        } else {
            return -1;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("主串:");
        String str = scanner.nextLine();
        System.out.print("子串:");
        String sub = scanner.nextLine();
        int index = BF(str,sub,0);
        System.out.println("第一次出现的位置:"+index);
    }
}