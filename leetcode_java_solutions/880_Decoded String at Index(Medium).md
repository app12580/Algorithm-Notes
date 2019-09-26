### description    
  An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:  
    
  If the character read is a letter, that letter is written onto the tape.  
  If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.  
  Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.  
    
     
    
  Example 1:  
    
  Input: S = "leet2code3", K = 10  
  Output: "o"  
  Explanation:   
  The decoded string is "leetleetcodeleetleetcodeleetleetcode".  
  The 10th letter in the string is "o".  
  Example 2:  
    
  Input: S = "ha22", K = 5  
  Output: "h"  
  Explanation:   
  The decoded string is "hahahaha".  The 5th letter is "h".  
  Example 3:  
    
  Input: S = "a2345678999999999999999", K = 1  
  Output: "a"  
  Explanation:   
  The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".  
     
    
  Note:  
    
  2 <= S.length <= 100  
  S will only contain lowercase letters and digits 2 through 9.  
  S starts with a letter.  
  1 <= K <= 10^9  
  The decoded string is guaranteed to have less than 2^63 letters.  
    
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Decoded String at Index.  
Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Decoded String at Index.  
  
  class Solution {  
      public String decodeAtIndex(String S, int K) {  
          int len = 0;  
          for(int i = 0; i < S.length(); i++) {  
              if(Character.isDigit(S.charAt(i))) {  
                  int count = S.charAt(i) - '0';  
                  int j = 0;  
                  for(; j < count - 1 && K > len; j++) {  
                        //注意这一行是 K > len， 是不含等号的，如果K恰好等于len，此时K==0，没有映射到字符上，反而是len才有意义  
                      K -= len;  
                  }  
                  if(j == count - 1) {  
                      //说明减到头了，继续执行  
                      len *= count;  
                  } else {  
                      //返回进入数字循环前的答案  
                      return decodeAtIndex(S, K);  
                  }  
              } else {  
                  len++;  
                  K--;  
                  if(K == 0) {  
                      return S.charAt(i) + "";  
                  }  
              }  
          }  
          return "";  
      }  
  }  
```    
    
### 个人解读    
    
  主要是遇到int超限时候的处理。  
  纠错： 数字是一位一位的操作，不是连续的。  
    
  总结：  
  尽量避免去创建一个string模拟全部字符，应该想办法直接定位到。  
  通过操作K，去调整。争取把K定位到字符出现的位置，而不要遇到数字时候的位置。  
    
    
tags:    
  -  数学  
  -  字符串解析  
