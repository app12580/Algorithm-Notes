### description    
  Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).  
    
  Range Sum Query 2D  
  The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.  
    
  Example:  
  Given matrix = [  
    [3, 0, 1, 4, 2],  
    [5, 6, 3, 2, 1],  
    [1, 2, 0, 1, 5],  
    [4, 1, 0, 1, 7],  
    [1, 0, 3, 0, 5]  
  ]  
    
  sumRegion(2, 1, 4, 3) -> 8  
  sumRegion(1, 1, 2, 2) -> 11  
  sumRegion(1, 2, 2, 4) -> 12  
  Note:  
  You may assume that the matrix does not change.  
  There are many calls to sumRegion function.  
  You may assume that row1 ≤ row2 and col1 ≤ col2.  
### solution    
```    
  class NumMatrix {  
    
      private int[][] dp;  
    
      public NumMatrix(int[][] matrix) {  
          if(matrix.length == 0 || matrix[0].length == 0) return;  
          int m = matrix.length;  
          int n = matrix[0].length;  
          dp = new int[m][n];  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  dp[i][j] = matrix[i][j];  
                  if(i==0&&j==0){  
                        
                  } else if(i == 0) {  
                      dp[i][j] += dp[i][j-1];  
                  } else if(j == 0) {  
                      dp[i][j] += dp[i-1][j];  
                  } else {  
                      dp[i][j] = dp[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];  
                  }  
              }  
          }  
      }  
    
      public int sumRegion(int row1, int col1, int row2, int col2) {  
          if(dp == null) return 0;  
          if(row1 == 0 && col1 == 0) {  
              return dp[row2][col2];  
          } else if(row1 == 0) {  
              return dp[row2][col2] - dp[row2][col1 - 1];  
          } else if(col1 == 0) {  
              return dp[row2][col2] - dp[row1-1][col2];  
          } else {  
              return dp[row2][col2] - dp[row1-1][col2] - dp[row2][col1 - 1] + dp[row1-1][col1-1];  
          }  
      }  
    
  }  
    
  /**  
   * Your NumMatrix object will be instantiated and called as such:  
   * NumMatrix obj = new NumMatrix(matrix);  
   * int param_1 = obj.sumRegion(row1,col1,row2,col2);  
   */  
```    
    
### 个人解读    
  创建一个dp[][]，存储所有从(0,0)到(i,j)点的和。  
  注意数组为null的情况。  
    
tags:    
  -  矩阵  
  -  模拟  
