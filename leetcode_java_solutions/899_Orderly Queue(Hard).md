### description    
  A string S of lowercase letters is given.  Then, we may make any number of moves.  
    
  In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.  
    
  Return the lexicographically smallest string we could have after any number of moves.  
    
     
    
  Example 1:  
    
  Input: S = "cba", K = 1  
  Output: "acb"  
  Explanation:   
  In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".  
  In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".  
  Example 2:  
    
  Input: S = "baaca", K = 3  
  Output: "aaabc"  
  Explanation:   
  In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".  
  In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".  
     
    
  Note:  
    
  1 <= K <= S.length <= 1000  
  S consists of lowercase letters only.  
### solution    
```    
// 方法一：数学规律  
Runtime: 2 ms, faster than 90.18% of Java online submissions for Orderly Queue.  
Memory Usage: 36.3 MB, less than 100.00% of Java online submissions for Orderly Queue.  
  
  class Solution {  
      public String orderlyQueue(String S, int K) {  
          if (K == 1) {  
              String ans = S;  
              for (int i = 0; i < S.length(); ++i) {  
                  String T = S.substring(i) + S.substring(0, i);  
                  if (T.compareTo(ans) < 0) ans = T;  
              }  
              return ans;  
          } else {  
              char[] ca = S.toCharArray();  
              Arrays.sort(ca);  
              return new String(ca);  
          }  
      }  
  }   
```    
    
### 个人解读    
  隐约就能感觉到有数学规律，当K>=2时，可以任意交换两个相邻的数字。  
    
  可以任意定一个i和i+1，然后书写过程尝试一下  
    
tags:    
  -  数学规律  
