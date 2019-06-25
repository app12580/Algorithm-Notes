### description    
  Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.  
    
  Examples:  
    
  s = "leetcode"  
  return 0.  
    
  s = "loveleetcode",  
  return 2.  
  Note: You may assume the string contain only lowercase letters.  
### solution    
```    
  class Solution {  
      public int firstUniqChar(String s) {  
          Integer[] arr = new Integer[26];  
          for(int i = 0; i < s.length(); i++) {  
              char ch = s.charAt(i);  
              int j = ch - 'a';  
              if(arr[j] == null) {  
                  arr[j] = i;  
              } else if(arr[j] != -1) {  
                  arr[j] = -1;  
              }  
          }  
          int res = -1;  
          for(Integer i: arr) {  
              if(i != null && !i.equals(-1)) {  
                  if(res == -1) {  
                      res = i;  
                  } else {  
                      res = Math.min(res, i);  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  因为是小写字母，所以找个数组作为中间遍历结果。最后return 索引最小的那一个。  
  本题中只需要返回最终数组中最小的非-1值即可。  
    
tags:    
  -  数组  
