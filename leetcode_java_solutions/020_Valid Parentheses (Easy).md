### description    
  Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.  
    
  An input string is valid if:  
    
  Open brackets must be closed by the same type of brackets.  
  Open brackets must be closed in the correct order.  
  Note that an empty string is also considered valid.  
    
  Example 1:  
    
  Input: "()"  
  Output: true  
  Example 2:  
    
  Input: "()[]{}"  
  Output: true  
  Example 3:  
    
  Input: "(]"  
  Output: false  
  Example 4:  
    
  Input: "([)]"  
  Output: false  
  Example 5:  
    
  Input: "{[]}"  
  Output: true  
    
  判断括号是否合法  
### solution    
```    
  class Solution {  
      public boolean isValid(String s) {  
          Stack<Character> stack = new Stack<>();  
          for(int i = 0; i < s.length(); i++) {  
              Character ch = s.charAt(i);  
              if(ch == '(' || ch == '[' || ch == '{'){  
                  stack.push(ch);  
              } else if(ch == ')') {  
                  if(stack.isEmpty()) return false;  
                  if(stack.peek() == '(') {  
                      stack.pop();  
                  } else {  
                      return false;  
                  }  
              } else if(ch == ']') {  
                  if(stack.isEmpty()) return false;  
                  if(stack.peek() == '[') {  
                      stack.pop();  
                  } else {  
                      return false;  
                  }  
              } else if(ch == '}') {  
                  if(stack.isEmpty()) return false;  
                  if(stack.peek() == '{') {  
                      stack.pop();  
                  } else {  
                      return false;  
                  }  
              }  
          }  
          return stack.isEmpty();  
      }  
  }  
```    
    
### 个人解读    
  这种题目明显是需要通过栈来实现的。  
  遍历字符串，遇到"左括号" 就进栈，遇到"右括号"就进行比较  
  遍历完后，如果栈非空则不合法。  
  读题时候忽略了字符串不含其他字符。  
    
tags:    
  -  栈  
