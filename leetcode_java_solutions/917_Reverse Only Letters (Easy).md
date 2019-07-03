### description    
  Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.  
    
     
    
  Example 1:  
    
  Input: "ab-cd"  
  Output: "dc-ba"  
  Example 2:  
    
  Input: "a-bC-dEf-ghIj"  
  Output: "j-Ih-gfE-dCba"  
  Example 3:  
    
  Input: "Test1ng-Leet=code-Q!"  
  Output: "Qedo1ct-eeLg=ntse-T!"  
     
    
  Note:  
    
  S.length <= 100  
  33 <= S[i].ASCIIcode <= 122   
  S doesn't contain \ or "  
    
    
### solution    
```    
  class Solution {  
      public String reverseOnlyLetters(String S) {  
          char[] chars = S.toCharArray();  
          int i = 0;  
          int j = chars.length - 1;  
          while(i < j) {  
              if (!Character.isLetter(chars[i])) {  
                  i++;  
              } else if (!Character.isLetter(chars[j])) {  
                  j--;  
              } else {  
                  char ch = chars[i];  
                  chars[i] = chars[j];  
                  chars[j] = ch;  
                  i++;  
                  j--;  
              }  
          }  
          return new String(chars);  
      }  
  }  
```    
    
### 个人解读    
  标准的双指针问题。  
    
tags:    
  -  双指针  
  -  数组  
