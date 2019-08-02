### description    
  You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.  
    
  If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.  
    
  Operations allowed:  
    
  Fill any of the jugs completely with water.  
  Empty any of the jugs.  
  Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.  
  Example 1: (From the famous "Die Hard" example)  
    
  Input: x = 3, y = 5, z = 4  
  Output: True  
  Example 2:  
    
  Input: x = 2, y = 6, z = 5  
  Output: False  
### solution    
```    
  class Solution {  
      public boolean canMeasureWater(int x, int y, int z) {  
          return z == 0 || (z <= x + y && z % gcd(x,y) == 0);  
      }  
    
      private int gcd(int x, int y) {  
          return y == 0 ? x : gcd(y, x % y);  
      }  
  }  
```    
    
### 个人解读    
  重点数学问题，一开始只会想着通过BFS或者DFS去解决，但是有更好的数学模型。  
  首先想到，对于最后的水，要么是+-x，要么是+-y，所以需要找到一组ab使得ax+by=z，只要能找到，则返回true。  
  有个叫裴蜀定理的东西，只要满足z是xy最大公约数的倍数即可。  
    
  注意： input 1,2,3 也算true  
    
tags:    
  -  重点数学  
