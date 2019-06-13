### description  
  A message containing letters from A-Z is being encoded to numbers using the following mapping:
  
  'A' -> 1
  'B' -> 2
  ...
  'Z' -> 26
  Given a non-empty string containing only digits, determine the total number of ways to decode it.
  
  Example 1:
  
  Input: "12"
  Output: 2
  Explanation: It could be decoded as "AB" (1 2) or "L" (12).
  Example 2:
  
  Input: "226"
  Output: 3
  Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
### solution  
```  
  class Solution {
   public int numDecodings(String s) {
     if(s.charAt(0) == '0') return 0;
          int[] dp = new int[s.length()];
          dp[0] = 1;
          for(int i = 1; i < s.length(); i++) {
              int res = 0;
              int cur = s.charAt(i) - '0';
              int pre = s.charAt(i - 1) - '0';
              int mix = pre *10 + cur;
              if(cur == 0 && (mix == 0 || mix > 26)) {
                  return 0;
              }
              if(cur > 0) {
                  res += dp[i-1];
              }
              if(mix <= 26 && mix >= 10) {
                  if(i > 1) {
                      res += dp[i - 2];
                  } else {
                      res += 1;
                  }
              }
              dp[i] = res;
          }
          return dp[s.length() - 1];
      }
  
  }
```  
  
### 个人解读  
  dp的使用方法二。
  f(n): 
     f(n)=f(n-1)
    if(n和n-1 in [1,26]), f(n) += f(n-2)
    
  试错了好多次，主要都是0的存在问题。
  
tags:  
  -  数组
  -  动态规划
