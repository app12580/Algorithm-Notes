### description    
  Write a function that takes a string as input and reverse only the vowels of a string.  
    
  Example 1:  
    
  Input: "hello"  
  Output: "holle"  
  Example 2:  
    
  Input: "leetcode"  
  Output: "leotcede"  
  Note:  
  The vowels does not include the letter "y".  
### solution    
```    
  class Solution {  
         public String reverseVowels(String s) {  
          char[] chars = s.toCharArray();  
          int i = 0;  
          int j = s.length() - 1;  
          while(i < j) {  
              if(!isVowel(chars[i])) {  
                  i++;  
              } else if(!isVowel(chars[j])) {  
                  j--;  
              } else {  
                  char t = chars[i];  
                  chars[i] = chars[j];  
                  chars[j] = t;  
                  i++;      //这个总是忘  
                  j--;      //这个总是忘  
              }  
          }  
          return new String(chars);  
      }  
    
      private boolean isVowel(char ch) {  
          return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';  
      }  
  }  
```    
    
### 个人解读    
  双指针问题。  
    
tags:    
  -  双指针  
  -  反转  
