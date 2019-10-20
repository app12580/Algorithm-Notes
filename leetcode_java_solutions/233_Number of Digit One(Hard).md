### description    
  Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.  
    
  Example:  
    
  Input: 13  
  Output: 6   
  Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.  
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of Digit One.  
Memory Usage: 33.1 MB, less than 16.67% of Java online submissions for Number of Digit One.  
  
  class Solution {  
     public int countDigitOne(int n) {  
    
      if (n <= 0) return 0;  
      int q = n, x = 1, ans = 0;    //x 是当前幅度10 100 或者1000 这种 ； q是指剩下的数字  
      do {  
          int digit = q % 10;   //在x幅度下的最后一位  
          q /= 10;  
          ans += q * x;  
          if (digit == 1) ans += n % x + 1;  
          if (digit >  1) ans += x;  
          x *= 10;  
      } while (q > 0);  
      return ans;  
    
  }  
  }  
```    
    
### 个人解读    
  纯数学问题，主要靠找规律。  
  统计每一位上的数字和。  
  https://leetcode.com/problems/number-of-digit-one/discuss/64382/JavaPython-one-pass-solution-easy-to-understand  
  The idea is to calculate occurrence of 1 on every digit. There are 3 scenarios, for example  
    
  if n = xyzdabc  
  and we are considering the occurrence of one on thousand, it should be:  
    
  (1) xyz * 1000                     if d == 0  
  (2) xyz * 1000 + abc + 1           if d == 1  
  (3) xyz * 1000 + 1000              if d > 1  
  iterate through all digits and sum them all will give the final answer  
    
    
tags:    
  -  重点数学  
  -  数字逻辑  
