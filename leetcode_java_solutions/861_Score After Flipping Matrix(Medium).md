### description  
  We have a two dimensional matrix A where each value is 0 or 1.
  
  A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.
  
  After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
  
  Return the highest possible score.
  
   
  
  Example 1:
  
  Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
  Output: 39
  Explanation:
  Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
  0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
   
  
  Note:
  
  1 <= A.length <= 20
  1 <= A[0].length <= 20
  A[i][j] is 0 or 1.
### solution  
```  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Score After Flipping Matrix.
Memory Usage: 38.1 MB, less than 80.00% of Java online submissions for Score After Flipping Matrix.

  class Solution {
      public int matrixScore(int[][] A) {
          int m = A.length;
          int n = A[0].length;
          for(int i = 0; i < m; i++) {
              if(A[i][0] == 0) {
                  for(int j = 0; j < n; j++) {
                      A[i][j] = 1 - A[i][j];
                  }
              }
          }
          int sum = 0;
          int base = 1;
          for(int j = n - 1; j >= 0; j--) {
              int count = 0;
              for(int i = 0; i < m; i++) {
                  if(A[i][j] == 1) {
                      count++;
                  }
              }
              count = Math.max(count, m - count);
              sum += count * base;
              base *= 2;
          }
          return sum;
      }
  }
  
```  
  
### 个人解读  
  {0,0,1,1}
  {1,0,1,0}
  {1,1,0,0}
  
  {1,1,1,1}
  {1,0,0,1}
  {1,1,1,1}
  
  贪婪算法：
  1、先横向改变，将所有首位变成1
  2、从第二列依次纵向，让1变成多数
  3、不需要实际变化，直接统计和即可
  
tags:  
  -  数学
  -  矩阵
