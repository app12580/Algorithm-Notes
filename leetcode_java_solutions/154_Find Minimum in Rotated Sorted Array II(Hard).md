### description    
  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.  
    
  (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).  
    
  Find the minimum element.  
    
  The array may contain duplicates.  
    
  Example 1:  
    
  Input: [1,3,5]  
  Output: 1  
  Example 2:  
    
  Input: [2,2,2,0,1]  
  Output: 0  
  Note:  
    
  This is a follow up problem to Find Minimum in Rotated Sorted Array.  
  Would allow duplicates affect the run-time complexity? How and why?  
### solution    
```    
  class Solution {  
        public int findMin(int[] nums) {  
          if(nums[0] < nums[nums.length-1]) return nums[0];  
          int min = nums[0];  
    
          for(int i=1; i< nums.length; i++) {  
              if(nums[i] < min) {  
                  return nums[i];  
              }  
          }  
          return min;  
      }  
  }  
```    
    
### 个人解读    
  如果数字不可重复，可以直接根据二分法nums[l][m][h]的关系来判断目标在哪里。  
  因为重复数字的存在，所以二分法并不能很好的判断数字。  
    
  总结：  
  1、如果未发生错位：则返回第一个数字  
     如果发生错位： 则返回第一个变小的数字  
  2、比起算法，本题更像是只为了证明重复数字导致二分法失效而已  
     但是Hard题目很有迷惑性  
    
tags:    
  -  数学  
