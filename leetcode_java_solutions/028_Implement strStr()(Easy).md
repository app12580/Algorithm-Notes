### description    
  Implement strStr().  
    
  Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.  
    
  Example 1:  
    
  Input: haystack = "hello", needle = "ll"  
  Output: 2  
  Example 2:  
    
  Input: haystack = "aaaaa", needle = "bba"  
  Output: -1  
  Clarification:  
    
  What should we return when needle is an empty string? This is a great question to ask during an interview.  
    
  For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().  
    
  模拟java的indexOf方法  
### solution    
```    
  class Solution {  
       public int strStr(String haystack, String needle) {  
          if(needle == null || needle.length() == 0) {  
              return 0;  
          }  
          char[] chars = haystack.toCharArray();  
          for(int i = 0; i <= chars.length - needle.length(); i++) {  
              for(int j = 0; j < needle.length(); j++) {  
                  if(chars[i+j] != needle.charAt(j)) {  
                      break;  
                  }  
                  if(j == needle.length() - 1 && chars[i+j] == needle.charAt(j)) {  
                      return i;  
                  }  
              }  
          }  
          return -1;  
      }  
  }  
```    
    
### 个人解读    
  只能按照题目要求，从左往右遍历，看每一次是否符合要求。  
  应该是有更高级的算法。  
    
tags:    
  -  高级算法  
  -  数组  
