### description    
  Given a balanced parentheses string S, compute the score of the string based on the following rule:  
    
  () has score 1  
  AB has score A + B, where A and B are balanced parentheses strings.  
  (A) has score 2 * A, where A is a balanced parentheses string.  
     
    
  Example 1:  
    
  Input: "()"  
  Output: 1  
  Example 2:  
    
  Input: "(())"  
  Output: 2  
  Example 3:  
    
  Input: "()()"  
  Output: 2  
  Example 4:  
    
  Input: "(()(()))"  
  Output: 6  
     
    
  Note:  
    
  S is a balanced parentheses string, containing only ( and ).  
  2 <= S.length <= 50  
### solution    
```    
  // 方法一：  
  class Solution {  
      public int scoreOfParentheses(String S) {  
          Stack<Integer> stack = new Stack<>();  
          for (char c : S.toCharArray()) {  
              if (c == '(') {  
                  stack.push(-1);  
              } else {  
                  int cur = 0;  
                  while (stack.peek() != -1) {  
                      cur += stack.pop();  
                  }  
                  stack.pop();  
                  stack.push(cur == 0 ? 1 : cur * 2);  
              }  
          }  
          int sum = 0;  
          while (!stack.isEmpty()) {  
              sum += stack.pop();  
          }  
          return sum;  
      }  
  }  
    
  //方法二  
  // 感觉方法二更容易理解一些  
  class Solution {  
        public int scoreOfParentheses(String S) {  
          Stack<Integer> stack = new Stack<>();  
          int cur = 0;  
          for (char c : S.toCharArray()) {  
              if (c == '(') {  
                  stack.push(cur);  
                  cur = 0;  
              } else {  
                  cur = stack.pop() + Math.max(cur * 2, 1);  
              }  
          }  
          return cur;  
      }  
  }  
```    
    
### 个人解读    
  采用栈，遇到成型的结果，往栈里面丢数字。  
    
  然后想想不可取，且不说打乱了栈的结构，而且字符串解析也必须使用一个单独的栈去解析。  
    
  反思：栈的使用场景，来回进出，不明数量，想清楚这两点后，会意识到，标记法无法完成int的解析工作，所以Stack<int>是必要的  
    
tags:    
  -  栈  
  -  数学  
