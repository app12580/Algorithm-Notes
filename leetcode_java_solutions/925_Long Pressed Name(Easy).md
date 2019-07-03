### description    
  Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.  
    
  You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.  
    
     
    
  Example 1:  
    
  Input: name = "alex", typed = "aaleex"  
  Output: true  
  Explanation: 'a' and 'e' in 'alex' were long pressed.  
  Example 2:  
    
  Input: name = "saeed", typed = "ssaaedd"  
  Output: false  
  Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.  
  Example 3:  
    
  Input: name = "leelee", typed = "lleeelee"  
  Output: true  
  Example 4:  
    
  Input: name = "laiden", typed = "laiden"  
  Output: true  
  Explanation: It's not necessary to long press any character.  
     
    
  Note:  
    
  name.length <= 1000  
  typed.length <= 1000  
  The characters of name and typed are lowercase letters.  
### solution    
```    
  class Solution {  
      public boolean isLongPressedName(String name, String typed) {  
          int i = 0;  
          int j = 0;  
           while(j < typed.length()) {  
              if(i == name.length()) i--;  
              char n = name.charAt(i);  
              char t = typed.charAt(j);  
              if(n == t) {  
                  i++;  
                  j++;  
              } else {  
                  if(i != 0 && name.charAt(i - 1) == t) {  
                      j++;  
                  } else {  
                      return false;  
                  }  
              }  
          }  
          return i == name.length()&& j == typed.length();  
      }  
  }  
```    
    
### 个人解读    
  需要双指针。  
  遇到不同的，就比较之后的；遇到相同的需要先判断名字里面是否重复。  
  先动j，然后再返回找i。  
    
  ```  
   if(i == name.length()) i--;  
  ```  
  这一个有点蒙出来的，没想到真能OK。  
    
tags:    
  -  特殊双指针  
