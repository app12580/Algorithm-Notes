### description    
  Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:  
    
  Integers in each row are sorted in ascending from left to right.  
  Integers in each column are sorted in ascending from top to bottom.  
  Example:  
    
  Consider the following matrix:  
    
  [  
    [1,   4,  7, 11, 15],  
    [2,   5,  8, 12, 19],  
    [3,   6,  9, 16, 22],  
    [10, 13, 14, 17, 24],  
    [18, 21, 23, 26, 30]  
  ]  
  Given target = 5, return true.  
    
  Given target = 20, return false.  
### solution    
```    
  class Solution {  
     public boolean searchMatrix(int[][] matrix, int target) {  
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;  
      int m = matrix.length, n = matrix[0].length;  
      int row = 0, col = n - 1;  
      while (row < m && col >= 0) {  
          if (target == matrix[row][col]) return true;  
          else if (target < matrix[row][col]) col--;  
          else row++;  
      }  
      return false;  
  }  
  }  
```    
    
### 个人解读    
  看到这题目的时候，脑海里的想法就是多线去找，要么DFS要么BFS，然而竟然有算法可以一条路走到通？？  
  反思： 看到此题目时候，首先不能无脑的就直接想着BFS一路搜过去，从左上角扩散出去。需要具体到点对点的情形考虑。  
```  
  当nums[i][j] < target时候，target可能在[i][j]的右上，左下，右下  
  当nums[i][j] > target时候，target可能在[i][j]的右上，左下，左上  
```  
  可能是因为我的大脑在收到数组的排序方式时候，就自动判断出了上面复杂的两种情形而使得这段信息被过滤掉了  
  每种分为三个区域会很麻烦  
  但是！！！如果[i][j]没有右上或者[i][j]没有左下时候，会发生什么  
  ```  
    右上角时  
    当nums[i][j] < target时候，target可能在[i][j]的左下，右下  
    当nums[i][j] > target时候，target可能在[i][j]的左下，左上  
      
    等价于：  
     当nums[i][j] < target时候，target可能在[i][j]的下边  
     当nums[i][j] > target时候，target可能在[i][j]的左边  
  ```  
  问题迎刃而解，至少思路上有了很大突破  
    
  //参考了《剑指offer》  
tags:    
  -  矩阵   
  -  从多线计算转化成单线计算   
