### description    
  Find the total area covered by two rectilinear rectangles in a 2D plane.  
    
  Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.  
    
  Rectangle Area  
    
  Example:  
    
  Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2  
  Output: 45  
  Note:  
    
  Assume that the total area is never beyond the maximum possible value of int.  
    
  计算重叠后的矩形面积  
### solution    
```    
  class Solution {  
       public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {  
          //AC EG  
          int res = 0;  
          res += Math.abs((C - A) * (D - B));  
          res += Math.abs((G - E) * (H - F));  
          res -= helper(A, C, E, G) * helper(B, D , F, H);  
          return res;  
      }  
    
      private int helper(int a, int b, int c, int d) {  
          if(a < d && c < b) {  
              return d - c + b - a - (Math.max(b,d) - Math.min(a,c));  
          } else {  
              return 0;  
          }  
      }  
  }  
```    
    
### 个人解读    
  主要难点在于求(a1,a2)和(a3,a4)两个区间的重叠区间大小。  
    
  需要先分开计算两个矩形面积，然后减去重合部分的面积。  
  计算区间大小的算法： 两段长度相加，然后减去两个最值的范围。需要先判断是重合。  
    
tags:    
  -  区间重合  
