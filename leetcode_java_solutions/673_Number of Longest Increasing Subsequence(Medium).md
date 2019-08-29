### description      
  Given an unsorted array of integers, find the number of longest increasing subsequence.    
      
  Example 1:    
  Input: [1,3,5,4,7]    
  Output: 2    
  Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].    
  Example 2:    
  Input: [2,2,2,2,2]    
  Output: 5    
  Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.    
  Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.    
### solution      
  ```    
  Runtime: 8 ms, faster than 79.08% of Java online submissions for Number of Longest Increasing Subsequence.    
  Memory Usage: 38.2 MB, less than 44.44% of Java online submissions for Number of Longest Increasing Subsequence.    
      
  class Solution {    
       
          
      public int findNumberOfLIS(int[] nums) {    
          if(nums == null || nums.length == 0) return 0;    
          int len = nums.length;    
          int[] count = new int[len];    
          int[] lenArr = new int[len];    
          int maxLen = 1;    
          int res = 1;    
          count[0] = 1;    
          lenArr[0] = 1;    
          for(int i = 1; i < len; i++) {    
              int cur = nums[i];    
              int curCount = 1;    
              int curLen = 1;    
              for(int j = i - 1; j >= 0; j--) {    
                  if(nums[j] >= cur) {    
                      continue;    
                  }    
                  if(lenArr[j] > curLen - 1) {    
                      curLen = lenArr[j] + 1;    
                      curCount = count[j];    
                  } else if(lenArr[j] == curLen - 1) {    
                      curCount += count[j];    
                  }    
              }    
              count[i] = curCount;    
              lenArr[i] = curLen;    
              if(curLen == maxLen) {    
                  res += curCount;    
              } else if(curLen > maxLen) {    
                  maxLen = curLen;    
                  res = curCount;    
              }    
          }    
      
          return res;    
      }    
      
          
  }    
      
  ```    
      
### 个人解读      
  打算通过一个int数组，用来存储以当前位置为节点的长度和数量。    
     
     
tags:      
  -  数学    
  -  数组解析    
      
