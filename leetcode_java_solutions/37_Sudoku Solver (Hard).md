### description    
  Write a program to solve a Sudoku puzzle by filling the empty cells.  
    
  A sudoku solution must satisfy all of the following rules:  
    
  Each of the digits 1-9 must occur exactly once in each row.  
  Each of the digits 1-9 must occur exactly once in each column.  
  Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.  
  Empty cells are indicated by the character '.'.  
    
    
  A sudoku puzzle...  
    
    
  ...and its solution numbers marked in red.  
    
  Note:  
    
  The given board contain only digits 1-9 and the character '.'.  
  You may assume that the given Sudoku puzzle will have a single unique solution.  
  The given board size is always 9x9.  
### solution    
```    
Runtime: 4 ms, faster than 90.66% of Java online submissions for Sudoku Solver.  
Memory Usage: 35.5 MB, less than 69.57% of Java online submissions for Sudoku Solver.  
  
  
  
  class Solution {  
      private boolean[][] rowsUsed = new boolean[9][10];  
      private boolean[][] colsUsed = new boolean[9][10];  
      private boolean[][] cubesUsed = new boolean[9][10];  
    
      public void solveSudoku(char[][] board) {  
          int m = board.length;  
          int n = board[0].length;  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  if(board[i][j] != '.') {  
                      int cur = board[i][j] - '0';  
                      rowsUsed[i][cur] = true;  
                      colsUsed[j][cur] = true;  
                      cubesUsed[cubeNum(i, j)][cur] = true;  
                  }  
              }  
          }  
          dfs(board, 0, 0);  
          int c  = 1;  
      }  
    
      private boolean dfs(char[][] board, int i, int j) {  
          if(i == 9) {  
              return true;  
          }  
          int nextJ = j + 1;  
          int nextI = i;  
          if(nextJ == 9) {  
              nextI = i + 1;  
              nextJ = 0;  
          }  
          if(board[i][j] != '.') {  
              if(dfs(board, nextI, nextJ)) {  
                  return true;  
              }  
          } else {  
              for(int k = 1; k < 10; k++) { //刚开始甚至让k从0开始了  
                  if(!rowsUsed[i][k] && !colsUsed[j][k] && !cubesUsed[cubeNum(i, j)][k]) {  
                      board[i][j] = (char)('0' + k);  
                      rowsUsed[i][k] = true;  
                      colsUsed[j][k] = true;  
                      cubesUsed[cubeNum(i, j)][k] = true;  
                      if(dfs(board, nextI, nextJ)) {  
                          return true;  
                      }  
                      board[i][j] = '.';  
                      rowsUsed[i][k] = false;  
                      colsUsed[j][k] = false;  
                      cubesUsed[cubeNum(i, j)][k] = false;  
                  }  
              }  
          }  
          return false;  
      }  
      private int cubeNum(int i, int j) {  
          int r = i / 3;  
          int c = j / 3;  
          return r * 3 + c;  
      }  
        
  }  
    
  // 优化之处，从原来的使用dfs用栈调用去下一个格子，优化成在方法内部直接修改i和j  
    
  while (row < 9 && board[row][col] != '.') {  
            row = col == 8 ? row + 1 : row;  
            col = col == 8 ? 0 : col + 1;  
        }  
          
          
    
```    
    
### 个人解读    
  数独，经典题目  
  没看答案前的思路： i和j全部遍历，然后每次循环获取当前格子可选的数字，然后for循环，dfs。接下来主要就是靠dfs，如果能顺利到达最后一格则出来结果了。  
  使用中间变量存储每行每列每格子的数字情况。  
 ```  
   private boolean[][] rowsUsed = new boolean[9][10];  
   private boolean[][] colsUsed = new boolean[9][10];  
   private boolean[][] cubesUsed = new boolean[9][10];  
 ```  
  
错误结论： 照着这个思路写出来了，但是运行效率非常慢，运行了好久都不出来结果。穷举法是不现实的呀。(X)  
           自己无能，连问题在哪都没找出就觉得解法不行（√）  
    
  主要问题在于非线性DFS的终止条件没弄好，即使出来正确结果了，仍然在不停的遍历，所以导致超时  
  指不是那种一步一步的dfs，而是要在运行在某一只棕情况下，终止所有的for循环。  
    
tags:    
  -  DFS  
  -  穷举  
  -  回溯  
