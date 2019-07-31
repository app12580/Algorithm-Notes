### description    
  Additive number is a string whose digits can form additive sequence.  
    
  A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.  
    
  Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.  
    
  Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.  
    
  Example 1:  
    
  Input: "112358"  
  Output: true   
  Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.   
               1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8  
  Example 2:  
    
  Input: "199100199"  
  Output: true   
  Explanation: The additive sequence is: 1, 99, 100, 199.   
               1 + 99 = 100, 99 + 100 = 199  
### solution    
```    
  import java.math.BigInteger;  
    
  class Solution {  
       
       public boolean isAdditiveNumber(String num) {  
          int n = num.length();  
          for (int i = 1; i <= n / 2; ++i) {  //i为第一个数字的长度  
              //每次循环，另[0,i]为第一个数字  
              if (num.charAt(0) == '0' && i > 1) return false;  
              BigInteger x1 = new BigInteger(num.substring(0, i));  
              for (int j = 1; Math.max(j, i) <= n - i - j; ++j) {     //i为第一个数字的长度，j为第二个数字的长度  
                  if (num.charAt(i) == '0' && j > 1) break;  
                  BigInteger x2 = new BigInteger(num.substring(i, i + j));  
                  if (isValid(x1, x2, j + i, num)) return true;  
              }  
          }  
          return false;  
      }  
    
      //辅助方法的四个参数  
      private boolean isValid(BigInteger x1, BigInteger x2, int start, String num) {  
          if (start == num.length()) return true;  
          x2 = x2.add(x1);  
          x1 = x2.subtract(x1);  
          String sum = x2.toString();  
          return num.startsWith(sum, start) && isValid(x1, x2, start + sum.length(), num);  
      }  
        
        
  }  
```    
    
### 个人解读    
  最大的疑虑在于，总感觉从左到右每次都dfs一次效率有点浪费。。。  
  需要注意的是为了方便使用，使用ij两个变量描述两个数字的长度  
    
tags:    
  -  DFS  
  -  数学  
  -  递归  
