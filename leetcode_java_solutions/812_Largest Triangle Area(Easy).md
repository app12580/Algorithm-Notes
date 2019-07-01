### description    
  You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.  
    
  Example:  
  Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]  
  Output: 2  
  Explanation:   
  The five points are show in the figure below. The red triangle is the largest.  
    
    
  Notes:  
    
  3 <= points.length <= 50.  
  No points will be duplicated.  
   -50 <= points[i][j] <= 50.  
  Answers within 10^-6 of the true value will be accepted as correct.  
    
### solution    
```    
  class Solution {  
      public double largestTriangleArea(int[][] points) {  
          double max = 0;  
          for(int[] i: points) {  
              for(int[] j: points) {  
                  for(int[] k: points) {  
                      max = Math.max(max, 0.5 * Math.abs(i[0]*j[1] + j[0]*k[1] + k[0]*i[1] - i[0]*k[1] - j[0]*i[1] - k[0]*j[1]));  
                  }  
              }  
          }  
          return max;  
      }  
  }  
```    
    
### 个人解读    
  
关键点在于如何求三角形面积。  
  综上所述，在平面直角坐标系中，若A（x1，y1），B（x2，y2），C（x3，y3），则△ABC的面积为1/2｜x1y2＋x2y3＋x3y1－x1y3－x2y1－x3y2｜．  
    
tags:    
  -  数学  
  -  三角形面积  
