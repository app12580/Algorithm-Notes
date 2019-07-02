### description    
  A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.  
    
  Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).  
    
     
    
  Example 1:  
    
  Input: [[4,3,8,4],  
          [9,5,1,9],  
          [2,7,6,2]]  
  Output: 1  
  Explanation:   
  The following subgrid is a 3 x 3 magic square:  
  438  
  951  
  276  
    
  while this one is not:  
  384  
  519  
  762  
    
  In total, there is only one magic square inside the given grid.  
  Note:  
    
  1 <= grid.length <= 10  
  1 <= grid[0].length <= 10  
  0 <= grid[i][j] <= 15  
### solution    
```    
  class Solution {  
     public int numMagicSquaresInside(int[][] grid) {  
          int row=grid.length;  
          int col=grid[0].length;  
          int result=0;  
          if(row<3||col<3) return 0;  
          for(int i=0;i<=row-3;i++){  
              for(int j=0;j<=col-3;j++){  
                  result = result + judge(grid,i,j);  
              }  
          }  
            return result;  
      }  
      public int judge(int[][] grid,int x,int y){  
          int result=1;  
          int[] poll=new int[9];  
          int sum=0;  
          int temp=0;  
          for(int i=0;i<3;i++){  
              sum+=grid[x][y+i];  
          } //第一列的sum  
          if(sum!=grid[x][y]+grid[x+1][y+1]+grid[x+2][y+2]) result=0;   //45度的sum  
          if(sum!=grid[x+2][y]+grid[x+1][y+1]+grid[x][y+2]) result=0;   //135  
          for(int i=0;i<3;i++)  
              if(sum!=grid[x+i][y]+grid[x+i][y+1]+grid[x+i][y+2]) result=0; //三行  
          for(int i=0;i<3;i++)  
              if(sum!=grid[x][y+i]+grid[x+1][y+i]+grid[x+2][y+i]) result=0; //三列  
          for(int i=0;i<3;i++)  
              for(int j=0;j<3;j++){  
                  if(grid[x+i][y+j]>9||grid[x+i][y+j]<1) return 0;  
                  poll[grid[x+i][y+j]-1]=1;  
              }  
          for(int i:poll) if(i==0) result=0;        //验证是否1-9拉满  
          return result;  
      }  
  }  
```    
    
### 个人解读    
  这题看着很头大，问题在于如何复用遍历过的数据。  
  思路一： 类似于八皇后问题，弄出来一堆数组，rows[][],cols[][],45[][],135[][]  
  讲道理，这种九宫格比很多Medium都要难多了把。。。  
    
  九宫格中心点一定是5。  
    
tags:    
  -  重点数学  
