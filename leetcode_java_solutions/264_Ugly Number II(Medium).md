### description    
  Write a program to find the n-th ugly number.  
    
  Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.   
    
  Example:  
    
  Input: n = 10  
  Output: 12  
  Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.  
  Note:    
    
  1 is typically treated as an ugly number.  
  n does not exceed 1690.  
### solution    
```    
  class Solution {  
      public int nthUglyNumber(int n) {  
             int[] dp = new int[n];  
          dp[0] = 1;  
          int f2 = 2;  
          int f3 = 3;  
          int f5 = 5;  
          int i2 = 0;  
          int i3 = 0;  
          int i5 = 0;  
          for(int i = 1; i < n; i++) {  
              int cur = Math.min(Math.min(f2, f3), f5);  
              dp[i] = cur;  
    
              if(f2 == cur) {  
                  f2 = 2 * dp[++i2];  
              }  
              if(f3 == cur) {  
                  f3 = 3 * dp[++i3];  
              }  
              if(f5 == cur) {  
                  f5 = 5 * dp[++i5];  
              }  
          }  
            
            
          return dp[n-1];  
      }  
  }  
```    
    
### 个人解读    
    
  最慢的方式就是挨个判断。问题是要找出数学的规律上。很容易联想到2*3*5=30，每30个数达成一次循环。  
  这题需要延伸，如果是235三个数字相加，那么如何排列顺序。因为本题也可以转化成1,log23,log25三者的相加排序问题。  
    
  ```  
  1,  
  2,3,4,5,6,8,9,10,12,15,16,18,20,24,25,27,30  
  32,36,40,45,48,50,54,60  
  ,64,72,75,80,81,90,96,  
  ```  
    
  2,3,4,5,6,8,9,10,12,15,16,18,20,24,25,27,30 好像30这样一个最小公倍数并没有什么用啊。。。  
    
  数学规律直接算不来是做不到的了。类似于动态规划，记录所有的丑数，然后每一次去用到前面已经有的结果。   
  丑数可以分成三类：2/3/5各自有一个序列。将这三个序列进行排序。每一序列等于2（or3or5）乘以前面所有的丑数。  
  
    
tags:    
  -  dp  
  -  乱序dp  
  -  重点数学  
