### description    
  Given an array of integers A and let n to be its length.  
    
  Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:  
    
  F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].  
    
  Calculate the maximum value of F(0), F(1), ..., F(n-1).  
    
  Note:  
  n is guaranteed to be less than 105.  
    
  Example:  
    
  A = [4, 3, 2, 6]  //sum = 15  
    
  F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25   
  F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16  // 25 +15 - 4 * 6  
  F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23  // 16 + 15 - 4 * 2  
  F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26  // 23 + 15 - 4 * 3  
    
  So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.  
### solution    
```    
  class Solution {  
      public int maxRotateFunction(int[] A) {  
          int allSum = 0;  
          int len = A.length;  
          int F = 0;  
          for (int i = 0; i < len; i++) {  
              F += i * A[i];  
              allSum += A[i];  
          }  
          int max = F;  
          for (int i = len - 1; i >= 1; i--) {  
              F = F + allSum - len * A[i];  
              max = Math.max(F, max);  
          }  
          return max;     
      }  
  }  
```    
    
### 个人解读    
  思路一就是流水账，每个都算一遍，然后取最大值。  
    
  反思：需要化零为整，当发现某个关于1,2,3,,,,n-1的规律时候，需要争取把规律变成1,2,3,4,,,n的样子，更容易控制  
    
    
tags:    
  -  数字逻辑  
  -  重点数学  
    