### description      
  Implement a basic calculator to evaluate a simple expression string.    
      
  The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.    
      
  Example 1:    
      
  Input: "3+2*2"    
  Output: 7    
  Example 2:    
      
  Input: " 3/2 "    
  Output: 1    
  Example 3:    
      
  Input: " 3+5 / 2 "    
  Output: 5    
  Note:    
      
  You may assume that the given expression is always valid.    
  Do not use the eval built-in library function.    
      
      
### solution      
```      
  class Solution {    
    public int calculate(String s) {    
      if (s == null) return 0;    
      s = s.trim().replaceAll(" ", "");    
      int length = s.length();    
          
      int res = 0;    
      long preVal = 0; // initial preVal is 0    
      char sign = '+'; // initial sign is +    
      int i = 0;    
      while (i < length) {    
          long curVal = 0;    
          while (i < length && (int)s.charAt(i) <= 57 && (int)s.charAt(i) >= 48) { // int    
              curVal = curVal*10 + (s.charAt(i) - '0');    
              i++;    
          }    
          if (sign == '+') {    
              res += preVal;  // update res    
              preVal = curVal;    
          } else if (sign == '-') {    
              res += preVal;  // update res    
              preVal = -curVal;    
          } else if (sign == '*') {    
              preVal = preVal * curVal; // not update res, combine preVal & curVal and keep loop    
          } else if (sign == '/') {    
              preVal = preVal / curVal; // not update res, combine preVal & curVal and keep loop    
          }    
          if (i < length) { // getting new sign    
              sign = s.charAt(i);    
              i++;    
          }    
      }    
      res += preVal;    
      return res;    
  }    
  }    
```      
      
### 个人解读      
    
  最关键最关键的一点：每次遍历获取到的数字，关键点在于它前面的运算符号，处理时候对于res的处理是上一次遍历时候获取的数字。    
    
  几点核心：    
  1、通过while来获取数字    
  2、遇到计算符号以后，才去处理前面的数字结果。加减直接放进res里面，乘除的话保留计算结果。    
    细节： res+=pre和pre=cur的顺序    
  3、先用正则预处理，去除所有的空格(并不是很影响效率)    
  4、每一次循环的内容：    
  --获取数字    
  --处理当前数字结果    
  --获取新的符号    
      
  A+B+C    
  举个例子：看到第一个加号的时候，才会去加上A。  
  问题在于，每一次处理的时候，数字和符号的前后关系。因为最后一位不是符号，所以符号还是尽量在前面的好。  
  //拿到A->处理，res+=0->下一次循环拿到B->处理res+=A->下一个符号是加号  
      
tags:      
  -  字符串    
  -  模拟    
  -  重点数学    
