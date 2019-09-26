### description      
  On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.    
      
  Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.    
      
  Now, we walk in a clockwise spiral shape to visit every position in this grid.     
      
  Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)     
      
  Eventually, we reach all R * C spaces of the grid.    
      
  Return a list of coordinates representing the positions of the grid in the order they were visited.    
      
       
      
  Example 1:    
      
  Input: R = 1, C = 4, r0 = 0, c0 = 0    
  Output: [[0,0],[0,1],[0,2],[0,3]]    
      
      
       
      
  Example 2:    
      
  Input: R = 5, C = 6, r0 = 1, c0 = 4    
  Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]    
      
      
       
      
  Note:    
      
  1 <= R <= 100    
  1 <= C <= 100    
  0 <= r0 < R    
  0 <= c0 < C    
### solution      
```      
    
//方法一 暴力法    
Runtime: 4 ms, faster than 62.24% of Java online submissions for Spiral Matrix III.    
Memory Usage: 38.5 MB, less than 12.50% of Java online submissions for Spiral Matrix III.    
    
  class Solution {    
         public int[][] spiralMatrixIII(int R, int C, int x, int y) {    
          int[][] res = new int[R * C][2];    
          int dx = 0, dy = 1, n = 0, tmp;    
          for (int j = 0; j < R * C; ++n) {    
              for (int i = 0; i < n / 2 + 1; ++i) {    
                  if (0 <= x && x < R && 0 <= y && y < C)    
                      res[j++] = new int[] {x, y};    
                  x += dx;    
                  y += dy;    
              }    
              tmp = dx;    
              dx = dy;    
              dy = -tmp;    
          }    
          return res;    
      }    
  }    
```      
      
### 个人解读      
  有点束手无策额。。。。    
      
  思路一： 分两步走，先遍历出来正方形，然后再遍历L形。    
      
  反思：    
  又犯了那个毛病，总想着最优化。    
      
  本题目可以使用暴力法，直接往大了遍历，遍历超了以后也不做额外处理，时间浪费就浪费了吧。    
  以后要反省自己，能暴力法就先试一下。    
      
tags:      
  -  矩阵    
