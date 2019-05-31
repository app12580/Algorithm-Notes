### description  
  Given an integer, return its base 7 string representation.  
    
  Example 1:  
  Input: 100  
  Output: "202"  
  Example 2:  
  Input: -7  
  Output: "-10"  
  Note: The input will be in range of [-1e7, 1e7].  
### solution  
```  
  class Solution {
    public String convertToBase7(int num) {
      if (num == 0) {
          return "0";
      }
      StringBuilder sb = new StringBuilder();
      boolean isNegative = num < 0;
      if (isNegative) {
          num = -num;
      }
      while (num > 0) {
          sb.append(num % 7);
          num /= 7;
      }
      String ret = sb.reverse().toString();
      return isNegative ? "-" + ret : ret;
  }
  }
```  
  
### 个人解读  
  进制转换，常见题型，主要就是通过while(x > 0)，然后去造一个数，如果是字符串还好，就怕返回一个int，还需要考虑越界问题。  
  如何判断字符串拼接时候怎么加的，因为n/=7，一直循环下去，导致当前循环的数字要大，要放在前面。  
  
tags:  
  -   循环变体  
  -   进制转换  
