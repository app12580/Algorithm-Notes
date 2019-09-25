### description    
  There is a special square room with mirrors on each of the four walls.  Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.  
    
  The square room has walls of length p, and a laser ray from the southwest corner first meets the east wall at a distance q from the 0th receptor.  
    
  Return the number of the receptor that the ray meets first.  (It is guaranteed that the ray will meet a receptor eventually.)  
    
     
    
  Example 1:  
    
  Input: p = 2, q = 1  
  Output: 2  
  Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.  
    
    
    
  Note:  
    
  1 <= p <= 1000  
  0 <= q <= p  
    
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Mirror Reflection.  
Memory Usage: 33 MB, less than 16.67% of Java online submissions for Mirror Reflection.  
  
  class Solution {  
      public int mirrorReflection(int p, int q) {  
          while (p % 2 == 0 && q % 2 == 0) {  
              p /= 2;  
              q /= 2;  
          }  
          if (p % 2 == 0) {  
              return 2;  
          } else if (q % 2 == 0) {  
              return 0;  
          } else {  
              return 1;  
          }  
      }  
  }  
```    
    
### 个人解读    
  本题目主要核心在于模型的确立。  
    
  5       5  
  4       4  
  3       3  
  2       2  
  1       1  
  0       0  
   
  撞到顶以后，0->3->4  
  P - ((3+3) -P) -> 2P - overFlow   
    
  本题目要么一步一步的去算，要么就是通过数学方法，直接算出来。  
    
  唉，好久没看到这么纯数学的方法了，有点莫名其妙的  
  参见(https://leetcode.com/problems/mirror-reflection/discuss/141765/Java-short-solution-with-a-sample-drawing)  
    
tags:    
  -  重点数学  
