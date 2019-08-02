### description    
  Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.  
    
  Example 1:  
    
  Input: a = 2, b = [3]  
  Output: 8  
  Example 2:  
    
  Input: a = 2, b = [1,0]  
  Output: 1024  
### solution    
```    
  
Runtime: 4 ms, faster than 65.16% of Java online submissions for Super Pow.  
Memory Usage: 38 MB, less than 93.33% of Java online submissions for Super Pow.  
  
  
  class Solution {  
      public int superPow(int a, int[] b) {  
          int res = 1;  
          for(int i = 0; i < b.length; i++) {  
              int cur = b[i];  
              res = helper(res, 10) * helper(a, cur);  
              res %= 1337;  
          }  
          return res;      
      }  
        
      public int helper(int num, int pow) {  
          int res = 1;  
          for(int i = 1; i <= pow; i++) {  
              res = res * (num % 1337);  
              res %= 1337;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  重点在于对于%计算的理解，对于任意一个整形数字A，A = x * 1337 + y；  
  那么乘以一个数B后的数字为 AB=1337*x*B+y*B，然后再%1337后，发现与yB的结果一致，所以可以进行裁剪。  
    
  需要注意的就是yB的时候处理int超限问题  
    
  主要需要注意的地方是，在数组偏移的时候，把之前的power10，然后再乘以新出来的数字。  
  a^124 = (a^12)^10 * a ^ 4。  
  把这个式子列出来就OK了  
    
tags:    
  -  复用中间结果  
  -  四则运算  
  -  重点数学  
