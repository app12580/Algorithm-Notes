### description    
  Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].  
    
  Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:  
    
  0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);  
  F.length >= 3;  
  and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.  
  Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.  
    
  Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.  
    
  Example 1:  
    
  Input: "123456579"  
  Output: [123,456,579]  
  Example 2:  
    
  Input: "11235813"  
  Output: [1,1,2,3,5,8,13]  
  Example 3:  
    
  Input: "112358130"  
  Output: []  
  Explanation: The task is impossible.  
  Example 4:  
    
  Input: "0123"  
  Output: []  
  Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.  
  Example 5:  
    
  Input: "1101111"  
  Output: [110, 1, 111]  
  Explanation: The output [11, 0, 11, 11] would also be accepted.  
  Note:  
    
  1 <= S.length <= 200  
  S contains only digits.  
### solution    
```    
Runtime: 3 ms, faster than 85.26% of Java online submissions for Split Array into Fibonacci Sequence.  
Memory Usage: 37.8 MB, less than 25.00% of Java online submissions for Split Array into Fibonacci Sequence.  
  
  class Solution {  
      
        public List<Integer> splitIntoFibonacci(String S) {  
          List<Integer> res = new ArrayList<>();  
          dfs(res, 0, S);  
          return res;  
      }  
    
      private boolean dfs(List<Integer> res, int index, String S) {  
          if (index == S.length()) {  
              return res.size() >= 3;  
          }  
          for (int i = index; i < S.length(); i++) {  
              if (i > index && S.charAt(index) == '0') {  
                  break;  
              }  
              long curL = Long.valueOf(S.substring(index, i+1));  
              if(curL > Integer.MAX_VALUE) break;  
              int cur = (int) curL;  
    
    
              if (res.size() <= 1) {  
                  res.add(cur);  
                  if (dfs(res, i + 1, S)) {  
                      return true;  
                  }  
                  res.remove(res.size() - 1);  
              } else {  
                  int pre1 = res.get(res.size() - 1);  
                  int pre2 = res.get(res.size() - 2);  
                  if (pre1 + pre2 == cur) {  
                      res.add(cur);  
                      if (dfs(res, i + 1, S)) {  
                          return true;  
                      }  
                      res.remove(res.size() - 1);  
                  }  
              }  
          }  
          return false;  
      }  
        
  }  
```    
    
### 个人解读    
  DFS，需要辅助函数想清楚什么参数。  
    
  写完以后发现报错，然而竟然是超限，把这个关键的忘了。。。感觉这种题目就算是long也不管用了。。。  
  ```  
  Input  
    "539834657215398346785398346991079669377161950407626991734534318677529701785098211336528511"  
    Output  
    [539834657,21,539834678,539834699,1079669377,1619504076,-1595793843,23710233,-1572083610,-1548373377]  
    Expected  
    []  
  ```  
    
  因为return是int的集合，所以大于int超限就可以直接pass了  
    
  这种DFS，辅助函数建立，集合参数，方法返回值boolean控制进度，都差不多是可以直接套用公式的了  
    
tags:    
  -  DFS  
