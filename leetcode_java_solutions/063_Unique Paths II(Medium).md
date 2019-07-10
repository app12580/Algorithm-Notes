### description    
  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).  
    
  The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).  
    
  Now consider if some obstacles are added to the grids. How many unique paths would there be?  
    
    
    
  An obstacle and empty space is marked as 1 and 0 respectively in the grid.  
    
  Note: m and n will be at most 100.  
    
  Example 1:  
    
  Input:  
  [  
    [0,0,0],  
    [0,1,0],  
    [0,0,0]  
  ]  
  Output: 2  
  Explanation:  
  There is one obstacle in the middle of the 3x3 grid above.  
  There are two ways to reach the bottom-right corner:  
  1. Right -> Right -> Down -> Down  
  2. Down -> Down -> Right -> Right  
### solution    
```    
  class Solution {  
      public int uniquePathsWithObstacles(int[][] obstacleGrid) {  
         if(obstacleGrid.length == 0 || obstacleGrid[0][0] == 1 ||obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1] == 1) return 0;  
          int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];  
          dp[0][0] = 1;  
          for(int i = 0; i < dp.length; i++) {  
              for(int j = 0; j < dp[0].length; j++) {  
                  if(i>0 && obstacleGrid[i-1][j] != 1) {  
                      dp[i][j] += dp[i-1][j];  
                  }  
                  if(j>0 && obstacleGrid[i][j-1] != 1) {  
                      dp[i][j] += dp[i][j-1];  
                  }  
              }  
          }  
          return dp[dp.length - 1][dp[0].length - 1];  
      }  
  }  
```    
    
### 个人解读    
  白给题目啊，只需要在计算的时候判断一下障碍物的情况就好。  
  反思，以后看到矩阵，上来就写m和n不要犹豫。  
  需要考虑好各种特殊情况，上来就是障碍物，终点是障碍物，障碍物在第一行，所以不能初始化。   
    
    
tags:    
  -  矩阵  
