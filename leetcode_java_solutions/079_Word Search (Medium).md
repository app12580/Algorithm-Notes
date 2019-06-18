### description    
  Given a 2D board and a word, find if the word exists in the grid.  
    
  The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.  
    
  Example:  
    
  board =  
  [  
    ['A','B','C','E'],  
    ['S','F','C','S'],  
    ['A','D','E','E']  
  ]  
    
  Given word = "ABCCED", return true.  
  Given word = "SEE", return true.  
  Given word = "ABCB", return false.  
### solution    
```    
  // 方法一： 超时了。。。  
  class Solution {  
      private boolean res = false;  
      private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  
    
    
      public boolean exist(char[][] board, String word) {  
          if(board == null || board.length == 0 || board[0].length == 0 || word == null ||word.length() == 0) {  
              return false;  
          }  
          boolean[][] marked = new boolean[board.length][board[0].length];  
          for(int i = 0; i < board.length; i++) {  
              for(int j = 0; j < board[0].length; j++) {  
                  if(!res){  
                      dfs("", board, marked, word, i, j);  
                      marked = new boolean[board.length][board[0].length];  
                  }  
              }  
          }  
          return res;  
      }  
    
      private void dfs(String prefix, char[][] board, boolean[][] marked, String word, int i, int j) {  
    
          char respect = word.charAt(prefix.length());  
          if(board[i][j] != respect) {  
              return;  
          } else {  
              prefix += respect;  
              if(prefix.length() == word.length()) {  
                  res = true;  
                  return;  
              }  
              marked[i][j] = true;  
              for(int[] d: direction) {  
                  int nextI = i + d[0];  
                  int nextJ = j + d[1];  
                  int[] nextPoint = new int[]{i, j};  
                  if(nextI >= 0 && nextI < board.length && nextJ >= 0 && nextJ < board[0].length && !marked[nextI][nextJ]) {  
                      dfs(prefix, board, marked, word, nextI, nextJ);  
                  }  
              }  
              marked[i][j] = false;  
          }  
    
      }  
  }  
    
  // 方法二 优化  
  class Solution {  
      private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  
    
    
      public boolean exist(char[][] board, String word) {  
          if(board == null || board.length == 0 || board[0].length == 0 || word == null ||word.length() == 0) {  
              return false;  
          }  
          boolean[][] marked = new boolean[board.length][board[0].length];  
          for(int i = 0; i < board.length; i++) {  
              for(int j = 0; j < board[0].length; j++) {  
                  if(dfs(0, board, marked, word, i, j)){  
                      return true;  
                  }  
              }  
          }  
          return false;  
      }  
    
      private boolean dfs(int curlen, char[][] board, boolean[][] marked, String word, int i, int j) {  
    
          char respect = word.charAt(curlen);  
          if(board[i][j] != respect) {  
              return false;  
          } else {  
              curlen++;  
              if(curlen == word.length()) {  
                  return true;  
              }  
              marked[i][j] = true;  
              for(int[] d: direction) {  
                  int nextI = i + d[0];  
                  int nextJ = j + d[1];  
                  int[] nextPoint = new int[]{i, j};  
                  if(nextI >= 0 && nextI < board.length && nextJ >= 0 && nextJ < board[0].length && !marked[nextI][nextJ]) {  
                      if(dfs(curlen, board, marked, word, nextI, nextJ)){  
                          return true;  
                      }  
                  }  
              }  
              marked[i][j] = false;  
              return  false;  
          }  
            
      }  
  }  
```    
    
### 个人解读    
  DFS， 问题是在DFS的过程中，如何存储已经走过的路径。难道用一个List去存储？？好像也不是不可以呀。  
  方法一超时，感觉思路和其他解法思路基本是一模一样的，但是效率查了一些。  
    
  一步步优化：  
  -- 把prefix的String改成长度len  //仍然超时  
  -- 去掉 marked = new boolean[board.length][board[0].length];  //仍然超时  
  -- 把全局变量的boolean参数变成方法的返回主子。  //通过  
  
    
tags:    
  -  DFS  
  -  优化  
  -  回溯  
