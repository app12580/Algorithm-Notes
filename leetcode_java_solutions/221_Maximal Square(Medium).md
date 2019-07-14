### description    
  Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.  
    
  Example:  
    
  Input:   
    
  1 0 1 0 0  
  1 0 1 1 1       
  1 1 1 1 1  
  1 0 0 1 0  
    
  Output: 4  
    
  矩阵中的最大矩形  
### solution    
```    
  class Solution {  
      public int maximalSquare(char[][] matrix) {  
          if(matrix==null || matrix.length==0 || matrix[0].length==0) return 0;  
          int rows=matrix.length;  
          int cols=matrix[0].length;  
          int result=0;  
          int[][] dp=new int[rows+1][cols+1];  
    
          for(int r=1;r<=rows;r++){  
              for(int c=1;c<=cols;c++){  
                  if(matrix[r-1][c-1]=='1'){  
                      dp[r][c]=Math.min(Math.min(dp[r-1][c],dp[r-1][c-1]),dp[r][c-1])+1;  
                      result=Math.max(result,dp[r][c]);  
                  }  
              }  
          }  
          return result*result;  
      }  
  }  
```    
    
### 个人解读    
  不会啊，好像是第一次遇到矩阵的curLen问题，完全没有头绪啊。  
    
  主要是dp的设定。  
  dp[i+1][j+1] 表示以matrix[i][j]为右下角的矩形最大是多少。  
    
  1 1 1 1  
  1 2 2 2  
  1 2 3 3  
  1 2 3 4  
    
  仔细思考上面的示例矩阵。只要当前点的上面点、左边点、左上点都OK，那么就可以成功的加上一个数字。  
    
tags:    
  -  矩阵  
  -  curLen  
