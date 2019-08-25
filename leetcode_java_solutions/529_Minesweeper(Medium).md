### description    
  Let's play the minesweeper game (Wikipedia, online game)!  
    
  You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.  
    
  Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:  
    
  If a mine ('M') is revealed, then the game is over - change it to 'X'.  
  If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.  
  If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.  
  Return the board when no more squares will be revealed.  
     
    
  Example 1:  
    
  Input:   
    
  [['E', 'E', 'E', 'E', 'E'],  
   ['E', 'E', 'M', 'E', 'E'],  
   ['E', 'E', 'E', 'E', 'E'],  
   ['E', 'E', 'E', 'E', 'E']]  
    
  Click : [3,0]  
    
  Output:   
    
  [['B', '1', 'E', '1', 'B'],  
   ['B', '1', 'M', '1', 'B'],  
   ['B', '1', '1', '1', 'B'],  
   ['B', 'B', 'B', 'B', 'B']]  
    
  Explanation:  
    
  Example 2:  
    
  Input:   
    
  [['B', '1', 'E', '1', 'B'],  
   ['B', '1', 'M', '1', 'B'],  
   ['B', '1', '1', '1', 'B'],  
   ['B', 'B', 'B', 'B', 'B']]  
    
  Click : [1,2]  
    
  Output:   
    
  [['B', '1', 'E', '1', 'B'],  
   ['B', '1', 'X', '1', 'B'],  
   ['B', '1', '1', '1', 'B'],  
   ['B', 'B', 'B', 'B', 'B']]  
    
  Explanation:  
    
     
    
  Note:  
    
  The range of the input matrix's height and width is [1,50].  
  The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.  
  The input board won't be a stage when game is over (some mines have been revealed).  
  For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.  
    
  模拟扫雷  
### solution    
```    
  
Runtime: 1 ms, faster than 87.91% of Java online submissions for Minesweeper.  
Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for Minesweeper.  
  
  
  class Solution {  
      private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};  
      public char[][] updateBoard(char[][] board, int[] click) {  
          int x = click[0];  
          int y = click[1];  
          if (board[x][y] == 'M') {  
              board[x][y] = 'X';  
              return board;  
          }  
          dfs(board, x, y);  
          return board;  
      }  
    
    
      private void dfs(char[][] board, int x, int y) {  
          if(x < 0 || y < 0 || x >= board.length || y >= board[0].length) {  
              return;  
          }  
          if(board[x][y] != 'M' && board[x][y] != 'E') {  
              return;  
          }  
          int mines = countMines(board, x, y);  
          if(mines > 0) {  
              board[x][y] = (char)(mines + '0');  
          } else {  
              board[x][y] = 'B';  
              for(int[] d: directions) {  
                  dfs(board, x + d[0], y + d[1]);  
              }  
          }  
    
      }  
    
      private int countMines(char[][] board, int x, int y) {  
          int count = 0;  
          for(int[] d: directions) {  
              int xx = x + d[0];  
              int yy = y + d[1];  
              if(xx < 0 || yy < 0 || xx >= board.length || yy >= board[0].length) {  
                  continue;  
              }  
              if(board[xx][yy] == 'M') {  
                  count++;  
              }  
          }  
          return count;  
      }  
    
  }  
```    
    
### 个人解读    
  因为出现了空白格的扩展，所以会出现递归调用。  
    
  M:暗雷  
  E:未操作过  
  B:空白格  
  X:已挖出的雷  
  数字  
    
  反思：一开始少写了如果该格子已经处理过，则需要终止的条件  
  应用了遍历的湮灭法。  
    
tags:    
  -  DFS  
  -  湮灭法  
