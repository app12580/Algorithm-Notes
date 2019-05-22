package com.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class InsertSort {
    private static int[] arr = {1, 6 ,7 ,8, 3, 5 ,11};

    private void sort(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }

        //外层i循环控制循环次数同时也是每次的起始preIndex
        for(int i = 0; i < arr.length - 1; i++) {
            int current = arr[i + 1];
            int preIndex = i;
            //从current开始往前遍历，如果current小于arr[preIndex]就向前插入
            // 同时preIndex要>=0
            while (preIndex >= 0 && current < arr[preIndex]) {  //这里如果是current <= arr[preIndex] 就变成不稳定的了
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    @Test
    public void test1() {
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
