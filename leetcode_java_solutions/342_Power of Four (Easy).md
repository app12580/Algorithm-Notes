### description    
  Given an integer (signed 32 bits), write a function to check whether it is a power of 4.  
    
  Example 1:  
    
  Input: 16  
  Output: true  
  Example 2:  
    
  Input: 5  
  Output: false  
  Follow up: Could you solve it without loops/recursion?  
### solution    
```    
  //通用算法  
  class Solution {  
      public boolean isPowerOfFour(int num) {  
          if(num < 1) {  
              return false;  
          }  
          while (num %4 == 0) {  
              num /= 4;  
          }  
          return num == 1;  
      }  
  }  
    
  //特殊算法  
  class Solution {  
      public boolean isPowerOfFour(int num) {  
      return num > 0 && (num & (num - 1)) == 0 && (num & 0b01010101010101010101010101010101) != 0;  
      //多了一个判断， 让奇数位置上一定有1  
  }  
  }  
    
    
```    
    
### 个人解读    
  首先有个通用算法。  
  4: 100  
  16: 10000  
  64: 1000000  
  在判断2的幂基础上多了一层判断。  
    
tags:    
  -   位运算    
