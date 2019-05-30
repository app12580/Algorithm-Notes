### description    
  Given two strings s and t , write a function to determine if t is an anagram of s.  
    
  Example 1:  
    
  Input: s = "anagram", t = "nagaram"  
  Output: true  
  Example 2:  
    
  Input: s = "rat", t = "car"  
  Output: false  
  Note:  
  You may assume the string contains only lowercase alphabets.  
    
  Follow up:  
  What if the inputs contain unicode characters? How would you adapt your solution to such case?  
    
   两个字符串包含的字符是否完全相同  
### solution    
```    
  class Solution {  
      public boolean isAnagram(String s, String t) {  
          int[] counts = new int[26];  
          for(char c: s.toCharArray()) {  
              counts[c - 'a']++;  
          }  
          for(char c: t.toCharArray()) {  
              counts[c - 'a']--;  
          }  
          for(int n: counts) {  
              if(n != 0) {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  遍历问题，明显需要存储全范围存储结果。  
    
tags:    
  -  数组  
  -  全范围存储结果  
