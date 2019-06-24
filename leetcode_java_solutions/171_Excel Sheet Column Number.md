### description    
  Given a column title as appear in an Excel sheet, return its corresponding column number.  
    
  For example:  
    
      A -> 1  
      B -> 2  
      C -> 3  
      ...  
      Z -> 26  
      AA -> 27  
      AB -> 28   
      ...  
  Example 1:  
    
  Input: "A"  
  Output: 1  
  Example 2:  
    
  Input: "AB"  
  Output: 28  
  Example 3:  
    
  Input: "ZY"  
  Output: 701  
### solution    
```    
  class Solution {  
      public int titleToNumber(String s) {  
          int res = 0;  
          for(int i = 0; i < s.length(); i++) {  
              char c = s.charAt(i);  
              res = res * 26 + (c - 'A' + 1);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  与[168](168_Excel%20Sheet%20Column%20Title%20(Easy).md)类似，不过本题是StringToInt，相对来说简单，因为类似于是正向解析。  
    
tags:    
  -  遍历  
