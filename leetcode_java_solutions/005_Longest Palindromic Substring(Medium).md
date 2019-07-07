### description    
  Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.  
    
  Example 1:  
    
  Input: "babad"  
  Output: "bab"  
  Note: "aba" is also a valid answer.  
  Example 2:  
    
  Input: "cbbd"  
  Output: "bb"  
    
  最长的回文子字符串  
### solution    
```    
  // 方法一 暴力法  
  Runtime: 56 ms, faster than 27.88% of Java online submissions for Longest Palindromic Substring.  
  Memory Usage: 35.8 MB, less than 99.97% of Java online submissions for Longest Palindromic Substring.  
    
    
  class Solution {  
       public String longestPalindrome(String s) {  
           if(s.length() <= 1) {  
              return s;  
          }  
          char[] chars = s.toCharArray();  
          for(int i = s.length() - 1; i >= 1; i--) {  //i表示间距  
              for(int j = 0; j < s.length() - i; j++) {  
                  if(isPalindrome(chars, j, i + j)) {  
                      return s.substring(j, i + j + 1);  
                  }  
              }  
          }  
          return s.substring(0, 1);  
      }  
    
      private boolean isPalindrome(char[] chars, int i, int j) {  
            
          while(i < j) {  
              if(chars[i++] != chars[j--]) {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
    
    
  // 方法二 优化：  
  class Solution {  
      private int sIndex = 0;  
      private int eIndex = 0;  
      int maxLength = 0;  
      public String longestPalindrome(String s) {  
          if(s.length()==0) return "";  
          char[] c = s.toCharArray();  
          boolean sameC = true;  
          char init = c[0];  
            
          for(int i=1;i<c.length;i++){  
              if(c[i]!=init) sameC = false;  
          }  
          if(sameC) return s;  
            
          int tempStart=0;  
          int tempEnd=0;  
          for(int i=0; i<c.length-1;i++){  
              calculatePalindrome(c, i-1, i+1);  
              if(c[i]==c[i+1]){  
                  calculatePalindrome(c, i, i+1);  
              }  
    
          }  
            
          return s.substring(sIndex,eIndex+1);  
      }  
        
      private void calculatePalindrome(char[] c, int start, int end){  
            
          while(start>=0 && end<c.length){  
              if(c[start]==c[end]){  
                  if((end-start) >= maxLength){  
                      sIndex=start;  
                      eIndex=end;  
                      maxLength = (end-start);  
                  }  
                      start--;  
                      end++;  
                  }else{  
                      break;  
                  }  
                    
              }  
          }  
            
      }  
        
```    
    
### 个人解读    
  先试一试暴力法。  
    
  方法二的优化：  
  核心思想在于从中间往两端判断而不是从两端往中间。  
    
  两种联想的途径： 2个起点1个重点； 重复计算造成冗余。  
    
tags:    
  -  回文字符串  
  -  优化  
