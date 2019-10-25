### description    
  Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.  
    
  Example:  
    
  Input: matrix = [[1,0,1],[0,-2,3]], k = 2  
  Output: 2   
  Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,  
               and 2 is the max number no larger than k (k = 2).  
  Note:  
    
  The rectangle inside the matrix must have an area > 0.  
  What if the number of rows is much larger than the number of columns?  
### solution    
```    
Runtime: 84 ms, faster than 63.30% of Java online submissions for Max Sum of Rectangle No Larger Than K.  
Memory Usage: 44.4 MB, less than 66.67% of Java online submissions for Max Sum of Rectangle No Larger Than K.  
  
  class Solution {  
     public int maxSumSubmatrix(int[][] matrix, int target) {  
          int row = matrix.length;  
          if(row==0)return 0;  
          int col = matrix[0].length;  
          int m = Math.min(row,col);  
          int n = Math.max(row,col);  
          //indicating sum up in every row or every column  
          boolean colIsBig = col>row;  
          int res = Integer.MIN_VALUE;  
          for(int i = 0;i<m;i++){  
              int[] array = new int[n];  
              // sum from row j to row i  
              for(int j = i;j>=0;j--){  
                  int val = 0;  
                  TreeSet<Integer> set = new TreeSet<Integer>();  
                  set.add(0);  
                  //traverse every column/row and sum up  
                  for(int k = 0;k<n;k++){  
                      array[k]=array[k]+(colIsBig?matrix[j][k]:matrix[k][j]);  
                      val = val + array[k];  
                      //use  TreeMap to binary search previous sum to get possible result   
                      Integer subres = set.ceiling(val-target);  
                      if(null!=subres){  
                          res=Math.max(res,val-subres);  
                      }  
                      set.add(val);  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  类似于之前的那个矩阵求最大可行矩形的题目，也是要把矩阵进行一维化，一层层的将数据压缩在一起下来。  
  除以此外，还通过一个treeSet很优雅的求出了区间最大和。  
  还有m和n的判断，用同一份代码实现了一行行推进和一列列推进。  
  可以说这一份参考答案代码很精致了  
    
    
  矩阵一维化模板：  
  ```  
      for(int i = 0; i < m; i++) {  
        for(int j = i; j >= 0; j--){  
            用一个一维数组存储结果  
        }    
      }  
        
      i=0  [0,0]  
      i=1  [1,1] [0,1]  
      i=2  [2,2] [1,2] [0,2]  
      这样子可以优雅的遍历出所有可能的区间和。  
  ```  
    
    
tags:    
  -  矩阵  
  -  区间和  
  -  矩阵一维化  
