package com.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        //key: 数组中的值 value:数组中的下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            if(map.containsKey(left)) {
                return new int[]{map.get(left), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    @Test
    public void test() {
        int[] arr = {2, 7 ,11 , 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(arr, target)));
    }


}
