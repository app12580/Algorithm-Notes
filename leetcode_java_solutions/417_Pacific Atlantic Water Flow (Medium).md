### description    
  Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.  
    
  Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.  
    
  Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.  
    
  Note:  
  The order of returned grid coordinates does not matter.  
  Both m and n are less than 150.  
  Example:  
    
  Given the following 5x5 matrix:  
    
    Pacific ~   ~   ~   ~   ~   
         ~  1   2   2   3  (5) *  
         ~  3   2   3  (4) (4) *  
         ~  2   4  (5)  3   1  *  
         ~ (6) (7)  1   4   5  *  
         ~ (5)  1   1   2   4  *  
            *   *   *   *   * Atlantic  
    
  Return:  
    
  [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).  
    
  能到达的太平洋和大西洋的区域  
    
### solution    
```    
  class Solution {  
      private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  
    
      public List<int[]> pacificAtlantic(int[][] matrix) {  
          if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {  
              return new ArrayList<>();  
          }  
    
          int m = matrix.length;  
          int n = matrix[0].length;  
          boolean[][] leftTop = new boolean[m][n];  
          boolean[][] rightBottom = new boolean[m][n];  
          List<int[]> res = new ArrayList<>();  
    
          for(int i = 0; i < n; i++) {  
              dfs(matrix, 0, i, leftTop);  
              dfs(matrix, m -1 , i, rightBottom);  
          }  
          for(int i = 0; i < m; i++) {  
              dfs(matrix, i, 0, leftTop);  
              dfs(matrix, i, n-1, rightBottom);  
          }  
    
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  if(leftTop[i][j] && rightBottom[i][j]) {  
                      res.add(new int[]{i, j});  
                  }  
              }  
          }  
          return res;  
    
      }  
        
      private void dfs(int[][] matrix, int r, int c, boolean[][] canReach) {  
          if (canReach[r][c]) {  
              return;  
          }  
          canReach[r][c] = true;  
          for (int[] d : direction) {  
              int nextR = d[0] + r;  
              int nextC = d[1] + c;  
              if (nextR < 0 || nextR >= matrix.length || nextC < 0 || nextC >= matrix[0].length  
                      || matrix[r][c] > matrix[nextR][nextC]) {  
    
                  continue;  
              }  
              dfs(matrix, nextR, nextC, canReach);  
          }  
      }  
  }  
```    
    
### 个人解读    
  DFS  
  两种思路：  
  思路一：从边缘往中间扩散  
  思路二：从中间往边缘扩散  
    
  由于需要两个标记：能到达左上&&能到右下。 所以一定需要两个标记，而且如果从中间遍历的话，还可能出现很多无效遍历。  
  从边缘遍历。  
    
  dfs方法写的时候纠结了好久。  
   ：要不要能进入方法的都是可达的 or 进入以后再判断  
   因为边缘调入法，所以还是让方法默认进入方法的都是可达的，这样也好终止。  
    
    
tags:    
  -  矩阵  
  -  DFS  
