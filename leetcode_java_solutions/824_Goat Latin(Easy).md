### description    
  A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.  
    
  We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)  
    
  The rules of Goat Latin are as follows:  
    
  If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.  
  For example, the word 'apple' becomes 'applema'.  
     
  If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".  
  For example, the word "goat" becomes "oatgma".  
     
  Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.  
  For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.  
  Return the final sentence representing the conversion from S to Goat Latin.   
    
     
    
  Example 1:  
    
  Input: "I speak Goat Latin"  
  Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"  
  Example 2:  
    
  Input: "The quick brown fox jumped over the lazy dog"  
  Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"  
     
    
  Notes:  
    
  S contains only uppercase, lowercase and spaces. Exactly one space between each word.  
  1 <= S.length <= 150.  
    
  根据各种麻烦的规则改造字符串  
### solution    
```    
  class Solution {  
     public String toGoatLatin(String S) {  
          String[] split = S.split(" ");  
          int i = 1;  
          StringBuilder builder = new StringBuilder();  
          for(String s: split) {  
              if(builder.length() != 0) {  
                  builder.append(" ");  
              }  
              char[] res = new char[i++];  
              Arrays.fill(res, 'a');  
              if(check(s.charAt(0))) {  
                  builder.append(s);  
              } else {  
                  builder.append(s.substring(1)).append(s.charAt(0));  
              }  
              builder.append("ma").append(res);  
          }  
          return builder.toString();  
      }  
    
    
      private boolean check(char c) {  
          return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';  
      }  
  }  
```    
    
### 个人解读    
  一点点满足要求即可。  
    
tags:    
  -  字符串  
