### description    
  Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.  
    
  Example 1:  
  Input:  
  [[1,1,1],  
   [1,0,1],  
   [1,1,1]]  
  Output:  
  [[0, 0, 0],  
   [0, 0, 0],  
   [0, 0, 0]]  
  Explanation:  
  For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0  
  For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0  
  For the point (1,1): floor(8/9) = floor(0.88888889) = 0  
  Note:  
  The value in the given matrix is in the range of [0, 255].  
  The length and width of the given matrix are in the range of [1, 150].  
### solution    
```    
  
Runtime: 5 ms, faster than 88.99% of Java online submissions for Image Smoother.  
Memory Usage: 42 MB, less than 71.71% of Java online submissions for Image Smoother.  
  
  
  class Solution {  
      public int[][] imageSmoother(int[][] M) {  
          int m = M.length;  
          int n = M[0].length;  
          int[][] res = new int[m][n];  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  int sum = sum(M, i, j);  
                  res[i][j] = sum;  
              }  
          }  
          return res;  
      }  
    
      private int[][] directions = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,-1},{-1,1}};  
        
      private int sum(int[][] m, int i, int j) {  
          int sum = m[i][j];  
          int count = 1;  
          for(int[] d: directions) {  
              int x = i + d[0];  
              int y = j + d[1];  
              if(x >= 0 && x < m.length && y >= 0 && y < m[0].length) {  
                  sum+= m[x][y];  
                  count++;  
              }  
          }  
          return sum/count;  
            
    
      }  
    
  }  
```    
    
### 个人解读    
  新建一个数组即可。先试试没有优化的情况。  
  发现效率还可以，就不优化了。  
    
tags:    
  -  矩阵  
