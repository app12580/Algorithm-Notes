### description    
  According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."  
    
  Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):  
    
  Any live cell with fewer than two live neighbors dies, as if caused by under-population.  
  Any live cell with two or three live neighbors lives on to the next generation.  
  Any live cell with more than three live neighbors dies, as if by over-population..  
  Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.  
  Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.  
    
  Example:  
    
  Input:   
  [  
    [0,1,0],  
    [0,0,1],  
    [1,1,1],  
    [0,0,0]  
  ]  
  Output:   
  [  
    [0,0,0],  
    [1,0,1],  
    [0,1,1],  
    [0,1,0]  
  ]  
  Follow up:  
    
  Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.  
  In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?  
    
  对于任一个点的周围活细胞个数：  
  1、[0,1]和[4,9]一定是死的  
  2、3： 一定是获得  
  3、2： 和上一轮是一致的  
    
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.  
Memory Usage: 34.9 MB, less than 100.00% of Java online submissions for Game of Life.  
  
  
  class Solution {  
        public void gameOfLife(int[][] board) {  
          if(board.length == 0 || board[0].length == 0) {  
              return;  
          }  
          int m = board.length;  
          int n = board[0].length;  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  if(board[i][j] % 10 == 1) {       //这里要根据个位数的情况  
                      helper(board, i, j);  
                  }  
              }  
          }  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  int cnt = board[i][j] / 10;  
                  if(cnt == 3) {  
                      board[i][j] = 1;  
                  } else if(cnt <= 1 || cnt >= 4) {  
                      board[i][j] = 0;  
                  } else {  
                      board[i][j] = board[i][j] % 10;  
                  }  
              }  
          }  
      }  
    
      private void helper(int[][] board, int i, int j) {  
          int[][] directions = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};  
          for(int[] d: directions) {  
              int x = i + d[0];  
              int y = j + d[1];  
              if(x >= 0 && y >= 0 && x < board.length && y < board[0].length) {  
                  board[x][y] += 10;  
              }  
          }  
    
      }  
  }  
```    
    
### 个人解读    
  使用十位数上的数字去统计个数。  
  注意细节： 数字被修改了以后，就不能直接用==1判断而是%10==1判断。  
  源数组的标记法合一。  
    
tags:    
  -  标记法  
