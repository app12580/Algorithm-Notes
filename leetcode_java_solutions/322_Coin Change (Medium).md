### description    
  You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.  
    
  Example 1:  
    
  Input: coins = [1, 2, 5], amount = 11  
  Output: 3   
  Explanation: 11 = 5 + 5 + 1  
  Example 2:  
    
  Input: coins = [2], amount = 3  
  Output: -1  
  Note:  
  You may assume that you have an infinite number of each kind of coin.  
    
  找零钱的最少硬币数  
### solution    
```    
  class Solution {  
      public int coinChange(int[] coins, int amount) {  
            if (amount == 0 || coins == null || coins.length == 0) {  
          return 0;  
      }  
         int[] dp = new int[amount + 1];  
          for(int coin: coins) {  
              for(int j = coin; j <= amount; j++) {  
                  if(j == coin) {  
                      dp[j] = 1;  
                  } else if(dp[j] == 0 && dp[j - coin] != 0){  
                      dp[j] = dp[j - coin] + 1;  
                  } else if(dp[j] == 0 && dp[j - coin] == 0){  
  //                    dp[j] = 0;  
                  } else if(dp[j] != 0 && dp[j - coin] == 0){  
  //                    dp[j] = dp[j];  
                  } else if(dp[j] != 0 && dp[j - coin] != 0){  
                      dp[j] = Math.min(dp[j], dp[j - coin] + 1);  
                  }  
              }  
          }  
          return dp[amount] == 0 ? -1 : dp[amount];  
      }  
  }  
```    
    
### 个人解读    
  完全背包问题  
  分类讨论想不清楚就全写出来就OK了，求min的时候难免回合0有很多牵扯。  
    
tags:    
  -  动态规划  
  -  完全背包  
  
  
