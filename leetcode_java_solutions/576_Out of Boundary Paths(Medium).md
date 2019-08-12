### description    
  There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.  
    
     
    
  Example 1:  
    
  Input: m = 2, n = 2, N = 2, i = 0, j = 0  
  Output: 6  
  Explanation:  
    
  Example 2:  
    
  Input: m = 1, n = 3, N = 3, i = 0, j = 1  
  Output: 12  
  Explanation:  
    
     
    
  Note:  
    
  Once you move the ball out of boundary, you cannot move it back.  
  The length and height of the grid is in range [1,50].  
  N is in range [0,50].  
### solution    
```    
  class Solution {  
      public int findPaths(int m, int n, int N, int i, int j) {  
          if (N == 0)  
              return 0;  
          long[][][] dp = new long[N][m + 2][n + 2];  
          dp[0][i + 1][j + 1] = 1;    //表示第i步(从0开始算第一步)开始时候，每个点有多少个可能。  
          long res = 0;  
          if (i == 0) res++;  
          if (i == m - 1) res++;  
          if (j == 0) res++;  
          if (j == n - 1) res++;  
          for (int k = 1; k < N; k++) {  
              for (int ii = 1; ii <= m; ii++) {  
                  for (int jj = 1; jj <= n; jj++) {  
                      dp[k][ii][jj] = dp[k - 1][ii - 1][jj] + dp[k - 1][ii + 1][jj] + dp[k - 1][ii][jj - 1] + dp[k - 1][ii][jj + 1];  
                      dp[k][ii][jj] %= 1000000007;      //这一行去掉就会报错  
                      if (ii == 1) res += dp[k][ii][jj];  
                      if (ii == m) res += dp[k][ii][jj];  
                      if (jj == n) res += dp[k][ii][jj];  
                      if (jj == 1) res += dp[k][ii][jj];  
                      res %= 1000000007;  
                  }  
              }  
          }  
          return (int) res;  
      }  
  }  
```    
    
### 个人解读    
  直接通过DP就好了，大不了再加上一个N的维度，一共三维维度。不要总想着通过数学去做简化，没必要。  
  
  关于下面这一行的思考    
  ```  
    dp[k][ii][jj] %= 1000000007;      //这一行去掉就会报错  
  ```  
  一开始以为难道不同步缩小会出错吗?后来验证了一下，是long超限导致的  
  ```  
      if(dp[k][ii][jj] < 0) return -111;  //会返回-111  
  ```  
    
tags:    
  -  dp  
  -  数学  
