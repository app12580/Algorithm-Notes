package com.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {


    public boolean judgeSquareSum(int c) {
        int i = 1;
        int j = (int)Math.sqrt(c);
        while(i <= j) {
            int sum = i * i + j * j;
            if(sum < c) {
                i++;
            } else if(sum > c) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }


}
