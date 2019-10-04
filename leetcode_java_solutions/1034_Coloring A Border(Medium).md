### description    
  Given a 2-dimensional grid of integers, each value in the grid represents the color of the grid square at that location.  
    
  Two squares belong to the same connected component if and only if they have the same color and are next to each other in any of the 4 directions.  
    
  The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).  
    
  Given a square at location (r0, c0) in the grid and a color, color the border of the connected component of that square with the given color, and return the final grid.  
    
     
    
  Example 1:  
    
  Input: grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3  
  Output: [[3, 3], [3, 2]]  
  Example 2:  
    
  Input: grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3  
  Output: [[1, 3, 3], [2, 3, 3]]  
  Example 3:  
    
  Input: grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2  
  Output: [[2, 2, 2], [2, 1, 2], [2, 2, 2]]  
     
    
  Note:  
    
  1 <= grid.length <= 50  
  1 <= grid[0].length <= 50  
  1 <= grid[i][j] <= 1000  
  0 <= r0 < grid.length  
  0 <= c0 < grid[0].length  
  1 <= color <= 1000  
### solution    
```    
  
Runtime: 2 ms, faster than 29.62% of Java online submissions for Coloring A Border.  
Memory Usage: 46.5 MB, less than 62.30% of Java online submissions for Coloring A Border.  
  
  
  class Solution {  
      
      private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  
    
      public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {  
          int m = grid.length;  
          int n = grid[0].length;  
          int val = grid[r0][c0];  
          Queue<int[]> queue = new LinkedList<>();  
          queue.offer(new int[]{r0, c0});  
          while(!queue.isEmpty()) {  
              int[] poll = queue.poll();  
              int px = poll[0];  
              int py = poll[1];  
              boolean isBorder = px == 0 || py == 0 || px == m - 1 || py == n - 1;  
              if(!isBorder) {  
                  boolean fourSame = true;  
                  for(int[] d: directions) {  
                      int nx = px + d[0];  
                      int ny = py + d[1];  
                      if(grid[nx][ny] == val) {  
                          queue.offer(new int[]{nx, ny});  
                      } else if(grid[nx][ny] == -1 || grid[nx][ny] == -2){  
    
                      } else {  
                          fourSame = false;  
                      }  
                  }  
                  if(!fourSame) {  
                      grid[px][py] = -1;  
                  } else {  
                      grid[px][py] = -2;  
                  }  
              } else {  
                  grid[px][py] = -1;  
                  for(int[] d: directions) {  
                      int nx = px + d[0];  
                      int ny = py + d[1];  
                      if(nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) {  
                          continue;  
                      }  
                      if(grid[nx][ny] == val) {  
                          queue.offer(new int[]{nx, ny});  
                      }  
                  }  
              }  
          }  
          for (int i = 0; i < m; i++) {  
              for (int j = 0; j < n; j++) {  
                  if (grid[i][j] == -1) {  
                      grid[i][j] = color;  
                  } else if(grid[i][j] == -2) {  
                      grid[i][j] = val;  
                  }  
              }  
          }  
          return grid;  
      }  
  }  
```    
    
### 个人解读    
  如果四周都是初始颜色，那么该点不会着色。  
    
  思路一：使用Queue，然后每次向四周扩展。  
  注意超时原因：A->B->A  
  所以需要使用标记法，标记过的就不要它再进入队列了，注意：fourSame的需要标记，判断着色的需要标记，一共两个标记。  
    
tags:    
  -  矩阵  
  -  BFS  
  -  标记法  
