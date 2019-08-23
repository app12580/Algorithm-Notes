### description      
  Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.    
      
       
      
  Example:    
      
  Input:    
  [    
   [ 1, 2, 3 ],    
   [ 4, 5, 6 ],    
   [ 7, 8, 9 ]    
  ]    
      
  Output:  [1,2,4,7,5,3,6,8,9]    
      
  Explanation:    
      
       
      
  Note:    
      
  The total number of elements of the given matrix will not exceed 10,000.    
### solution      
```      
Runtime: 2 ms, faster than 92.43% of Java online submissions for Diagonal Traverse.    
Memory Usage: 46 MB, less than 37.50% of Java online submissions for Diagonal Traverse.    
    
  class Solution {    
     public int[] findDiagonalOrder(int[][] matrix) {    
          if(matrix.length  == 0) return new int[0];    
          int len1 = matrix.length;    
          int len2 = matrix[0].length;    
          int[] res = new int[len1 * len2];    
      
          int x = 0;    
          int y = 0;    
          int index = 0;    
          int[] x_up = {-1, 1};    
          int[] x_down = {1, -1};    
          int[] right = {0, 1};    
          int[] down = {1, 0};    
      
          res[index++] = matrix[0][0];    
          boolean isUp = true;    
          while(index < res.length) {    
              if(isUp) {    
                  //尝试能否向上    
                  if(x - 1 >= 0 && y + 1 < len2) {    
                      //可以向上    
                      x -= 1;    
                      y += 1;    
                      res[index++] = matrix[x][y];    
                  } else {    
                      // 分情况向右还是向下    
                      if(y < len2 - 1) {    
                          y += 1;    
                      } else {    
                          x += 1;    
                      }    
                      res[index++] = matrix[x][y];    
                      isUp = false;    
                  }    
              } else {    
                  //尝试能否向下    
                  if(x + 1 < len1 && y - 1 >= 0) {    
                      //可以向上    
                      x += 1;    
                      y -= 1;    
                      res[index++] = matrix[x][y];    
                  } else {    
                      // 分情况向下还是向右    
                      if(x < len1 - 1) {    
                          x += 1;    
                      } else {    
                          y += 1;    
                      }    
                      res[index++] = matrix[x][y];    
                      isUp = true;    
                  }    
              }    
          }    
          return res;    
      }    
  }    
```      
      
### 个人解读      
  有两种大局思想：控制for循环次数遍历or遇到障碍再换方向。    
      
  控制循环for次数，需要判断length是奇数还是偶数。可以逆向操作。    
      
  WTF？？不知道为什么，竟然没有注意到源数组不是正方形矩阵，给它默认成了正方形了。。。    
  ```    
  class Solution {    
       public int[] findDiagonalOrder(int[][] matrix) {    
          int len = matrix.length;    
          int[] res = new int[len * len];    
          int[] x_up = {-1, 1};    
          int[] x_down = {1, -1};    
          int[] right = {0, 1};    
          int[] down = {1, 0};    
      
          int x = -1;    
          int y = 0;    
          int index = 0;    
          for(int i = 1; i <= len; i++) {    
              if(i % 2 == 1) {    
                  x += down[0];    
                  y += down[1];    
                  res[index++] = matrix[x][y];    
                  for(int j = 0; j < i - 1; j++) {    
                      x += x_up[0];    
                      y += x_up[1];    
                      res[index++] = matrix[x][y];    
                  }    
              } else {    
                  x += right[0];    
                  y += right[1];    
                  res[index++] = matrix[x][y];    
                  for(int j = 0; j < i - 1; j++) {    
                      x += x_down[0];    
                      y += x_down[1];    
                      res[index++] = matrix[x][y];    
                  }    
              }    
          }    
      
          x = len ;    
          y = len - 1;    
          index = res.length - 1;    
          for(int i = 1; i < len; i++) {    
              if(i % 2 == 1) {    
                  x += -1;    
                  y += 0;    
                  res[index--] = matrix[x][y];    
                  for(int j = 0; j < i - 1; j++) {    
                      x += x_down[0];    
                      y += x_down[1];    
                      res[index++] = matrix[x][y];    
                  }    
              } else {    
                  x += 0;    
                  y += -1;    
                  res[index--] = matrix[x][y];    
                  for(int j = 0; j < i - 1; j++) {    
                      x += x_up[0];    
                      y += x_up[1];    
                      res[index--] = matrix[x][y];    
                  }    
              }    
          }    
          return res;    
      }    
      
  }    
  ```    
      
tags:      
  -  矩阵  
  -  方向向量  
  -  数学  
