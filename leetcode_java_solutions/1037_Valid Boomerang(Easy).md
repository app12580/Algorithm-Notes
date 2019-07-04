### description    
  A boomerang is a set of 3 points that are all distinct and not in a straight line.  
    
  Given a list of three points in the plane, return whether these points are a boomerang.  
    
     
    
  Example 1:  
    
  Input: [[1,1],[2,3],[3,2]]  
  Output: true  
  Example 2:  
    
  Input: [[1,1],[2,2],[3,3]]  
  Output: false  
     
    
  Note:  
    
  points.length == 3  
  points[i].length == 2  
  0 <= points[i][j] <= 100  
    
  判断三个点是否在一条直线上  
    
### solution    
```    
  class Solution {  
      public boolean isBoomerang(int[][] points) {  
          if (points.length != 3) {  
              return false;  
          }  
          int x1 = points[0][0];  
          int y1 = points[0][1];  
          int x2 = points[1][0];  
          int y2 = points[1][1];  
          int x3 = points[2][0];  
          int y3 = points[2][1];  
          if(x1 == x2 && y1 == y2 || x1 == x3 && y1 == y3 ||x3 == x2 && y3 == y2) {  
              return false;  
          }  
       
          long s1 = (long)(x1 - x2) * (y3 - y2);  
          long s2 = (long)(x3 - x2) * (y1 - y2);  
            
          return s1 != s2;  
      }  
  }  
```    
    
### 个人解读    
  一步一步判断就完事了。  
    
  因为测试用例范围很小，所以不用考虑超限问题。  
    
tags:    
  -  流水账  
