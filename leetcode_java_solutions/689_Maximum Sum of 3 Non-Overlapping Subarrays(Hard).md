### description    
  In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.  
    
  Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.  
    
  Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.  
    
  Example:  
    
  Input: [1,2,1,2,6,7,5,1], 2  
  Output: [0, 3, 5]  
  Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].  
  We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.  
     
    
  Note:  
    
  nums.length will be between 1 and 20000.  
  nums[i] will be between 1 and 65535.  
  k will be between 1 and floor(nums.length / 3).  
### solution    
```    
// 方法一： 前缀和 + 三分法  
Runtime: 6 ms, faster than 21.67% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.  
Memory Usage: 37.9 MB, less than 100.00% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.  
  class Solution {  
     public int[] maxSumOfThreeSubarrays(int[] nums, int k) {  
          int[] sums = new int[nums.length - k + 1];  
          int sum = 0;  
          for (int i = 0; i < k; i++) {  
              sum += nums[i];  
          }  
          sums[0] = sum;  
          for (int i = k; i < nums.length; i++) {  
              sum += nums[i] - nums[i - k];  
              sums[i - k + 1] = sum;  
          }  
          // 从sums中找到三个最大的，并且 j - i >= k  
          int maxVal = Integer.MIN_VALUE;  
          int maxIndex = 0;  
          int[][] left = new int[sums.length][2]; //[0]是val， [1]是索引，从左到右遍历时候  
          int[][] right = new int[sums.length][2]; //[0]是val， [1]是索引，从左到右遍历时候  
          for(int i = 0; i < sums.length; i++) {  
              int cur = sums[i];  
              if(cur > maxVal) {  
                  maxVal = cur;  
                  maxIndex = i;  
              }  
              left[i][0] = maxVal;  
              left[i][1] = maxIndex;  
          }  
          maxVal = Integer.MIN_VALUE;  
          maxIndex = 0;  
          for(int i = sums.length - 1; i >= 0; i--) {  
              int cur = sums[i];  
              if(cur >= maxVal) {  
                  maxVal = cur;  
                  maxIndex = i;  
              }  
              right[i][0] = maxVal;  
              right[i][1] = maxIndex;  
          }  
          //开始正式计算  
          int leftIndex = 0;  
          int midIndex = k;  
          int rightIndex = right[k+k][1];  
          // 0 1 2 3 4 5; k = 2  
          for(int i = k + 1; i <= sums.length - 1 - k; i++) {  
              int preSum = sums[leftIndex] + sums[midIndex] + sums[rightIndex];  
              int curSum = sums[i] + left[i - k][0] + right[i+k][0];  
              if(curSum > preSum) {  
                  leftIndex = left[i - k][1];  
                  rightIndex = right[i + k][1];  
                  midIndex = i;  
                  //如果相等，明显先前的字典排序更小一些  
              }  
          }  
          return new int[]{leftIndex, midIndex, rightIndex};  
      }  
    
  }  
```    
    
### 个人解读    
  首先可以想到一个优化就是创建一个长度为n-k+1的数组，里面存着每k个数字区间的和。  
    
  然后想到DP，但是DP模型遇到点障碍：  
  1、需要多维还是多个？  
  2、根据某一位是否结尾包括还是根据123个区间。  
  3、就算使用暴力法，也不过是O(N^3)的复杂度  
    
  三分法，可以省去动态规划的步骤。  
    
tags:    
  -  三分法  
