### description    
  Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.  
    
  Example 1:  
    
  Input: nums = [1,2,3,1], k = 3  
  Output: true  
  Example 2:  
    
  Input: nums = [1,0,1,1], k = 1  
  Output: true  
  Example 3:  
    
  Input: nums = [1,2,3,1,2,3], k = 2  
  Output: false  
### solution    
```    
  class Solution {  
      public boolean containsNearbyDuplicate(int[] nums, int k) {  
           Map<Integer, Integer> map = new HashMap<>();  
          for(int i = 0; i < nums.length; i++) {  
              Integer index = map.getOrDefault(nums[i], -1);  
              if(index != -1) {  
                  if(i - index <= k) {  
                      return true;  
                  }  
              }  
              map.put(nums[i], i);  
          }  
          return false;  
      }  
  }  
    
  //速度最快的方法  
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Contains Duplicate II.  
  Memory Usage: 40 MB, less than 95.45% of Java online submissions for Contains Duplicate II.  
    
  class Solution {  
      public boolean containsNearbyDuplicate(int[] nums, int k) {  
          if (nums.length == 0)  
              return false;  
          if (nums.length > 5000)  
              return false;  
          for (int i = 0; i < nums.length; i++) {  
              for (int j = 0; j < nums.length; j++) {  
                  if (i != j) {  
                      if (nums[i] == nums[j]) {  
                          if (Math.abs(j - i) <= k)  
                              return true;  
                      }  
                  }  
              }  
          }  
          return false;  
      }  
  }  
    
  Runtime: 282 ms  
  Memory Usage: 41.2 MB  
    
  class Solution {  
      public boolean containsNearbyDuplicate(int[] nums, int k) {  
           for(int i = 0; i < nums.length; i++) {  
              for(int j = i + 1; j < nums.length && j <= i + k; j++) {  
                  if(nums[i] == nums[j]) {  
                      return true;  
                  }  
              }  
          }  
          return false;  
      }  
  }  
```    
    
### 个人解读    
  遍历就完事了，从0到len-k，然后注意细节是否+-1。  
    
  没注意是最多k而不是等于k。那么就弄一个HashMap来记录中间遍历结果。  
  需要注意本题的速度最快方法。  
    
tags:    
  -  for循环优化  
  -  遍历  
  -  数组  
