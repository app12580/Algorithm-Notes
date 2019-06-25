### description    
  Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.  
    
     
    
  Example 1:  
    
  Input: "abab"  
  Output: True  
  Explanation: It's the substring "ab" twice.  
  Example 2:  
    
  Input: "aba"  
  Output: False  
  Example 3:  
    
  Input: "abcabcabcabc"  
  Output: True  
  Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)  
### solution    
```    
  class Solution {  
      public boolean repeatedSubstringPattern(String s) {  
          if(s.length() == 1) return false;  
          int len = s.length();  
          for(int i = 1; i < len; i++) {        //注意这里，一开始错误写成 i * i <= len  
              if(len % i != 0) {  
                  continue;  
              }  
              if(check(s, i)) {  
                  return true;  
              }  
          }  
          return false;  
      }  
    
      private boolean check(String s, int k) {  
          for(int i = k; i <s.length(); i++) {  
              if(s.charAt(i) != s.charAt(i - k)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  先获取string长度，然后获取所有因数，分别判断。  
    
  ```  
  Input  "a"  
  Output  true  
  Expected  false  
    
  真服了，这种特殊情况LeetCode经常不说清楚  
  ```  
    
    
tags:    
  -  数组  
  -  数学  
  -  字符串  
