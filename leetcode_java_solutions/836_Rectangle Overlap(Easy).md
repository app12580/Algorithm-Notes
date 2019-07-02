### description    
  A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.  
    
  Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.  
    
  Given two (axis-aligned) rectangles, return whether they overlap.  
    
  Example 1:  
    
  Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]  
  Output: true  
  Example 2:  
    
  Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]  
  Output: false  
  Notes:  
    
  Both rectangles rec1 and rec2 are lists of 4 integers.  
  All coordinates in rectangles will be between -10^9 and 10^9.  
    
  矩阵重叠  
### solution    
```    
  class Solution {  
      public boolean isRectangleOverlap(int[] rec1, int[] rec2) {  
          //        [x1, y1, x2, y2]  
          return rec1[0] < rec2[2] && rec2[0] < rec1[2] && rec1[1] < rec2[3] && rec2[1] < rec1[3];  
      }  
  }  
```    
    
### 个人解读    
  经典算法，先联想一维的情况  
  对于[x1,y1]和[x2,y2]  
  return x1 < y2 && x2 < y1  
  然后二维同理。  
    
  解读：如果两个区间重合，那么一定能找到重合区间的某个值Val，有：  
  x1<Val<y1和x2<Val<y2。  
    
  刚开始傻了，不能用||，而应该用&&，必须x轴和y轴都要交叉才行。  
    
    
tags:    
  -  重点数学  
