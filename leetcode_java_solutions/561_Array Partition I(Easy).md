### description    
  Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.  
    
  Example 1:  
  Input: [1,4,3,2]  
    
  Output: 4  
  Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).  
  Note:  
  n is a positive integer, which is in the range of [1, 10000].  
  All the integers in the array will be in the range of [-10000, 10000].  
### solution    
```    
  class Solution {  
      public int arrayPairSum(int[] nums) {  
          Arrays.sort(nums);  
          int sum = 0;  
          for(int i = 0; i < nums.length; i+=2) {  
              sum += nums[i];  
          }  
          return sum;  
      }  
  }  
```    
    
### 个人解读    
  示例在疯狂暗示这题需要排序，然后两两一组，问题是如何证明。  
    
  证明：  
  赋予实际意义  
  有2n个数字，需要献祭掉一半的数字，每个献祭者可以保留一个比它弱的活下来。  
  那么就会导致，最强的数字肯定被献祭，那么它要保护谁呢？容易知道保护原本第二强的。  
  剩下的数字不断重复此过程。  
    
    
tags:    
  -  数学  
