### description    
  Given a string S and a string T, count the number of distinct subsequences of S which equals T.  
    
  A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).  
    
  Example 1:  
    
  Input: S = "rabbbit", T = "rabbit"  
  Output: 3  
  Explanation:  
    
  As shown below, there are 3 ways you can generate "rabbit" from S.  
  (The caret symbol ^ means the chosen letters)  
    
  rabbbit  
  ^^^^ ^^  
  rabbbit  
  ^^ ^^^^  
  rabbbit  
  ^^^ ^^^  
  Example 2:  
    
  Input: S = "babgbag", T = "bag"  
  Output: 5  
  Explanation:  
    
  As shown below, there are 5 ways you can generate "bag" from S.  
  (The caret symbol ^ means the chosen letters)  
    
  babgbag  
  ^^ ^  
  babgbag  
  ^^    ^  
  babgbag  
  ^    ^^  
  babgbag  
    ^  ^^  
  babgbag  
      ^^^  
### solution    
```    
//方法一： 多维DP  
  
Runtime: 5 ms, faster than 70.74% of Java online submissions for Distinct Subsequences.  
Memory Usage: 35.8 MB, less than 92.31% of Java online submissions for Distinct Subsequences.  
  
  
  class Solution {  
       public int numDistinct(String s, String t) {  
          int m = s.length();  
          int n = t.length();  
          int[][] dp = new int[m + 1][n + 1];  
          for(int i = 0; i < m; i++) {  
              dp[i][0] = 1;  
          }  
          for (int j = 0; j < n; j++) {  
              for (int i = 0; i < m; i++) {                  
                  if (s.charAt(i) == t.charAt(j)) {  
                      dp[i + 1][j + 1] = dp[i][j + 1] + dp[i][j];  
                  } else {  
                      dp[i + 1][j + 1] = dp[i][j + 1];  
                  }  
              }  
          }  
           
          return dp[m][n];  
      }  
  }  
```    
    
### 个人解读    
  多维DP的思路是没错的，主要是转移方程的建立：  
  1、如果两字母不同：  
     那么当前[i][j]的进度和[i-1][j]的进度是一样子的(考虑到了遍历方向)  
  2、如果两字母相同：  
     分成两部分：当前j进度已经有的dp[i-][j] + 新出来的个数dp[i-1][j-1]  
    
tags:    
  -  多维DP  
  -  数学  
