### description    
  Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).  
    
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
  Output: 3  
  Explanation: B = [4,6,3]  
     
    
  Note:  
    
  1 <= A.length <= 10000  
  0 <= A[i] <= 10000  
  0 <= K <= 10000  
### solution    
```    
Runtime: 9 ms, faster than 73.98% of Java online submissions for Smallest Range II.  
Memory Usage: 38.7 MB, less than 100.00% of Java online submissions for Smallest Range II.  
  
  class Solution {  
      public int smallestRangeII(int[] A, int K) {  
          Arrays.sort(A);  
          int res = A[A.length - 1] - A[0];  
          int max;  
          int min;  
          for(int i = 0; i < A.length - 1; i++) {  
            //i点的核心意义：[0,i]的点都要+2K,[i+1,-1]的点都要+0  
              max = Math.max(A[i] + 2 * K, A[A.length - 1]);  
              min = Math.min(A[0] + 2 * K, A[i + 1]);  
              res = Math.min(max - min, res);  
          }  
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  首先两种方法，要么数学法，要么遍历。  
  数学法比较靠谱，需要获取数列最大值和最小值，如果max-min >=2K，问题可解，否则需要另行分析。  
  为了使变形之后的极差最小，所以需要向中心靠拢，考虑(max + min) / 2 为中心，然后数组向中心扩展。  
    
  反思：  
  1、根据用例才发现的规律：  
  ```  
  [4,7,4]  
  4  
    
  如果max - min <= K  
  ```  
    
  失败，基本上是遇到一个错误算例然后就打补丁，连着改了七八遍，感觉这种mid两边向中心靠拢的思路宣告破产。  
  错误原因：   
  a b      c  
        acb  
    中间的某两个数字，一个向右成为最大，另一个向左成为最小。并且这两个数字还是要相邻的。  
    
  总结：  
  最终操作是某个点左边全部+K,右边全部-K,然而问题是这个点在哪需要遍历一遍。  
    
  错误算法  
  ```  
  class Solution {  
         public int smallestRangeII(int[] A, int K) {  
          int min = A[0];  
          int max = A[0];  
          for(int a: A) {  
              min = Math.min(min, a);  
              max = Math.max(max, a);  
          }  
          if(max - min <= K) {  
              return max - min;  
          }  
          int res = max - min;  
          int mid = min + (max - min) / 2;  
          min = Integer.MAX_VALUE;  
          max = Integer.MIN_VALUE;  
          boolean flag = false;  
          for(int a: A) {  
              if(a == mid) {  
                  flag = true;  
              } else if(a > mid) {  
                  min = Math.min(min, a - K);  
                  max = Math.max(max, a - K);  
              } else {  
                  min = Math.min(min, a + K);  
                  max = Math.max(max, a + K);  
              }  
          }  
          if(flag) {  
              if(min == Integer.MAX_VALUE && max == Integer.MIN_VALUE) {  
                  return 0;  
              } else if(min == Integer.MAX_VALUE) {  
                  // 说明全部上调了  
                  return mid + K - max;  
              } else if(max == Integer.MIN_VALUE) {  
                  return min - (mid - K);  
              }  
              return Math.min(Math.max(max, mid + K) - min, max - Math.min(min, mid - K));  
          }  
          return Math.min(res, max - min);  
      }  
    
  }  
  ```  
    
tags:    
  -  重点数学  
  -  数组  
