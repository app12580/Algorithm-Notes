### description    
  Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.  
    
  Example 1:  
    
  Input: [2,3,-2,4]  
  Output: 6  
  Explanation: [2,3] has the largest product 6.  
  Example 2:  
    
  Input: [-2,0,-1]  
  Output: 0  
  Explanation: The result cannot be 2, because [-2,-1] is not a subarray.  
    
  最大乘积子序列  
### solution    
```    
Runtime: 1 ms, faster than 99.21% of Java online submissions for Maximum Product Subarray.  
Memory Usage: 39.7 MB, less than 7.17% of Java online submissions for Maximum Product Subarray.  
  
  class Solution {  
      public int maxProduct(int[] nums) {  
          int res = nums[0];        //通过这一行就可以解决掉最终结果是负数的情况了  
          int len = nums.length;  
          int[] negativeDp = new int[len];  
          int[] positiveDp = new int[len];  
          for(int i = 0; i < len; i++) {  
              int cur = nums[i];  
              if(i == 0) {  
                  if(cur > 0) {  
                      positiveDp[0] = cur;  
                      res = cur;  
                  } else {  
                      negativeDp[0] = cur;  
                  }  
              } else {  
                  if(cur > 0) {  
                      positiveDp[i] = Math.max(positiveDp[i-1] * cur, cur);  
                      res = Math.max(res, positiveDp[i]);  
                      negativeDp[i] = negativeDp[i-1] * cur;  //先当成0处理吧  
                  } else {  
                      positiveDp[i] = negativeDp[i-1] * cur;  //先当成0处理吧  
                      res = Math.max(res, positiveDp[i]);  
                      negativeDp[i] = Math.min(cur, positiveDp[i-1] * cur);  
                  }  
              }  
          }  
    
          return res;  
      }  
  }  
```    
    
### 个人解读    
  真的好奇，英文的题目描述里没看到乘法相关的字眼啊。  
    
  正数、负数、0，三者的情况。  
  想着弄两个dp[]，分别存储以当前节点为负数的最大值和最小值。  
    
    
tags:    
  -  动态规划  
  -  数学  
