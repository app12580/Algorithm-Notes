### description    
  On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.  
    
  A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.  
    
  The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].  
    
  Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.  
    
  Examples:  
    
  Input: board = [[1,2,3],[4,0,5]]  
  Output: 1  
  Explanation: Swap the 0 and the 5 in one move.  
  Input: board = [[1,2,3],[5,4,0]]  
  Output: -1  
  Explanation: No number of moves will make the board solved.  
  Input: board = [[4,1,2],[5,0,3]]  
  Output: 5  
  Explanation: 5 is the smallest number of moves that solves the board.  
  An example path:  
  After move 0: [[4,1,2],[5,0,3]]  
  After move 1: [[4,1,2],[0,5,3]]  
  After move 2: [[0,1,2],[4,5,3]]  
  After move 3: [[1,0,2],[4,5,3]]  
  After move 4: [[1,2,0],[4,5,3]]  
  After move 5: [[1,2,3],[4,5,0]]  
  Input: board = [[3,2,4],[1,5,0]]  
  Output: 14  
  Note:  
    
  board will be a 2 x 3 array as described above.  
  board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].  
    
  拼图游戏  
    
### solution    
```    
// 方法一： BFS  
Runtime: 5 ms, faster than 86.69% of Java online submissions for Sliding Puzzle.  
Memory Usage: 35.5 MB, less than 100.00% of Java online submissions for Sliding Puzzle.  
  
  class Solution {  
       Map<String, String> mmm = new HashMap<>();  
    
      public int slidingPuzzle(int[][] board) {  
          StringBuilder builder = new StringBuilder();  
          for(int i = 0; i < 2; i++) {  
              for(int j = 0; j < 3; j++) {  
                  builder.append(board[i][j]);  
              }  
          }  
          Set<String> seen = new HashSet<>();  
          String str = builder.toString();  
          seen.add(str);  
          Queue<String> queue = new LinkedList<>();  
          queue.add(str);  
          int res = 0;  
          while(!queue.isEmpty()) {  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  String poll = queue.poll();  
                  if("123450".equals(poll)) {  
                      return res;  
                  }  
                  char[] chars = poll.toCharArray();  
                  int index = 0;  
                  for(int j = 0; j < 6; j++) {  
                      if(chars[j] == '0') {  
                          index = j;  
                          break;  
                      }  
                  }  
                  String next = null;  
                  if(index != 3 && index > 0 && !seen.contains((next = afterSwap(chars, index, index - 1)))) {  
                      queue.offer(next);  
                      seen.add(next);  
                  }  
                  if(index != 2 && index < 5 && !seen.contains((next = afterSwap(chars, index, index + 1)))) {  
                      queue.offer(next);  
                      seen.add(next);  
                  }  
                  if(index > 2 && !seen.contains((next = afterSwap(chars, index, index - 3)))) {  
                      queue.offer(next);  
                      seen.add(next);  
                  }  
                  if(index < 3 && !seen.contains((next = afterSwap(chars, index, index + 3)))) {  
                      queue.offer(next);  
                      seen.add(next);  
                  }  
              }  
              res++;  
          }  
          return -1;  
      }  
    
        
    
      public String afterSwap(char[] chars, int x, int y) {  
          char[] copy = new char[6];  
          for(int i = 0; i < 6; i++) {  
              if(i == x) {  
                  copy[i] = chars[y];  
              } else if(i == y) {  
                  copy[y] = chars[x];  
              } else {  
                  copy[i] = chars[i];  
              }  
          }  
          return new String(copy);  
      }  
  }  
```    
    
### 个人解读    
  游戏交互逻辑  
    
  思路一：BFS，无脑暴力法，通过6个字符的字符串来唯一确定已经走过的情况。0的索引i可以和i-1,i+1,i-3,i+3交换位置。  
    
  总结：  
  BFS就完事了，比想象的简单很多。  
    
tags:    
  -  BFS  
  -  游戏逻辑  
