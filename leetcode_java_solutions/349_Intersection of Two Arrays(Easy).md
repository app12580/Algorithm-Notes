### description    
Given two arrays, write a function to compute their intersection.  
  
Example 1:  
  
Input: nums1 = [1,2,2,1], nums2 = [2,2]  
Output: [2]  
Example 2:  
  
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]  
Output: [9,4]  
Note:  
  
Each element in the result must be unique.  
The result can be in any order.    
### solution    
```    
  class Solution {  
      public int[] intersection(int[] nums1, int[] nums2) {  
          List<Integer> list = new ArrayList<>();  
          Set<Integer> set = new HashSet<>();  
          for(int num: nums1) {  
             set.add(num);  
          }  
          for(int num: nums2) {  
              if(set.contains(num) ) {  
                  list.add(num);  
                  set.remove(num);  
              }  
          }  
    
          int[] res = new int[list.size()];  
          int i = 0;  
          for(int num: list) {  
              res[i++] = num;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  注意结果不是重复的。  
  思路一： 预处理排序，想了想效率不高。  
  思路二： 搞个Map作为中间存储结果。  
    
tags:    
  -  Hash表  
