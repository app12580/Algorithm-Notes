### description    
  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.  
    
  (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).  
    
  You are given a target value to search. If found in the array return its index, otherwise return -1.  
    
  You may assume no duplicate exists in the array.  
    
  Your algorithm's runtime complexity must be in the order of O(log n).  
    
  Example 1:  
    
  Input: nums = [4,5,6,7,0,1,2], target = 0  
  Output: 4  
  Example 2:  
    
  Input: nums = [4,5,6,7,0,1,2], target = 3  
  Output: -1  
    
    
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.  
Memory Usage: 40.5 MB, less than 7.82% of Java online submissions for Search in Rotated Sorted Array.  
  
  
  class Solution {  
      public int search(int[] nums, int target) {  
          int l = 0;  
          int h = nums.length - 1;  
          while(l <= h) {  
              int m = l + (h - l) / 2 ;  
              if(nums[m] == target) {  
                  return m;  
              } else if(nums[m] > target) {  
                  //分为左边是单调区间和非单调区间  
                  if(nums[l] < nums[m] && nums[l] <= target || nums[l] > nums[m]) { //一开始没有加上等号  
                      h = m - 1;  
                  } else {  
                      l = m + 1;  
                  }  
              } else {  
                  if(nums[h] > nums[m] && nums[h] >= target || nums[h] < nums[m]) {  //两边对称的思考  
                      l = m + 1;  
                  } else {  
                      h = m - 1;  
                  }  
              }  
          }  
    
          return -1;  
      }  
  }  
```    
    
### 个人解读    
  二分法应用一  
  需要找出判断关系式子  
    
  [4,5,6,7,0,1,2]  
  [7,0,1,2,4,5,6]  
    
  l m h  
  需要先判断m的位置：是拐点前还是拐点后。如果拐点前，比较[l,m]，如果target在nums[l]和nums[m]中间，则左传递。  
    
  完全凭感觉写的，竟然通过了，我自己都惊呆了。  
    
    
tags:    
  -  二分法  
  -  rotate数组  
