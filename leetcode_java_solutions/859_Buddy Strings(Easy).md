### description    
  Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.  
    
     
    
  Example 1:  
    
  Input: A = "ab", B = "ba"  
  Output: true  
  Example 2:  
    
  Input: A = "ab", B = "ab"  
  Output: false  
  Example 3:  
    
  Input: A = "aa", B = "aa"  
  Output: true  
  Example 4:  
    
  Input: A = "aaaaaaabc", B = "aaaaaaacb"  
  Output: true  
  Example 5:  
    
  Input: A = "", B = "aa"  
  Output: false  
     
    
  Note:  
    
  0 <= A.length <= 20000  
  0 <= B.length <= 20000  
  A and B consist only of lowercase letters.  
### solution    
```    
  
效率还可以，就不优化了额  
Runtime: 2 ms, faster than 69.97% of Java online submissions for Buddy Strings.  
Memory Usage: 36 MB, less than 100.00% of Java online submissions for Buddy Strings.  
  
  
  class Solution {  
      public boolean buddyStrings(String A, String B) {  
           if(A.length() != B.length()) {  
              return false;  
          }  
    
          // 需要想清楚，如何区分第一次不同，第二次不同，第三次不同。  
          int firstIndex = -1;  
          char firstChar1 = ' ';  
          char firstChar2 = ' ';  
          boolean mustSame = false;  
    
          for(int i = 0; i < A.length(); i++) {  
              if(A.charAt(i) == B.charAt(i)) {  
                  continue;  
              }  
              if(firstIndex == -1) {  
                  firstChar1 = A.charAt(i);  
                  firstChar2 = B.charAt(i);  
                  firstIndex = i;  
              } else if(!mustSame) {  
                  if(firstChar1 != B.charAt(i) || firstChar2 != A.charAt(i)) {  
                      return false;  
                  }  
                  mustSame = true;  
              } else {  
                  return false;  
              }  
          }  
    
          if(firstIndex == -1) {  
              //此时两字符串相等  
              Set<Character> set = new HashSet<>();  
              for(char ch: A.toCharArray()) {  
                  if(set.contains(ch)) {  
                      return true;  
                  }  
                  set.add(ch);  
              }  
              return false;  
          }  
            
          return true;  
      }  
  }  
```    
    
### 个人解读    
  需要先比较长度，然后记录两次不同的情况。  
    
  这种情况怎么处理呀。  
  ```  
  Input: A = "aa", B = "aa"  
  Output: true  
   ```  
   单独处理吧，当A.equals(B)的时候，单独判断。  
  
tags:    
  -  字符串  
  -  循环标记  
