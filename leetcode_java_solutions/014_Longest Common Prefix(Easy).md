### description    
  Write a function to find the longest common prefix string amongst an array of strings.  
    
  If there is no common prefix, return an empty string "".  
    
  Example 1:  
    
  Input: ["flower","flow","flight"]  
  Output: "fl"  
  Example 2:  
    
  Input: ["dog","racecar","car"]  
  Output: ""  
  Explanation: There is no common prefix among the input strings.  
  Note:  
    
  All given inputs are in lowercase letters a-z.  
    
  获取最长公共前缀。  
### solution    
```    
  class Solution {  
        public String longestCommonPrefix(String[] strs) {  
          if(strs == null || strs.length == 0) return "";  
          StringBuilder builder = new StringBuilder();  
          int i = 0;  
          boolean flag = true;  
          char ch = '\u0000';  
          while(flag){  
              ch = '\u0000';  
              for(String s: strs) {  
                  if(i >= s.length()) {  
                      flag = false;  
                      break;  
                  }  
                  if(ch == '\u0000') {  
                      ch = s.charAt(i);  
                  } else if(ch != s.charAt(i)) {  
                      flag = false;  
                      break;  
                  }  
              }  
              if(flag) builder.append(ch);  
              i++;  
          }  
          return builder.toString();  
      }  
  }  
```    
    
### 个人解读    
  这题除了遍历还有其他解法吗？  
  解法写的啰嗦了点，但这也是最符合思维习惯的写法了。  
    
tags:    
  -  嵌套循环  
