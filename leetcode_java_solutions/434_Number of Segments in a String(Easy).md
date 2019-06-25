### description    
  Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.  
    
  Please note that the string does not contain any non-printable characters.  
    
  Example:  
    
  Input: "Hello, my name is John"  
  Output: 5  
    
  找出字符串被"\s{n}"分割的部分数量   
### solution    
```    
  class Solution {  
      public int countSegments(String s) {  
           char[] chars = s.toCharArray();  
          boolean hasWord = false;  
          int count = 0;  
          for(int i = 0; i < chars.length; i++) {  
              char ch = chars[i];  
              if(ch == ' ') {  
                  if(hasWord) {  
                      count++;  
                      hasWord = false;  
                  }  
              } else {  
                  if(!hasWord) {  
                      hasWord = true;  
                  }  
              }  
          }  
          if(hasWord) {  
              count++;  
          }  
          return count;  
      }  
  }  
```    
    
### 个人解读    
  指针遍历，只要定义好boolean标记的含义，就可以轻松写出来。  
    
tags:    
  -  指针  
  -  字符串  
