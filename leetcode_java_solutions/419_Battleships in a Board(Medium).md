### description    
  Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:  
  You receive a valid board, made of only battleships or empty slots.  
  Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.  
  At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.  
  Example:  
  X..X  
  ...X  
  ...X  
  In the above board there are 2 battleships.  
  Invalid Example:  
  ...X  
  XXXX  
  ...X  
  This is an invalid board that you will not receive - as battleships will always have a cell separating between them.  
  Follow up:  
  Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?  
### solution    
```    
  
//方法一： 先到原则   
这效率可以嗷。。  
Runtime: 1 ms, faster than 94.03% of Java online submissions for Battleships in a Board.  
Memory Usage: 43.3 MB, less than 43.48% of Java online submissions for Battleships in a Board.  
  
  
  class Solution {  
      public int countBattleships(char[][] board) {  
           int res = 0;  
          int m = board.length;  
          int n = board[0].length;  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  char ch = board[i][j];  
                  if(ch == '.') continue;  
                  if((i == 0 || board[i - 1][j] == '.') && (j == 0 || board[i][j - 1] == '.')) {  
                      res++;  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  根据题目要求，意思是标记法和湮灭法都不让用了，那就只能用数学法。  
    
  新定义一个概念：先到原则，意思是一行里面有多个时候，就把他算进去。  
    
tags:    
  -  先到原则  
  -  矩阵  
  -  数学法  
    
