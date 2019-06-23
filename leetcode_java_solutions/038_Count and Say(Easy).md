### description    
  The count-and-say sequence is the sequence of integers with the first five terms as following:  
    
  1.     1  
  2.     11  
  3.     21  
  4.     1211  
  5.     111221  
  1 is read off as "one 1" or 11.  
  11 is read off as "two 1s" or 21.  
  21 is read off as "one 2, then one 1" or 1211.  
    
  Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.  
    
  Note: Each term of the sequence of integers will be represented as a string.  
    
     
    
  Example 1:  
    
  Input: 1  
  Output: "1"  
  Example 2:  
    
  Input: 4  
  Output: "1211"  
### solution    
```    
  class Solution {  
       public String countAndSay(int n) {  
          String res = "1";  
          for(int i = 1; i < n; i++) {  
              StringBuilder builder = new StringBuilder();  
              char ch = res.charAt(0);  
              int count = 0;  
              for(int j = 0; j < res.length(); j++) {  
                  char cur = res.charAt(j);  
                  if(cur == ch) {  
                      count++;  
                  } else {  
                      builder.append(count);  
                      builder.append(ch);  
                      count = 1;  
                      ch = cur;  
                  }  
              }  
              if(count > 0) {  
                  builder.append(count);  
                  builder.append(ch);  
                    
              }  
              res = builder.toString();  
          }  
          return res;  
    
      }  
  }  
```    
    
### 个人解读    
  流程还需要debug通过的，遇到过的几个问题：count设置为1而不是0。  
    
tags:    
  -  遍历  
  -  字符串  
