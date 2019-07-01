### description    
  You are standing at position 0 on an infinite number line. There is a goal at position target.  
    
  On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.  
    
  Return the minimum number of steps required to reach the destination.  
    
  Example 1:  
  Input: target = 3  
  Output: 2  
  Explanation:  
  On the first move we step from 0 to 1.  
  On the second step we step from 1 to 3.  
  Example 2:  
  Input: target = 2  
  Output: 3  
  Explanation:  
  On the first move we step from 0 to 1.  
  On the second move we step  from 1 to -1.  
  On the third move we step from -1 to 2.  
  Note:  
  target will be a non-zero integer in the range [-10^9, 10^9].  
### solution    
```    
  class Solution {  
     public int reachNumber(int target) {  
          target = Math.abs(target);  
          int step = 0;  
          int sum = 0;  
          while (sum < target) {  
              step++;  
              sum += step;  
          }  
          while ((sum-target) %2 != 0) {  
              step++;  
              sum += step;  
          }  
          return step;  
      }  
  }  
```    
    
### 个人解读    
  第一反应是递归，但是递归有问题，正负数两个方向，数组大小要不要越过target都是问题。  
  然后发现，如果先左再右这样相当于向右移动一步，感觉这是一个突破点。  
    
  首先前几步尽量靠近target，等到最后再左右横跳，问题是这样是不是最短的。  
    
  最后发现一个核心问题  
  ```  
  With a number of step x, we have characteristics as below:  
    
  f(x) - sum interger number from (1 -> x)  
  all possible targets can be generated from x steps:  
  [-f(x), -f(x) + 2, -f(x) + 4, ..., f(x) - 4, f(x) -2, f(x)]  
  Example: With x = 4, all value of target can be generated: [-10, -8, -6, -4, -2, 0, 2, 4, 6, 8, 10]. With x = 5, we got: [-15, -13, ..., -1, 1, 3, ..., 13, 15]  
  If f(x) is odd, it only can generate all odd target. If f(x) is even, the targets will be even.  
  Now, Given a target, we just need to find the mininum x which has f(x) >= target, and f(x) % 2 == target % 2.  
  ```  
    
  对于前4个数字，可以遍历到[-10,10]中的所有偶数，这是肥肠重要的信息。  
    
tags:    
  -  重点数学  
