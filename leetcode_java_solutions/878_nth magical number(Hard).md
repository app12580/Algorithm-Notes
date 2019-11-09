### description    
  A positive integer is magical if it is divisible by either A or B.
  
  Return the N-th magical number.  Since the answer may be very large, return it modulo 10^9 + 7.
  
   
  
  Example 1:
  
  Input: N = 1, A = 2, B = 3
  Output: 2
  Example 2:
  
  Input: N = 4, A = 2, B = 3
  Output: 6
  Example 3:
  
  Input: N = 5, A = 2, B = 4
  Output: 10
  Example 4:
  
  Input: N = 3, A = 6, B = 4
  Output: 8
   
  
  Note:
  
  1 <= N <= 10^9
  2 <= A <= 40000
  2 <= B <= 40000
     
### solution    
```    
//  方法一： 二分法  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Nth Magical Number.
Memory Usage: 33.1 MB, less than 50.00% of Java online submissions for Nth Magical Number.
  class Solution {  
      public int nthMagicalNumber(int N, int A, int B) {  
          int MOD = 1_000_000_007;  
          int L = A / gcd(A, B) * B;  
    
          long lo = 0;  
          long hi = (long) 1e15;  
          while (lo < hi) {  
              long mi = lo + (hi - lo) / 2;  
              // If there are not enough magic numbers below mi...  
              if (mi / A + mi / B - mi / L < N)  
                  lo = mi + 1;  
              else  
                  hi = mi;  
          }  
    
          return (int) (lo % MOD);  
      }  
    
      public int gcd(int x, int y) {  
          if (x == 0) return y;  
          return gcd(y % x, x);  
      }  
  }  
     
```    
    
### 个人解读    
  之前有一个题目，好像是第n个丑数，通过维系三个数列，每次三者比较出来一个最小的数。  
  [264](264_Ugly%20Number%20II(Medium).md)  
  查了一下，又新出来1021题目，那个量很大，不能用一个一个比较的方法，使用了二分法。  
  本题目和1021那个题目是一样一样，然而本题目两个因数，那个题目是三个因数。并且本题目是Hard，而那个是Medium。  
    
  本题目虽然只能知道在[0,X ]中有多少数字满足条件。  
    
tags:    
  -  目标值问题  
  -  二分法  
