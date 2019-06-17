### description    
  Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.  
    
  A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.  
    
    
    
  Example:  
    
  Input: "23"  
  Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].  
### solution    
```    
  class Solution {  
       private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};  
    
      public List<String> letterCombinations(String digits) {  
          List<String> res = new ArrayList<>();  
          if(digits == null || digits.length() == 0) {  
              return res;  
          }  
          dfs("", res, digits);  
          return res;  
      }  
    
      private void dfs(String prefix, List<String> res, String digits) {  
          if(prefix.length() == digits.length()) {  
              res.add(prefix);  
              return;  
          }  
    
          char cur = digits.charAt(prefix.length());  
          String choice = KEYS[cur - '0'];  
          for(char c: choice.toCharArray()) {  
              prefix += c;  
              dfs(prefix, res, digits);  
              prefix = prefix.substring(0, prefix.length() - 1);  
          }  
      }  
  }  
```    
    
### 个人解读    
  题目完全可以转换成排列的问题。  
  标准的排列问题，使用回溯算法+DFS。  
    
tags:    
  -  DFS  
  -  回溯  
  -  排列组合  
