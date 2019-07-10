### description    
  Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:  
    
  Integers in each row are sorted from left to right.  
  The first integer of each row is greater than the last integer of the previous row.  
  Example 1:  
    
  Input:  
  matrix = [  
    [1,   3,  5,  7],  
    [10, 11, 16, 20],  
    [23, 30, 34, 50]  
  ]  
  target = 3  
  Output: true  
  Example 2:  
    
  Input:  
  matrix = [  
    [1,   3,  5,  7],  
    [10, 11, 16, 20],  
    [23, 30, 34, 50]  
  ]  
  target = 13  
  Output: false  
### solution    
```    
//方法一： 二分法  
Runtime: 4 ms, faster than 14.08% of Java online submissions for Search a 2D Matrix.  
Memory Usage: 37.7 MB, less than 98.19% of Java online submissions for Search a 2D Matrix.  
  
  class Solution {  
      public boolean searchMatrix(int[][] matrix, int target) {  
          if(matrix.length == 0) return false;  
          int m = matrix.length, n = matrix[0].length;  
          int l1 = 0, l2 = 0, h1 = m - 1, h2 = n - 1;  
          while(l1 * n + l2 <= h1 * n + h2) {  
  //            int m1 = l1 + (h1 - l1) / 2;  
  //            int m2 = l2 + (h2 - l2) / 2;  
              int distance = (l1 * n + l2 + h1 * n + h2) / 2;  
              int m1 = distance / n;  
              int m2 = distance % n;  
    
              System.out.println(m1 + "; " + m2);  
              int v = matrix[m1][m2];  
              if(v == target) {  
                  return true;  
              } else if(v > target) {  
                  if(m2 > 0) {  
                      h2 = m2 - 1;  
                      h1 = m1;  
                  } else {  
                      h2 = n - 1;  
                      h1 = m1 - 1;  
                  }  
              } else {  
                  if(m2 < n - 1) {  
                      l2 = m2 + 1;  
                      l1 = m1;  
                  } else {  
                      l2 = 0;  
                      l1 = m1 + 1;  
                  }  
              }  
    
          }  
    
          return false;  
      }  
  }  
    
  // 方法二：优化  
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.  
  Memory Usage: 43 MB, less than 5.03% of Java online submissions for Search a 2D Matrix.  
    
  class Solution {  
      public boolean searchMatrix(int[][] matrix, int target) {  
          if (matrix.length == 0 || matrix[0].length == 0) return false;  
          int m = matrix.length, n = matrix[0].length;  
          int rowL = 0;  
          int rowH = m - 1;  
          //应用三： 小于等于target的最右值  
          while(rowL <= rowH) {  
              int rowM = rowL + (rowH - rowL) / 2;  
              if(matrix[rowM][0] == target) {  
                  return true;  
              } else if(matrix[rowM][0] < target) {  
                  rowL = rowM + 1;  
              } else {  
                  rowH = rowM - 1;  
              }  
          }  
          int colL = 0;  
          int colH = n - 1;  
          if(rowH == -1) return false;      //这一行是关键  
          while(colL <= colH) {  
              int colM = colL + (colH - colL) / 2;  
              int v = matrix[rowH][colM];  
              if(v == target) {  
                  return true;  
              } else if(v < target) {  
                  colL = colM + 1;  
              } else {  
                  colH = colM - 1;  
              }  
          }  
          return false;  
      }  
  }  
```    
    
### 个人解读    
  思路一： 使用了二分法的模型一，主要难点在于点位置的判断，利用表达式：l1 * n + l2  
    
  看到了方法一效率很低，然后想到了二分法的另外一种应用解法，先二分法找到row，然后再找col。  
    
  算法都是要依据人脑的解决思路(如何想到解法)。  
  研究算法模板帮助真的很大  
    
tags:    
  -  计算分步  
  -  二分法应用三  
