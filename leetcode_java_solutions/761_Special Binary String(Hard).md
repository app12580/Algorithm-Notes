### description    
  Special binary strings are binary strings with the following two properties:  
    
  The number of 0's is equal to the number of 1's.  
  Every prefix of the binary string has at least as many 1's as 0's.  
  Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them. (Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)  
    
  At the end of any number of moves, what is the lexicographically largest resulting string possible?  
    
  Example 1:  
  Input: S = "11011000"  
  Output: "11100100"  
  Explanation:  
  The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.  
  This is the lexicographically largest string possible after some number of swaps.  
  Note:  
    
  S has length at most 50.  
  S is guaranteed to be a special binary string as defined above.  
### solution    
```    
// 方法一： 递归  
Runtime: 3 ms, faster than 63.94% of Java online submissions for Special Binary String.  
Memory Usage: 36 MB, less than 66.67% of Java online submissions for Special Binary String.  
  
  class Solution {  
      public String makeLargestSpecial(String S) {  
          int count = 0, i = 0;  
          List<String> res = new ArrayList<String>();  
          for (int j = 0; j < S.length(); ++j) {  
              if (S.charAt(j) == '1') count++;  
              else count--;  
              if (count == 0) {  
                  res.add('1' + makeLargestSpecial(S.substring(i + 1, j)) + '0');  
                  i = j + 1;  
              }  
          }  
          Collections.sort(res, Collections.reverseOrder());  
          return String.join("", res);  
      }  
  }  
```    
    
### 个人解读    
    
  对于特殊字符串：一定有如下特性：  
  1、第一位是1，最后一位是0  
  2、形如n个1n个0这种的，叫做不可拆分字符串，否则称为可拆分字符串。  
  3、目标所求变成了，可拆分字符串内部的所有不可拆分字符串排序后的结果  
    
tags:    
  -  数学  
  -  字符串拼接  
