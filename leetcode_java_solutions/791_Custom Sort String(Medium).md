### description    
  S and T are strings composed of lowercase letters. In S, no letter occurs more than once.  
    
  S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.  
    
  Return any permutation of T (as a string) that satisfies this property.  
    
  Example :  
  Input:   
  S = "cba"  
  T = "abcd"  
  Output: "cbad"  
  Explanation:   
  "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".   
  Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.  
     
    
  Note:  
    
  S has length at most 26, and no character is repeated in S.  
  T has length at most 200.  
  S and T consist of lowercase letters only.  
### solution    
```    
    
  // 方法二  
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Custom Sort String.  
  Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Custom Sort String.  
  class Solution {  
      public String customSortString(String S, String T) {  
         StringBuilder res = new StringBuilder();  
          int[] need = new int[26];  
          for(char ch: T.toCharArray()) {  
              need[ch - 'a']++;  
          }  
          for(char ch: S.toCharArray()) {  
              int index = ch - 'a';  
              while(need[index]-- > 0) {  
                  res.append(ch);  
              }  
          }  
          for(int i = 0; i < 26; i++) {  
              if(need[i] > 0) {  
                  for(int j = 0; j < need[i]; j++) {  
                      res.append((char)('a' + i));  
                  }  
              }  
          }  
          return res.toString();  
      }  
  }  
    
  // 方法一  
  class Solution {  
      public String customSortString(String S, String T) {  
          char[] charArray = T.toCharArray();  
          Character[] chars = new Character[charArray.length];  
          for(int i = 0; i < charArray.length; i++) {  
              chars[i] = charArray[i];  
          }  
          Arrays.sort(chars, (c1, c2) -> S.indexOf(c1+"") - S.indexOf(c2+""));  
          for(int i = 0; i < charArray.length; i++) {  
              charArray[i] = chars[i];  
          }  
          return new String(charArray);  
      }  
  }  
```    
    
### 个人解读    
  思路一： 直接修改排序规则，通过排序  
  思路二： 汇总全部信息，然后再去创建结果  
    
tags:    
  -  有限数组  
  -  排序  
