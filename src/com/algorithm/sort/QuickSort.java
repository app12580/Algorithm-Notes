package com.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {

//    private static int[] arr = {1, 6, 7, 8, 3, 5, 11};

    public void quickSort(int[] array) {
        _quickSort(array, 0, array.length - 1);
    }


    public void _quickSort(int[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);  //将list数组进行一分为二
            _quickSort(list, low, middle - 1);        //对低字表进行递归排序
            _quickSort(list, middle + 1, high);       //对高字表进行递归排序
        }
    }

    //为什么先找high的坐标：因为中轴的坐标是0
    public int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];    //数组的第一个作为中轴，
        while (low < high) {
            // 步骤1：找到合适的high
            // 步骤2：把low的位置变成high的值
            // 步骤3：寻找low的新坐标
            // 步骤4：把high的位置的值修复
            while (low < high && list[high] > tmp) {    //令high变成右边起，第一个小于等于tmp的坐标
                high--;
            }
            list[low] = list[high];   // 把low变成比tmp小的值
            while (low < high && list[low] < tmp) {
                low++;              //
            }
            list[high--] = list[low];   //因为high的值被放到low了，所以要修复high位置的值
        }
        // 步骤5： 跳出循环，然后把low的位置复原成tmp，return low
        list[low] = tmp;              //中轴记录到尾
        return low;                   //返回中轴的位置
    }


    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    @Test
    public void test1() {
//        int[] arr = {3,2,3,1,2,4,5,5,6};
        int[] arr = {3,2,3,1,2,67,7,74,5,7,5,6,-2,0};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
