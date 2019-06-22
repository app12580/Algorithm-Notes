### description    
  Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.  
    
  Symbol       Value  
  I             1  
  V             5  
  X             10  
  L             50  
  C             100  
  D             500  
  M             1000  
  For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.  
    
  Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:  
    
  I can be placed before V (5) and X (10) to make 4 and 9.   
  X can be placed before L (50) and C (100) to make 40 and 90.   
  C can be placed before D (500) and M (1000) to make 400 and 900.  
  Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.  
    
  Example 1:  
    
  Input: "III"  
  Output: 3  
  Example 2:  
    
  Input: "IV"  
  Output: 4  
  Example 3:  
    
  Input: "IX"  
  Output: 9  
  Example 4:  
    
  Input: "LVIII"  
  Output: 58  
  Explanation: L = 50, V= 5, III = 3.  
  Example 5:  
    
  Input: "MCMXCIV"  
  Output: 1994  
  Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.  
    
    
### solution    
```    
  class Solution {  
        public int romanToInt(String s) {  
    
          char[] chars = s.toCharArray();  
          int sum = 0;  
          int pre = getInt(chars[0]);  
          int next = 0;  
          for(int i = 0; i < chars.length - 1; i++) {  
              next = getInt(chars[i + 1]);  
              if(pre < next) {  
                  sum -= pre;  
              } else {  
                  sum += pre;  
              }  
              pre = next;  
          }  
          sum += getInt(chars[chars.length - 1]);  
          return sum;  
      }  
    
      public int getInt(char ch) {  
          switch (ch) {  
              case 'I':  
                  return 1;  
              case 'V':  
                  return 5;  
              case 'X':  
                  return 10;  
              case 'L':  
                  return 50;  
              case 'C':  
                  return 100;  
              case 'D':  
                  return 500;  
              case 'M':  
                  return 1000;  
    
          }  
          return -1;  
      }  
  }  
```    
    
### 个人解读    
  首先要熟悉规则。  
  罗马数字。  
  I II III IV V .  
  带负数的最多只有一个。  
  所以只需要比较当前数字和下一个数字的比较，最后一个一定为正数。  
    
  只要明白只需要比较一次pre和next就足够了。  
    
tags:    
  -  数组  
  -  模型  
