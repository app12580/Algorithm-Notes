### description    
  Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.  
    
  A region is captured by flipping all 'O's into 'X's in that surrounded region.  
    
  Example:  
    
  X X X X  
  X O O X  
  X X O X  
  X O X X  
  After running your function, the board should be:  
    
  X X X X  
  X X X X  
  X X X X  
  X O X X  
  Explanation:  
    
  Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.  
    
  填充封闭区域  
  使被 'X' 包围的 'O' 转换为 'X'。(把内部的O转化成X)  
### solution    
```    
  class Solution {  
     public void solve(char[][] board) {  
          if(board == null || board.length == 0 || board[0].length == 0) {  
              return;  
          }  
          int m = board.length;  
          int n = board[0].length;  
          //首行和尾行  
          for(int i = 0; i < n; i++) {  
              dfs(board, 0, i);  
              dfs(board, m - 1, i);  
          }  
          // 左列和右列  
          for(int i = 1; i < m - 1; i++) {  
              dfs(board, i, 0);  
              dfs(board, i, n - 1);  
          }  
            
          for(int i = 0;  i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  if(board[i][j] == 'T') {  
                      board[i][j] = 'O';  
                  } else if(board[i][j] == 'O') {  
                      board[i][j] = 'X';  
                  }  
              }  
          }  
    
      }  
        
      private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  
      private void dfs(char[][] board, int i, int j) {  
          if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'X'|| board[i][j] == 'T') {  
              return;  
          }  
          board[i][j] = 'T';  
          for(int[] d: direction) {  
              dfs(board, i + d[0], j + d[1]);  
          }  
      }  
    
  }  
```    
    
### 个人解读    
  有过创建一个二维数组用来做标记 ，但是感觉很乱，就没往下想了。然而标记法可以与源数组合并在一起的。曾经字符串有个-1的操作。  
    
  总结： 标记法，从外圈每一个DFS；标记数组与源数组合并。  
    
tags:    
  -  DFS  
  -  矩阵  
