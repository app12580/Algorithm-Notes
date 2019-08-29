### description      
  Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:    
      
  Any left parenthesis '(' must have a corresponding right parenthesis ')'.    
  Any right parenthesis ')' must have a corresponding left parenthesis '('.    
  Left parenthesis '(' must go before the corresponding right parenthesis ')'.    
  '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.    
  An empty string is also valid.    
  Example 1:    
  Input: "()"    
  Output: True    
  Example 2:    
  Input: "(*)"    
  Output: True    
  Example 3:    
  Input: "(*))"    
  Output: True    
  Note:    
  The string size will be in the range [1, 100].    
### solution      
```      
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Valid Parenthesis String.    
  Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Valid Parenthesis String.    
  class Solution {    
      public boolean checkValidString(String s) {    
          int low = 0;    
          int high = 0;    
          for(int i =0; i < s.length(); i++) {    
              char cur = s.charAt(i);    
              if(cur == '(') {    
                  low++;    
                  high++;    
              } else if(cur == ')') {    
                  if(low > 0) {    
                      low--;    
                  }    
                  high--;    
              } else {    
                  if(low > 0) {    
                      low--;    
                  }    
                  high++;    
              }    
              if(high < 0) {    
                  return false;    
              }    
          }    
          return low == 0;    
      }    
      
  }    
      
```      
      
### 个人解读      
  因为*的引入，导致很麻烦。    
      
  思路一：    
  1、只统计*的个数。不考虑括号之间的相对位置。    
    无法解决："**((*"    
    失败之后应该意识到，*和位置有关。  
      
        
  思路二：    
  因为左括号可以无限叠加，等到最后才知道可不可行，所以只需要针对右括号的进度。    
  所以设置两个变量，用来描述当前可接受的右括号的数量范围。(最大值和最小值)    
          
      
  ```    
    思路一：    
        
    public boolean checkValidString(String s) {    
        int starCount = 0;    
        int leftCount = 0;    
        char[] chars = s.toCharArray();    
        for(int i = 0; i < chars.length; i++) {    
            char cur = chars[i];    
            if(cur == '*') {    
                starCount++;    
            } else if(cur == '(') {    
                leftCount++;    
            } else {    
                if(leftCount > 0) {    
                    leftCount--;    
                } else {    
                    if(starCount > 0) {    
                        starCount--;    
                    } else {    
                        return false;    
                    }    
                }    
            }    
        }    
        return starCount >= leftCount;    
    }    
    
    
  ```    
tags:      
  -  数学    
  -  字符串解析    
  -  找出关键变量    
