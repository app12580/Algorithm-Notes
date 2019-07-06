### description    
  Given a string, find the length of the longest substring without repeating characters.  
    
  Example 1:  
    
  Input: "abcabcbb"  
  Output: 3   
  Explanation: The answer is "abc", with the length of 3.   
  Example 2:  
    
  Input: "bbbbb"  
  Output: 1  
  Explanation: The answer is "b", with the length of 1.  
  Example 3:  
    
  Input: "pwwkew"  
  Output: 3  
  Explanation: The answer is "wke", with the length of 3.   
               Note that the answer must be a substring, "pwke" is a subsequence and not a substring.  
### solution    
```    
  class Solution {  
      public int lengthOfLongestSubstring(String s) {  
           if(s.length() == 0) {  
              return 0;  
          }  
          int max = 0;  
          int[] arr = new int[128];  
          Arrays.fill(arr, -1);  
          arr[s.charAt(0)] = 0;  
          int begin = 0;  
          for(int i = 1; i < s.length(); i++) {  
              char cur = s.charAt(i);  
              int before = arr[cur];  
              if(before >= begin) {  
                  max = Math.max(max, i - begin);  
                  begin = before + 1;  
              }  
              arr[cur] = i;  
          }  
          max = Math.max(max, s.length() - begin);  
          return max;  
      }  
  }  
```    
    
### 个人解读    
  想办法用一个int[26]存储每个字符最后的索引，还有一个int index存储此时的起始点。  
  在遍历过程，当前索引的字符加入是'a'，然后去找'a'的索引，与index比较，如果比index小则继续，否则出来比较长度。  
    
tags:    
  -  中间数组  
  -  字符索引  
