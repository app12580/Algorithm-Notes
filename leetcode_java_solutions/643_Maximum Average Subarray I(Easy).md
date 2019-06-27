### description    
  Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.  
    
  Example 1:  
    
  Input: [1,12,-5,-6,50,3], k = 4  
  Output: 12.75  
  Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75  
     
    
  Note:  
    
  1 <= k <= n <= 30,000.  
  Elements of the given array will be in the range [-10,000, 10,000].  
### solution    
```    
  class Solution {  
      public double findMaxAverage(int[] nums, int k) {  
           int sum = 0;  
          int max = Integer.MIN_VALUE;  
          // 0 1 2 3 4   2  
          for(int i = 0; i < nums.length; i++) {  
              if(i < k - 1) {  
                  sum += nums[i];  
              } else if(i == k - 1) {  
                  sum += nums[i];  
                  max = sum;  
              } else {  
                  sum = sum - nums[i - k] + nums[i];  
                  max = Math.max(max, sum);  
              }  
          }  
          return (double)max / k;  
      }  
  }  
```    
    
### 个人解读    
  从左到右遍历，然后用若干个变量存储当前和，经过某种处理或判断，来调整变量。  
  本题因为控制了个数k，感觉从头到尾全部算一遍也未尝不可。  
    
    
tags:    
  -  数学  
  -  遍历  
