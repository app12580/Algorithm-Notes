### description  
  Given an integer matrix, find the length of the longest increasing path.
  
  From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
  
  Example 1:
  
  Input: nums = 
  [
    [9,9,4],
    [6,6,8],
    [2,1,1]
  ] 
  Output: 4 
  Explanation: The longest increasing path is [1, 2, 6, 9].
  Example 2:
  
  Input: nums = 
  [
    [3,4,5],
    [3,2,6],
    [2,2,1]
  ] 
  Output: 4 
  Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
### solution  
```  
// 方法一： DFS+中间存储矩阵
Runtime: 7 ms, faster than 92.76% of Java online submissions for Longest Increasing Path in a Matrix.
Memory Usage: 40.4 MB, less than 89.80% of Java online submissions for Longest Increasing Path in a Matrix.

  class Solution {
     private int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
  
      public int longestIncreasingPath(int[][] matrix) {
          if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
              return 0;
          }
          int m = matrix.length;
          int n = matrix[0].length;
          int[][] dp = new int[m][n];
          int max = 0;
          for(int i = 0; i < m; i++) {
              for(int j = 0; j< n; j++) {
                  max = Math.max(max, dfs(matrix, dp, i, j));
              }
          }
          return max;
      }
  
      // 以ij为起点的链的长度
      private int dfs(int[][] matrix, int[][] dp, int i, int j) {   //error: 一开始没想好这里面存储什么，还额外一个参数preCount导致计数混乱
          if(dp[i][j] != 0) return dp[i][j];
          int next = 0;
          for(int[] d: directions) {
              int x = i + d[0];
              int y = j + d[1];
              if(x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) continue;
              if(matrix[i][j] < matrix[x][y]) {
                  int t = dfs(matrix, dp, x, y);
                  next = Math.max(next, t);
              }
          }
          dp[i][j] = next + 1;
          return next + 1;
      }
  }
```  
  
### 个人解读  
  思路一：DFS
  问题一：标记法还是湮灭法？因为是递增数列，单向的，所以可以直接使用数学法
  
tags:  
  -  DFS
  -  矩阵
