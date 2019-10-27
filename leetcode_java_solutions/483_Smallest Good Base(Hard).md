### description      
  For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.    
      
  Now given a string representing n, you should return the smallest good base of n in string format.    
      
  Example 1:    
      
  Input: "13"    
  Output: "3"    
  Explanation: 13 base 3 is 111.    
       
      
  Example 2:    
      
  Input: "4681"    
  Output: "8"    
  Explanation: 4681 base 8 is 11111.    
       
      
  Example 3:    
      
  Input: "1000000000000000000"    
  Output: "999999999999999999"    
  Explanation: 1000000000000000000 base 999999999999999999 is 11.    
       
      
  Note:    
      
  The range of n is [3, 10^18].    
  The string representing n is always valid and will not have leading zeros.    
### solution      
```      
// 二分法 数学    
  Runtime: 167 ms, faster than 25.78% of Java online submissions for Smallest Good Base.    
  Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Smallest Good Base.    
  import java.math.BigInteger;  
    
  class Solution {  
      public String smallestGoodBase(String n) {  
          long num = Long.parseLong(n);  
          long res = 0;  
          for(int k = 60; k >= 2; k--){  
              long l = 2, h = num;  
              while(l < h){  
                  long m = l + (h - l) / 2;  
                  BigInteger left = BigInteger.valueOf(m);  
                  left = left.pow(k).subtract(BigInteger.ONE);  // (m ^ k - 1)  
                  BigInteger right = BigInteger.valueOf(num).multiply(BigInteger.valueOf(m).subtract(BigInteger.ONE));  
                  // num * (m - 1)  
                  int cmr = left.compareTo(right);  
                  if(cmr == 0){  
                      res =  m;  
                      break;  
                  } else if(cmr < 0){  
                      l = m + 1;  
                  } else {  
                      h = m;  
                  }  
              }  
              if(res != 0) break;  
          }  
          return "" + res;  
      }  
  }    
```      
      
### 个人解读      
  感觉需要参考一下等比公式，感觉没用(感觉错了。。。)    
      
  观察下面这个示例，发现n-1一定是满足条件的。问题来了，有没有一个好的二分法来解决这个问题呢？    
      
  sum = (q ^ n - 1) / (q - 1)    
  sum * (q - 1) == (q ^ n - 1)    
  如果左面大，说明q小了 （指数扩展的速度慢，问题在于底数小）    
  如果左面小，说明q大了     
  代码控制的是指数n保持不变    
    
  就算知道了这点，但还是一个很奇怪的二分法。从指数开始遍历，记住60这个点  
  ```  
  Why K starts from 2 to 60? Because log2(10 ^ 18 + 1) = 59.79471, (we should take smallest value of of X, which is 2 and largest value of n, which is 10^18 in this case). So K < 60.  
    
  
  ```  
      
      
  ```    
  Input: "1000000000000000000"    
  Output: "999999999999999999"    
  Explanation: 1000000000000000000 base 999999999999999999 is 11.    
  ```    
      
tags:      
  -  数学    
  -  二分法    
