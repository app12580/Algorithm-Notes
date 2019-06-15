### description    
  Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.  
    
  Example:  
    
  nums = [1, 2, 3]  
  target = 4  
    
  The possible combination ways are:  
  (1, 1, 1, 1)  
  (1, 1, 2)  
  (1, 2, 1)  
  (1, 3)  
  (2, 1, 1)  
  (2, 2)  
  (3, 1)  
    
  Note that different sequences are counted as different combinations.  
    
  Therefore the output is 7.  
     
    
  Follow up:  
  What if negative numbers are allowed in the given array?  
  How does it change the problem?  
  What limitation we need to add to the question to allow negative numbers?  
    
  Credits:  
  Special thanks to @pbrother for adding this problem and creating all test cases.  
    
  给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。  
### solution    
```    
  class Solution {  
      public int combinationSum4(int[] nums, int target) {  
            int[] dp = new int[target + 1];  
          dp[0] = 1;  
          for(int i = 1; i <= target; i++) {  
              for(int num: nums) {  
                  if(num <= i) {  
                      dp[i] += dp[i - num];  
                  }  
              }  
          }  
          return dp[target];  
      }  
  }  
```    
    
### 个人解读    
  完全背包问题；统计求和问题；  
  因为顺序不同算不同个数，所以外层dp内层元素循环。  
    
tags:    
  -  动态规划  
  -  完全背包  
  -  外层dp内层元素  
