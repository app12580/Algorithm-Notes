### description    
  Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.  
    
  Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:  
    
  If S[i] == "I", then A[i] < A[i+1]  
  If S[i] == "D", then A[i] > A[i+1]  
     
    
  Example 1:  
    
  Input: "IDID"  
  Output: [0,4,1,3,2]  
  Example 2:  
    
  Input: "III"  
  Output: [0,1,2,3]  
  Example 3:  
    
  Input: "DDI"  
  Output: [3,2,0,1]  
     
    
  Note:  
    
  1 <= S.length <= 10000  
  S only contains characters "I" or "D".  
    
  把DI 转换成匹配的int数组  
### solution    
```    
  class Solution {  
      public int[] diStringMatch(String S) {  
          int l = 0;  
          int h = S.length();  
          int[] res = new int[h + 1];  
          for(int i = 0; i < S.length(); i++) {  
              switch (S.charAt(i)) {  
                  case 'D':  
                      res[i] = h--;  
                      break;  
                  case 'I':  
                      res[i] = l++;  
                      break;  
              }  
          }  
          res[res.length - 1] = l;  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  感觉明显需要贪心算法  
  如果是D：则前面是最大的，反之：略。  
  注意最后一位的细节  
  ```  
  res[res.length - 1] = l;  
  ```  
    
    
tags:    
  -  贪心算法  
  -  数学  
