### description    
  Given a positive integer, return its corresponding column title as appear in an Excel sheet.  
    
  For example:  
    
      1 -> A  
      2 -> B  
      3 -> C  
      ...  
      26 -> Z  
      27 -> AA  
      28 -> AB   
      ...  
  Example 1:  
    
  Input: 1  
  Output: "A"  
  Example 2:  
    
  Input: 28  
  Output: "AB"  
  Example 3:  
    
  Input: 701  
  Output: "ZY"  
### solution    
```    
  class Solution {  
      public String convertToTitle(int n) {  
           StringBuilder sb = new StringBuilder();  
          while (n > 0) {  
              int c = n % 26  == 0 ? 26 : n % 26;  
              sb.append((char)(c+ 'A' - 1));  
              n = (n - 1) / 26;  
          }  
          return sb.reverse().toString();  
      }  
  }  
    
  //递归解法  
  public String convertToTitle(int n) {  
      if (n == 0) {  
          return "";  
      }  
      n--;  
      return convertToTitle(n / 26) + (char) (n % 26 + 'A');  
  }  
```    
    
### 个人解读    
  比起一般的进制转换，区别主要在0上面，Excel格式的，是不存在0的，相当于1,2,3,4,5,6,7,8,9,11,12,,,  
  递归的算法呢，主要是出于%的运算特点而做的n--操作。(将1,2,3....26映射到A,B,C....Z， 直接%26的话，就会导致Z的映射变为了0，与前一个发生了26的断代)  
  然后根据递归的这个--操作，就会对%有了更深一层的认识。  
  对于一个数n，如果说n/26的结果表示n是26的几倍的话，那么，(n-1)/26  
  假设27，要分成 1 和 1  
   27 / 26 ,27 % 26  
  假设26，要分成 0 和 26  
  26 / 26, 26 % 26 == 0 ？ 26 ： 26 % 26  
  修订： (26 - 1) / 26, 26 % 26  
  所以说，把问题拆的很细很细以后，for循环就非常好写了。  
    
tags:    
  -   进制转换  
  -   细化情景  
