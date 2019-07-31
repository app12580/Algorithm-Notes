### description    
  Write a program to find the nth super ugly number.  
    
  Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.  
    
  Example:  
    
  Input: n = 12, primes = [2,7,13,19]  
  Output: 32   
  Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12   
               super ugly numbers given primes = [2,7,13,19] of size 4.  
  Note:  
    
  1 is a super ugly number for any given primes.  
  The given numbers in primes are in ascending order.  
  0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.  
  The nth super ugly number is guaranteed to fit in a 32-bit signed integer.  
    
### solution    
```    
  class Solution {  
      public int nthSuperUglyNumber(int n, int[] primes) {  
          int[] dp = new int[n];  
          dp[0] = 1;  
          int len = primes.length;  
          int[] factors = new int[len];  
          for(int i = 0; i< len; i++) {  
              factors[i] = primes[i];  
          }  
          int[] factorIndexs = new int[len];  
          for(int i = 1; i < n; i++) {  
              int min = factors[0];  
              for(int j = 1; j < len; j++) {  
                  min = Math.min(min, factors[j]);  
              }  
              dp[i] = min;  
              for(int j = 0; j < len; j++) {  
                  if(min == factors[j]) {  
                      factors[j] = dp[++factorIndexs[j]] * primes[j];  
                  }  
              }  
          }  
    
          return dp[n - 1];  
      }  
  }  
```    
    
### 个人解读    
  与第nth个丑数的思路是一样的，核心思想在于利用中间结果。每一种序列都是从最终数列，从左到右乘以当前因数的。  
    
tags:    
  -  数学  
  -  中间结果  
