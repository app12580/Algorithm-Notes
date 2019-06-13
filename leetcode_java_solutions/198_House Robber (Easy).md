### description    
  You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.  
    
  Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.  
    
  Example 1:  
    
  Input: [1,2,3,1]  
  Output: 4  
  Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).  
               Total amount you can rob = 1 + 3 = 4.  
  Example 2:  
    
  Input: [2,7,9,3,1]  
  Output: 12  
  Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).  
               Total amount you can rob = 2 + 9 + 1 = 12.  
### solution    
```    
  class Solution {  
      public int rob(int[] nums) {  
          int pre2 = 0;  
          int pre1 = 0;  
          int res = 0;  
          for(int i = 0; i < nums.length; i++) {  
              res = Math.max(pre2 + nums[i], pre1);  
              pre2 = pre1;  
              pre1 = res;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  动态规划，对于这题目，总是会去想要考虑是否包含当前节点，然而实际这点并不重要，可以忽略掉。    
  f(n) = max(f(n-2)+nums[n], f(n-1))    
    
tags:    
  -  动态规划  
