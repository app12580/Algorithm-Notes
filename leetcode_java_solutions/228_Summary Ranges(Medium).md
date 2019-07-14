### description    
  Given a sorted integer array without duplicates, return the summary of its ranges.  
    
  Example 1:  
    
  Input:  [0,1,2,4,5,7]  
  Output: ["0->2","4->5","7"]  
  Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.  
  Example 2:  
    
  Input:  [0,2,3,4,6,8,9]  
  Output: ["0","2->4","6","8->9"]  
  Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.  
### solution    
```    
  class Solution {  
     public List<String> summaryRanges(int[] nums) {  
          List<String> res = new ArrayList<>();  
          if(nums.length == 0) return res;  
          int right = nums[0];  
          int left = nums[0];  
          for(int i = 1; i < nums.length; i++) {  
              if(nums[i] > right + 1) {  
                  if(left != right) {  
                      res.add(left + "->" + right);  
                  } else {  
                      res.add(left + "");  
                  }  
                  left = nums[i];  
                  right = nums[i];  
              } else {  
                  right = nums[i];  
              }  
          }  
          if(left != right) {  
              res.add(left + "->" + right);  
          } else {  
              res.add(left + "");  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  cueLen问题  
    
  注意两点细节：  
  1、出循环时候要再添加一次  
  2、判断left和right是否相等的情况。  
    
tags:    
  -  curLen问题  
