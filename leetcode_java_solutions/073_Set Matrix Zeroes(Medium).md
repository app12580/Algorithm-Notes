### description    
  Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.  
    
  Example 1:  
    
  Input:   
  [  
    [1,1,1],  
    [1,0,1],  
    [1,1,1]  
  ]  
  Output:   
  [  
    [1,0,1],  
    [0,0,0],  
    [1,0,1]  
  ]  
  Example 2:  
    
  Input:   
  [  
    [0,1,2,0],  
    [3,4,5,2],  
    [1,3,1,5]  
  ]  
  Output:   
  [  
    [0,0,0,0],  
    [0,4,5,0],  
    [0,3,1,0]  
  ]  
  Follow up:  
    
  A straight forward solution using O(mn) space is probably a bad idea.  
  A simple improvement uses O(m + n) space, but still not the best solution.  
  Could you devise a constant space solution?  
### solution    
```    
class Solution {  
    public void setZeroes(int[][] matrix) {  
         int m = matrix.length;  
        int n = matrix[0].length;  
        boolean firstCol = false;  
        for(int i = 0; i < m; i++) {  
            if(matrix[i][0] == 0) {  
                firstCol = true;  
            }  
            for(int j = 1; j < n; j++) {  
                if(matrix[i][j] == 0) {  
                    matrix[i][0] = 0;  
                    matrix[0][j] = 0;  
                }  
            }  
        }  
        for(int i = m - 1; i >= 0; i--) {       //这里需要注意，把第一行的放在最后，因为第一行的本身作为列标记，但是会被首行影响，所以需要发挥作用后再处理  
            for(int j = 1; j < n; j++) {  
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {  
                    matrix[i][j] = 0;  
                }  
            }  
            if(firstCol) matrix[i][0] = 0;  
        }  
    }  
}  
```    
    
### 个人解读    
  m*n的是弄成一个标记。  
  m+n的可以用一个set存储哪些row和哪些col。  
  in-place的话需要临时标记，并且还需要跳过0。  
    
  in-place的解法：  
  标记法的高级应用：使用处理结果作为标记。  
  以最左列作为行标记，最上行(去除第一个)作为列标记，单独弄一个作为第一列的标记。  
   
    
tags:    
  -  矩阵  
  -  标记法的高级应用  
