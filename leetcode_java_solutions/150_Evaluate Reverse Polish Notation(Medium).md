### description    
  Evaluate the value of an arithmetic expression in Reverse Polish Notation.  
    
  Valid operators are +, -, *, /. Each operand may be an integer or another expression.  
    
  Note:  
    
  Division between two integers should truncate toward zero.  
  The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.  
  Example 1:  
    
  Input: ["2", "1", "+", "3", "*"]  
  Output: 9  
  Explanation: ((2 + 1) * 3) = 9  
  Example 2:  
    
  Input: ["4", "13", "5", "/", "+"]  
  Output: 6  
  Explanation: (4 + (13 / 5)) = 6  
  Example 3:  
    
  Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]  
  Output: 22  
  Explanation:   
    ((10 * (6 / ((9 + 3) * -11))) + 17) + 5  
  = ((10 * (6 / (12 * -11))) + 17) + 5  
  = ((10 * (6 / -132)) + 17) + 5  
  = ((10 * 0) + 17) + 5  
  = (0 + 17) + 5  
  = 17 + 5  
  = 22  
### solution    
```    
  class Solution {  
      public int evalRPN(String[] tokens) {  
          Stack<Integer> stack = new Stack<>();  
          for (int i = 0; i < tokens.length; i++) {  
              String cur = tokens[i];  
              switch (cur) {  
                  case "+": {  
                      int num1 = stack.pop();  
                      int num2 = stack.pop();  
                      stack.push(num1 + num2);  
                      break;  
                  }  
                  case "-": {  
                      int num2 = stack.pop();       //需要注意这里的细节，先出来的作为减数  
                      int num1 = stack.pop();  
                      stack.push(num1 - num2);  
                      break;  
                  }  
                  case "*": {  
                      int num1 = stack.pop();  
                      int num2 = stack.pop();  
                      stack.push(num1 * num2);  
                      break;  
                  }  
                  case "/": {  
                      int num2 = stack.pop();   //需要注意这里的细节，先出来的作为除数  
                      int num1 = stack.pop();  
                      stack.push(num1 / num2);  
                      break;  
                  }  
                  default: {  
                      stack.push(Integer.valueOf(cur));  
                  }  
              }  
          }  
          return stack.peek();  
      }  
  }  
```    
    
### 个人解读    
  使用Stack就完事了。保证数据肯定有结果，并且除数不是0很关键。  
  需要注意细节  
    
tags:    
  -  栈  
