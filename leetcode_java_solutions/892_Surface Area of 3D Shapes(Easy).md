### description    
  On a N * N grid, we place some 1 * 1 * 1 cubes.  
    
  Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).  
    
  Return the total surface area of the resulting shapes.  
    
     
    
  Example 1:  
    
  Input: [[2]]  
  Output: 10  
  Example 2:  
    
  Input: [[1,2],[3,4]]  
  Output: 34  
  Example 3:  
    
  Input: [[1,0],[0,2]]  
  Output: 16  
  Example 4:  
    
  Input: [[1,1,1],[1,0,1],[1,1,1]]  
  Output: 32  
  Example 5:  
    
  Input: [[2,2,2],[2,1,2],[2,2,2]]  
  Output: 46  
     
    
  Note:  
    
  1 <= N <= 50  
  0 <= grid[i][j] <= 50  
    
  三维形体的表面积  
### solution    
```    
  class Solution {  
      public int surfaceArea(int[][] grid) {  
          int res = 0;  
          int m = grid.length;  
          int n = grid[0].length;  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  int val = grid[i][j];  
                  if(val == 0) {  
                      continue;  
                  }  
                  res += 2;  
                  int ii = i == 0 ? 0 : grid[i-1][j];  
                  if(val > ii) {  
                      res += (val - ii) * 2;  
                  }  
                  int jj = j == 0 ? 0 : grid[i][j-1];  
                  if(val > jj) {  
                      res += (val - jj) * 2;  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  比投影面积更复杂，因为多了内部的面积。  
    
  要么整体法，要么局部法，感觉局部法更靠谱一些，每增加一个，计算表面积增加了多少。  
    
  每次只和左上两个格子比较。  
  俯视图的面积是固定加2的，然后左上每多出来一个正方体，表面积+2。需要脑补一下多出来的部分。  
  这个规律也适用于左上两个边缘的地方。  
    
tags:    
  -  矩阵  
  -  数学  
