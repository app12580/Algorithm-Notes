### description    
  You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.  
    
  Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.  
    
     
    
  Example 1:  
    
  ┌───┐  
  │   │  
  └───┼──>  
      │  
    
  Input: [2,1,1,2]  
  Output: true  
  Example 2:  
    
  ┌──────┐  
  │      │  
  │  
  │  
  └────────────>  
    
  Input: [1,2,3,4]  
  Output: false   
  Example 3:  
    
  ┌───┐  
  │   │  
  └───┼>  
    
  Input: [1,1,1,1]  
  Output: true   
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Self Crossing.  
Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Self Crossing.  
  
  public class Solution {  
      public boolean isSelfCrossing(int[] x) {  
          int l = x.length;  
          if(l <= 3) return false;  
            
          for(int i = 3; i < l; i++){  
              if(x[i] >= x[i-2] && x[i-1] <= x[i-3]) return true;  //Fourth line crosses first line and onward  
              if(i >=4)  
              {  
                  if(x[i-1] == x[i-3] && x[i] + x[i-4] >= x[i-2]) return true; // Fifth line meets first line and onward  
              }  
              if(i >=5)  
              {  
                  if(x[i-2] - x[i-4] >= 0 && x[i] >= x[i-2] - x[i-4] && x[i-1] >= x[i-3] - x[i-5] && x[i-1] <= x[i-3]) return true;  // Sixth line crosses first line and onward  
              }  
          }  
          return false;  
      }  
  }  
```    
    
### 个人解读    
  关键点一：如何判断两条直线相交？ 类似于区间的相交判定  
  关键点二：是否暴力法？  n条线只会与n-1,n-2,n-3交叉  
  关键点三：要求O(1)space  
  关键点四：是否一定要模拟出来xy坐标系？ 看了很多解答，都没有，都是直接从原来的数组进行分析的。  
    
  方法一： 三种情况分析  
  https://leetcode-cn.com/problems/self-crossing/solution/335lu-jing-jiao-cha-by-zhangll/  
  第一种 条件为 i>=3 || x[i] >= x[i-2] || x[i-1] <= x[i-3]  
  第二种 条件为 i> 3 || x[i-1] == x[i-3] || x[i-4] + x[i] >= x[i-2]  
  第三种 条件为 i> 4 || x[i-3]-x[i-5] <= x[i-1] <= x[i-3] || x[i-2]-x[i-4] <= x[i] <= x[i-2] || x[i-2] > x[i-4]  
    
    
tags:    
  -  数学  
  -  图形法  
  -  究极·分类讨论  
