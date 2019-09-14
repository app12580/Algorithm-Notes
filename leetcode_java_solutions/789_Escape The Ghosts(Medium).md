### description    
  You are playing a simplified Pacman game. You start at the point (0, 0), and your destination is (target[0], target[1]). There are several ghosts on the map, the i-th ghost starts at (ghosts[i][0], ghosts[i][1]).  
    
  Each turn, you and all ghosts simultaneously *may* move in one of 4 cardinal directions: north, east, west, or south, going from the previous point to a new point 1 unit of distance away.  
    
  You escape if and only if you can reach the target before any ghost reaches you (for any given moves the ghosts may take.)  If you reach any square (including the target) at the same time as a ghost, it doesn't count as an escape.  
    
  Return True if and only if it is possible to escape.  
    
  Example 1:  
  Input:   
  ghosts = [[1, 0], [0, 3]]  
  target = [0, 1]  
  Output: true  
  Explanation:   
  You can directly reach the destination (0, 1) at time 1, while the ghosts located at (1, 0) or (0, 3) have no way to catch up with you.  
  Example 2:  
  Input:   
  ghosts = [[1, 0]]  
  target = [2, 0]  
  Output: false  
  Explanation:   
  You need to reach the destination (2, 0), but the ghost at (1, 0) lies between you and the destination.  
  Example 3:  
  Input:   
  ghosts = [[2, 0]]  
  target = [1, 0]  
  Output: false  
  Explanation:   
  The ghost can reach the target at the same time as you.  
  Note:  
    
  All points have coordinates with absolute value <= 10000.  
  The number of ghosts will not exceed 100.  
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Escape The Ghosts.  
Memory Usage: 41 MB, less than 75.00% of Java online submissions for Escape The Ghosts.  
  
  
  class Solution {  
      public boolean escapeGhosts(int[][] ghosts, int[] target) {  
          int dis = Math.abs(target[0]) + Math.abs(target[1]);  
          for(int[] g: ghosts) {  
              if(!check(g, target, dis)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
      private boolean check(int[] g, int[] target, int dis) {  
          int a = g[0] - target[0];  
          int b = g[1] - target[1];  
          int gDis = Math.abs(a) + Math.abs(b);  
          if(gDis <= dis) {  
              return false;  
          }  
          if(g[0] >= 0 && g[1] >= 0 && g[0] <= target[0] && g[1] <= target[1]) {  
              return false;  
          }  
          return true;  
      }  
    
  }  
```    
    
### 个人解读    
  本题目容易让人想到如果绕圈圈怎么办？？  
  因此，本题目隐藏了一条要素，只要鬼比玩家先到，那么就算输了。  
    
  所以先需要判断鬼和玩家到终点的距离，如果玩家更近再判断鬼如何去拦截。  
  需要判断如果在场外，能否拦截到？  
  因为如果拦截到，则证明至少距离是一致的，所以场外就不会拦截到了。  
  (前提，玩家不会绕道，没有任何必要)  
  然而wrong answer了。  
    
  知道问题了。。。不同的ghost是可以合作去拦截的。。。  
    
  猜测一下，拦截的时候，只需要拦截到两条线就好了。  
    
  反思了一下，即使合作拦截了，也是能拦截到就代表鬼能先到。  
  问题在于距离求错了，不能求两点直线距离。  
    
  总结：  
  这种情况下，玩家总最短最中间是最佳玩法。  
   
    
tags:    
  -  AI  
