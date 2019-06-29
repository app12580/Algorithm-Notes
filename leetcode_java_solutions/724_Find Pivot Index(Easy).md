### description    
  Given an array of integers nums, write a method that returns the "pivot" index of this array.  
    
  We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.  
    
  If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.  
    
  Example 1:  
    
  Input:   
  nums = [1, 7, 3, 6, 5, 6]  
  Output: 3  
  Explanation:   
  The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.  
  Also, 3 is the first index where this occurs.  
     
    
  Example 2:  
    
  Input:   
  nums = [1, 2, 3]  
  Output: -1  
  Explanation:   
  There is no index that satisfies the conditions in the problem statement.  
     
    
  Note:  
    
  The length of nums will be in the range [0, 10000].  
  Each element nums[i] will be an integer in the range [-1000, 1000].  
### solution    
```    
Runtime: 1 ms, faster than 100.00% of Java online submissions for Find Pivot Index.
Memory Usage: 38 MB, less than 100.00% of Java online submissions for Find Pivot Index.

  class Solution {  
      public int pivotIndex(int[] nums) {  
          int sum = 0;  
          for(int num: nums) {  
              sum += num;  
          }  
          int cur = 0;  
          for(int i = 0; i < nums.length; i++) {  
              int num = nums[i];  
              if(cur * 2 + num == sum) {  
                  return i;  
              }  
              cur += num;  
          }  
          return -1;  
    
      }  
  }  
```    
    
### 个人解读    
  因为要求是sum的一半，所以想着要不要先把sum求出来，之后再遍历一次，只要curSum * 2 + cur = sum，那么就可以return了。但是这样需要两次遍历。  
  如果一次遍历的话，就需要一个东西去存储中间结果，感觉得不偿失了。  
  这题还蛮简单的。  
    
tags:    
  -  数学  
  -  预处理  
