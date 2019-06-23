### description    
  Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.  
    
  If the last word does not exist, return 0.  
    
  Note: A word is defined as a character sequence consists of non-space characters only.  
    
  Example:  
    
  Input: "Hello World"  
  Output: 5  
    
  返回最后一个单词的长度  
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Length of Last Word.  
Memory Usage: 35.6 MB, less than 99.97% of Java online submissions for Length of Last Word.  
  
  
  class Solution {  
         public int lengthOfLastWord(String s) {  
          int count = 0;  
    
          boolean flag = true;  
          int left = -1;      //最后一个单词左边的字符  
          int right = -1;     //右边第一个不为空的字符  
          for(int i = s.length() - 1; i >= 0; i--) {  
              char cur = s.charAt(i);  
              if(cur == ' ') {  
                  if(flag) {  
                      continue;  
                  } else {  
                      left = i;  
                      break;  
                  }  
              } else {  
                  if(flag) {  
                      right = i;  
                  }  
                  flag = false;  
              }  
          }  
          return right - left;  
      }  
  }  
```    
    
### 个人解读    
 题干没说两边可以有空格。  
 因为空格的元素，所以想要双指针(预处理效率低)。  
 最后写代码时候，发现，实际上是单指针遍历，双指针标记法。  
   
    
tags:    
  -  双指针  
