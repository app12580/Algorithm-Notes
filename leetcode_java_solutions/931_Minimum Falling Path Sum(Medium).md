### description    
  Given a square array of integers A, we want the minimum sum of a falling path through A.  
    
  A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.  
    
     
    
  Example 1:  
    
  Input: [[1,2,3],[4,5,6],[7,8,9]]  
  Output: 12  
  Explanation:   
  The possible falling paths are:  
  [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]  
  [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]  
  [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]  
  The falling path with the smallest sum is [1,4,7], so the answer is 12.  
    
     
    
  Note:  
    
  1 <= A.length == A[0].length <= 100  
  -100 <= A[i][j] <= 100  
### solution    
```    
Runtime: 5 ms, faster than 19.10% of Java online submissions for Minimum Falling Path Sum.  
Memory Usage: 40 MB, less than 33.33% of Java online submissions for Minimum Falling Path Sum.  
  
  class Solution {  
      public int minFallingPathSum(int[][] A) {  
           int m = A.length;   
          int n = A[0].length;  
          int[][] dp = new int[m][n];  
          for(int i = m - 1; i >= 0; i--) {  
              for(int j = 0; j < n; j++) {  
                  if(i == m - 1) {  
                      dp[i][j] = A[i][j];  
                  } else {  
                      int val = A[i][j];  
                      dp[i][j] = val + dp[i + 1][j];  
                      if(j != 0) {  
                          dp[i][j] = Math.min(dp[i][j], val + dp[i + 1][j - 1]);  
                      }  
                      if(j != n - 1) {  
                          dp[i][j] = Math.min(dp[i][j], val + dp[i + 1][j + 1]);  
                      }  
                  }  
              }  
          }  
          int res = dp[0][0];  
          for(int i = 1; i < n; i++) {  
              res = Math.min(res, dp[0][i]);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  关键点在于，后排的结果与前排无关，无论从哪里掉下来的[2,2]，后面的结果是一致的，所以可以采用dp解决。  
    
  优化思路：不新建int[][]，直接单向修改，在原来基础上改变数据，一层层向上。  
    
tags:    
  -  动态规划  
