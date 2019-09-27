### description    
  In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)  
    
  Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.  
    
  Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)  
    
     
    
  Example 1:  
    
  Input: [[0,1],[1,0]]  
  Output: 1  
  Example 2:  
    
  Input: [[0,1,0],[0,0,0],[0,0,1]]  
  Output: 2  
  Example 3:  
    
  Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]  
  Output: 1  
     
    
  Note:  
    
  1 <= A.length = A[0].length <= 100  
  A[i][j] == 0 or A[i][j] == 1  
  
### solution    
```    
Runtime: 6 ms, faster than 100.00% of Java online submissions for Shortest Bridge.  
Memory Usage: 45.9 MB, less than 92.31% of Java online submissions for Shortest Bridge.  
  
  class Solution {  
      private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  
    
      public int shortestBridge(int[][] A) {  
          int m = A.length;  
          int n = A[0].length;  
          int res = 1;  
          Queue<int[]> queue = new LinkedList<>();  
          boolean flag = false;  
          for (int i = 0; i < m; i++) {  
              if (flag) break;  
              for (int j = 0; j < n; j++) {  
                  if (A[i][j] == 1) {  
                      init(A, i, j, queue);  
                      flag = true;  
                      break;  
                  }  
              }  
          }  
          while (!queue.isEmpty()) {  
              int size = queue.size();  
              for (int i = 0; i < size; i++) {  
                  int[] poll = queue.poll();  
                  int x = poll[0];  
                  int y = poll[1];  
                  for (int[] d : directions) {  
                      int xx = x + d[0];  
                      int yy = y + d[1];  
                      if (xx >= 0 && yy >= 0 && xx < A.length && yy < A[0].length) {  
                          if(A[xx][yy] == 1) {  
                              return res;  
                          } else if(A[xx][yy] == 0) {  
                              A[xx][yy] = -1;  
                              queue.add(new int[]{xx, yy});  
                          }  
                      }  
                  }  
              }  
              res++;  
          }  
          return res;  
      }  
            
    
      //湮灭1，然后令周围0坐标的进队列  
    
      private void init(int[][] A, int x, int y, Queue<int[]> queue) {  
          A[x][y] = -1;  
          for (int[] d : directions) {  
              int xx = x + d[0];  
              int yy = y + d[1];  
              if (xx >= 0 && yy >= 0 && xx < A.length && yy < A[0].length) {  
                  if (A[xx][yy] == 0) {  
                      A[xx][yy] = -1;  
                      queue.add(new int[]{xx, yy});  
                  } else if (A[xx][yy] == 1) {  
                      init(A, xx, yy, queue);  
                  }  
              }  
          }  
      }  
  }  
```    
    
### 个人解读    
  这种题目，感觉大概率是 DF，遍历每个节点，然后将周围一圈设置成2，然后是3，直到相遇。  
    
  一次成，效率还可以嗷~  
    
tags:    
  -  BFS  
  -  矩阵  
    
