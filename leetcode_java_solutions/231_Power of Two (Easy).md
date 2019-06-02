### description    
  Given an integer, write a function to determine if it is a power of two.  
    
  Example 1:  
    
  Input: 1  
  Output: true   
  Explanation: 20 = 1  
  Example 2:  
    
  Input: 16  
  Output: true  
  Explanation: 24 = 16  
  Example 3:  
    
  Input: 218  
  Output: false  
### solution    
```    
  //通用算法：  
  class Solution {  
      public boolean isPowerOfTwo(int n) {  
          if (n < 1) {  
                return false;  
            }  
      
            while (n % 2 == 0) {  
                n /= 2;  
            }  
      
            return n == 1;  
      }  
  }  
    
  //位运算算法  
  class Solution {  
      public boolean isPowerOfTwo(int n) {  
          return n > 0 && (n&(n-1)) == 0;  
      }  
  }  
```    
    
### 个人解读    
  有一种通用算法：一直累除以2。  
  特殊算法：明显的2的幂和二进制位运算扯上了关系。这时候参考位运算的几个骚操作：  
    
  去除 n 的位级表示中最低的那一位    n&(n-1)    
  得到 n 的位级表示中最低的那一位    n&(-n)     
  去除 n 的位级表示中最高的那一位    n-n&(\~n+1)     
  看看这里面能不能用上的。如果是2的幂，那么该数字的二进制形式只有1个1，剩下全是0。发现n&(n-1)可用  
    
tags:    
  -  位运算  
  -  n&(n-1)    
