### description    
  Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.  
    
  A subsequence of a string S is obtained by deleting 0 or more characters from S.  
    
  A sequence is palindromic if it is equal to the sequence reversed.  
    
  Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.  
    
  Example 1:  
  Input:   
  S = 'bccb'  
  Output: 6  
  Explanation:   
  The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.  
  Note that 'bcb' is counted only once, even though it occurs twice.  
  Example 2:  
  Input:   
  S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'  
  Output: 104860361  
  Explanation:   
  There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.  
  Note:  
    
  The length of S will be in the range [1, 1000].  
  Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.  
### solution    
```    
  // 解法一：   
  Runtime: 77 ms, faster than 31.01% of Java online submissions for Count Different Palindromic Subsequences.  
  Memory Usage: 71.6 MB, less than 33.33% of Java online submissions for Count Different Palindromic Subsequences.  
  class Solution {  
    public int countPalindromicSubsequences(String S) {  
      int n = S.length();  
      int mod = 1000000007;  
      int[][][] dp = new int[4][n][n];  
    
      for (int i = n-1; i >= 0; --i) {  
        for (int j = i; j < n; ++j) {  
          for (int k = 0; k < 4; ++k) {  
            char c = (char) ('a' + k);  
            if (j == i) {  
              if (S.charAt(i) == c) dp[k][i][j] = 1;  
              else dp[k][i][j] = 0;  
            } else { // j > i  
              if (S.charAt(i) != c) dp[k][i][j] = dp[k][i+1][j];  
              else if (S.charAt(j) != c) dp[k][i][j] = dp[k][i][j-1];  
              else { // S[i] == S[j] == c  
                if (j == i+1) dp[k][i][j] = 2; // "aa" : {"a", "aa"}  
                else { // length is > 2  
                  dp[k][i][j] = 2;  
                  for (int m = 0; m < 4; ++m) { // count each one within subwindows [i+1][j-1]  
                    dp[k][i][j] += dp[m][i+1][j-1];  
                    dp[k][i][j] %= mod;  
                  }  
                }  
              }  
            }  
          }  
        }  
      }  
    
      int ans = 0;  
      for (int k = 0; k < 4; ++k) {  
        ans += dp[k][0][n-1];  
        ans %= mod;  
      }  
    
      return ans;  
    }  
  }   
    
  // 解法二：  
  Runtime: 22 ms, faster than 96.59% of Java online submissions for Count Different Palindromic Subsequences.  
  Memory Usage: 45 MB, less than 100.00% of Java online submissions for Count Different Palindromic Subsequences.  
      
  class Solution {  
      int[][] memo, prv, nxt;  
      byte[] A;  
      int MOD = 1_000_000_007;  
    
      public int countPalindromicSubsequences(String S) {  
          int N = S.length();  
          prv = new int[N][4];  
          nxt = new int[N][4];  
          memo = new int[N][N];  
          for (int[] row: prv) Arrays.fill(row, -1);  
          for (int[] row: nxt) Arrays.fill(row, -1);  
    
          A = new byte[N];  
          int ix = 0;  
          for (char c: S.toCharArray()) {  
              A[ix++] = (byte) (c - 'a');  
          }  
    
          int[] last = new int[4];  
          Arrays.fill(last, -1);  
          for (int i = 0; i < N; ++i) {  
              last[A[i]] = i;  
              for (int k = 0; k < 4; ++k)  
                  prv[i][k] = last[k];  
          }  
    
          Arrays.fill(last, -1);  
          for (int i = N-1; i >= 0; --i) {  
              last[A[i]] = i;  
              for (int k = 0; k < 4; ++k)  
                  nxt[i][k] = last[k];  
          }  
    
          return dp(0, N-1) - 1;  
      }  
    
      public int dp(int i, int j) {  
          if (memo[i][j] > 0) return memo[i][j];  
          int ans = 1;  
          if (i <= j) {  
              for (int k = 0; k < 4; ++k) {  
                  int i0 = nxt[i][k];  
                  int j0 = prv[j][k];  
                  if (i <= i0 && i0 <= j) ans++;    // 至少包含k这个长度为1的回文字符串  
                  if (-1 < i0 && i0 < j0) ans += dp(i0 + 1, j0 - 1);    // 如果i0 < j0 说明里面至少有两个k，  
                  if (ans >= MOD) ans -= MOD;  
              }  
          }  
          memo[i][j] = ans; // 如果i > j 说明只有aa这种长度为2的回文，调用前，恰好i0 == j0 - 1  
          return ans;  
      }  
  }   
```    
    
### 个人解读    
  思路一： 感觉使用DP，问题是，当加入一个新的字符串后，如何与前面的获得联系？  
  猜测：可能用零件式DP，用一个Map存储所有String的可能情况  
  另外字符之间索引的前后关系也很重要。  
    
  官方解答：https://leetcode-cn.com/problems/count-different-palindromic-subsequences/solution/tong-ji-bu-tong-hui-wen-zi-zi-fu-chuan-by-leetcode/  
    
  解法一：  
  1、dp[k][i][j]: 表示从区间[i,j]范围内，所有起始字符为k的结果个数  
  2、找出转移公式  
  如果 S[i] != 'a'+x，则 dp[x][i][j] = dp[x][i+1][j]  
  如果 S[j] != 'a'+x，则 dp[x][i][j] = dp[x][i][j-1]  
  如果 S[i] == S[j] == 'a'+x，则 dp[x][i][j] = 2 + dp[0][i+1][j-1] + dp[1][i+1][j-1] + dp[2][i+1][j-1] + dp[3][i+1][j-1]。当第一个和最后一个字符相同时，我们需要计算子串 S[i+1][j-1] 中所有不同的回文（a、b、c、d 中的每一个）加上第一个和最后一个字符的两个回文。  
     
  3、因为DP方向是向i+1,j-1方向扩散的，所以i从n-1开始遍历，j因为本身要大于i，所以j从i开始遍历  
    
  解法二：  
  优化成二维，省去了k的判断  
    
    
  总结：根据只有abcd四个字符进行DP建模  
  1、都是递进式, 用[i,j]的范围进行描述  
  2、解法一是把四种分开计算  
    
    
tags:    
  -  字符串  
  -  回文  
  -  大数统计  
  -  全部非空子序列  
