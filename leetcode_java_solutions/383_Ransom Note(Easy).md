### description    
  Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.  
    
  Each letter in the magazine string can only be used once in your ransom note.  
    
  Note:  
  You may assume that both strings contain only lowercase letters.  
    
  canConstruct("a", "b") -> false  
  canConstruct("aa", "ab") -> false  
  canConstruct("aa", "aab") -> true  
### solution    
```    
  class Solution {  
      public boolean canConstruct(String ransomNote, String magazine) {  
          int[] arr = new int[26];  
          for(char ch: magazine.toCharArray()) {  
              arr[ch - 'a']++;  
          }  
          for(char ch: ransomNote.toCharArray()) {  
              if(arr[ch - 'a'] == 0) {  
                  return false;  
              } else {  
                  arr[ch - 'a']--;  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  搞个东西作为中间遍历结果，因为只有小写字母，所以用数组即可。  
    
tags:    
  -  数组  
  -  遍历  
