### description  
  Implement a basic calculator to evaluate a simple expression string.
  
  The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
  
  Example 1:
  
  Input: "1 + 1"
  Output: 2
  Example 2:
  
  Input: " 2-1 + 2 "
  Output: 3
  Example 3:
  
  Input: "(1+(4+5+2)-3)+(6+8)"
  Output: 23
  Note:
  You may assume that the given expression is always valid.
  Do not use the eval built-in library function.
### solution  
```  
// 参考答案一： 使用栈
Runtime: 7 ms, faster than 88.63% of Java online submissions for Basic Calculator.
Memory Usage: 38.9 MB, less than 100.00% of Java online submissions for Basic Calculator.

  class Solution {
     public int calculate(String s) {
      Stack<Integer> stack = new Stack<Integer>();  //每次遇到(才会向栈里面存储值
      int result = 0;       //表示当前区间内的值
      int number = 0;
      int sign = 1;
      for(int i = 0; i < s.length(); i++){
          char c = s.charAt(i);
          if(Character.isDigit(c)){
              number = 10 * number + (int)(c - '0');
          }else if(c == '+'){
              result += sign * number;
              number = 0;
              sign = 1;
          }else if(c == '-'){
              result += sign * number;
              number = 0;
              sign = -1;
          }else if(c == '('){
              //we push the result first, then sign;
              stack.push(result);
              stack.push(sign);
              //reset the sign and result for the value in the parenthesis
              sign = 1;   
              result = 0;
          }else if(c == ')'){
              result += sign * number;  
              number = 0;
              result *= stack.pop();    //stack.pop() is the sign before the parenthesis
              result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
              
          }
      }
      if(number != 0) result += sign * number;
      return result;
  }
  }
```  
  
### 个人解读  
  对于这种计算器问题，不清楚会有多少层嵌套，要么使用stack要么就递归逃课。因为常用的标记法并不明确需要几个变量去做标记。
  
  思路一：
  1、尝试使用栈
  2、对于for循环里面的处理，数字的处理，可以num = num * 10 + cur
  
  思路是没错的，关键是控制不好，想不清楚括号时候如何使用。
  
tags:  
  -  
