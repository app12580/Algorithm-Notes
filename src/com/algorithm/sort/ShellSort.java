package com.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class ShellSort {

    private static int[] arr = {1, 6 ,7 ,8, 3, 5 ,11};

    private void sort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
    }

    @Test
    public void test1() {
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }



}
