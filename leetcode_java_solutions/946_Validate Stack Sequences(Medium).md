### description  
  Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
  
   
  
  Example 1:
  
  Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
  Output: true
  Explanation: We might do the following sequence:
  push(1), push(2), push(3), push(4), pop() -> 4,
  push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
  Example 2:
  
  Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
  Output: false
  Explanation: 1 cannot be popped before 2.
   
  
  Note:
  
  0 <= pushed.length == popped.length <= 1000
  0 <= pushed[i], popped[i] < 1000
  pushed is a permutation of popped.
  pushed and popped have distinct values.
### solution  
```  
Runtime: 3 ms, faster than 79.71% of Java online submissions for Validate Stack Sequences.
Memory Usage: 38.1 MB, less than 100.00% of Java online submissions for Validate Stack Sequences.

  class Solution {
      public boolean validateStackSequences(int[] pushed, int[] popped) {
          Stack<Integer> stack = new Stack<>();
          int index = 0;
          for(int p : pushed){
             stack.push(p);
             while(!stack.isEmpty() && stack.peek() == popped[index]) {
                 stack.pop();
                 index++;
             }
          }
          return stack.isEmpty();
      }
  }
```  
  
### 个人解读  
  首先举个例子：
  1,2,3可以的结果有：
  123，132,321,231,213
  不可以：312
  分析：第一个pop3说明其他的都没有pop，因此12必须以21的形式返回。
  
  解题核心： 
  根据pop的数据，可以唯一确定操作全过程，然后互只需要判断这个过程完事后是否可以全部遍历完即可。
  
tags:  
  -  
