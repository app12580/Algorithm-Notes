### description    
  The Hamming distance between two integers is the number of positions at which the corresponding bits are different.  
    
  Now your job is to find the total Hamming distance between all pairs of the given numbers.  
    
  Example:  
  Input: 4, 14, 2  
    
  Output: 6  
    
  Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just  
  showing the four bits relevant in this case). So the answer will be:  
  HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.  
  Note:  
  Elements of the given array are in the range of 0 to 10^9  
  Length of the array will not exceed 10^4.  
### solution    
```    
  
Runtime: 19 ms, faster than 39.16% of Java online submissions for Total Hamming Distance.  
Memory Usage: 39 MB, less than 98.98% of Java online submissions for Total Hamming Distance.  
  
  
  class Solution {  
      public int totalHammingDistance(int[] nums) {  
          int res = 0;  
          int c = 1;  
          for(int i = 0; i < 32; i++) {  
              int zeros = 0;  
              int ones = 0;  
              for(int num: nums) {  
                  if((num & c) > 0) {  
                      ones++;  
                  } else {  
                      zeros++;  
                  }  
              }  
              c <<= 1;  
              res += zeros * ones;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  如果流水账计算的话，明显效率很差。  
    
  有个思路，外层循环是32，每一次比较所以数字的某一位，那么问题就来了，所有的位放在一块，能计算出来总的hamming距离吗？  
    
  0 0 0 : 0  
  1 0 0 : 2  
  1 1 0 : 2  
  1 1 1 : 0  
    
  0 0 0 0: 0  
  0 0 0 1: 3  
  0 0 1 1: 4  
  0 1 1 1: 3  
  1 1 1 1: 0  
    
  所以： 总距离 = 0的个数 * 1的个数  
    
  然后下一个问题： 1位的总距离 和 2位的总距离应该怎么统计和？   
  答案： 用加法即可。  
    
  0100  
  1110  
  0010  
  0 + 2 + 2 + 2 = 6  
    
  感觉思路可行。    
    
tags:    
  -  位运算  
