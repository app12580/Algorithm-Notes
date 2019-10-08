### description    
  Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.  
    
  Formally, a parentheses string is valid if and only if:  
    
  It is the empty string, or  
  It can be written as AB (A concatenated with B), where A and B are valid strings, or  
  It can be written as (A), where A is a valid string.  
  Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.  
    
     
    
  Example 1:  
    
  Input: "())"  
  Output: 1  
  Example 2:  
    
  Input: "((("  
  Output: 3  
  Example 3:  
    
  Input: "()"  
  Output: 0  
  Example 4:  
    
  Input: "()))(("  
  Output: 4  
     
    
  Note:  
    
  S.length <= 1000  
  S only consists of '(' and ')' characters.  
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Add to Make Parentheses Valid.  
Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Minimum Add to Make Parentheses Valid.  
  
  class Solution {  
      public int minAddToMakeValid(String S) {  
          char[] chars = S.toCharArray();  
          int res = 0;  
          int count = 0;  
          for(char ch: chars) {  
              if(ch == '(') {  
                  count++;  
              } else {  
                  if(count > 0) {  
                      count--;  
                  } else {  
                      res++;  
                  }  
              }  
          }  
          return res + count;  
      }  
  }  
```    
    
### 个人解读    
  类似于那个(*)判断*的存在整体是否合法的题目。  
  第一感觉这题目很简单，只需要实时判断即可。  
    
tags:    
  -  流水账  
