### description    
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.  
      
    For example, given n = 3, a solution set is:  
      
    [  
      "((()))",  
      "(()())",  
      "(())()",  
      "()(())",  
      "()()()"  
    ]  
      
### solution    
```    
  class Solution {  
      public List<String> generateParenthesis(int n) {  
          List<String> res = new ArrayList<>();  
          char[] chars = new char[n * 2];  
          dfs(chars, res, 0, 0, 0, n);  
          return res;  
      }  
    
      private void dfs(char[] chars, List<String> res, int index, int left, int right, int n) {  
          if(index == chars.length) {  
              res.add(new String(chars));  
              return;  
          }  
          //如果能写右边在写左边  
          if(left > right) {  
              chars[index] = ')';  
              dfs(chars, res, index + 1, left, right + 1, n);  
          }  
          if(left < n) {  
              chars[index] = '(';  
              dfs(chars, res, index + 1, left + 1, right, n);  
          }  
      }  
  }  
```    
    
### 个人解读    
  DFS，先办法描述当前进度。  
  通过左括号个数和右括号个数，每次只需要执行一次操作。  
  
 几点说明：  
 1、使用char[]可以减少结构的消耗。  
 2、因为使用了index，所以不需要回溯。  
 3、本题属于结果总集算法(另一种是唯一解法，需要boolean进行终止)，需要考虑回溯的情况。  
  
tags:    
  -  DFS  
  -  回溯  
