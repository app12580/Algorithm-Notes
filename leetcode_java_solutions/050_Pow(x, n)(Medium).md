### description    
  Implement pow(x, n), which calculates x raised to the power n (xn).  
    
  Example 1:  
    
  Input: 2.00000, 10  
  Output: 1024.00000  
  Example 2:  
    
  Input: 2.10000, 3  
  Output: 9.26100  
  Example 3:  
    
  Input: 2.00000, -2  
  Output: 0.25000  
  Explanation: 2-2 = 1/22 = 1/4 = 0.25  
  Note:  
    
  -100.0 < x < 100.0  
  n is a 32-bit signed integer, within the range [−231, 231 − 1]  
### solution    
```    
  class Solution {  
      public double myPow(double x, int n) {  
          if(n == 0) return 1;  
          if(n > 0) {  
             double res = x;  
              if(n % 2 == 0) {  
                  return myPow(x * x, n /2);  
              } else {  
                  return x * myPow(x * x, n /2);  
              }  
          } else {  
              if(n == Integer.MIN_VALUE) {  
                  return 1.0 / x / myPow(x, Integer.MAX_VALUE);  
              }  
              return  1.0 / myPow(x, -n);  
          }  
      }  
  }  
```    
    
### 个人解读    
  服了，不光是计算结果超限，初始参数也可以是超限的，尤其是负数时，还没有一个与之对应的正数。  
    
  这题目第一看过来很蒙蔽，因为不知道要考什么，还以为是double有什么奇怪的特性经过测试用例之后才明白，主要考两点：  
    
  1、使用二分法提高效率；  
  2、输入的int超限怎么处理  
    
    
tags:    
  -  题目描述要考啥  
