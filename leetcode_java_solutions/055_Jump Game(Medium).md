### description    
  Given an array of non-negative integers, you are initially positioned at the first index of the array.  
    
  Each element in the array represents your maximum jump length at that position.  
    
  Determine if you are able to reach the last index.  
    
  Example 1:  
    
  Input: [2,3,1,1,4]  
  Output: true  
  Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.  
  Example 2:  
    
  Input: [3,2,1,0,4]  
  Output: false  
  Explanation: You will always arrive at index 3 no matter what. Its maximum  
               jump length is 0, which makes it impossible to reach the last index.  
### solution    
```    
  
// 方法一：   
Runtime: 118 ms, faster than 30.76% of Java online submissions for Jump Game.  
Memory Usage: 40.8 MB, less than 53.77% of Java online submissions for Jump Game.  
  
  class Solution {  
      public boolean canJump(int[] nums) {  
           int len = nums.length;  
          boolean[] dp = new boolean[len];  
          dp[0] = true;  
          for(int i = 0; i < len; i++) {  
              if(!dp[i]) break;  
              int step = nums[i];  
              for(int j = i + 1; j <len && j <= i + step; j++) {  
                  dp[j] = true;  
                  if(j == len - 1) break;  
              }  
          }  
          return dp[len - 1];  
      }  
  }  
    
    
  //方法二 ： 优化后  
  Runtime: 1 ms, faster than 99.58% of Java online submissions for Jump Game.  
  Memory Usage: 39.3 MB, less than 99.85% of Java online submissions for Jump Game.  
    
    
  class Solution {  
      public boolean canJump(int[] nums) {  
          int max = 0;  
          for(int i = 0; i < nums.length; i++) {  
              if(i > max) return false;  
              int cur = nums[i];  
              max = Math.max(max, i + cur);  
              if(max >= nums.length - 1) return true;  
          }  
            
          return max >= nums.length - 1;  
      }  
  }  
```    
    
### 个人解读    
    
  第一思路就是用动态规划，最后return dp[n]  
    
  方法一：写着写着就意识到了贪心算法的存在。然后会意识到，只要找出最大值就好，没必要用数组存储。  
    
tags:    
  -  动态规划  
  -  贪心算法  
