package com.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class HeapSort {
    private static int[] arr = {1, 6 ,7 ,8, 3, 5 ,11};
    private int len;

    public  void sort(int[] array) {
        len = array.length;
        if(len < 1) {
            return;
        }
        //1.构建一个最大堆
        buildMaxHeap(array);
        //2.循环将堆首位(最大值)与末位交换，然后再重新调整最大堆
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0);
        }
    }

    public void buildMaxHeap(int[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    public void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if(i * 2 < len && array[i * 2] > array[maxIndex]){
            maxIndex = i * 2;
        }
        //如果有右子树，且右子树大于父节点，则将最大指针指向右树
        if(i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex]) {
            maxIndex = i * 2 + 1;
        }
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归他调整与父节点交换的位置。
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Test
    public void test1() {
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
