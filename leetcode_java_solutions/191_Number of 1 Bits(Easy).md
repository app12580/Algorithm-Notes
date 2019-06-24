### description    
  Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).  
    
     
    
  Example 1:  
    
  Input: 00000000000000000000000000001011  
  Output: 3  
  Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.  
  Example 2:  
    
  Input: 00000000000000000000000010000000  
  Output: 1  
  Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.  
  Example 3:  
    
  Input: 11111111111111111111111111111101  
  Output: 31  
  Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.  
     
    
  Note:  
    
  Note that in some languages such as Java, there is no unsigned integer type. In this case, the input will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.  
  In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3 above the input represents the signed integer -3.  
    
    
### solution    
```    
  耗时波动好大。。。  
  //Runtime: 1 ms, faster than 19.45% of Java online submissions for Number of 1 Bits.  
    Memory Usage: 33.3 MB, less than 5.07% of Java online submissions for Number of 1 Bits.  
      
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of 1 Bits.  
    Memory Usage: 33.5 MB, less than 5.07% of Java online submissions for Number of 1 Bits.  
      
  public class Solution {  
      // you need to treat n as an unsigned value  
      public int hammingWeight(int n) {  
           int cnt = 0;  
          while(n != 0) {  
              if((n & 1) == 1) {  
                  cnt++;  
              }  
              n >>>= 1;  
          }  
          return cnt;  
      }  
  }  
```    
    
### 个人解读    
  位运算，需要无符号右移。  
  疑虑:终止条件!=0还是for循环32次好。  
    
tags:    
  -  位运算  
