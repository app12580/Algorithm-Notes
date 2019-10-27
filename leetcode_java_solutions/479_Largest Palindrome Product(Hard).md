### description    
  Find the largest palindrome made from the product of two n-digit numbers.  
    
  Since the result could be very large, you should return the largest palindrome mod 1337.  
    
     
    
  Example:  
    
  Input: 2  
    
  Output: 987  
    
  Explanation: 99 x 91 = 9009, 9009 % 1337 = 987  
    
     
    
  Note:  
    
  The range of n is [1,8].  
### solution    
```    
Runtime: 244 ms, faster than 67.40% of Java online submissions for Largest Palindrome Product.  
Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for Largest Palindrome Product.  
    
  class Solution {  
       public int largestPalindrome(int n) {  
          if(n == 1) return 9;  
          //计算给定位数的最大值  
          long max = (long)Math.pow(10,n) - 1;  
          //从max - 1开始循环，原因见上文  
          for(long i = max - 1; i > max / 10; i--){  
              //1. 构造回文数  
              String s1 = String.valueOf(i);  
              long rev = Long.parseLong(s1 + new StringBuilder(s1).reverse().toString());  
              //2. 检验该回文数能否由给定的数相乘得到  
              for(long x = max; x * x >= rev; x --){  
                  if(rev % x == 0) return (int)(rev % 1337);  
              }  
          }  
          return -1;  
      }  
  }  
```    
    
### 个人解读    
  99: 9999  
  98: 9889  
  97: 9779  
  这样子一点点判断右边的能否被两个数字相除得到  
  //Array(9, 987, 123, 597, 677, 1218, 877, 475)(n-1)  
    
tags:    
  -  数学  
  -  数字逻辑  
