### description    
  Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.  
    
  Note: For the purpose of this problem, we define empty string as valid palindrome.  
    
  Example 1:  
    
  Input: "A man, a plan, a canal: Panama"  
  Output: true  
  Example 2:  
    
  Input: "race a car"  
  Output: false  
    
    
### solution    
```    
  class Solution {  
          public boolean isPalindrome(String s) {  
                 if(s == null || s.length() == 0) {  
              return true;  
          }  
          int i = 0;  
          int j = s.length() - 1;  
          while(i < j) {  
              while(!Character.isLetterOrDigit(s.charAt(i)) && i < j) {  
                  i++;  
              }  
              while(!Character.isLetterOrDigit(s.charAt(j))  && i < j ) {  
                  j--;  
              }  
              if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {  
                  return false;  
              }  
              i++;  
              j--;  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  双指针遍历。  
    
   ```  
     
   ???? 不应该是true吗？？  
     Input  "0P"  
     Output  true  
     Expected  false  
     
   ```  
   alphanumeric 包括字母和数字。。。  
     
   不能直接Math.abs(s.charAt(i) - s.charAt(j))这样判断，可以同时判断字母和数字  
     
   好像没必要在while里面迁移指针，速度并没有提升。  
     
tags:    
  -  双指针  
  -  字符串  
