### description  
  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
  
  The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
  
  How many possible unique paths are there?
  
  
  Above is a 7 x 3 grid. How many possible unique paths are there?
  
  Note: m and n will be at most 100.
  
  Example 1:
  
  Input: m = 3, n = 2
  Output: 3
  Explanation:
  From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
  1. Right -> Right -> Down
  2. Right -> Down -> Right
  3. Down -> Right -> Right
  Example 2:
  
  Input: m = 7, n = 3
  Output: 28
  
  从左上到右下的路径和
### solution  
```  
// 方法一 累加法： 速度快但是内存高
  class Solution {
      public int uniquePaths(int m, int n) {
          int[][] grid = new int[m][n];
          for(int i = 0; i < m; i++) {
              grid[i][0] = 1;
          }
          for(int j = 0; j < n; j++) {
              grid[0][j] = 1;
          }
          for(int i = 1; i < m; i++) {
              for(int j = 1; j < n; j++) {
                  grid[i][j] += grid[i-1][j] + grid[i][j-1];
              }
          }
          return grid[m-1][n-1];
      }
  }
```  
  
### 个人解读  
  路径和问题，每个格子等于左边和上边两个格子加上本格值。  
  如果是最上一行或者最左一列，单独处理一下就好。  
  一种是一步步累加求和，另一种做法使用二项式分布计算。
  
tags:  
  -  矩阵
  -  动态规划
  -  排列组合
