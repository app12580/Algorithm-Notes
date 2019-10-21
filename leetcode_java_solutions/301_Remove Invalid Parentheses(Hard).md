### description    
  Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.  
    
  Note: The input string may contain letters other than the parentheses ( and ).  
    
  Example 1:  
    
  Input: "()())()"  
  Output: ["()()()", "(())()"]  
  Example 2:  
    
  Input: "(a)())()"  
  Output: ["(a)()()", "(a())()"]  
  Example 3:  
    
  Input: ")("  
  Output: [""]  
### solution    
```    
// 参考答案  
Runtime: 1 ms, faster than 100.00% of Java online submissions for Remove Invalid Parentheses.  
Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for Remove Invalid Parentheses.  
  
  class Solution {  
      public List<String> removeInvalidParentheses(String s) {  
          List<String> output = new ArrayList<>();  
          removeHelper(s, output, 0, 0, '(', ')');  
          return output;  
      }  
    
      public void removeHelper(String s, List<String> output, int iStart, int jStart, char openParen, char closedParen) {  
          int numOpenParen = 0, numClosedParen = 0;  
          for (int i = iStart; i < s.length(); i++) {  
              if (s.charAt(i) == openParen) numOpenParen++;  
              if (s.charAt(i) == closedParen) numClosedParen++;  
              if (numClosedParen > numOpenParen) {   
                  for (int j = jStart; j <= i; j++)   
                      if (s.charAt(j) == closedParen && (j == jStart || s.charAt(j - 1) != closedParen))  
                          // 删除最左边的)或者))))中的第一个  
                          removeHelper(s.substring(0, j) + s.substring(j + 1, s.length()), output, i, j, openParen, closedParen);  
                  return;   
              }  
          }  
          //反过来在运算一遍，用处理)的方法处理(  
          String reversed = new StringBuilder(s).reverse().toString();  
          if (openParen == '(')  
              removeHelper(reversed, output, 0, 0, ')','(');  
          else  
              output.add(reversed);  
      }  
  }  
```    
    
### 个人解读    
  首先递归是必要的。当遇到因为)过多而需要分开的时候，此时需要将前面的部分单独计算。后面的进行递归处理。  
  几点疑问：  
  1、当遇到)多出左边的时候，是立即处理还是while到最右的)在处理？  
   "((())))" 还是处理"((()))))))"  
    
  https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution  
  关于参考答案的几点解读：  
  1、直接使用String而不是char[]，不用回溯  
  2、通过reverse处理'('  
  3、每次只删除))))的第一个  
    
tags:    
  -  字符串   
  -  递归   
  -  重点数学   1
