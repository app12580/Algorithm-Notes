### description    
  Given two strings S and T, each of which represents a non-negative rational number, return True if and only if they represent the same number. The strings may use parentheses to denote the repeating part of the rational number.  
    
  In general a rational number can be represented using up to three parts: an integer part, a non-repeating part, and a repeating part. The number will be represented in one of the following three ways:  
    
  <IntegerPart> (e.g. 0, 12, 123)  
  <IntegerPart><.><NonRepeatingPart>  (e.g. 0.5, 1., 2.12, 2.0001)  
  <IntegerPart><.><NonRepeatingPart><(><RepeatingPart><)> (e.g. 0.1(6), 0.9(9), 0.00(1212))  
  The repeating portion of a decimal expansion is conventionally denoted within a pair of round brackets.  For example:  
    
  1 / 6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66)  
    
  Both 0.1(6) or 0.1666(6) or 0.166(66) are correct representations of 1 / 6.  
    
     
    
  Example 1:  
    
  Input: S = "0.(52)", T = "0.5(25)"  
  Output: true  
  Explanation:  
  Because "0.(52)" represents 0.52525252..., and "0.5(25)" represents 0.52525252525..... , the strings represent the same number.  
  Example 2:  
    
  Input: S = "0.1666(6)", T = "0.166(66)"  
  Output: true  
  Example 3:  
    
  Input: S = "0.9(9)", T = "1."  
  Output: true  
  Explanation:   
  "0.9(9)" represents 0.999999999... repeated forever, which equals 1.  [See this link for an explanation.]  
  "1." represents the number 1, which is formed correctly: (IntegerPart) = "1" and (NonRepeatingPart) = "".  
     
    
  Note:  
    
  Each part consists only of digits.  
  The <IntegerPart> will not begin with 2 or more zeros.  (There is no other restriction on the digits of each part.)  
  1 <= <IntegerPart>.length <= 4  
  0 <= <NonRepeatingPart>.length <= 4  
  1 <= <RepeatingPart>.length <= 4  
### solution    
```    
  // 方法一： 转化成分数类  
  Runtime: 4 ms, faster than 26.73% of Java online submissions for Equal Rational Numbers.  
  Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Equal Rational Numbers.  
  class Solution {  
      public boolean isRationalEqual(String S, String T) {  
          Fraction f1 = convert(S);  
          Fraction f2 = convert(T);  
          return f1.n == f2.n && f1.d == f2.d;  
      }  
    
      public Fraction convert(String S) {  
          int state = 0; //whole, decimal, repeating  
          Fraction ans = new Fraction(0, 1);  
          int decimal_size = 0;  
    
          for (String part: S.split("[.()]")) {  
              state++;  
              if (part.isEmpty()) continue;  
              long x = Long.valueOf(part);  
              int sz = part.length();  
    
              if (state == 1) { // whole  
                   ans.iadd(new Fraction(x, 1));  
              } else if (state == 2) { // decimal  
                   ans.iadd(new Fraction(x, (long) Math.pow(10, sz)));  
                   decimal_size = sz;  
              } else { // repeating  
                   long denom = (long) Math.pow(10, decimal_size);  
                   denom *= (long) (Math.pow(10, sz) - 1);  
                   ans.iadd(new Fraction(x, denom));  
              }  
          }  
          return ans;  
      }  
  }  
    
  class Fraction {  
      long n, d;  
      Fraction(long n, long d) {  
          long g = gcd(n, d);  
          this.n = n / g;  
          this.d = d / g;  
      }  
    
      public void iadd(Fraction other) {  
          long numerator = this.n * other.d + this.d * other.n;  
          long denominator = this.d * other.d;  
          long g = Fraction.gcd(numerator, denominator);  
          this.n = numerator / g;  
          this.d = denominator / g;  
      }  
    
      static long gcd(long x, long y) {  
          return x != 0 ? gcd(y % x, x) : y;  
      }  
  }  
     
```    
    
### 个人解读    
  思路一：先通过数学方法，把小数转化成分数，然后再比较  
    
  核心关键：用一个分数类，完成全部的整数分数循环部分的操作。  
    
  方法二：Lee215  
  括号前面的变成一个double，然后括号里面的重复个20个变，如果这两个double相等，就认为相等了  
    
  学到的经验：封装成对象更容易处理一些，毕竟要面向对象。  
    
tags:    
  -  分数类  
