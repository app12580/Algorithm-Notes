### description    
  Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.  
    
     
    
  Example 1:  
    
  Input: s1 = "ab" s2 = "eidbaooo"  
  Output: True  
  Explanation: s2 contains one permutation of s1 ("ba").  
  Example 2:  
    
  Input:s1= "ab" s2 = "eidboaoo"  
  Output: False  
     
    
  Note:  
    
  The input strings only contain lower case letters.  
  The length of both given strings is in range [1, 10,000].  
    
### solution    
```    
  class Solution {  
     public boolean checkInclusion(String s1, String s2) {  
          if(s1.length() > s2.length()) return false;  
          int len = s1.length();  
          int[] arr = new int[26];  
          for(int i = 0; i < s1.length(); i++) {  
              arr[s1.charAt(i) - 'a']++;  
          }  
          for(int i = 0; i < s2.length(); i++) {  
              if(i < len - 1) {  
                  arr[s2.charAt(i) - 'a']--;  
              } else if(i == len - 1){  
                  arr[s2.charAt(i) - 'a']--;  
                  boolean flag = false;  
                  if(check(arr)) {  
                      return true;  
                  }  
                    
              } else {  
                  arr[s2.charAt(i) - 'a']--;  
                  arr[s2.charAt(i - len) - 'a']++;  
                  if(check(arr)) {  
                      return true;  
                  }  
              }  
          }  
    
          return false;  
      }  
    
      private boolean check(int[] arr) {  
          for(int num: arr) {  
              if(num != 0) {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  curLen问题，由于是排列组合，所以导致长度必须是一致的，可以从这点出发。  
    
tags:    
  -  curLen  
  -  字符串特征  
