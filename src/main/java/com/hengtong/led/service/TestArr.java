package com.hengtong.led.service;

import java.util.Scanner;


public class TestArr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printArray(getSpiralArray(3,4,false));
        System.out.println();
//        printArray(getSpiralArray(2,2,true));
    }
    //返回螺旋数组
    public static int [][] getSpiralArray(int m,int n,boolean reverse){
        //定义一个长度为m*n的数组。并按顺序初始化
        int [] numArray = new int[m*n];
        for(int i = 0 ; i < numArray.length ; i++){
            if(!reverse){
                numArray[i] = (i+1);
            }else{
                numArray[i] = (numArray.length-i);
            }
        }
        //初始化数组下标
        int foot = 0;

        //声明螺旋数组
        int[][] array = new int[m][n];
        //计算"层数"，以m和n中较小的数为准
        int layer = m<n?
                m:n;
        layer = (layer%2==1)?(layer / 2 + 1):(layer / 2);

        // 从外层到里层循环
        for (int i = 0; i < layer; i++) {
            //从左到右
            for (int j = i; j < n - i; j++) {
                array[i][j] = numArray[foot++];
                if(foot>=m*n){
                    return array;
                }
            }
            // 从上到下
            for (int j = i + 1;  j < m - i; j++) {
                array[j][n - i - 1] = numArray[foot++];
                if(foot>=m*n){
                    return array;
                }
            }
            // 从右到左
            for (int j = n - i - 2; j >= i; j--) {
                array[m - i - 1][j] = numArray[foot++];
                if(foot>=m*n){
                    return array;
                }
            }
            // 从下到上
            for (int j = m - i - 2; j > i; j--) {
                array[j][i] = numArray[foot++];
                if(foot>=m*n){
                    return array;
                }
            }
        }
        return array;
    }
    //打印二维数组
    public static void printArray(int [][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if(array[i][j]<10){
                    System.out.print("0");
                }
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
