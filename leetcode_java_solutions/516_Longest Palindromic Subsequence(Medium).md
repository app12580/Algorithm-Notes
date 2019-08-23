### description    
  Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.  
    
  Example 1:  
  Input:  
    
  "bbbab"  
  Output:  
  4  
  One possible longest palindromic subsequence is "bbbb".  
  Example 2:  
  Input:  
    
  "cbbd"  
  Output:  
  2  
  One possible longest palindromic subsequence is "bb".  
### solution    
```    
  
Runtime: 20 ms, faster than 87.05% of Java online submissions for Longest Palindromic Subsequence.  
Memory Usage: 47.8 MB, less than 5.55% of Java online submissions for Longest Palindromic Subsequence.  
  
  class Solution {  
       public int longestPalindromeSubseq(String s) {  
          if(s.length() == 0) {  
              return 0;  
          }  
          char[] chars = s.toCharArray();  
          int len = s.length();  
          int[][] dp = new int[len][len];  
          for(int i = 0; i < len; i++) {        //j是右边界，i是左边界  
              dp[i][i] = 1;  
              for(int j = i-1; j>=0; j--) {  
                  if(chars[i] == chars[j]) {  
                      dp[j][i] = dp[j + 1][i - 1] + 2;  
                  } else {  
                      dp[j][i] = Math.max(dp[j+1][i], dp[j][i-1]);  
                  }  
              }  
          }  
          return dp[0][len - 1];  
      }  
  }  
```    
    
### 个人解读    
  重点题目类型，应该属于一个非常实用并且常见的题目。  
    
  字符串回文，有两点经验：  
  1、至少需要一个帮助方法，判断字符串是否是回文  
  2、回文遍历，可以从中间向两边出发  
    
  //超时算法：  
  ```  
  class Solution {  
       private int max = 1;  
      public int longestPalindromeSubseq(String s) {  
          if(s.length() == 0) {  
              return 0;  
          }  
          char[] chars = s.toCharArray();  
          for(int i = 0; i < s.length(); i++) {  
              dfs(chars, i - 1, i + 1, 1);  
              if(i > 0 && chars[i] == chars[i - 1]) {  
                  max = Math.max(max, 2);  
                  dfs(chars, i - 2, i + 1, 2);  
              }  
          }  
          return max;  
      }  
    
      private void dfs(char[] chars, int left, int right, int alreadyCount) {  
          if(left < 0 || right >= chars.length) return;  
          if( chars[left] == chars[right]) {  
              max = Math.max(max, alreadyCount + 2);  
              dfs(chars, left - 1, right + 1, alreadyCount + 2);  
          } else {  
              dfs(chars, left - 1, right + 1, alreadyCount);  
              dfs(chars, left, right + 1, alreadyCount);  
              dfs(chars, left - 1, right, alreadyCount);  
          }  
      }  
  }  
  ```  
    
  优化，通过一个二维dp数组来保存中间结果。  
  类似于背包问题的优化思想，从小到大扩散，方向性十分重要。  
  因此两个for循环的方向性很关键，不能无脑都是j++  
    
tags:    
  -  动态规划  
  -  定向循环  
