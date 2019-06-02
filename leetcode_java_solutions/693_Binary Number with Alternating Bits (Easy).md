### description    
  Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.  
    
  Example 1:  
  Input: 5  
  Output: True  
  Explanation:  
  The binary representation of 5 is: 101  
  Example 2:  
  Input: 7  
  Output: False  
  Explanation:  
  The binary representation of 7 is: 111.  
  Example 3:  
  Input: 11  
  Output: False  
  Explanation:  
  The binary representation of 11 is: 1011.  
  Example 4:  
  Input: 10  
  Output: True  
  Explanation:  
  The binary representation of 10 is: 1010.  
    
  判断一个数的位级表示是否不会出现连续的 0 和 1  
### solution    
```    
  class Solution {  
      public boolean hasAlternatingBits(int n) {  
            int c = n >> 1;  
          c = c ^ n;  
          return (c & (c + 1)) == 0;  
      }  
  }  
```    
    
### 个人解读    
  看例子，没有考虑前面的那一串为0的前缀。  
  然后找规律：  
  1  
  10  
  101  
  1010  
    
  ...一共就31个情况。。。直接写个集合看在不在里面就完事了  
  位运算分为两类，比较和位移，本题明显从位移角度考虑更好。  
  问题是左移还是右移：因为首位是1，而末尾位不确定，如果左移会导致末尾位出现干扰项，所以右移。  
  位移之后，然后需要考虑与或异或用哪一个？  
       &    |    ^  
  00   0    0    0  
  11   1    1    0  
  01   0    1    1  
    
   101  
  1010  
    
   10011  
  100110  
    
  期望的结果全部1和0作比较，而干扰项是00和11，所以一定要选择^运算。  
    
    
tags:    
  -  位运算  
  -  位移  
