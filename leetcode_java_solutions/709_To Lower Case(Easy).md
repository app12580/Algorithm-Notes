### description    
  Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.  
    
     
    
  Example 1:  
    
  Input: "Hello"  
  Output: "hello"  
  Example 2:  
    
  Input: "here"  
  Output: "here"  
  Example 3:  
    
  Input: "LOVELY"  
  Output: "lovely"  
### solution    
```    
  class Solution {  
      public String toLowerCase(String str) {  
          char[] chars = str.toCharArray();  
          for(int i = 0; i < chars.length; i++) {  
              char ch = chars[i];  
              if(ch >= 'A' && ch <= 'Z') {  
                  chars[i] = (char)(ch + 32);  
              }  
          }  
          return new String(chars);  
      }  
  }  
```    
    
### 个人解读    
  略  
    
tags:    
  -   模拟   
  -   字符串   
