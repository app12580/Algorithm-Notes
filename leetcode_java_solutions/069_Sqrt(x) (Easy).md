### description    
  Implement int sqrt(int x).  
    
  Compute and return the square root of x, where x is guaranteed to be a non-negative integer.  
    
  Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.  
    
  Example 1:  
    
  Input: 4  
  Output: 2  
  Example 2:  
    
  Input: 8  
  Output: 2  
  Explanation: The square root of 8 is 2.82842..., and since   
               the decimal part is truncated, 2 is returned.  
                
   返回平方根              
### solution    
```    
  class Solution {  
    public int mySqrt(int x) {  
        if(x <= 0) {  
              return 0;  
          }  
          int l = 1;  
          int h = x;  
          while(l <= h) {  
              int m = l + (h - l) / 2;  
              int sqrt = x / m;  
              if(sqrt == m) {  
                  return m;  
              } else if(sqrt < m) {  
                  h = m - 1;  
              } else {  
                  l = m + 1;  
              }  
          }  
          return h;  
  }  
  }  
```    
    
### 个人解读    
  求解平方数，除了正常的循环遍历写法，还有其他写法：  
  牛顿方程法；魔数法。  
    
  二分法的应用，如果mid恰好则return，否则遍历到最后return一个。  
  因为while条件和+-1操作只影响最终一轮，所以需要想清楚，跳出循环后，return的是l还是h。  
    
  避免int * int，应该使用除法计算。  
    
tags:    
  - 二分法    
