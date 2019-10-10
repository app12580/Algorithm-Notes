### description    
  A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:  
    
  For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;  
  OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.  
  That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.  
    
  Return the length of a maximum size turbulent subarray of A.  
    
     
    
  Example 1:  
    
  Input: [9,4,2,10,7,8,8,1,9]  
  Output: 5  
  Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])  
  Example 2:  
    
  Input: [4,8,12,16]  
  Output: 2  
  Example 3:  
    
  Input: [100]  
  Output: 1  
     
    
  Note:  
    
  1 <= A.length <= 40000  
  0 <= A[i] <= 10^9  
### solution    
```    
//方法一：  
Runtime: 7 ms, faster than 25.86% of Java online submissions for Longest Turbulent Subarray.  
Memory Usage: 42.4 MB, less than 100.00% of Java online submissions for Longest Turbulent Subarray.  
  
  class Solution {  
     public int maxTurbulenceSize(int[] A) {  
          int res = 0;  
          int index = 0;  
          int left = 0;  
          int pre = 0;  
          while (index < A.length) {  
              if (index == left) {  
                  res = 1;  
              } else if (A[index] == A[index - 1]) {  
                  left = index;  
                  pre = 1;  
              } else if (index == left + 1) {  
                  pre = 2;  
                  res = Math.max(res, 2);  
              } else {  
                  if(diff(A[index] - A[index-1], A[index - 1] - A[index - 2])) {  
                      pre++;  
                      res = Math.max(pre, res);  
                  } else {  
                      left = index - 1;  
                      pre = 2;  
                  }  
              }  
              index++;  
          }  
          return res;  
    
      }  
    
      // 如果为true，表示两者异号  
      public boolean diff(int a, int b) {  
          if (a == 0 || b == 0) return false;  
          return a > 0 && b < 0 || a < 0 && b > 0;  
      }  
  }  
```    
    
### 个人解读    
  curLen问题。  
  思路一：标记法  
  一次成，开森~  
    
tags:    
  -  curLen  
  -  标记法  
