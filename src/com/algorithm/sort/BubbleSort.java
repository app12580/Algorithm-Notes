package com.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class BubbleSort {

    private static int[] arr = {1, 6 ,7 ,8, 3, 5 ,11};

    /**
     * 个人习惯：
     * 外层i循环控制循环次数
     * 内层j循环再控制业务逻辑
     */
    private void sort(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }

        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
    }

    @Test
    public void test1() {
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }



}
