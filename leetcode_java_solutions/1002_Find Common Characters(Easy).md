### description    
  Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.  
    
  You may return the answer in any order.  
    
     
    
  Example 1:  
    
  Input: ["bella","label","roller"]  
  Output: ["e","l","l"]  
  Example 2:  
    
  Input: ["cool","lock","cook"]  
  Output: ["c","o"]  
     
    
  Note:  
    
  1 <= A.length <= 100  
  1 <= A[i].length <= 100  
  A[i][j] is a lowercase letter  
### solution    
```    
  class Solution {  
      public List<String> commonChars(String[] A) {  
          int[] chars = new int[26];  
          int[] cur;  
          boolean hasFirst = false;  
          for(String s: A) {  
              cur = new int[26];  
              for(char ch: s.toCharArray()) {  
                  cur[ch - 'a']++;  
              }  
              if(!hasFirst) {  
                  hasFirst = true;  
                  chars = cur;  
              } else {  
                  for(int i = 0; i < chars.length; i++) {  
                      chars[i] = Math.min(chars[i], cur[i]);  
                  }  
              }  
          }  
    
          List<String> res = new ArrayList<>();  
          for(int i = 0; i < chars.length; i++) {  
              for(int j = 0; j < chars[i]; j++) {  
                  res.add(String.valueOf((char)('a' + i)));  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  流水账  
  先找出每一个字符串的数量，然后比较。重复此过程。  
    
tags:    
  -  流水账  
