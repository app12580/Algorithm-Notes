### description    
  Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.  
    
  Note:  
  The given integer is guaranteed to fit within the range of a 32-bit signed integer.  
  You could assume no leading zero bit in the integer’s binary representation.  
  Example 1:  
  Input: 5  
  Output: 2  
  Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.  
  Example 2:  
  Input: 1  
  Output: 0  
  Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.  
   求一个数的补数。补数是对该数的二进制表示取反，并且不包含前导零位。  
### solution    
```    
  //方法一： 通过遍历，然后赋值  
  class Solution {  
      public int findComplement(int num) {  
          int res = 0;  
          int t = 1;  
          while(num > 0) {  
              if((num & 1) == 0) {  
                  res |= t;  
              }  
              t <<= 1;  
              num >>>= 1;  
          }  
          return res ;  
      }  
  }  
    
  //方法二  根据掩码  
  本方法所谓掩码， 比如 101，掩码就是111，  10010，掩码就是11111  
  public int findComplement(int num) {  
      if (num == 0) return 1;  // 步骤一：找到掩码  
      int mask = 1 << 30;     
      while ((num & mask) == 0) mask >>= 1;   这一步结束后，mask变成 100000  
      mask = (mask << 1) - 1;  
      return num ^ mask;   // 步骤二： 返回掩码与num异或  
  }  
```    
    
### 个人解读    
    
    
tags:    
  -     
