### description    
  On a 2-dimensional grid, there are 4 types of squares:  
    
  1 represents the starting square.  There is exactly one starting square.  
  2 represents the ending square.  There is exactly one ending square.  
  0 represents empty squares we can walk over.  
  -1 represents obstacles that we cannot walk over.  
  Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.  
    
     
    
  Example 1:  
    
  Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]  
  Output: 2  
  Explanation: We have the following two paths:   
  1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)  
  2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)  
  Example 2:  
    
  Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]  
  Output: 4  
  Explanation: We have the following four paths:   
  1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)  
  2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)  
  3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)  
  4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)  
  Example 3:  
    
  Input: [[0,1],[2,0]]  
  Output: 0  
  Explanation:   
  There is no path that walks over every empty square exactly once.  
  Note that the starting and ending square can be anywhere in the grid.  
     
    
  Note:  
    
  1 <= grid.length * grid[0].length <= 20  
### solution    
```    
  方法一： DFS + 回溯  
  Runtime: 1 ms, faster than 78.88% of Java online submissions for Unique Paths III.  
  Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Unique Paths III.  
    
  class Solution {  
      int ans;  
      int[][] grid;  
      int tr, tc;  
      int[] dr = new int[]{0, -1, 0, 1};  
      int[] dc = new int[]{1, 0, -1, 0};  
      int R, C;  
    
      public int uniquePathsIII(int[][] grid) {  
          this.grid = grid;  
          R = grid.length;  
          C = grid[0].length;  
    
          int todo = 0;  
          int sr = 0, sc = 0;  
          for (int r = 0; r < R; ++r)  
              for (int c = 0; c < C; ++c) {  
                  if (grid[r][c] != -1) {  
                      todo++;  
                  }  
    
                  if (grid[r][c] == 1) {  
                      sr = r;  
                      sc = c;  
                  } else if (grid[r][c] == 2) {  
                      tr = r;  
                      tc = c;  
                  }  
              }  
    
          ans = 0;  
          dfs(sr, sc, todo);  
          return ans;  
      }  
    
      public void dfs(int r, int c, int todo) {  
          todo--;  
          if (todo < 0) return;  
          if (r == tr && c == tc) {  
              if (todo == 0) ans++;  
              return;  
          }  
    
          grid[r][c] = 3;  
          for (int k = 0; k < 4; ++k) {  
              int nr = r + dr[k];  
              int nc = c + dc[k];  
              if (0 <= nr && nr < R && 0 <= nc && nc < C) {  
                  if (grid[nr][nc] % 2 == 0)  
                      dfs(nr, nc, todo);  
              }  
          }  
          grid[r][c] = 0;  
      }  
  }  
     
```    
    
### 个人解读    
    
  DFS+回溯， 使用湮灭法，只能说最意外的是没想到效率这么高。做了这么多Hard题目，DFS+回溯一般都是会TLE的  
    
tags:    
  -  矩阵  
  -  DFS  
  -  回溯  
