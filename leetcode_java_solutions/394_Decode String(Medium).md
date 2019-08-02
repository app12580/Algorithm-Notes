### description    
  Given an encoded string, return its decoded string.  
    
  The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.  
    
  You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.  
    
  Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].  
    
  Examples:  
    
  s = "3[a]2[bc]", return "aaabcbc".  
  s = "3[a2[c]]", return "accaccacc".  
  s = "2[abc]3[cd]ef", return "abcabccdcdcdef".  
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Decode String.  
Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Decode String.  
  
  
  class Solution {  
        public String decodeString(String s) {  
        String res = ""; //每次的结果  
         Stack<String> strStack = new Stack<>();  //每次进入中括号前的结果栈  
         Stack<Integer> countStack = new Stack<>();  
         int idx = 0;  
         while(idx < s.length()) {  
             char cur = s.charAt(idx);  
             if(cur == '[') {  
                 //需要重新整理str  
                 strStack.push(res);  
                 res = "";  
                 idx++;  
             } else if(cur == ']') {  
                  int count = countStack.pop();  
                  StringBuilder builder = new StringBuilder(strStack.pop());  
                  for(int i = 0; i <count; i++) {  
                      builder.append(res);  
                  }  
                  idx++;  
                  res = builder.toString();  
             } else if(Character.isDigit(cur)) {  
                  int count = 0;  
                  while(Character.isDigit(s.charAt(idx))) {  
                      count = count * 10 + s.charAt(idx) - '0';  
                      idx++;  
                  }  
                  countStack.push(count);  
             } else {  
                 res += s.charAt(idx++);  
             }  
    
         }  
    
         return res;  
      }  
  }  
```    
    
### 个人解读    
  感觉可以用栈结构。栈用来存储字符信息，再弄一个int用来存储当前括号的信息。问题在于栈里面数字和字符是混在一起的。  
  每次遍历有四种情况：数字、字符、左括号、右括号。  
    
  本题目很核心，有很多经典的思路在里面。  
  1、循环的第二种形式，使用while  
  2、关键点，使用Stack<String>来存储东西。  
      
tags:    
  -  栈  
  -  重点问题  
