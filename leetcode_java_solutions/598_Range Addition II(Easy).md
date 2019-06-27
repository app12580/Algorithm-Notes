### description    
  Given an m * n matrix M initialized with all 0's and several update operations.  
    
  Operations are represented by a 2D array, and each operation is represented by an array with two positive integers a and b, which means M[i][j] should be added by one for all 0 <= i < a and 0 <= j < b.  
    
  You need to count and return the number of maximum integers in the matrix after performing all the operations.  
    
  Example 1:  
  Input:   
  m = 3, n = 3  
  operations = [[2,2],[3,3]]  
  Output: 4  
  Explanation:   
  Initially, M =   
  [[0, 0, 0],  
   [0, 0, 0],  
   [0, 0, 0]]  
    
  After performing [2,2], M =   
  [[1, 1, 0],  
   [1, 1, 0],  
   [0, 0, 0]]  
    
  After performing [3,3], M =   
  [[2, 2, 1],  
   [2, 2, 1],  
   [1, 1, 1]]  
    
  So the maximum integer in M is 2, and there are four of it in M. So return 4.  
  Note:  
  The range of m and n is [1,40000].  
  The range of a is [1,m], and the range of b is [1,n].  
  The range of operations size won't exceed 10,000.  
### solution    
```    
  
  class Solution {  
      public int maxCount(int m, int n, int[][] ops) {  
          Integer minI = Integer.MAX_VALUE;  
          Integer minJ = Integer.MAX_VALUE;  
          int cnt = 0;  
    
          for(int i = 0; i < ops.length; i++) {  
              int x = ops[i][0];  
              int y = ops[i][1];  
              if(x == 0 || y == 0) {  
                  cnt++;  
                  continue;  
              }  
              minI = Math.min(x, minI);  
              minJ = Math.min(y, minJ);  
          }  
          if(cnt == ops.length) return m * n;  
          return minI * minJ;  
      }  
  }  
```    
    
### 个人解读    
  两种思路： 一、先把操作后的矩阵求出来，然后再计算。 二、通过数学方法，直接把最大值找出来。  
    
  解读：每次增加数值，都是从左上角拉出来一个矩形。所以M[0][0]一定是最大，并且等于ops中不含i、j等于0的。  
  感觉解法出来了，遍历ops，获取i的最小值，然后再乘以j的最小值，二者相乘就是结果。  
    
  测试用例感觉不全，如果ops里面超过m和n的情况好像没有考虑诶。  
    
tags:    
  -  数学  
