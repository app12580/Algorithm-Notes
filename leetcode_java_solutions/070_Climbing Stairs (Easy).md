### description    
  You are climbing a stair case. It takes n steps to reach to the top.  
    
  Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?  
    
  Note: Given n will be a positive integer.  
    
  Example 1:  
    
  Input: 2  
  Output: 2  
  Explanation: There are two ways to climb to the top.  
  1. 1 step + 1 step  
  2. 2 steps  
  Example 2:  
    
  Input: 3  
  Output: 3  
  Explanation: There are three ways to climb to the top.  
  1. 1 step + 1 step + 1 step  
  2. 1 step + 2 steps  
  3. 2 steps + 1 step  
### solution    
```    
  class Solution {  
      public int climbStairs(int n) {  
          int pre = 1;  
          int cur = 1;  
          int res = 1;  
          for(int i = 1; i < n; i++) {  
              res = pre + cur;  
              pre = cur;  
              cur = res;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  首先想到递归，然后得到表达式。  
  爬楼梯，斐波那契数列的实际应用。  
  写的时候稍微注意一下极值，找1,2,3试一下，看结果对不对。  
    
tags:    
  -  动态规划  
  -  斐波那契数列  
    
