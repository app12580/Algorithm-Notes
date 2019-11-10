### description    
  Given a string S, count the number of distinct, non-empty subsequences of S .  
    
  Since the result may be large, return the answer modulo 10^9 + 7.  
    
     
    
  Example 1:  
    
  Input: "abc"  
  Output: 7  
  Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".  
  Example 2:  
    
  Input: "aba"  
  Output: 6  
  Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".  
  Example 3:  
    
  Input: "aaa"  
  Output: 3  
  Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".  
     
    
     
    
  Note:  
    
  S contains only lowercase letters.  
  1 <= S.length <= 2000  
### solution    
```    
// 方法一： 一维DP  
Runtime: 3 ms, faster than 81.22% of Java online submissions for Distinct Subsequences II.  
Memory Usage: 35.6 MB, less than 100.00% of Java online submissions for Distinct Subsequences II.  
  
  class Solution {  
      public int distinctSubseqII(String S) {  
          int MOD = 1_000_000_007;  
          int N = S.length();  
          int[] dp = new int[N+1];  
          dp[0] = 1;  
    
          int[] last = new int[26];  
          Arrays.fill(last, -1);  
    
          for (int i = 0; i < N; ++i) {  
              int x = S.charAt(i) - 'a';  
              dp[i+1] = dp[i] * 2 % MOD;  
              if (last[x] >= 0)  
                  dp[i+1] -= dp[last[x]];  
              dp[i+1] %= MOD;  
              last[x] = i;  
          }  
    
          dp[N]--;  
          if (dp[N] < 0) dp[N] += MOD;  
          return dp[N];  
      }  
     
  }  
```    
    
### 个人解读    
  和[115](115_Distinct%20Subsequences(Hard).md)对比一下，虽然感觉题目名字一样，但干的事情完全是两回事。  
    
  动态规划，问题是如何建模  
    
  思路一：  
  int[i][j] 表示前i个字符中，以j为结尾的单词个数，int[0].length == 27,j==26时候，表示总量。  
  最后return dp[len][26]  
    
  dp[i][j] = dp[i - 1][j]  
  if(j == cur - 'a') dp[i][j] += dp[i - 1][26] - dp[i-1][j]  
    
  换个写法：  
  if(j == cur - 'a') dp[i][j] = dp[i-1][26] * 2  
  else dp[i][j] = dp[i-1][j]  
    
  这样不行，因为 ajj,ajjj,aj, 在遇到新的j时候，并不知道哪些j结尾的可以新加一个j  
      
      
思路二： 官方解答  
  关键点在于： 每次重复的，比如'b'，重复的数量是上一次b的加入产生数量。  
    
  只要认清重复的字符串哪里来的很关键  
    
tags:    
  -  大数统计  
  -  全部非连续子序列  
  -  动态规划  
