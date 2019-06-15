### description    
  In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.  
    
  For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.  
    
  Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.  
    
  Note:  
    
  The given numbers of 0s and 1s will both not exceed 100  
  The size of given string array won't exceed 600.  
     
    
  Example 1:  
    
  Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3  
  Output: 4  
    
  Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”  
     
    
  Example 2:  
    
  Input: Array = {"10", "0", "1"}, m = 1, n = 1  
  Output: 2  
    
  Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".  
    
  01 字符构成最多的字符串  
### solution    
```    
  class Solution {  
      public int findMaxForm(String[] strs, int m, int n) {  
         //m个0，n个1  
          int[][] dp = new int[m+1][n+1];  
          int res = 0;  
          for(String s: strs) {  
              int zeros = 0;  
              int ones = 0;  
              for(char ch: s.toCharArray()) {  
                  if(ch == '0'){  
                      zeros++;  
                  } else if(ch == '1') {  
                      ones++;  
                  }  
              }  
    
              for(int i = m; i >= zeros; i--) {  
                  for(int j = n; j >= ones; j--) {  
                      dp[i][j] = Math.max(dp[i][j], dp[i-zeros][j-ones] + 1);  
                      res = Math.max(res, dp[i][j]);  
                  }  
              }  
    
          }  
          return dp[m][n];  
      }  
  }  
```    
    
### 个人解读    
  双背包问题  
  从这题引发的疑问，dp[m][n]是否有解，还是说要遍历一遍dp[m][n]。  
    
tags:    
  -  动态规划  
  -  背包问题  
