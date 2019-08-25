### description    
  Given two strings representing two complex numbers.  
    
  You need to return a string representing their multiplication. Note i2 = -1 according to the definition.  
    
  Example 1:  
  Input: "1+1i", "1+1i"  
  Output: "0+2i"  
  Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.  
  Example 2:  
  Input: "1+-1i", "1+-1i"  
  Output: "0+-2i"  
  Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.  
  Note:  
    
  The input strings will not have extra blank.  
  The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.  
### solution    
```    
  
Runtime: 4 ms, faster than 38.62% of Java online submissions for Complex Number Multiplication.  
Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Complex Number Multiplication.  
  
  class Solution {  
      public String complexNumberMultiply(String a, String b) {  
          String result = "";  
          String[] A = a.split("\\+");  
          String[] B = b.split("\\+");  
          int a1 = Integer.parseInt(A[0]);  
          int b1 = Integer.parseInt(A[1].replace("i",""));  
    
          int a2 = Integer.parseInt(B[0]);  
          int b2 = Integer.parseInt(B[1].replace("i",""));  
    
          int a1a2 = a1 * a2;  
          int b1b2 = b1 * b2;  
          int a1b2a2b1 = (a1 * b2) + (b1 * a2);  
    
          String afinal = (a1a2 + (-1 * b1b2)) + "";  
          String bfinal = a1b2a2b1 + "i";  
          result = afinal+"+"+bfinal;  
          return result;  
      }  
  }  
```    
    
### 个人解读    
  很奇怪啊，好好地，"+-"这东西是闹什么啊？？？？  
  凭什么"0+2i"的0不被省略  
  凭什么"+-"计算完了还要恢复成"+-"  
    
  不清不楚的，题目里面也不说明白。  
  垃圾题目  
    
  真TM的服了，等于说整个题目只有"+"和"+-"，而没有"+-"  
    
tags:    
  -  字符串解析  
