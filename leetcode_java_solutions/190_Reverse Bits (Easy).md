### description    
  Reverse bits of a given 32 bits unsigned integer.  
    
     
    
  Example 1:  
    
  Input: 00000010100101000001111010011100  
  Output: 00111001011110000010100101000000  
  Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.  
  Example 2:  
    
  Input: 11111111111111111111111111111101  
  Output: 10111111111111111111111111111111  
  Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10101111110010110010011101101001.  
    
  翻转一个数的比特位  
### solution    
```    
  public class Solution {  
      // you need treat n as an unsigned value  
      public int reverseBits(int n) {  
            int res = 0;  
          for(int i = 0; i < 32; i++) {     //这里需要判断32次  
              res <<= 1;            //  
              if((n & 1) == 1) {    //这一步是实际操作位数，需要放在 res左移的前面  
                  res |= 1;  
              }  
              n >>>= 1;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  处理int类型的数据时候，有一个特点，就是int只能处理结尾，而不能上来找到int的大的那一段，while条件要与0靠近。然而！位运算不同，不能以0作为标准，因为可能即使为0了，还需要看看补码里面前面一共有多少个0，可以考虑for循环32次解决。  
  第一次写算法的时候，for循环里面的顺序搞不清。可以这样想：一共需要判断32次末尾位，需要左移31次。  
  如果判断在前，之后再左移，就会出现再完成32次末尾位，需要左移31次，之后再左移一次。会出现多左移一次。  
  对于for循环，还是需要跳出代码，通过伪代码来辅助理解。  
    
    
tags:    
  -  位运算  
  -  位移  
