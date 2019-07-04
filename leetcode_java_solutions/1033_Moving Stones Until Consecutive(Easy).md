### description    
  Three stones are on a number line at positions a, b, and c.  
    
  Each turn, you pick up a stone at an endpoint (ie., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints.  Formally, let's say the stones are currently at positions x, y, z with x < y < z.  You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.  
    
  The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.  
    
  When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]  
    
     
    
  Example 1:  
    
  Input: a = 1, b = 2, c = 5  
  Output: [1,2]  
  Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.  
  Example 2:  
    
  Input: a = 4, b = 3, c = 2  
  Output: [0,0]  
  Explanation: We cannot make any moves.  
  Example 3:  
    
  Input: a = 3, b = 5, c = 1  
  Output: [1,2]  
  Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.  
     
    
  Note:  
    
  1 <= a <= 100  
  1 <= b <= 100  
  1 <= c <= 100  
  a != b, b != c, c != a  
### solution    
```    
  class Solution {  
      public int[] numMovesStones(int a, int b, int c) {  
         if(a > b) {  
              a ^= b;  
              b ^= a;  
              a ^= b;  
          }  
          if(a > c) {  
              a ^= c;  
              c ^= a;  
              a ^= c;  
          }  
          if(b > c) {  
              b ^= c;  
              c ^= b;  
              b ^= c;  
          }  
    
          int max = Math.abs(b - a - 1) + Math.abs(c - b - 1);  
          int min  = 2;  
          if(b - a == 1 && c - b == 1) {  
              min = 0;  
          } else if(b - a == 2 || c - b == 2 || b - a == 1 || c - b == 1) {  
              min = 1;  
          }  
    
          return new int[]{min, max};  
      }  
  }  
```    
    
### 个人解读    
  数学问题，根据差值进行比较。  
    
  刚开始的思维误区： 只能两边往中间靠拢这一种方式。  
  0： 连续  
  1： 某两个值差为2  // 注： 还有差为1 的情况  
  2： 其他情况。  
    
tags:    
  -  数学  
  -  注意细节  
