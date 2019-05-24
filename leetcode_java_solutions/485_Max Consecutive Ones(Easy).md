### description    
  Given a binary array, find the maximum number of consecutive 1s in this array.  
    
  Example 1:  
  Input: [1,1,0,1,1,1]  
  Output: 3  
  Explanation: The first two digits or the last three digits are consecutive 1s.  
      The maximum number of consecutive 1s is 3.  
  Note:  
    
  The input array will only contain 0 and 1.  
  The length of input array is a positive integer and will not exceed 10,000  
    
### solution    
```    
  class Solution {  
      public int findMaxConsecutiveOnes(int[] nums) {  
          int maxCount = 0;  
          int curCount = 0;  
          for(int num: nums) {  
              if(num == 1) {  
                  curCount++;  
                  maxCount = Math.max(maxCount, curCount);  
              } else {  
                  curCount = 0;  
              }  
          }  
          return maxCount;  
      }  
  }  
```    
    
### 个人解读    
  容易有一个误区，需要声明一个中间变量用来存储前一个位置是否是1，然而：只要无脑+=curCount就行，前一个位置不是1，无非就是curCount=0罢了  
    
tags:    
  - 数组  
