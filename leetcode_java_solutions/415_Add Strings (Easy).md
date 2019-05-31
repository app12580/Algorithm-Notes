### description    
  Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.  
    
  Note:  
    
  The length of both num1 and num2 is < 5100.  
  Both num1 and num2 contains only digits 0-9.  
  Both num1 and num2 does not contain any leading zero.  
  You must not use any built-in BigInteger library or convert the inputs to integer directly.  
### solution    
```  
  class Solution {
      public String addStrings(String num1, String num2) {
           StringBuilder sb = new StringBuilder();
          int i = num1.length() - 1;
          int j = num2.length() - 1;
          int carry = 0;
          while(i >= 0 || j >= 0) {
              int sum = 0;
              if(i >= 0) {
                  sum += Integer.valueOf(num1.charAt(i) + "");
              }
              if(j >= 0) {
                  sum += Integer.valueOf(num2.charAt(j) + "");
              }
              sum += carry;
              sb.append(sum % 10);
              carry = sum / 10;
              i--;
              j--;
          }
          if(carry > 0) {
              sb.append("1");
          }
          return sb.reverse().toString();
      }
  }
```  
  
### 个人解读  
  等同于67，典型的双指针遍历问题。  
  
tags:  
  -  字符串  
  -  双指针  
