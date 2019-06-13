### description    
  Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.  
    
  Note: You can only move either down or right at any point in time.  
    
  Example:  
    
  Input:  
  [  
    [1,3,1],  
    [1,5,1],  
    [4,2,1]  
  ]  
  Output: 7  
  Explanation: Because the path 1→3→1→1→1 minimizes the sum.  
    
  求矩阵的最短路径和  
### solution    
```    
  class Solution {  
      public int minPathSum(int[][] grid) {  
          int m = grid.length;  
          int n = grid[0].length;  
          for(int j = 1; j < n; j++) {  
              grid[0][j] += grid[0][j-1];  
          }  
          for(int i = 1; i < m; i++) {  
              grid[i][0] += grid[i-1][0];  
          }  
          for(int i = 1; i < m; i++) {  
              for(int j = 1; j < n; j++) {  
                  grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);  
              }  
          }  
          return grid[m-1][n-1];  
      }  
  }  
```    
    
### 个人解读    
  路径和问题，每个格子等于左边和上边两个格子加上本格值。  
  如果是最上一行或者最左一列，单独处理一下就好。  
    
tags:    
  -  矩阵  
  -  动态规划  
