### description    
  Validate if a given string can be interpreted as a decimal number.  
    
  Some examples:  
  "0" => true  
  " 0.1 " => true  
  "abc" => false  
  "1 a" => false  
  "2e10" => true  
  " -90e3   " => true  
  " 1e" => false  
  "e3" => false  
  " 6e-1" => true  
  " 99e2.5 " => false  
  "53.5e93" => true  
  " --6 " => false  
  "-+3" => false  
  "95a54e53" => false  
    
  Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:  
    
  Numbers 0-9  
  Exponent - "e"  
  Positive/negative sign - "+"/"-"  
  Decimal point - "."  
  Of course, the context of these characters also matters in the input.  
    
  Update (2015-02-10):  
  The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.  
### solution    
```    
  import java.util.regex.Pattern;  
    
  class Solution {  
     public boolean isNumber(String s) {  
      s = s.trim();  
        
      boolean pointSeen = false;  
      boolean eSeen = false;  
      boolean numberSeen = false;  
      boolean numberAfterE = true;  
      for(int i=0; i<s.length(); i++) {  
          if('0' <= s.charAt(i) && s.charAt(i) <= '9') {  
              numberSeen = true;  
              numberAfterE = true;  
          } else if(s.charAt(i) == '.') {  
              if(eSeen || pointSeen) {  
                  return false;  
              }  
              pointSeen = true;  
          } else if(s.charAt(i) == 'e') {  
              if(eSeen || !numberSeen) {  
                  return false;  
              }  
              numberAfterE = false;  
              eSeen = true;  
          } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {  
              if(i != 0 && s.charAt(i-1) != 'e') {  
                  return false;  
              }  
          } else {  
              return false;  
          }  
      }  
        
      return numberSeen && numberAfterE;  
  }  
  }  
```    
    
### 个人解读    
  校验一个字符串是否符合数字描述。  
    
  直接抄答案把，主要是这种问题讨论情况特别多，而且题目描述里面也不说清楚。  
  比如"0000"这种是否通过？示例就看不出来  
    
  HAHAH，https://leetcode.com/problems/valid-number/discuss/23741/The-worst-problem-i-have-ever-met-in-this-oj  
  这种吐槽贴竟然是最多票数，看来有很多人见解一致。  
    
    
  ```  
  Cannot agree more and I feel I have a totally different opinion on what can be called a number(cry)  
    
  I will share out the test cases I have in hope to help you understand what OJ define as a "number".  
    
      test(1, "123", true);  
      test(2, " 123 ", true);  
      test(3, "0", true);  
      test(4, "0123", true);  //Cannot agree  
      test(5, "00", true);  //Cannot agree  
      test(6, "-10", true);  
      test(7, "-0", true);  
      test(8, "123.5", true);  
      test(9, "123.000000", true);  
      test(10, "-500.777", true);  
      test(11, "0.0000001", true);  
      test(12, "0.00000", true);  
      test(13, "0.", true);  //Cannot be more disagree!!!  
      test(14, "00.5", true);  //Strongly cannot agree  
      test(15, "123e1", true);  
      test(16, "1.23e10", true);  
      test(17, "0.5e-10", true);  
      test(18, "1.0e4.5", false);  
      test(19, "0.5e04", true);  
      test(20, "12 3", false);  
      test(21, "1a3", false);  
      test(22, "", false);  
      test(23, "     ", false);  
      test(24, null, false);  
      test(25, ".1", true); //Ok, if you say so  
      test(26, ".", false);  
      test(27, "2e0", true);  //Really?!  
      test(28, "+.8", true);    
      test(29, " 005047e+6", true);  //Damn = =|||  
  Here is the final Regex I got based on their definition  
    
  Pattern.matches("(\\+|-)?(\\d+(\\.\\d*)?|\\.\\d+)(e(\\+|-)?\\d+)?", s);  
  But I thought my original one should be more rigorous!  
    
  Pattern.matches("-?(([1-9]{1}+\\d*|0)(\\.\\d+)?|\\.\\d+)(e-?[1-9]{1}+\\d*)?", s);  
  ```  
    
tags:    
  -  了解即可  
