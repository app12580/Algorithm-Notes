package com.algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 0;
        int end = Integer.MIN_VALUE;
        for(int[] interval: intervals) {
            if(interval[0] >= end) {
                count++;
                end = interval[1];
            }
        }
        return intervals.length - count;

    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
