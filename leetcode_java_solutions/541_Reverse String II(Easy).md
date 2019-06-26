### description    
    
  Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.  
  Example:  
  Input: s = "abcdefg", k = 2  
  Output: "bacdfeg"  
  Restrictions:  
  The string consists of lower English letters only.  
  Length of the given string and k will in the range [1, 10000]  
    
  给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。  
    
  反转k个字符，跳过k个字符，重复此操作。  
    
### solution    
```    
  class Solution {  
      public String reverseStr(String s, int k) {  
          char[] chars = s.toCharArray();  
          int len = s.length();  
          int start = 0;  
          while(start < len) {  
              if(len - start > 2 * k) {  
                  swap(chars, start, start + k - 1);  
                  start += 2 * k;  
              } else if(len - start > k) {  
                  swap(chars, start, start + k - 1);  
                  break;  
              } else {  
                  swap(chars, start, len - 1);  
                  break;  
              }  
          }  
          return new String(chars);  
      }  
        
      private void swap(char[] chars, int start, int end) {  
          while(start < end) {  
              char t = chars[start];  
              chars[start] = chars[end];  
              chars[end] = t;  
              start++;  
              end--;  
          }  
      }  
  }  
```    
    
### 个人解读    
  这题目描述需要好好品一下。  
  正向考虑，每次只干一件事情。先判断与k和2k的关系，然后分别处理。  
    
tags:    
  -  分类讨论  
