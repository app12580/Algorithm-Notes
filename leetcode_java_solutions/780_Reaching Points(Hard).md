### description    
  A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).  
    
  Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.  
    
  Examples:  
  Input: sx = 1, sy = 1, tx = 3, ty = 5  
  Output: True  
  Explanation:  
  One series of moves that transforms the starting point to the target is:  
  (1, 1) -> (1, 2)  
  (1, 2) -> (3, 2)  
  (3, 2) -> (3, 5)  
    
  Input: sx = 1, sy = 1, tx = 2, ty = 2  
  Output: False  
    
  Input: sx = 1, sy = 1, tx = 1, ty = 1  
  Output: True  
### solution    
```    
// 方法一： 展专项储  
  Runtime: 1 ms, faster than 100.00% of Java online submissions for Reaching Points.  
  Memory Usage: 33.3 MB, less than 12.50% of Java online submissions for Reaching Points.  
    
  class Solution {  
      public boolean reachingPoints(int sx, int sy, int tx, int ty) {  
          while(true) {  
              if(tx < sx || ty < sy) return false;  
              if(tx == sx) {    // 当相等时候，说明达到最终阶段，此时的基准是sy和sx  
                  return (ty - sy) % sx == 0;  
              }  
              if(ty == sy) {  
                  return (tx - sx) % sy == 0;  
              }  
              if(tx > ty) {  
                  tx -= ty;  
              } else if(tx < ty){  
                  ty -= tx;  
              } else {  
                  return false;  
              }  
          }  
      }  
  }  
```    
    
### 个人解读    
  感觉有两种可能，一种是尽可能的遍历；另外一种就是参照两水壶接水的那个题目。  
    
  a     b  
  a+b   b  
  a+2b  b  
  a+3b  b  
    
  a     b  
  a     b+a  
  a     b+2a  
  b+3a  b+2a  
  2b+5a b+2a  
    
  (1, 1) -> (1, 2)  
  (1, 2) -> (3, 2)  
  (3, 2) -> (3, 5)  
    
  逆向思维，从后往前看的话，会发现数字都是:a,b(a > b) -> b, a - b。  
  关键效率是1,1 ->1,99999999这种情况  
    
    
tags:    
  -  数学  
  -  数字逻辑  
