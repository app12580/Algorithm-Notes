### description    
  In a N x N grid representing a field of cherries, each cell is one of three possible integers.  
    
     
    
  0 means the cell is empty, so you can pass through;  
  1 means the cell contains a cherry, that you can pick up and pass through;  
  -1 means the cell contains a thorn that blocks your way.  
     
    
  Your task is to collect maximum number of cherries possible by following the rules below:  
    
     
    
  Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);  
  After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;  
  When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);  
  If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.  
     
    
     
    
  Example 1:  
    
  Input: grid =  
  [[0, 1, -1],  
   [1, 0, -1],  
   [1, 1,  1]]  
  Output: 5  
  Explanation:   
  The player started at (0, 0) and went down, down, right right to reach (2, 2).  
  4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].  
  Then, the player went left, up, up, left to return home, picking up one more cherry.  
  The total number of cherries picked up is 5, and this is the maximum possible.  
     
    
  Note:  
    
  grid is an N by N 2D array, with 1 <= N <= 50.  
  Each grid[i][j] is an integer in the set {-1, 0, 1}.  
  It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.  
     
### solution    
```    
参考答案： 传纸条问题 官方答案  
Runtime: 16 ms, faster than 72.66% of Java online submissions for Cherry Pickup.  
Memory Usage: 40.4 MB, less than 77.78% of Java online submissions for Cherry Pickup.  
  
  class Solution {  
      int[][][] memo;  
      int[][] grid;  
      int N;  
      public int cherryPickup(int[][] grid) {  
          this.grid = grid;  
          N = grid.length;  
          memo = new int[N][N][N];  
          for (int[][] layer: memo)  
              for (int[] row: layer)  
                  Arrays.fill(row, Integer.MIN_VALUE);  
          return Math.max(0, dp(0, 0, 0));  
      }  
      public int dp(int r1, int c1, int c2) {  
          int r2 = r1 + c1 - c2;  
          if (N == r1 || N == r2 || N == c1 || N == c2 ||  
                  grid[r1][c1] == -1 || grid[r2][c2] == -1) {  
              return -999999;          
          } else if (r1 == N-1 && c1 == N-1) {  
              return grid[r1][c1];  
          } else if (memo[r1][c1][c2] != Integer.MIN_VALUE) {  
              return memo[r1][c1][c2];  
          } else {  
              int ans = grid[r1][c1];  
              if (c1 != c2) ans += grid[r2][c2];  
              ans += Math.max(Math.max(dp(r1, c1+1, c2+1), dp(r1+1, c1, c2+1)),  
                              Math.max(dp(r1, c1+1, c2), dp(r1+1, c1, c2)));  
              memo[r1][c1][c2] = ans;  
              return ans;  
          }  
      }  
  }  
     
```    
    
### 个人解读    
  完全不知道怎么搞啊。。。。  
  总不能DFS X DFS的把。。。  
    
  知道了个名词，传纸条问题  
    
  题目关键点：  
  1、意识到先走完，再回头，会产生动态的矩阵，会很麻烦，直接DFS指数复杂度  
  2、转变问题变成两条路同步进行，这样就不会产生动态矩阵，秋后算账的麻烦  
  3、两点细节：x1+y1=x2+y2可以少一个维度；同一个点只能捡一次  
  4、使用递归来进行DP  
    
tags:    
  -  传纸条问题  
  -  动态规划     
