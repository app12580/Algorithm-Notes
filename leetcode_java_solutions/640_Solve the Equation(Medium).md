### description    
  Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.  
    
  If there is no solution for the equation, return "No solution".  
    
  If there are infinite solutions for the equation, return "Infinite solutions".  
    
  If there is exactly one solution for the equation, we ensure that the value of x is an integer.  
    
  Example 1:  
  Input: "x+5-3+x=6+x-2"  
  Output: "x=2"  
  Example 2:  
  Input: "x=x"  
  Output: "Infinite solutions"  
  Example 3:  
  Input: "2x=x"  
  Output: "x=0"  
  Example 4:  
  Input: "2x+3x-6x=x+2"  
  Output: "x=-1"  
  Example 5:  
  Input: "x=x+2"  
  Output: "No solution"  
### solution    
```    
Runtime: 1 ms, faster than 86.89% of Java online submissions for Solve the Equation.  
Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Solve the Equation.  
  
  class Solution {  
         private final String infinite = "Infinite solutions";  
      private final String noSolution = "No solution";  
    
      public String solveEquation(String equation) {  
          int xishu = 0;  
          int sum = 0;  
          boolean left = true;  
          int index = 0;  
          boolean addFlag = true;  
    
          while(index < equation.length()) {  
              char cur = equation.charAt(index);  
              if(cur == '+') {  
                  addFlag = left;  
              } else if(cur == '-') {  
                  addFlag = !left;  
              } else if(cur == 'x') {  
                  if(addFlag) {  
                      xishu += 1;  
                  } else {  
                      xishu -= 1;  
                  }  
              } else if(cur == '=') {  
                  left = false;  
                  addFlag = false;  
              } else {  
                  //其他数字  
                  int pre = index;  
                  while(index < equation.length() - 1 && helper(equation.charAt(index + 1))) {  
                      index++;  
                  }  
                  if(equation.charAt(index) == 'x') {  
                      int num = Integer.valueOf(equation.substring(pre, index));  
                      if(addFlag) {  
                          xishu += num;  
                      } else {  
                          xishu -= num;  
                      }  
                  } else {  
                      int num = Integer.valueOf(equation.substring(pre, index + 1));  
                      if(addFlag) {  
                          sum += num;  
                      } else {  
                          sum -= num;  
                      }  
                  }  
              }  
              index++;  
          }  
    
          if(sum == 0) {  
              if(xishu == 0) {  
                  return infinite;  
              } else {  
                  return "x=0";  
              }  
          } else {  
              if(xishu == 0) {  
                  return noSolution;  
              } else {  
                  return "x=" + (-sum / xishu);  
              }  
          }  
      }  
    
      private boolean helper(char c) {  
          return c == 'x' || Character.isDigit(c);  
      }  
    
  }  
```    
    
### 个人解读    
  解方程问题，字符串解析、类似于计算器。  
  字符串解析，使用while来解析。  
    
  通过各种标记进行计算。  
  使用while方式解析  
    
tags:    
  -  字符串解析  
  -  while解析  
