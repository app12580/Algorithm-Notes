### description    
  Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].  
    
  After this process, we have some array B.  
    
  Return the smallest possible difference between the maximum value of B and the minimum value of B.  
    
     
    
  Example 1:  
    
  Input: A = [1], K = 0  
  Output: 0  
  Explanation: B = [1]  
  Example 2:  
    
  Input: A = [0,10], K = 2  
  Output: 6  
  Explanation: B = [2,8]  
  Example 3:  
    
  Input: A = [1,3,6], K = 3  
  Output: 0  
  Explanation: B = [3,3,3] or B = [4,4,4]  
     
    
  Note:  
    
  1 <= A.length <= 10000  
  0 <= A[i] <= 10000  
  0 <= K <= 10000  
    
  调整数组，对每个数字进行[-K,+K]范围内的操作，使最终结果的最大值与最小值最接近。  
  只需要返回最终的差。  
    
### solution    
```    
  class Solution {  
      public int smallestRangeI(int[] A, int K) {  
          int min = Integer.MAX_VALUE;  
          int max = Integer.MIN_VALUE;  
          for(int a: A) {  
              min = Math.min(min, a);  
              max = Math.max(max, a);  
          }  
          return Math.max(0, max - min - K - K );  
      }  
  }  
```    
    
### 个人解读    
  数学问题，比较max-min 和 2K  
    
tags:    
  -  数学  
