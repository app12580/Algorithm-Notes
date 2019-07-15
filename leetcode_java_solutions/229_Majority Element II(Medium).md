### description    
  Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.  
    
  Note: The algorithm should run in linear time and in O(1) space.  
    
  Example 1:  
    
  Input: [3,2,3]  
  Output: [3]  
  Example 2:  
    
  Input: [1,1,1,3,3,2,2,2]  
  Output: [1,2]  
### solution    
```    
  class Solution {  
      public List<Integer> majorityElement(int[] nums) {  
          List<Integer> res = new ArrayList<>();  
          if(nums.length == 0) return res;  
          if(nums.length == 1) {  
              res.add(nums[0]);  
              return res;  
          }  
          Arrays.sort(nums);  
          int preVal = nums[0];  
          int count = 1;  
          for(int i = 1; i < nums.length; i++) {  
              int cur = nums[i];  
              if(cur == preVal) {  
                  count++;  
              } else {  
                  if(count > nums.length / 3) {  
                      res.add(preVal);  
                  }  
                  preVal = cur;  
                  count = 1;  
              }  
          }  
          if(count > nums.length / 3) {  
              res.add(preVal);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  不能使用其他的内存空间。  
  首先肯定是需要排序的，然后根据数组大小变成curLen问题。  
  curLen问题的处理方式：先把0扔进去。  
    
tags:    
  -  curLen  
