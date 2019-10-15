### description    
  The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.  
    
    
    
  Given an integer n, return the number of distinct solutions to the n-queens puzzle.  
    
  Example:  
    
  Input: 4  
  Output: 2  
  Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.  
  [  
   [".Q..",  // Solution 1  
    "...Q",  
    "Q...",  
    "..Q."],  
    
   ["..Q.",  // Solution 2  
    "Q...",  
    "...Q",  
    ".Q.."]  
  ]  
### solution    
```    
Runtime: 1 ms, faster than 97.18% of Java online submissions for N-Queens II.  
Memory Usage: 32.9 MB, less than 8.70% of Java online submissions for N-Queens II.  
  
  class Solution {  
      private char[][] nQueens;  
      private boolean[] colUsed;  
      private boolean[] diagonals45Used;  
      private boolean[] diagonals135Used;  
      private int n;  
      private int res;  
    
      public int totalNQueens(int n) {  
          nQueens = new char[n][n];  
          for (int i = 0; i < n; i++) {  
              Arrays.fill(nQueens[i], '.');  
          }  
          colUsed = new boolean[n];  
          diagonals45Used = new boolean[2 * n - 1];  
          diagonals135Used = new boolean[2 * n - 1];  
          this.n = n;  
          backtracking(0);  
          return res;  
      }  
    
      private void backtracking(int row) {  
          if (row == n) {  
              res++;  
              return;  
          }  
    
          for (int col = 0; col < n; col++) {  
              int diagonals45Idx = row + col;  
              int diagonals135Idx = n - 1 - (row - col);  
              if (colUsed[col] || diagonals45Used[diagonals45Idx] || diagonals135Used[diagonals135Idx]) {  
                  continue;  
              }  
              nQueens[row][col] = 'Q';  
              colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = true;  
              backtracking(row + 1);  
              colUsed[col] = diagonals45Used[diagonals45Idx] = diagonals135Used[diagonals135Idx] = false;  
              nQueens[row][col] = '.';  
          }  
      }  
  }  
```    
    
### 个人解读    
  统计八皇后的个数，方法与统计八皇后所有结果一致。  
  核心就是DFS+回溯。  
  本题目的核心在于构造模型，构造3个数组，用来描述每一列，每一45度，每一135度使用情况。  
  并没有什么比统计具体结果更加优化的地方。  
    
tags:    
  -  DFS    
  -  n皇后    
  -  回溯    
