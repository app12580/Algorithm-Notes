### description    
  Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.  
    
  You need to find the shortest such subarray and output its length.  
    
  Example 1:  
  Input: [2, 6, 4, 8, 10, 9, 15]  
  Output: 5  
  Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.  
  Note:  
  Then length of the input array is in range [1, 10,000].  
  The input array may contain duplicates, so ascending order here means <=.  
### solution    
```    
  Runtime: 2 ms, faster than 92.84% of Java online submissions for Shortest Unsorted Continuous Subarray.  
  Memory Usage: 39.7 MB, less than 97.86% of Java online submissions for Shortest Unsorted Continuous Subarray.  
    
    
  class Solution {  
      public int findUnsortedSubarray(int[] nums) {  
          int min = Integer.MAX_VALUE;  
          int max = Integer.MIN_VALUE;  
          int left = -1;  
          int right = -1;  
          for(int i = 1; i < nums.length; i++) {  
              if(nums[i] < nums[i - 1]) {  
                  if(left == -1) {  
                      left = i;  
                  } else {  
                      right = i;  
                  }  
              }  
          }  
          if(left == -1) {  
              return 0;  
          }  
          if(right == -1) {  
              right = left;  
          }  
          for(int i = left; i <= right; i++) {  
              min = Math.min(min, nums[i]);  
              max = Math.max(max, nums[i]);  
          }  
          int res = right - left + 1;  
          for(int i = right + 1; i < nums.length; i++) {  
              if(nums[i] < max || nums[i] == nums[i-1]) {  
                  res++;  
                  min = Math.min(min, nums[i]);  
                  right++;  
              } else {  
                  break;  
              }  
          }  
          for(int i = left - 1; i >= 0; i--) {  
              if(nums[i] > min || nums[i] == nums[i+1]) {  
                  res++;  
                  max = Math.max(max, nums[i]);  
                  left--;  
              } else {  
                  break;  
              }  
          }  
            
          return res;  
      }  
  }  
```    
    
### 个人解读    
  心路历程：  
  循环入栈法；关注哪里发生了减数列  
    
  盯了示例一段时间，发现栈很难用，然后64,10 9这里两个关键点，发现找出这段(4~9)区间内的最大值和最小值，然后再区间两端外推动即可。  
  ```  
  Input: [2, 6, 4, 8, 10, 9, 15]  
  Output: 5  
  ```  
  向外扩展的同时，可能会引入新的极值。所以有了重复性的操作：左右左，左边扩展了两次。本题有多重解法，最后发现我这解法效率还阔以。  
    
tags:    
  -  数学  
  -  多次遍历  
  -  多种解法  
