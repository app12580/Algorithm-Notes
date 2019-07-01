### description      
  Given two integers L and R, find the count of numbers in the range [L, R] (inclusive) having a prime number of set bits in their binary representation.    
      
  (Recall that the number of set bits an integer has is the number of 1s present when written in binary. For example, 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)    
      
  Example 1:    
      
  Input: L = 6, R = 10    
  Output: 4    
  Explanation:    
  6 -> 110 (2 set bits, 2 is prime)    
  7 -> 111 (3 set bits, 3 is prime)    
  9 -> 1001 (2 set bits , 2 is prime)    
  10->1010 (2 set bits , 2 is prime)    
  Example 2:    
      
  Input: L = 10, R = 15    
  Output: 5    
  Explanation:    
  10 -> 1010 (2 set bits, 2 is prime)    
  11 -> 1011 (3 set bits, 3 is prime)    
  12 -> 1100 (2 set bits, 2 is prime)    
  13 -> 1101 (3 set bits, 3 is prime)    
  14 -> 1110 (3 set bits, 3 is prime)    
  15 -> 1111 (4 set bits, 4 is not prime)    
  Note:    
      
  L, R will be integers L <= R in the range [1, 10^6].    
  R - L will be at most 10000.    
      
  找出[L,R]区间中，满足以下要求的int数量：    
  二进制下，1的个数为质数    
### solution      
```      
  class Solution {    
       public int countPrimeSetBits(int L, int R) {    
          int count = 0;    
          for(int i = L; i <= R; i++) {    
              if(check(i)) {    
                  count ++;    
              }    
          }    
          return count;    
      
      }    
      
      // i是正数    
      private boolean check(int i) {    
          int[] primes = {2,3,5,7,11,13,17,19};    
          int count = 0;    
          while(i > 0) {    
              if((i & 1) == 1) {    
                  count++;    
              }    
              i >>= 1;    
          }    
          for(int p: primes) {    
              if(p == count) {    
                  return true;    
              }    
              if(p > count) {    
                  break;    
              }    
          }    
          return false;    
      }    
      
  }    
```      
      
### 个人解读      
  先查找1的数量，然后挨个判断    
    
  看前面的算法使用了Integer.bitCount()方法  
    
  这题好像可以用位运算的那几个模板。    
      
tags:      
  -  数学    
  -  位运算    
