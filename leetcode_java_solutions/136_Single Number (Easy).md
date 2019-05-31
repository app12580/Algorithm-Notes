### description    
  Given a non-empty array of integers, every element appears twice except for one. Find that single one.  
    
  Note:  
    
  Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?  
    
  Example 1:  
    
  Input: [2,2,1]  
  Output: 1  
  Example 2:  
    
  Input: [4,1,2,1,2]  
  Output: 4  
  数组中唯一一个不重复的元素  
### solution    
```    
  class Solution {  
      public int singleNumber(int[] nums) {  
          int res = 0;  
          for(int num: nums) {  
              res = res ^ num;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  全部^就完事了，本题的扩展的才是麻烦事，有一个数只出现1次，其他每个出现三次时候怎么办。  
    
tags:    
  -   位运算  
