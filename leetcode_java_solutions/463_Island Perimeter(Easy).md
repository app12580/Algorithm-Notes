### description    
  You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.  
    
  Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).  
    
  The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.  
    
     
    
  Example:  
    
  Input:  
  [[0,1,0,0],  
   [1,1,1,0],  
   [0,1,0,0],  
   [1,1,0,0]]  
    
  Output: 16  
    
  Explanation: The perimeter is the 16 yellow stripes in the image below:  
  
  求岛的周长。  
  前提：只有一个岛，没有地中海。  
      
### solution    
```    
  class Solution {  
      public int islandPerimeter(int[][] grid) {  
          int res = 0;  
          for(int i = 0; i < grid.length; i++) {  
              for(int j = 0; j < grid[0].length; j++) {  
                  if(grid[i][j] != 1) {  
                      continue;  
                  }  
                  if(i == 0) {  
                      res++;  
                  } else if(grid[i - 1][j] == 0){  
                      res++;  
                  }  
                  if(i == grid.length - 1) {  
                      res++;  
                  } else if(grid[i + 1][j] == 0){  
                      res++;  
                  }  
                  if(j == 0) {  
                      res++;  
                  } else if(grid[i][j - 1] == 0){  
                      res++;  
                  }  
                  if(j == grid[0].length - 1) {  
                      res++;  
                  } else if(grid[i][j + 1] == 0){  
                      res++;  
                  }  
    
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  思路： 要选好遍历方向，选好哪些边是需要去考虑的。  
  盯着图看了一会，就发现规律了。岛（数值为1）之间的周长互不干涉，每个1旁边有几个0(或处在地图边缘)周长就加1。  
    
tags:    
  -  数学  
  -  矩阵  
