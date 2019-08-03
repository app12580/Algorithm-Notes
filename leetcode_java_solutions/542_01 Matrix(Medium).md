|### description    
  Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.  
    
  The distance between two adjacent cells is 1.  
    
     
    
  Example 1:  
    
  Input:  
  [[0,0,0],  
   [0,1,0],  
   [0,0,0]]  
    
  Output:  
  [[0,0,0],  
   [0,1,0],  
   [0,0,0]]  
  Example 2:  
    
  Input:  
  [[0,0,0],  
   [0,1,0],  
   [1,1,1]]  
    
  Output:  
  [[0,0,0],  
   [0,1,0],  
   [1,2,1]]  
     
    
  Note:  
    
  The number of elements of the given matrix will not exceed 10,000.  
  There are at least one 0 in the given matrix.  
  The cells are adjacent in only four directions: up, down, left and right.  
    
    
### solution    
```    
  class Solution {  
      public int[][] updateMatrix(int[][] matrix) {  
           int m = matrix.length;  
          int n = matrix[0].length;  
          Queue<int[]> queue = new LinkedList<>();  
          int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  if(matrix[i][j] != 0) {  
                      matrix[i][j] = Integer.MAX_VALUE;  
                  } else {  
                      queue.offer(new int[]{i, j});  
                  }  
              }  
          }  
          while(!queue.isEmpty()) {  
              int[] poll = queue.poll();  
              int val = matrix[poll[0]][poll[1]];  
              for(int[] d: directions) {  
                  int x = poll[0] + d[0];  
                  int y = poll[1] + d[1];  
                  if(x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > val + 1) {  
                      matrix[x][y] = val + 1;  
                      queue.offer(new int[]{x, y});  
                  }  
              }  
          }  
          return matrix;  
      }  
  }  
```    
    
### 个人解读    
  矩阵的遍历，要么DFS要么BFS，因为DFS效率很低，所以采用BFS。  
  注：能用标准的BFS或者DFS就想着套模板了，别乱搞一些骚操作，没必要。  
    
  因为BFS的时候需要把每一个格子取成离他最近的最小值+1，所以需要预处理，把所有的1变成Max。  
  然后有个疑问，每次bfs的时候，是先改变值在入队，还是先入队再改变值。  
  先改变值比较好，要不然等出队以后还需要再四个方向遍历一遍。  
    
tags:    
  -  矩阵  
  -  BFS  
