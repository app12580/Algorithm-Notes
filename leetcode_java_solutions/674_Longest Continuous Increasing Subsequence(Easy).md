### description    
  Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).  
    
  Example 1:  
  Input: [1,3,5,4,7]  
  Output: 3  
  Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.   
  Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.   
  Example 2:  
  Input: [2,2,2,2,2]  
  Output: 1  
  Explanation: The longest continuous increasing subsequence is [2], its length is 1.   
  Note: Length of the array will not exceed 10,000.  
    
  找出最长递增序列长度  
### solution    
```    
  class Solution {  
      public int findLengthOfLCIS(int[] nums) {  
          if(nums == null || nums.length== 0) return 0;  
           int res = 1;  
          int cur = 1;  
          for(int i = 1; i < nums.length ; i++) {  
              if(nums[i] > nums[i-1]) {  
                  cur++;  
              } else {  
                  res = Math.max(res, cur);  
                  cur = 1;  
              }  
          }  
    
          return Math.max(res, cur);  
      }  
  }  
```    
    
### 个人解读    
  遍历即可，注意cur和res的细节。  
    
tags:    
  -  遍历  
