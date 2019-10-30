### description    
  There is a strange printer with the following two special requirements:  
    
  The printer can only print a sequence of the same character each time.  
  At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.  
  Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.  
    
  Example 1:  
  Input: "aaabbb"  
  Output: 2  
  Explanation: Print "aaa" first and then print "bbb".  
  Example 2:  
  Input: "aba"  
  Output: 2  
  Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.  
  Hint: Length of the given string will not exceed 100.  
### solution    
```    
Runtime: 23 ms, faster than 23.64% of Java online submissions for Strange Printer.  
Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Strange Printer.  
  
  
  class Solution {  
       public int strangePrinter(String s) {  
          if (s == null || s.length() == 0) {  
              return 0;  
          }  
            
          int n = s.length();  
          int[][] dp = new int[n][n];  
          for (int i = 0; i < n; i++) {  
              dp[i][i] = 1;  
              if (i < n - 1) {  
                  dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 1 : 2;  
              }  
          }  
            
          for (int len = 2; len < n; len++) {  
              for (int start = 0; start + len < n; start++) {  
                  dp[start][start + len] = len + 1;  
                  for (int k = 0; k < len; k++) {  
                      int temp = dp[start][start + k] + dp[start + k + 1][start + len];  
                      dp[start][start + len] = Math.min(  
                          dp[start][start + len],  
                          s.charAt(start + k) == s.charAt(start + len) ? temp - 1 : temp  
                      );  
                  }  
              }  
          }  
            
          return dp[0][n - 1];  
      }  
  }  
```    
    
### 个人解读    
  简单分析一下，只获取第一次或者最后一次出现明显不够。  
  如果无脑DFS明显指数复杂度，考虑一下贪婪算法，如果没有就只能DP了  
    
  DP模型的考虑  
  前i个字符需要的印刷次数，同时为了和后面的联系起来。  
  int[][] dp = new int[n + 1][26]  
  第二个下标表示字符j在最后一格出现过。  
    
  一个简单的优化，连续的字符不影响结果  
    
  思路二：  
  https://leetcode.com/problems/strange-printer/discuss/106810/Java-O(n3)-DP-Solution-with-Explanation-and-Simple-Optimization  
  int[][] dp = new int[n][n] 表示[i,j]这些字符串的最小操作  
  状态转移方程：   
  s.charAt(start + k) == s.charAt(start + len) ? temp - 1 : temp  
  整体等于两边的相加，如果前半部分最后一个与后半段的最后一个相等。那么减1.  
    
    
  思路一： wrong answer  
  算了，DP失败，果然Hard题目还是很看Dp模型的啊。。。  
    
  ```  
  class Solution {  
     public int strangePrinter(String s) {  
         if(s == null || s.length() == 0) return 0;  
          int[][] dp = new int[s.length()][26];  
          for (int i = 0; i < s.length(); i++) {  
              int cur = s.charAt(i) - 'a';  
              if (i == 0) {  
                  for (int j = 0; j < 26; j++) {  
                      dp[0][j] = 2;  
                  }  
                  dp[0][cur] = 1;  
              } else {  
                  int pre = s.charAt(i - 1) - 'a';  
    
                  if (s.charAt(i) == s.charAt(i - 1)) {  
                      for (int j = 0; j < 26; j++) {  
                          dp[i][j] = dp[i - 1][j];  
                      }  
                  } else {  
                      for (int j = 0; j < 26; j++) {  
                          dp[i][j] = dp[i - 1][j] + 1;  
                      }  
                      dp[i][cur] = Math.min(dp[i][cur], dp[i - 1][cur]);  
                  }  
              }  
          }  
          return dp[s.length() - 1][s.charAt(s.length() - 1) - 'a'];  
      }  
  }  
  ```  
  ```  
  Input  "tbgtgb"  
  Output  5  
  Expected  4  
  ```  
  tttt  
  tbbbbb  
  tbgggb  
  tbgtgb  
    
tags:    
  -    
