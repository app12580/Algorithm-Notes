### description    
  Given a 32-bit signed integer, reverse digits of an integer.  
    
  Example 1:  
    
  Input: 123  
  Output: 321  
  Example 2:  
    
  Input: -123  
  Output: -321  
  Example 3:  
    
  Input: 120  
  Output: 21  
  Note:  
  Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.  
    
  int反转，遇到超限的返回0。  
### solution    
```    
  class Solution {  
      public int reverse(int x) {  
           int res = 0;  
          while(x != 0) {  
              int tail = x % 10;  
              int temp = res * 10 + tail;  
              if((temp - tail) / 10 != res) {  
                  return 0;  
              }  
              res = temp;  
              x /= 10;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  常用功能，主要难点有两个：负数和0的处理；以及超限的处理。  
  负数：首先需要明确%的用法  
  ```  
  java里面，只和被除数有关系  
          System.out.println(4 % 3);        //1  
          System.out.println(-4 % 3);       //-1  
          System.out.println(4 % -3);       //1  
          System.out.println(-4 % -3);      //-1  
  ```  
  java里面是取余运算  
    
  超限处理：  
  ```  
  if((temp - tail) / 10 != res) {  
    return 0;  
}  
  ```  
    
tags:    
  -  int超限  
