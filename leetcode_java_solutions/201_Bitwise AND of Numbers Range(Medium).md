### description    
  Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.  
    
  Example 1:  
    
  Input: [5,7]  
  Output: 4  
  Example 2:  
    
  Input: [0,1]  
  Output: 0  
### solution    
```    
  class Solution {  
      public int rangeBitwiseAnd(int m, int n) {  
         int i = 0;  
          while(m != n) {  
              m >>= 1;  
              n >>= 1;  
              i++;  
          }  
          return m * (1 << i);  
      }  
  }  
```    
    
### 个人解读    
  看见题目一脸懵逼，这种能有什么位运算的内在规律吗？  
  就算从int的32位去考虑，也很难想。  
    
  去翻翻位运算圣经：发现  
  ```  
    去除 n 的位级表示中最低的那一位    n&(n-1)    
  ```  
  并没有卵用  
    
  ```  
  The hardest part of this problem is to find the regular pattern.  
  For example, for number 26 to 30  
  Their binary form are:  
  11010  
  11011  
  11100　　  
  11101　　  
  11110  
    
  Because we are trying to find bitwise AND, so if any bit there are at least one 0 and one 1, it always 0. In this case, it is 11000.  
  So we are go to cut all these bit that they are different. In this case we cut the right 3 bit.  
  ```  
  简单翻译一下：最后的结果一定就是前几位的内容。  
    
tags:    
  -  位运算  
  -  重点数学  
