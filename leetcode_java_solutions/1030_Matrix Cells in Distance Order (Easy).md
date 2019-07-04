### description    
  We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.  
    
  Additionally, we are given a cell in that matrix with coordinates (r0, c0).  
    
  Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)  
    
     
    
  Example 1:  
    
  Input: R = 1, C = 2, r0 = 0, c0 = 0  
  Output: [[0,0],[0,1]]  
  Explanation: The distances from (r0, c0) to other cells are: [0,1]  
  Example 2:  
    
  Input: R = 2, C = 2, r0 = 0, c0 = 1  
  Output: [[0,1],[0,0],[1,1],[1,0]]  
  Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]  
  The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.  
  Example 3:  
    
  Input: R = 2, C = 3, r0 = 1, c0 = 2  
  Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]  
  Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]  
  There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].  
     
    
  Note:  
    
  1 <= R <= 100  
  1 <= C <= 100  
  0 <= r0 < R  
  0 <= c0 < C  
### solution    
```    
  class Solution {  
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {  
          int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};  
          int[][]  res = new int[R * C][2];  
          boolean[][] flag = new boolean[R][C];  
          flag[r0][c0] = true;  
    
          int index = 0;  
          Queue<int[]> queue = new LinkedList<>();  
          queue.offer(new int[]{r0, c0});  
          while (!queue.isEmpty()) {  
              int[] poll = queue.poll();  
              int curX = poll[0];  
              int curY = poll[1];  
              res[index++] = new int[]{curX, curY};  
              for(int[] d: directions) {  
                  int nextX = curX + d[0];  
                  int nextY = curY + d[1];  
                  if(nextX >= 0 && nextY >= 0 && nextX < R && nextY < C && !flag[nextX][nextY]) {  
                      queue.offer(new int[]{nextX, nextY});  
                      flag[nextX][nextY] = true;        //注意标记的位置  
                  }   
              }  
    
          }  
          return res;  
      }  
    
    
  }  
```    
    
### 个人解读    
  BFS。  
    
  从给定点往四周扩散。  
    
  重点在于需要控制好BFS扩散的方向。三种方法：湮灭法不适用；数学法可以，但是麻烦；选择采用标记法。  
    
  反思一： 标记的位置一开始放错了，应该在每次入队列的时候，就开始标记，而非出队列的时候。因此，也需要先把第一个点先预处理了。  
    
    
tags:    
  -  BFS  
  -  标记法  
