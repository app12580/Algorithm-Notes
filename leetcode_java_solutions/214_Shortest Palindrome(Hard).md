### description    
  Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.  
    
  Example 1:  
    
  Input: "aacecaaa"  
  Output: "aaacecaaa"  
  Example 2:  
    
  Input: "abcd"  
  Output: "dcbabcd"  
### solution    
```    
  
// 方法一： 流水账  
  class Solution {  
     public String shortestPalindrome(String s) {  
         if(s.length() == 0) return "";  
          char[] chars = s.toCharArray();  
          int right = 0;  
          for(int i = s.length() - 1; i >= 1; i--) {  
              if(isPalindrome(chars, 0, i)) {  
                  right = i;  
                  break;  
              }  
          }  
          if(right == s.length() - 1) return s;  
          StringBuilder builder = new StringBuilder(s.substring(right + 1));  
          return builder.reverse().toString() + s;  
      }  
    
      private boolean isPalindrome(char[] chars, int i, int j) {  
          while(i < j) {  
              if(chars[i] != chars[j])return false;  
              i++;  
              j--;  
          }  
          return true;  
      }  
    
  }  
```    
    
### 个人解读    
  首先需要好好读明白题目，知道要干什么。  
    
  问题转化：找出以起始点开始的最长回文字符串。  
    
  方法一： 流水账  
  感觉效率还可以吧  
    
tags:    
  -  字符串  
  -  回文  
