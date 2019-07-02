### description    
  Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.  
    
  Example 1:  
    
  Input: S = "ab#c", T = "ad#c"  
  Output: true  
  Explanation: Both S and T become "ac".  
  Example 2:  
    
  Input: S = "ab##", T = "c#d#"  
  Output: true  
  Explanation: Both S and T become "".  
  Example 3:  
    
  Input: S = "a##c", T = "#a#c"  
  Output: true  
  Explanation: Both S and T become "c".  
  Example 4:  
    
  Input: S = "a#c", T = "b"  
  Output: false  
  Explanation: S becomes "c" while T becomes "b".  
  Note:  
    
  1 <= S.length <= 200  
  1 <= T.length <= 200  
  S and T only contain lowercase letters and '#' characters.  
  Follow up:  
    
  Can you solve it in O(N) time and O(1) space?  
### solution    
```    
  class Solution {  
      public boolean backspaceCompare(String S, String T) {  
          char[] chars1 = S.toCharArray();  
          char[] chars2 = T.toCharArray();  
          int index = 0;  
          for(int i = 0; i < chars1.length; i++) {  
              if(chars1[i] == '#') {  
                  index = Math.max(0, index - 1);  
              } else {  
                  chars1[index++] = chars1[i];  
              }  
          }  
          int s1 = index; //s1为修正后的字符串长度  
          index = 0;  
          for(int i = 0; i < chars2.length; i++) {  
              if(chars2[i] == '#') {  
                  index = Math.max(0, index - 1);  
              } else {  
                  chars2[index++] = chars2[i];  
              }  
          }  
          if(s1 != index) {  
              return false;  
          }  
          for(int i = 0; i < s1; i++) {  
              if(chars1[i] != chars2[i]) {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  需要找一个合适的结构去存储字符串遍历过程。  
  用char[]效率应该是最高的。  
    
tags:    
  -  字符串  
  -  遍历  
