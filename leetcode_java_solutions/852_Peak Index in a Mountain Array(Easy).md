### description    
  Let's call an array A a mountain if the following properties hold:  
    
  A.length >= 3  
  There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]  
  Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].  
    
  Example 1:  
    
  Input: [0,1,0]  
  Output: 1  
  Example 2:  
    
  Input: [0,2,1,0]  
  Output: 1  
  Note:  
    
  3 <= A.length <= 10000  
  0 <= A[i] <= 10^6  
  A is a mountain, as defined above.  
### solution    
```    
  class Solution {  
      public int peakIndexInMountainArray(int[] A) {  
          int flag = A[1] - A[0];   //0表示初始，正数表示上升，负数表示下降  
          for(int i = 1; i < A.length - 1; i++) {  
              int cur = A[i + 1] - A[i];  
              if((cur > 0 && flag < 0) || (cur < 0 && flag > 0)) {  
                  return i;  
              } else {  
                  flag = cur;  
              }  
          }  
          return -1;  
      }  
  }  
```    
    
### 个人解读    
  想起来了[376](376_Wiggle%20Subsequence%20(Medium).md)，  
  up = down + 1;  
  down = up + 1;  
  那个神奇的处理。  
    
  凡是遇到乘法都要注意超限问题。  
    
  ```  
  错误： if(cur * flag < 0) {  
   正确： if((cur > 0 && flag < 0) || (cur < 0 && flag > 0))  
  ```  
    
tags:    
  -  数学  
  -  int超限  
