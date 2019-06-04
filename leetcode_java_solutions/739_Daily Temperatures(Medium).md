### description    
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.  
  
For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].  
  
Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].    
### solution    
```    
  class Solution {  
      public int[] dailyTemperatures(int[] T) {  
          int[] res = new int[T.length];  
          Stack<Integer> stack = new Stack<>();  
          for(int i = 0; i < T.length; i++) {  
              while(!stack.isEmpty() && T[i] > T[stack.peek()]) {  
                  Integer pop = stack.pop();  
                  res[pop] = i - pop;  
              }  
              stack.push(i);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  循环入栈问题，先要确定入栈和出栈条件。出栈条件：当cur大于栈顶值。  
  注意栈里面存放的是索引值。  
    
tags:    
  -  栈  
  -  循环入栈  
