### description    
  On a N * N grid, we place some 1 * 1 * 1 cubes that are axis-aligned with the x, y, and z axes.  
    
  Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).  
    
  Now we view the projection of these cubes onto the xy, yz, and zx planes.  
    
  A projection is like a shadow, that maps our 3 dimensional figure to a 2 dimensional plane.   
    
  Here, we are viewing the "shadow" when looking at the cubes from the top, the front, and the side.  
    
  Return the total area of all three projections.  
    
     
    
  Example 1:  
    
  Input: [[2]]  
  Output: 5  
  Example 2:  
    
  Input: [[1,2],[3,4]]  
  Output: 17  
  Explanation:   
  Here are the three projections ("shadows") of the shape made with each axis-aligned plane.  
    
  Example 3:  
    
  Input: [[1,0],[0,2]]  
  Output: 8  
  Example 4:  
    
  Input: [[1,1,1],[1,0,1],[1,1,1]]  
  Output: 14  
  Example 5:  
    
  Input: [[2,2,2],[2,1,2],[2,2,2]]  
  Output: 21  
     
    
  Note:  
    
  1 <= grid.length = grid[0].length <= 50  
  0 <= grid[i][j] <= 50  
    
  返回三视图总面积  
### solution    
```    
  class Solution {  
      public int projectionArea(int[][] grid) {  
           int m = grid.length;  
          int n = grid[0].length;  
            
          int res = 0;  
          int[] maxI = new int[m];  
          int[] maxJ = new int[n];  
            
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  int val = grid[i][j];  
                  if(val != 0) res++;  
                  maxI[i] = Math.max(maxI[i], val);  
                  maxJ[j] = Math.max(maxJ[j], val);  
              }  
          }  
            
          for(int i: maxI) {  
              res += i;  
          }  
          for(int j: maxJ) {  
              res += j;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  数学，三视图挨个分析  
    
  俯视图： grid不为0的个数。  
  主视图： 所有j中的最大值之和。  
  左视图： 所有i中的最大值之和。  
    
  要一次遍历的时候，同时处理三个视图。  
    
tags:    
  -  矩阵  
  -  数学  
