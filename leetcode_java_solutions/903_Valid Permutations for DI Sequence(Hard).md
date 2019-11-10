### description    
  We are given S, a length n string of characters from the set {'D', 'I'}. (These letters stand for "decreasing" and "increasing".)  
    
  A valid permutation is a permutation P[0], P[1], ..., P[n] of integers {0, 1, ..., n}, such that for all i:  
    
  If S[i] == 'D', then P[i] > P[i+1], and;  
  If S[i] == 'I', then P[i] < P[i+1].  
  How many valid permutations are there?  Since the answer may be large, return your answer modulo 10^9 + 7.  
    
   Example 1:  
     
   Input: "DID"  
   Output: 5  
   Explanation:   
   The 5 valid permutations of (0, 1, 2, 3) are:  
   (1, 0, 3, 2)  
   (2, 0, 3, 1)  
   (2, 1, 3, 0)  
   (3, 0, 2, 1)  
   (3, 1, 2, 0)  
      
     
   Note:  
     
   1 <= S.length <= 200  
   S consists only of characters from the set {'D', 'I'}.  
     
   题目描述：英文说得思维真的好难理解。。。看示例即可。  
### solution    
```    
  // 方法一： DP  
    
  Runtime: 35 ms, faster than 43.24% of Java online submissions for Valid Permutations for DI Sequence.  
  Memory Usage: 36 MB, less than 100.00% of Java online submissions for Valid Permutations for DI Sequence.  
    
  class Solution {  
    public int numPermsDISequence(String S) {  
          int n = S.length(), m = (int) (1e9 + 7);  
          int[][] dp = new int[n+1][n+1]; //表示区间[0,i]符合前面的要求构建的数量，并且末尾数字是j  
          dp[0][0] = 1;  
          for(int i = 1; i <= n; i++)  
              for(int j = 0; j <= i; j++)  
                  if(S.charAt(i - 1) == 'D')  
                      for(int k = j; k <= i-1; k++)  
                          dp[i][j] = dp[i][j]%m + dp[i-1][k]%m;  
                  else  
                      for(int k = 0; k <= j-1; k++)  
                          dp[i][j] = dp[i][j]%m + dp[i-1][k]%m;  
          int res = 0;  
          for(int i = 0; i <= n; i++)  
              res = res%m + dp[n][i]%m;  
          return res%m;  
      }  
    
  }  
```    
    
### 个人解读    
  首先有一个感觉，很大程度上，与数字无关，大概率不需要通过全部数字构造去呈现，而是一个数学公式求和来计算。  
    
  思路一：构造一个DP模型，从而通过拼接式或者递进式，让两段结果拼接起来。  
  DI 与DI拼接，需要第一个I后面的数字可以D一下。  
    
  这题目好难啊。。。主要是DP方程很复杂，需要纸笔进行分析。  
    
  https://leetcode.com/problems/valid-permutations-for-di-sequence/discuss/196939/Easy-to-understand-solution-with-detailed-explanation  
    
  递进式DP，每新增一个数字时候，去根据前面的已有结果进行扩展。从而实现DP转移方程  
    
tags:    
  -  重点DP    
