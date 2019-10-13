### description    
  Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.  
    
  '?' Matches any single character.  
  '*' Matches any sequence of characters (including the empty sequence).  
  The matching should cover the entire input string (not partial).  
    
  Note:  
    
  s could be empty and contains only lowercase letters a-z.  
  p could be empty and contains only lowercase letters a-z, and characters like ? or *.  
  Example 1:  
    
  Input:  
  s = "aa"  
  p = "a"  
  Output: false  
  Explanation: "a" does not match the entire string "aa".  
  Example 2:  
    
  Input:  
  s = "aa"  
  p = "*"  
  Output: true  
  Explanation: '*' matches any sequence.  
  Example 3:  
    
  Input:  
  s = "cb"  
  p = "?a"  
  Output: false  
  Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.  
  Example 4:  
    
  Input:  
  s = "adceb"  
  p = "*a*b"  
  Output: true  
  Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".  
  Example 5:  
    
  Input:  
  s = "acdcb"  
  p = "a*c?b"  
  Output: false  
### solution    
```    
//方法一： 二维DP 暴力法  
Runtime: 153 ms, faster than 5.17% of Java online submissions for Wildcard Matching.  
Memory Usage: 36.9 MB, less than 100.00% of Java online submissions for Wildcard Matching.  
  
  class Solution {  
    public boolean isMatch(String s, String p) {  
          if (s == null || p == null) {  
              return false;  
          }  
          boolean[][] dp = new boolean[s.length()+1][p.length()+1];  
          dp[0][0] = true;  
          for (int i = 0; i < p.length(); i++) {  
              if (p.charAt(i) == '*') {  
                  dp[0][i+1] = true;  
              } else {  
                  break;  
              }  
          }  
          for (int i = 0 ; i < s.length(); i++) {  
              for (int j = 0; j < p.length(); j++) {  
                  if (p.charAt(j) == '?') {  
                      dp[i+1][j+1] = dp[i][j];  
                  }  
                  if (p.charAt(j) == s.charAt(i)) {  
                      dp[i+1][j+1] = dp[i][j];  
                  }  
                  if (p.charAt(j) == '*') {  
                      //如果dp[0~i+1][j]有为true的，那么dp[i+1][j+1]为true  
                      for(int k = i+1; k >= 0; k--) {  
                          if(dp[k][j]) {  
                              dp[i+1][j+1] = true;  
                          }  
                      }  
                  }  
              }  
          }  
          return dp[s.length()][p.length()];  
      }  
  }  
    
  // 方法二： 重点贪婪  
   public boolean isMatch(String str, String pattern) {  
          int s = 0, p = 0, match = 0, starIdx = -1;  
          // starIdx *出现的索引  
          // match 与*同时出现的s索引处(*匹配的第一个s索引)  
          //终止条件里面不用管p的么。。。  
          while (s < str.length()){  
              if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){  
              // 两边同时增长一个  
                  s++;  
                  p++;  
              } else if (p < pattern.length() && pattern.charAt(p) == '*'){  
              // 如果此时p是*，值增长p  
                  starIdx = p;  
                  match = s;  
                  p++;  
                  //经过这一步骤以后，p++，s不变，即假设*匹配了空串，继续遍历。  
                  //starIdx只保留最后的，解读：贪婪算法，最后出现的，因为前面是从少到多匹配S的，所以最后的*会匹配最少的字符  
                  //从少到多的好处是不会错过答案  
              } else if (starIdx != -1){  
              //  如果曾经出现过starIdx，此时可以回溯，直到找到s中下一个与p中*后面符合的点  
                  //如果找不到，就会跳出循环了  
                  p = starIdx + 1;  
                  match++;  
                  s = match;  
              } else {  
                  //可能情况： p没了；sp不匹配，并且没有出现过*  
                  return false;  
              }  
          }  
    
          //check for remaining characters in pattern  
          while (p < pattern.length() && pattern.charAt(p) == '*')  
              p++;  
    
          return p == pattern.length();  
      }  
  
```    
    
### 个人解读    
  与[010](010_Regular%20Expression%20Matching(Hard).md)类似，那个题目是匹配'.'和'*'，而本题目是'?'和'*'  
    
  只是'*'的含义是不同的。  
    
  方法二的贪婪算法： 遇到*尽可能的少匹配。  
  整个贪婪算法也是，要让pattern尽可能的少匹配。这样子不会错过答案，如果s多了就去回溯。  
    
    
tags:    
  -  多维DP  
  -  字符串  
  -  贪婪算法  
  -  重点数学  
