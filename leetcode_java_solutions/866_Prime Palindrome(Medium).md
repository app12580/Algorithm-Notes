### description    
  Find the smallest prime palindrome greater than or equal to N.  
    
  Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.   
    
  For example, 2,3,5,7,11 and 13 are primes.  
    
  Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.   
    
  For example, 12321 is a palindrome.  
    
     
    
  Example 1:  
    
  Input: 6  
  Output: 7  
  Example 2:  
    
  Input: 8  
  Output: 11  
  Example 3:  
    
  Input: 13  
  Output: 101  
     
    
  Note:  
    
  1 <= N <= 10^8  
  The answer is guaranteed to exist and be less than 2 * 10^8.  
### solution    
```    
Runtime: 34 ms, faster than 45.10% of Java online submissions for Prime Palindrome.  
Memory Usage: 36.1 MB, less than 50.00% of Java online submissions for Prime Palindrome.  
  
  
  class Solution {  
        public int primePalindrome(int N) {  
          if (8 <= N && N <= 11) return 11;  
          for (int x = 1; x < 100000; x++) {  
              String s = Integer.toString(x), r = new StringBuilder(s).reverse().toString().substring(1);  
              int y = Integer.parseInt(s + r);  
              if (y >= N && isPrime(y)) return y;  
          }  
          return -1;  
      }  
    
      public Boolean isPrime(int x) {  
          if (x < 2 || x % 2 == 0) return x == 2;  
          for (int i = 3; i * i <= x; i += 2)  
              if (x % i == 0) return false;  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  看了很多的解答，基本都是挨个遍历，然后分别判断是质数，或者判断是回文  
    
  https://leetcode.com/problems/prime-palindrome/discuss/146798/All-Even-Digits-Palindrome-are-Divisible-by-11  
  这个算是唯一有进展的只是  
  里面证明了，偶数回文数，一定是11的倍数。  
    
    
tags:    
  -  数学  
  -  质数  
  -  回文  
