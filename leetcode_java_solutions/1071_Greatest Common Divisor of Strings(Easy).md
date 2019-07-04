### description    
  For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)  
    
  Return the largest string X such that X divides str1 and X divides str2.  
    
     
    
  Example 1:  
    
  Input: str1 = "ABCABC", str2 = "ABC"  
  Output: "ABC"  
  Example 2:  
    
  Input: str1 = "ABABAB", str2 = "ABAB"  
  Output: "AB"  
  Example 3:  
    
  Input: str1 = "LEET", str2 = "CODE"  
  Output: ""  
     
    
  Note:  
    
  1 <= str1.length <= 1000  
  1 <= str2.length <= 1000  
  str1[i] and str2[i] are English uppercase letters.  
### solution    
```    
  class Solution {  
      public String gcdOfStrings(String str1, String str2) {  
            
          int sameIndex = -1;  
          for(int i = 0; i < Math.min(str1.length(), str2.length()); i++) {  
              if(str1.charAt(i) == str2.charAt(i)) {  
                  sameIndex++;  
              } else {  
                  break;  
              }  
          }  
          if(sameIndex == -1) return "";  
          for(int i = sameIndex; i >= 0; i--) {  
             if(check(str1, str1.substring(0, i + 1)) && check(str2, str2.substring(0, i + 1))) {  
                  return str1.substring(0, i + 1);  
              }  
          }  
          return "";  
    
      }  
    
      private boolean check(String str, String substring) {  
          int len = str.length();  
          int subLen = substring.length();  
          if(len % subLen != 0) {  
              return false;  
          }  
          int count = len / subLen;  
          StringBuilder builder = new StringBuilder();  
          for(int i = 0; i < count; i++) {  
              builder.append(substring);  
          }  
          return builder.toString().equals(str);  
      }  
    
  }  
    
  // 方法二： 类似于最大公约数的辗转相除法  
  public String gcdOfStrings(String s1, String s2) {  
            
          if (s1 == null) {  
              return "";  
          }  
          if (s2 == null) {  
              return "";  
          }  
         if(s1.length()<s2.length())  
              return gcdOfStrings(s2, s1);  //前面放大的  
          if(s2.length() == 0)  
              return s1;  
          if(s1.startsWith(s2)){  
              while(s1.startsWith(s2)){  
                  s1 = s1.substring(s2.length());  
              }  
              return gcdOfStrings(s2, s1);  
          } else {  
              return "";  
          }  
      }  
```    
    
### 个人解读    
  结果一定是str1和str2开头的一部分，所以：  
  先从头开始遍历，然后获取到最大的字符串，再去判断。  
    
  ```  
  Input  "TAUXXTAUXXTAUXXTAUXXTAUXX"  "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX"  
  Output  "TAUXXTAUXXTAUXXTAUXXTAUXX"  
  Expected  "TAUXX"  
    
  TAUXXTAUXXTAUXXTAUXXTAUXX  
    
  TAUXXTAUXXTAUXXTAUXXTAUXX  
  TAUXXTAUXXTAUXXTAUXX  
    
  ```  
    
  问题在于有一个地方1没写成2  
    
tags:    
  -  字符串  
  -  最大公约数  
  -  优化  
