### description    
  Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.  
    
  Find all the elements of [1, n] inclusive that do not appear in this array.  
    
  Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.  
    
  Example:  
    
  Input:  
  [4,3,2,7,8,2,3,1]  
    
  Output:  
  [5,6]  
    
  从[1,n]，有的出现两次，有的出现一次，还有一些不出现。  
  找出所有不出现的数字。  
### solution    
```    
  class Solution {  
      public List<Integer> findDisappearedNumbers(int[] nums) {  
         List<Integer> res = new ArrayList<>();  
          if(nums == null || nums.length == 0) {  
              return res;  
          }  
            
          for(int i = 0; i < nums.length; i++) {  
              int num = Math.abs(nums[i]);  
              if(nums[num - 1] > 0) {  
                  nums[num - 1] *= -1;  
              }  
          }  
          for(int i = 0; i < nums.length; i++) {  
              if(nums[i] > 0) {  
                  res.add(i+1);  
              }  
          }  
            
          return res;  
      }  
  }  
```    
    
### 个人解读    
  先试试下排序的效果怎么样。  
  注意题目要素： n是数组的大小。  
    
  思路： 想用一个int[n]作为标记，然而without extra space and in O(n) runtime，所以采用标记合一法。  
    
  反思： 主要还是阅读英文描述时候，总是忽略重要信息。  
    
tags:    
  -  题目描述  
  -  标记合一  
