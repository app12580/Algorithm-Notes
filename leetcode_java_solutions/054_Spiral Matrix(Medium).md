### description    
  Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.  
    
  Example 1:  
    
  Input:  
  [  
   [ 1, 2, 3 ],  
   [ 4, 5, 6 ],  
   [ 7, 8, 9 ]  
  ]  
  Output: [1,2,3,6,9,8,7,4,5]  
  Example 2:  
    
  Input:  
  [  
    [1, 2, 3, 4],  
    [5, 6, 7, 8],  
    [9,10,11,12]  
  ]  
  Output: [1,2,3,4,8,12,11,10,9,5,6,7]  
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix.  
Memory Usage: 34.5 MB, less than 99.79% of Java online submissions for Spiral Matrix.  
  
  
  class Solution {  
        private int[][] directions = {{1,0},{0,-1},{-1,0},{0,1}};  
      public List<Integer> spiralOrder(int[][] matrix) {  
          List<Integer> res = new ArrayList<>();  
          if(matrix.length == 0) return res;  
          int m =matrix.length;  
          int n = matrix[0].length;  
          for(int i = 0; i < n; i++) {  
              res.add(matrix[0][i]);  
          }  
          int r = m;  
          int c = n;  
          int d = 3;  
          int cnt = (m - 1) * n;  
          int x = 0;  
          int y = n - 1;  
          while(cnt > 0) {  
              r = r - 1;  
              d = (d+1) % 4;  
              for(int i = 0; i < r; i++) {  
                  x = x + directions[d][0];  
                  y = y + directions[d][1];  
                  res.add(matrix[x][y]);  
                  cnt--;  
              }  
              if(cnt == 0) {  
                  break;  
              }  
    
              c = c - 1;  
              d = (d+1) % 4;  
              for(int i = 0; i < c; i++) {  
                  x = x + directions[d][0];  
                  y = y + directions[d][1];  
                  res.add(matrix[x][y]);  
                  cnt--;  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  自动转向问题，方向的问题很好搞定了，问题是步长的怎么解决。标记法太耗时，试试找规律。  
    
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
  -  矩阵  
  -  数学法控制方向  
