### description    
  We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points, and spaces, and ended up with the string S.  Return a list of strings representing all possibilities for what our original coordinates could have been.  
    
  Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".  
    
  The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)  
    
  Example 1:  
  Input: "(123)"  
  Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]  
  Example 2:  
  Input: "(00011)"  
  Output:  ["(0.001, 1)", "(0, 0.011)"]  
  Explanation:   
  0.0, 00, 0001 or 00.01 are not allowed.  
  Example 3:  
  Input: "(0123)"  
  Output: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]  
  Example 4:  
  Input: "(100)"  
  Output: [(10, 0)]  
  Explanation:   
  1.0 is not allowed.  
     
    
  Note:  
    
  4 <= S.length <= 12.  
  S[0] = "(", S[S.length - 1] = ")", and the other elements in S are digits.  
### solution    
```    
Runtime: 4 ms, faster than 90.23% of Java online submissions for Ambiguous Coordinates.  
Memory Usage: 37 MB, less than 100.00% of Java online submissions for Ambiguous Coordinates.  
   
  class Solution {  
     public List<String> ambiguousCoordinates(String S) {  
          List<String> res = new ArrayList<>();  
          S = S.substring(1, S.length() - 1);  
          for(int i = 0; i < S.length() - 1; i++) {  
              //i为左边部分最后一位  
              String left = S.substring(0, i + 1);  
              String right = S.substring(i+1, S.length());  
              List<String> lList = helper(left);  
              List<String> rList = helper(right);  
              for(String l: lList) {  
                  for(String r: rList) {  
                      res.add("(" + l + ", " + r + ")");  
                  }  
              }  
          }  
          return res;  
      }  
    
      private List<String> helper(String s) {  
          List<String> res = new ArrayList<>();  
          //只需要判断小数点的位置。0开头是一个特殊的节点，0结尾也是特殊的节点  
          if(s.length() == 1) {  
              res.add(s);  
              return res;  
          }  
          int len = s.length();  
          if(s.charAt(0) == '0' && s.charAt(len - 1) == '0') {  
              return res;  
          } else if(s.charAt(0) == '0') {  
              res.add("0." + s.substring(1));  
              return res;  
          } else if(s.charAt(len - 1) == '0') {  
              res.add(s);  
              return res;  
          } else {  
              //此时前后都没有0，可以任意增加小数点  
              res.add(s);  
              for(int i = 0; i < len-1; i++) {  
                  res.add(s.substring(0, i + 1) + "." + s.substring(i+1));  
              }  
              return res;  
          }  
      }  
    
  }  
```    
    
### 个人解读    
  把问题全部拆解，然后问题就迎刃而解了。  
    
tags:    
  -  字符串解析  
  -  数字逻辑  
