### description    
  Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.  
    
  If possible, output any possible result.  If not possible, return the empty string.  
    
  Example 1:  
    
  Input: S = "aab"  
  Output: "aba"  
  Example 2:  
    
  Input: S = "aaab"  
  Output: ""  
  Note:  
    
  S will consist of lowercase letters and have length in range [1, 500].  
### solution    
```    
  class Solution {  
     public String reorganizeString(String S) {  
          int[] chars = new int[26];  
          for(int i = 0; i < S.length(); i++) {  
              char cur = S.charAt(i);  
              chars[cur - 'a']++;  
          }  
          int max = 0;  
          int letter = 0;  
          for(int i = 0; i < 26; i++) {  
              int c = chars[i];  
              if(c > max) {  
                  max = Math.max(max, c);  
                  letter = i;  
              }  
          }  
          if(max > (S.length() + 1) / 2) {  
              return "";  
          }  
          char[] res = new char[S.length()];  
          int idx = 0;  
          while(chars[letter] > 0) {  
              res[idx] = (char)(letter + 'a');  
              idx += 2;  
              chars[letter]--;  
          }  
          for(int i = 0; i < chars.length; i++) {  
              while(chars[i] > 0) {  
                  if(idx >= res.length) {  
                      idx = 1;  
                  }  
                  res[idx] = (char)(i + 'a');  
                  idx += 2;  
                  chars[i]--;  
              }  
          }  
          return String.valueOf(res);  
    
      }  
    
  }  
```    
    
### 个人解读    
  感觉可以通过数学方法计算，比较出现的最多次数，与总数去比较。  
    
  5:4,7：5       max > (n + 1) / 2 or max > n / 2 + 1  
  4:3, 6: 4     max > (n + 1) / 2 or max > n / 2  
    
  问题在于之后的如何排序上。  
  思路一：先把最多的排序了，然后排序第二多的。之后就可以无差别填充了。但是总觉的这样很麻烦。。。  
  纠错：第不需要特殊对待第二多的。  
    
    
tags:    
  -  数学  
