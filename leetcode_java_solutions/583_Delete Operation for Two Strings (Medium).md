### description    
  Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.  
    
  Example 1:  
  Input: "sea", "eat"  
  Output: 2  
  Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".  
  Note:  
  The length of given words won't exceed 500.  
  Characters in given words can only be lower-case letters.  
    
   删除两个字符串的字符使它们相等，获取需要删除次数的最小值。  
### solution    
```    
  class Solution {  
      public int minDistance(String word1, String word2) {  
          int m = word1.length();  
          int n = word2.length();  
    
          int[][] dp = new int[m+1][n+1];  
          for(int i = 1; i <= m; i++) {  
              for(int j = 1; j <= n; j++) {  
                  if(word1.charAt(i-1) == word2.charAt(j-1)) {  
                      dp[i][j] = dp[i - 1][j - 1] + 1;  
                  } else {  
                      dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);  
                  }  
              }  
          }  
          return m + n - 2 * dp[m][n];  
      }  
  }  
```    
    
### 个人解读    
  因为遇到两个字母不同的时候，不知道要删除哪一个，所以只能遍历所有或者存储所有了。  
  想到过二维动态规划了，但是并不知道要如何去定义。因为觉得一个数字不够，需要两个数字来分别存储删了几个数字。  
  如果不存储删的个数，而是存储公共子序列的个数会怎么样。性质是一样的，只不过存储公共长度只需要一个量。  
  m - x = n - y = 公共长度；这样原来需要x和y两个变量的就变成了一个公共长度就足够了。  
  像这种二维动态规划问题，只有dp[0][0]=0这一个初始化边界条件。  
  length要不要+1的问题，因为dp需要一个作为临界值，所以很多时候+1是必要的。  
  只需要统计当前进度，没必要统计要删哪些。  
    
tags:    
  -  二维动态规划  
  
