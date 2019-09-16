### description    
  A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.  
    
  The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.  
    
  Here are the rules of Tic-Tac-Toe:  
    
  Players take turns placing characters into empty squares (" ").  
  The first player always places "X" characters, while the second player always places "O" characters.  
  "X" and "O" characters are always placed into empty squares, never filled ones.  
  The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.  
  The game also ends if all squares are non-empty.  
  No more moves can be played if the game is over.  
  Example 1:  
  Input: board = ["O  ", "   ", "   "]  
  Output: false  
  Explanation: The first player always plays "X".  
    
  Example 2:  
  Input: board = ["XOX", " X ", "   "]  
  Output: false  
  Explanation: Players take turns making moves.  
    
  Example 3:  
  Input: board = ["XXX", "   ", "OOO"]  
  Output: false  
    
  Example 4:  
  Input: board = ["XOX", "O O", "XOX"]  
  Output: true  
  Note:  
    
  board is a length-3 array of strings, where each string board[i] has length 3.  
  Each board[i][j] is a character in the set {" ", "X", "O"}.  
### solution    
```    
// 方法一： 流水账  
Runtime: 1 ms, faster than 12.29% of Java online submissions for Valid Tic-Tac-Toe State.  
Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Valid Tic-Tac-Toe State.  
Runtime: 1 ms, faster than 12.29% of Java online submissions for Valid Tic-Tac-Toe State.  
Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Valid Tic-Tac-Toe State.  
  class Solution {  
      public boolean validTicTacToe(String[] board) {  
         int count1 = 0;  
          int count2 = 0;  
          for(String b: board) {  
              for(char c: b.toCharArray()) {  
                  if(c == 'X') count1++;  
                  if(c == 'O') count2++;  
              }  
          }  
          if(count1 < count2 || count1 > count2 + 1) {  
              return false;  
          }  
          int[][][] pos = {{{0,0},{0,1},{0,2}},{{1,0},{1,1},{1,2}},{{2,0},{2,1},{2,2}},{{0,0},{1,0},{2,0}},{{0,1},{1,1},{2,1}},{{0,2},{1,2},{2,2}},{{0,0},{1,1},{2,2}},{{0,2},{1,1},{2,0}}};  
            
          boolean vic1 = false;  
          boolean vic2 = false;  
          for(int[][] p: pos) {  
              //p:{{0,0},{0,1},{0,2}}  
              if(board[p[0][0]].charAt(p[0][1]) == board[p[1][0]].charAt(p[1][1]) && board[p[1][0]].charAt(p[1][1]) ==  board[p[2][0]].charAt(p[2][1])) {  
                  if(board[p[0][0]].charAt(p[0][1]) == 'X') {  
                      vic1 = true;  
                  } else if(board[p[0][0]].charAt(p[0][1]) == 'O') {  
                      vic2 = true;  
                  }  
              }  
          }  
          if(vic1 && vic2) {  
              return false;  
          } else if(vic1) {  
              return count1 == count2 + 1;  
          } else if(vic2) {  
              return count1 == count2;  
          } else {  
              return true;  
          }  
      }  
  }  
```    
    
### 个人解读    
  分两步，  
  1、判断出现次数是否合理  
  2、判断会否出现多个结果  
    
  反思：  
  出现多个结果可以是同一个玩家多个结果。所以正确判断是是否双方都有胜利，并且如果O胜利，那么X就不能多走一步了。  
  另外还需要判断结束时候各自的数量，另外还有" "三个一排的时候要排除掉  
    
  优化思路：  
  把三个String转化成一维的数字。  
    
    
tags:    
  -  流水账  
