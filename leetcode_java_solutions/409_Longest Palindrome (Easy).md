### description    
  Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.  
    
  This is case sensitive, for example "Aa" is not considered a palindrome here.  
    
  Note:  
  Assume the length of given string will not exceed 1,010.  
    
  Example:  
    
  Input:  
  "abccccdd"  
    
  Output:  
  7  
    
  Explanation:  
  One longest palindrome that can be built is "dccaccd", whose length is 7.  
  字符串可以组成的最长的回文字符串长度  
### solution    
```    
  class Solution {  
      public int longestPalindrome(String s) {  
          int[] counts = new int[256];  
          for(char c: s.toCharArray()) {  
              counts[c]++;  
          }  
          int res = 0;  
          for(int num: counts) {  
              res += num / 2 * 2;  
          }  
          if(res < s.length()) {  
              res++;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  也没啥好说的了。。。。  
    
tags:    
  -  数组  
  -  全范围存储结果  
