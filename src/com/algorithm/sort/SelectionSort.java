package com.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class SelectionSort {
    private static int[] arr = {1, 6 ,7 ,8, 3, 5 ,11};

    /**
     * 从小到大遍历，每次把最小的放在最前面
     * @param arr
     */
    private void sort(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }

        for(int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            //这一步j=i+1可以减少一次判断
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int t = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = t;

        }
    }

    @Test
    public void test1() {
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
