### description    
  Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.  
    
  Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)  
    
  Example 1:  
    
  [[0,0,1,0,0,0,0,1,0,0,0,0,0],  
   [0,0,0,0,0,0,0,1,1,1,0,0,0],  
   [0,1,1,0,1,0,0,0,0,0,0,0,0],  
   [0,1,0,0,1,1,0,0,1,0,1,0,0],  
   [0,1,0,0,1,1,0,0,1,1,1,0,0],  
   [0,0,0,0,0,0,0,0,0,0,1,0,0],  
   [0,0,0,0,0,0,0,1,1,1,0,0,0],  
   [0,0,0,0,0,0,0,1,1,0,0,0,0]]  
  Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.  
  Example 2:  
    
  [[0,0,0,0,0,0,0,0]]  
  Given the above grid, return 0.  
  Note: The length of each dimension in the given grid does not exceed 50.  
    
  查找最大的连通面积  
### solution    
```    
  class Solution {  
       private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  
      int m,n;  
    
      public int maxAreaOfIsland(int[][] grid) {  
          m = grid.length;  
          n = grid[0].length;  
          int max = 0;  
            
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  max = Math.max(dfs(grid, i, j), max);                  
              }  
          }  
          return max;  
      }  
    
      private int dfs(int[][] grid, int i, int j) {  
          if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {  
              return 0;  
          }  
          int res = 1;  
          grid[i][j] = 0;  
          for(int[] d: direction) {  
              res += dfs(grid, i + d[0], j + d[1]);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  使用DFS获取最大面积  
  防止重复计算的方法：湮灭法。  
    
tags:    
  -  DFS  
