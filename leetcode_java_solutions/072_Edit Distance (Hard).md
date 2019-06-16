### description    
  Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.  
    
  You have the following 3 operations permitted on a word:  
    
  Insert a character  
  Delete a character  
  Replace a character  
  Example 1:  
    
  Input: word1 = "horse", word2 = "ros"  
  Output: 3  
  Explanation:   
  horse -> rorse (replace 'h' with 'r')  
  rorse -> rose (remove 'r')  
  rose -> ros (remove 'e')  
  Example 2:  
    
  Input: word1 = "intention", word2 = "execution"  
  Output: 5  
  Explanation:   
  intention -> inention (remove 't')  
  inention -> enention (replace 'i' with 'e')  
  enention -> exention (replace 'n' with 'x')  
  exention -> exection (replace 'n' with 'c')  
  exection -> execution (insert 'u')  
### solution    
```    
  class Solution {  
      public int minDistance(String word1, String word2) {  
            int m = word1.length();  
          int n = word2.length();  
          int[][] dp = new int[m + 1][n + 1];  
          for(int i = 1; i <= m; i++) {  
              dp[i][0] = i;  
          }  
          for(int j = 1; j <= n; j++) {  
              dp[0][j] = j;  
          }  
          for(int i = 1; i <= m; i++) {  
              for(int j = 1; j <= n; j++) {  
                  if(word1.charAt(i-1) == word2.charAt(j-1)) {  
                      dp[i][j] = dp[i-1][j-1];  
                  } else {  
                      dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;  
                  }  
              }  
          }  
          return dp[m][n];  
      }  
  }  
```    
    
### 个人解读    
  二维动态规划，主要在于状态转移方程如何写。  
  如果s1[i] == s2[j] 那么dp[i][j] = dp[i-1][j-1]  
  如果s1[i] != s2[j] 那么dp[i][j]:  
  插入or删除操作: dp[i][j-1] + 1 || dp[i-1][j] + 1   
  替换操作： dp[i-1][j-1] + 1  
  注意初始化，[i][0] = i 和 [0][j] = j  
    
  想出来状态转移方程以后，基本上题目已经解出来了。  
    
tags:    
  -  二维动态规划  
