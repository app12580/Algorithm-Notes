### description    
  Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.  
    
  To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].  
    
  To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].  
    
  Example 1:  
    
  Input: [[1,1,0],[1,0,1],[0,0,0]]  
  Output: [[1,0,0],[0,1,0],[1,1,1]]  
  Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].  
  Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]  
  Example 2:  
    
  Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]  
  Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]  
  Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].  
  Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]  
  Notes:  
    
  1 <= A.length = A[0].length <= 20  
  0 <= A[i][j] <= 1  
    
  先反转数组，然后01互换。  
### solution    
```    
  class Solution {  
      public int[][] flipAndInvertImage(int[][] A) {  
          int m = A.length;  
          int n = A[0].length;  
          int[][] res = new int[m][n];  
    
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  res[i][j] = 1 - A[i][n - j - 1];  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  这种题类似于那个 return n % 9;的题目，要么按照步骤一步一步来，要么就是找到最终结果所满足的某种特质。  
  本题直接遍历，每次另a[i][j] = 1 - a[i][n - j];  
    
tags:    
  -  矩阵  
