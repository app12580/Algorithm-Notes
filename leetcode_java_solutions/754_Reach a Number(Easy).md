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
  
```  
  
### 个人解读  
  第一反应是递归，但是递归有问题，正负数两个方向，数组大小要不要越过target都是问题。
  然后发现，如果先左再右这样相当于向右移动一步，感觉这是一个突破点。
  
  首先前几步尽量靠近target，等到最后再左右横跳，问题是这样是不是最短的。
  
tags:  
  -  数学
