### description    
  Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.  
    
  Example 1:  
    
  Input:  
  A: [1,2,3,2,1]  
  B: [3,2,1,4,7]  
  Output: 3  
  Explanation:   
  The repeated subarray with maximum length is [3, 2, 1].  
     
    
  Note:  
    
  1 <= len(A), len(B) <= 1000  
  0 <= A[i], B[i] < 100  
### solution    
```    
  
Runtime: 51 ms, faster than 24.68% of Java online submissions for Maximum Length of Repeated Subarray.  
Memory Usage: 50.2 MB, less than 80.00% of Java online submissions for Maximum Length of Repeated Subarray.  
  
  class Solution {  
      public int findLength(int[] A, int[] B) {  
      int m = A.length;  
          int n = B.length;  
          int[][] dp = new int[m+1][n+1];  
          int res = 0;  
          for(int i = 1; i <= m; i++) {  
              for(int j = 1; j <= n; j++) {  
                  if(A[i - 1] == B[j - 1]) {  
                      dp[i][j] = dp[i-1][j-1] + 1;  
                      if(dp[i][j] > res) {  
                          res = dp[i][j];  
                      }  
                  }  
              }  
          }  
    
          return res;  
      }  
  }  
```    
    
### 个人解读    
  典型的二维dp问题，应该套用模板即可  
    
  反思：  
  subarray 表示重复的子区间。  
    
  二维DP模板二： ij表示为结束端点  
    
tags:    
  -  二维DP  
  -  代码模板  
