### description    
  Given an unsorted array of integers, find the length of longest increasing subsequence.  
    
  Example:  
    
  Input: [10,9,2,5,3,7,101,18]  
  Output: 4   
  Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.   
  Note:  
    
  There may be more than one LIS combination, it is only necessary for you to return the length.  
  Your algorithm should run in O(n2) complexity.  
  Follow up: Could you improve it to O(n log n) time complexity?  
    
  最长递增子序列(非连续子序列)  
### solution    
```    
  class Solution {  
     public int lengthOfLIS(int[] nums) {  
          if(nums == null || nums.length == 0) return 0;  
          int[] dp = new int[nums.length];  
          dp[0]  = 1;  
          int res = 1;  
          for(int i = 1; i < nums.length; i++) {  
              int max  = 1;  
              int cur = nums[i];  
              for(int j = 0; j < i; j++) {  
                  if(cur > nums[j]) {  
                      max = Math.max(max, dp[j] + 1);  
                  }  
              }  
              dp[i] = max;  
              res = Math.max(res, max);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  dp使用方法，获取dp[i]  (以i结尾的递增子序列长度)。  
  
  想要变成O(NlgN)的复杂度，需要存储中间结果。构建一个中间数组，如果长度相同，让末尾值最小，然后如果大于末尾值，添加进末尾。  
    
  定义一个 tails 数组，其中 tails[i] 存储长度为 i + 1 的最长递增子序列的最后一个元素。对于一个元素 x，  
  如果它大于 tails 数组所有的值，那么把它添加到 tails 后面，表示最长递增子序列长度加 1；  
  如果 tails[i-1] < x <= tails[i]，那么更新 tails[i] = x。  
    
    
tags:    
  -  数组  
  -  动态规划  
