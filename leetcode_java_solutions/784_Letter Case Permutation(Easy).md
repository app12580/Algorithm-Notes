### description    
  Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.  
    
  Examples:  
  Input: S = "a1b2"  
  Output: ["a1b2", "a1B2", "A1b2", "A1B2"]  
    
  Input: S = "3z4"  
  Output: ["3z4", "3Z4"]  
    
  Input: S = "12345"  
  Output: ["12345"]  
  Note:  
    
  S will be a string with length between 1 and 12.  
  S will consist only of letters or digits.  
### solution    
```    
  class Solution {  
        public List<String> letterCasePermutation(String S) {  
          List<String> res = new ArrayList<>();  
          dfs(res, 0, S.toCharArray());  
          return res;  
      }  
    
      private void dfs(List<String> res, int i, char[] chars) {  
          if(i == chars.length) {  
              res.add(new String(chars));  
              return;  
          }  
          char ch = chars[i];  
          if(ch <= 'z' && ch >= 'a'){  
              dfs(res, i + 1, chars);  
              chars[i] = (char) (ch - 32);  
              dfs(res, i + 1, chars);  
          } else if(ch <= 'Z' && ch >= 'A') {  
              dfs(res, i + 1, chars);  
              chars[i] = (char) (ch + 32);  
              dfs(res, i + 1, chars);  
          } else {  
              dfs(res, i + 1, chars);  
          }  
                    
      }  
    
  }  
```    
    
### 个人解读    
  标准的DFS获取所有解的应用。  
  因为字符串使用前缀很蠢，所以采用回溯法。  
    
tags:    
  -  dfs  
  -  回溯  
