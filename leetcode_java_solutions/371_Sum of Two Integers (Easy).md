### description    
  Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.  
    
  Example 1:  
    
  Input: a = 1, b = 2  
  Output: 3  
  Example 2:  
    
  Input: a = -2, b = 3  
  Output: 1  
  用位运算实现整数的加法  
### solution    
```    
  public int getSum(int a, int b) {  
      return b == 0 ? a : getSum((a ^ b), (a & b) << 1);  
  }  
```    
    
### 个人解读    
  位运算实现加法，需要列数竖式理解。  
  a + b = a ^ b + (a & b) << 1  
  重复此操作，直到不会产生进位为止。  
  可以用递归，另外也可以用循环，一个为^，另一个为&，终止条件为&的那一个为0  
    
tags:    
  -  位运算  
