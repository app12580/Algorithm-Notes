### description    
  We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?  
    
  Note that our partition must use every number in A, and that scores are not necessarily integers.  
    
  Example:  
  Input:   
  A = [9,1,2,3,9]  
  K = 3  
  Output: 20  
  Explanation:   
  The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.  
  We could have also partitioned A into [9, 1], [2], [3, 9], for example.  
  That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.  
     
    
  Note:  
    
  1 <= A.length <= 100.  
  1 <= A[i] <= 10000.  
  1 <= K <= A.length.  
  Answers within 10^-6 of the correct answer will be accepted as correct.  
### solution    
```    
//方法一：暴力三维DP  
Runtime: 7 ms, faster than 36.91% of Java online submissions for Largest Sum of Averages.  
Memory Usage: 47.2 MB, less than 7.14% of Java online submissions for Largest Sum of Averages.  
  
  class Solution {  
      public double largestSumOfAverages(int[] A, int K) {  
          double[][][] dp = new double[A.length][A.length][K];  
          return dfs(A, dp, 0, A.length - 1, K - 1);  
      }  
    
      private double dfs(int[] A, double[][][] dp, int start, int end, int k) {  
          if(dp[start][end][k] > 0) {  
              return dp[start][end][k];  
          }  
          if(k == 0) {  
              double sum = 0;  
              for(int i = start; i <= end; i++) {  
                  sum += A[i];  
              }  
              double value = sum / (end - start + 1);  
              dp[start][end][k] = value;  
              return value;  
          }  
    
          // 0 5 1  
          double res = 0;  
          for(int i = start; i <= end - k; i++) {  
              double v1 = dp[start][i][0] = dfs(A, dp, start, i, 0);  
              double v2 = dp[i + 1][end][k - 1] = dfs(A, dp, i+1, end, k -1);  
              res = Math.max(res, v1 + v2);  
          }  
          dp[start][end][k] = res;  
          return res;  
      }  
    
  }  
    
  //方法二：优化成二维  
  class Solution {  
        public double largestSumOfAverages(int[] A, int K) {  
          int N = A.length;  
          double[][] memo = new double[N+1][N+1];  
          double cur = 0;  
          for (int i = 0; i < N; ++i) {  
              cur += A[i];  
              memo[i + 1][1] = cur / (i + 1);  
          }  
          return search(N, K, A, memo);  
      }  
    
      public double search(int n, int k, int[] A, double[][] memo) {  
          if (memo[n][k] > 0) return memo[n][k];  
          if (n < k) return 0;  
          double cur = 0;  
          for (int i = n - 1; i > 0; --i) {  
              cur += A[i];  
              memo[n][k] = Math.max(memo[n][k], search(i, k - 1, A, memo) + cur / (n - i));  
          }  
          return memo[n][k];  
      }  
    
  }  
    
   
```    
    
### 个人解读    
  因为觉得需要区分最大值，然后还需要分析区间数目，所以比起来用数学方法各种标记，还不如干脆一个二维DP算了。  
  分析了一下，二维不够，需要三维，start，end，和k  
    
  优化成二维，主要是固定start=0，然后只有end和k两个维度  
    
tags:    
  -  多维DP  
  -  数组  
