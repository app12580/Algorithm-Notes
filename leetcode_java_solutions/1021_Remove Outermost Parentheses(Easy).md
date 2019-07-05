### description    
  A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.  
    
  A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.  
    
  Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.  
    
  Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.  
    
     
    
  Example 1:  
    
  Input: "(()())(())"  
  Output: "()()()"  
  Explanation:   
  The input string is "(()())(())", with primitive decomposition "(()())" + "(())".  
  After removing outer parentheses of each part, this is "()()" + "()" = "()()()".  
  Example 2:  
    
  Input: "(()())(())(()(()))"  
  Output: "()()()()(())"  
  Explanation:   
  The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".  
  After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".  
  Example 3:  
    
  Input: "()()"  
  Output: ""  
  Explanation:   
  The input string is "()()", with primitive decomposition "()" + "()".  
  After removing outer parentheses of each part, this is "" + "" = "".  
     
    
  Note:  
    
  S.length <= 10000  
  S[i] is "(" or ")"  
  S is a valid parentheses string  
### solution    
```    
  class Solution {  
      public String removeOuterParentheses(String S) {  
          boolean hasFirst = false;  
          boolean canAdd = false;  
          int unClose = 0;  
          StringBuilder builder = new StringBuilder();  
          StringBuilder process = new StringBuilder();  
          for(int i = 0; i < S.length(); i++) {  
              char cur = S.charAt(i);  
              process.append(cur);  
              if(cur == '(') {  
                  if(!hasFirst) {  
                      hasFirst = true;  
                  } else if(!canAdd) {  
                      canAdd = true;  
                  }  
                  unClose++;  
              } else {  
                  unClose--;  
                  if(unClose == 0) {  
                      if(canAdd) {  
                          builder.append(process.substring(1, process.length() - 1));  
                      }  
                      process.delete(0, process.length());  
                  }  
              }  
    
          }  
          return builder.toString();  
      }  
  }  
    
  //优化   
  class Solution {  
  public String removeOuterParentheses(String S) {  
          StringBuilder sb = new StringBuilder();  
    
          int count = 0;  
         
          for (char c : S.toCharArray()) {  
              if (c == '(') { // it's a left parenthesis  
                  count++;  
                  if (count == 1) { // skip the 1st left parenthesis        //这两个continue非常精髓  
                      continue;         //continue的含义： 什么情况下，不需要放进builder里面: 第一个左括号  
                  }  
              }  
    
              else {  // it's a right parenthesis  
                  count--;  
                  if (count == 0) {  
                      continue;             //最后一个右括号  
                  }  
              }  
              sb.append(c);  
          }  
          return sb.toString();  
      }  
  }  
```    
    
### 个人解读    
  需要一个StringBuilder来存储每一轮的结果。  
  用一个栈去遍历字符串 (×并不需要)  
  使用标记法，从左往右遍历，当符合要求的时候，StringBuilder.append(start+1, end-1)。  
    
tags:    
  -  标记法  
  -  优化  
