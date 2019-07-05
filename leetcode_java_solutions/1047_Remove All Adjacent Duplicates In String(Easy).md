### description    
  Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.  
    
  We repeatedly make duplicate removals on S until we no longer can.  
    
  Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.  
    
     
    
  Example 1:  
    
  Input: "abbaca"  
  Output: "ca"  
  Explanation:   
  For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".  
     
    
  Note:  
    
  1 <= S.length <= 20000  
  S consists only of English lowercase letters.  
### solution    
```    
  class Solution {  
      public String removeDuplicates(String S) {  
           char[] chars = S.toCharArray();  
          int index = 0;  //前一个字母的索引  
          for(int i = 1; i < chars.length; i++) {  
              if(index >= 0 && chars[i] == chars[index]) {  
                  index--;  
              } else {  
                  chars[++index] = chars[i];  
              }  
          }  
            
          return new String(chars, 0, index + 1);  
      }  
  }  
```    
    
### 个人解读    
  感觉这种数据结构可以用栈。但是数组也是可以用的。   
  注意好细节。  
    
tags:    
  -  字符串  
