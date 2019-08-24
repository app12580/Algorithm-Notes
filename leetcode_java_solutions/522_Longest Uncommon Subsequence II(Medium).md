### description    
  Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.  
    
  A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.  
    
  The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.  
    
  Example 1:  
  Input: "aba", "cdc", "eae"  
  Output: 3  
  Note:  
    
  All the given strings' lengths will not exceed 10.  
  The length of the given list will be in the range of [2, 50].  
### solution    
```    
  Runtime: 2 ms, faster than 55.79% of Java online submissions for Longest Uncommon Subsequence II.  
  Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Longest Uncommon Subsequence II.  
    
  class Solution {  
       
      public int findLUSlength(String[] strs) {  
          Set<String> set = new HashSet<>();  
          Set<String> hasSet = new HashSet<>();  
          int max = -1;  
          for (String s : strs) {  
              if (hasSet.contains(s)) {  
                  continue;  
              }  
              if (set.contains(s)) {  
                  set.remove(s);  
                  hasSet.add(s);  
              } else {  
                  set.add(s);  
              }  
          }  
          for (String s : set) {  
              boolean flag = true;  
              for (String ss : hasSet) {  
                  if (isSubsequence(s, ss)) {  
                      flag = false;  
                      break;  
                  }  
              }  
              if (flag) {  
                  max = Math.max(max, s.length());  
              }  
          }  
          return max;  
      }  
    
      private boolean isSubsequence(String sub, String full) {  
          if (sub.length() >= full.length()) {  
              return false;  
          }  
          int index = 0;  
          for (int i = 0; i < sub.length(); i++) {  
              while (index < full.length() && full.charAt(index) != sub.charAt(i)) {  
                  index++;  
              }  
              if (index == full.length()) {  
                  return false;  
              }  
              index++;  
          }  
          return true;  
      }  
        
        
  }  
```    
    
### 个人解读    
  误区：最长的特殊字符串，那不就是把重复的字符串排除后，然后谁最长就是谁了。  
  剩下的最长可能是其他的子字符串。  
    
  然后判断每个字符串是不是重复字符串的子序列。  
  没想到这样效率竟然还可以。    
    
    
    
tags:    
  -  字符串解析  
