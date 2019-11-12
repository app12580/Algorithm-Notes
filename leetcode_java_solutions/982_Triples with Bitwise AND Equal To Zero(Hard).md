### description    
  Given an array of integers A, find the number of triples of indices (i, j, k) such that:  
    
  0 <= i < A.length  
  0 <= j < A.length  
  0 <= k < A.length  
  A[i] & A[j] & A[k] == 0, where & represents the bitwise-AND operator.  
     
    
  Example 1:  
    
  Input: [2,1,3]  
  Output: 12  
  Explanation: We could choose the following i, j, k triples:  
  (i=0, j=0, k=1) : 2 & 2 & 1  
  (i=0, j=1, k=0) : 2 & 1 & 2  
  (i=0, j=1, k=1) : 2 & 1 & 1  
  (i=0, j=1, k=2) : 2 & 1 & 3  
  (i=0, j=2, k=1) : 2 & 3 & 1  
  (i=1, j=0, k=0) : 1 & 2 & 2  
  (i=1, j=0, k=1) : 1 & 2 & 1  
  (i=1, j=0, k=2) : 1 & 2 & 3  
  (i=1, j=1, k=0) : 1 & 1 & 2  
  (i=1, j=2, k=0) : 1 & 3 & 2  
  (i=2, j=0, k=1) : 3 & 2 & 1  
  (i=2, j=1, k=0) : 3 & 1 & 2  
     
    
  Note:  
    
  1 <= A.length <= 1000  
  0 <= A[i] < 2^16  
    
  按位与之后和为0  
### solution    
```    
// 动态规划：   
Runtime: 1147 ms, faster than 19.15% of Java online submissions for Triples with Bitwise AND Equal To Zero.  
Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Triples with Bitwise AND Equal To Zero.  
  
  class Solution {  
      public int countTriplets(int[] A) {  
          int N = 1 << 16;  
          int[] dp = new int[N];        // 表示经过与运算后，结果为i的数量， 后面会更新三次  
          // 这个初始化是关键  
          dp[N - 1] = 1;        // 因为dp从大到小优化，同时任何和N-1与运算的结果都是N-1  
          for (int i = 0; i < 3; i++) {  
              int[] temp = new int[N];  
              for (int k = 0; k < N; k++) {  
                  for (int a : A) {  
                      temp[k & a] += dp[k];  
                  }  
              }  
              dp = temp;  
          }  
          return dp[0];  
      }  
  }  
```    
    
### 个人解读    
  01 10 11  
    
  必须要有01  
  001： 3  
  011： 3  
  012： 6  
  
  总结： 通过动态规划计数，使用tempDp。然后每加入一个int a，就去遍历dp[t]，然后另dp[t&a] += dp[t];
    
tags:    
  -  动态规划  
  -  位运算  
