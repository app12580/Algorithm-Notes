### description    
  Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.  
    
  Example:  
    
  Input: 3  
  Output:  
  [  
   [ 1, 2, 3 ],  
   [ 8, 9, 4 ],  
   [ 7, 6, 5 ]  
  ]  
### solution    
```    
// 嘿嘿嘿， 还可以嗷  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix II.  
Memory Usage: 34.2 MB, less than 5.06% of Java online submissions for Spiral Matrix II.  
  
  class Solution {  
      private int[][] directions = {{1,0},{0,-1},{-1,0},{0,1}};  
      public int[][] generateMatrix(int n) {  
          if(n <= 0) return new int[0][];  
          int[][] res = new int[n][n];  
          int val = 1;  
          int d = 3;  
          for(int i = 0; i < n; i++) {  
              res[0][i] = val++;  
          }  
          int x = 0;  
          int y = n - 1;  
          int cnt = n - 1;  
          while(val <= n * n) {  
              d = (d + 1) % 4;  
              for(int i = 0; i < cnt; i++) {  
                  x += directions[d][0];  
                  y += directions[d][1];  
                  res[x][y] = val++;  
              }  
              if(val <= n * n) {  
                  d = (d + 1) % 4;  
                  for(int i = 0; i < cnt; i++) {  
                      x += directions[d][0];  
                      y += directions[d][1];  
                      res[x][y] = val++;  
                  }  
              }  
              cnt--;  
          }  
    
          return res;  
      }  
  }  
```    
    
### 个人解读    
  类似于[54](054_Spiral%20Matrix(Medium).md)  
  ```       
    // 6 X 5    
    // 5    4    5   3    4  2   3  1 2    
    // m-1, n-1,m-1,n-2,m-2,n-3,m-3,n-4    
    //    [    
    //            [1, 2, 3, 4, 4, 4],    
    //            [5, 6, 7, 8, 8, 8],    
    //            [9,10,11,12,12,12]    
    //            [9,10,11,12,12,12]    
    //            [9,10,11,12,12,12]    
    //    ]    
  ```     
    
    
tags:    
  -  螺旋矩阵  
  -  找规律  
