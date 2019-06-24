### description    
  Given a pattern and a string str, find if str follows the same pattern.  
    
  Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.  
    
  Example 1:  
    
  Input: pattern = "abba", str = "dog cat cat dog"  
  Output: true  
  Example 2:  
    
  Input:pattern = "abba", str = "dog cat cat fish"  
  Output: false  
  Example 3:  
    
  Input: pattern = "aaaa", str = "dog cat cat dog"  
  Output: false  
  Example 4:  
    
  Input: pattern = "abba", str = "dog dog dog dog"  
  Output: false  
  Notes:  
  You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.  
### solution    
```    
  class Solution {  
      public boolean wordPattern(String pattern, String str) {  
          Map<String, Integer> map1 = new HashMap<>();  
          Map<Character, Integer> map2 = new HashMap<>();  
          String[] split = str.split(" ");  
          if(split.length !=  pattern.length()) return false;  
    
          for(int i = 0; i < split.length; i++) {  
              String s = split[i];  
              Character ch = pattern.charAt(i);  
              if(map1.containsKey(s) && map2.containsKey(ch)) {  
                   if(!map1.get(s).equals(map2.get(ch))) {      //注意这里不能用==  
                      return false;  
                  }  
              } else if(!map1.containsKey(s) && !map2.containsKey(ch)) {  
                  map1.put(s, i);  
                  map2.put(ch, i);  
              } else {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
    
  错误方法一：  
  ```  
    
  "abba"  
  "dog dog dog dog"  
    
  out: true  
  expect: false  
    
  class Solution {  
      public boolean wordPattern(String pattern, String str) {  
          int[] index = new int[26];  
          Arrays.fill(index, -1);  
          String[] split = str.split(" ");  
          if(split.length != pattern.length()) return false;  
    
          for(int i = 0; i < split.length; i++) {  
              char c = pattern.charAt(i);  
              if(index[c - 'a'] == -1) {  
                  index[c - 'a'] = i;  
              } else {  
                  if(!split[i].equals(split[index[c-'a']])) {  
                      return false;  
                  }  
              }  
          }  
          return true;  
      }  
  }  
  ```  
    
  因为总是丢了西瓜扔芝麻，要么只考虑pattern要么只考虑str，所以最后直接弄两个map算了。  
  注意Integer比较不能用==(缓存池问题)。  
    
tags:    
  -  字符串  
  -  遍历  
  -  hash表  
