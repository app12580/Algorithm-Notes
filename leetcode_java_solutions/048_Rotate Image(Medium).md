### description    
  You are given an n x n 2D matrix representing an image.  
    
  Rotate the image by 90 degrees (clockwise).  
    
  Note:  
    
  You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.  
    
  Example 1:  
    
  Given input matrix =   
  [  
    [1,2,3],  
    [4,5,6],  
    [7,8,9]  
  ],  
    
  rotate the input matrix in-place such that it becomes:  
  [  
    [7,4,1],  
    [8,5,2],  
    [9,6,3]  
  ]  
  Example 2:  
    
  Given input matrix =  
  [  
    [ 5, 1, 9,11],  
    [ 2, 4, 8,10],  
    [13, 3, 6, 7],  
    [15,14,12,16]  
  ],   
    
  rotate the input matrix in-place such that it becomes:  
  [  
    [15,13, 2, 5],  
    [14, 3, 4, 1],  
    [12, 6, 8, 9],  
    [16, 7,10,11]  
  ]  
### solution    
```    
  class Solution {  
       public void rotate(int[][] matrix) {  
          int m = matrix.length;  
          int n = matrix[0].length;  
          int[][] res = new int[n][m];  
          //只遍历一半  
          for(int i = 0; i < m; i++) {  
              for(int j = i + 1; j < n; j++) {  
                  matrix[i][j] = matrix[i][j] ^ matrix[j][i];  
                  matrix[j][i] = matrix[i][j] ^ matrix[j][i];  
                  matrix[i][j] = matrix[i][j] ^ matrix[j][i];  
              }  
          }  
          for(int[] arr: matrix) {  
              reverse(arr);  
          }  
      }  
    
      private void reverse(int[] arr) {  
          int l = 0;  
          int h = arr.length - 1;  
          while(l < h) {  
              arr[l] = arr[l] ^ arr[h];  
              arr[h] = arr[l] ^ arr[h];  
              arr[l] = arr[l] ^ arr[h];  
              l++;  
              h--;  
          }  
      }  
  }  
```    
    
### 个人解读    
  主要是找到ij变化后的规律：  
  两种思路：  
  1、完整写出，然后找规律  
  ```  
  00 01  
  10 11  
  20 21  
  30 31  
    
  30 20 10 00  
  31 21 11 01  
    
  m = 2 , n = 4  
    
  00->03  
  01->13  
  10->02  
  11->12  
  20->01  
  21->11  
  30->01  
  31->10  
    
  23位相等  
  14位和为3  
  ```  
  2、图形分析  
    
  首先ij互换的话，长什么样子，再进行比较，发现后续横坐标不变，纵坐标与n反转。  
  x2 = y1  
  y2 = n - x1 - 1  
    
  可能数学上有更高级的向量解释方法。  
    
  难点二： 如何in-place解决问题  
  可以根据图形分析，先ij互换，然后再每一行反转。  
    
    
tags:    
  -  矩阵  
  -  O(n)Place  
