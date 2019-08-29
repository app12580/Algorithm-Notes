### description  
  There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.
  
  Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
  
  Flip all the lights.
  Flip lights with even numbers.
  Flip lights with odd numbers.
  Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
   
  
  Example 1:
  
  Input: n = 1, m = 1.
  Output: 2
  Explanation: Status can be: [on], [off]
   
  
  Example 2:
  
  Input: n = 2, m = 1.
  Output: 3
  Explanation: Status can be: [on, off], [off, on], [off, off]
   
  
  Example 3:
  
  Input: n = 3, m = 1.
  Output: 4
  Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
   
  
  Note: n and m both fit in range [0, 1000].
### solution  
```  
  class Solution {
      public int flipLights(int n, int m) {
          if(m == 0) return 1;
          if(n == 1) {
              return 2;
          } else if(n == 2) {
              if(m == 1) {
                  return 3;
              } else {
                  return 4;
              }
          } else {
              if(m == 1) {
                  return 4;
              } else if(m == 2) {
                  return 7;
              } else {
                  return 8;
              }
          }
      }
  }
```  
  
### 个人解读  
  最蠢的就是全部遍历。
  
  然后发现有轮回一说。
  
  1、前面三种只有两种情况。
  2、间隔3的话，分为0,1三种情况。
  
  前面三种有冲突的情况。
  
  感觉可以不用遍历，直接计算出来。
  
  00:0,2,3,4
  01:1,2,3,
  10:1,2,3
  11:1,2,3,
  
  100:1,3
  000:0,2
  
  分为按不按第四个按钮的两种情况
  
tags:  
  -  数学
  -  逐条分析
  
