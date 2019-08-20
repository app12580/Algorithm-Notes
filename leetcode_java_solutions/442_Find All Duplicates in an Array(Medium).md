### description    
  Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.  
    
  Find all the elements that appear twice in this array.  
    
  Could you do it without extra space and in O(n) runtime?  
    
  Example:  
  Input:  
  [4,3,2,7,8,2,3,1]  
    
  Output:  
  [2,3]  
### solution    
```    
  
Runtime: 6 ms, faster than 90.04% of Java online submissions for Find All Duplicates in an Array.  
Memory Usage: 48.2 MB, less than 36.11% of Java online submissions for Find All Duplicates in an Array.  
  
  
  class Solution {  
      public List<Integer> findDuplicates(int[] nums) {  
          List<Integer> res = new ArrayList<>();  
          for(int i = 0; i < nums.length; i++) {  
              int cur = Math.abs(nums[i]);  
              if(nums[cur - 1] < 0) {  
                  res.add(cur);  
              } else {  
                  nums[cur - 1] *= -1;  
              }  
    
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  重点是自然数组，可以把数值和下标联系在一起。  
  然后使用源数组标记法，通过负数作为标记。  
    
tags:    
  -  自然数组  
