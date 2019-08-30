### description    
  Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.  
    
     
    
  Example 1:  
    
  Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4  
  Output: True  
  Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.  
     
    
  Note:  
    
  1 <= k <= len(nums) <= 16.  
  0 < nums[i] < 10000.  
### solution    
```    
  class Solution {  
       
      public boolean canPartitionKSubsets(int[] nums, int k) {  
          if(nums == null || nums.length < k) {  
              return false;  
          }  
          int sum = 0;  
          for(int num: nums) {  
              sum += num;  
          }  
          if(sum % k != 0) {  
              return false;  
          }  
          int target = sum / k;  
          Arrays.sort(nums);  
          return dfs(nums, target, new int[k], nums.length - 1);  
      }  
    
      private boolean dfs(int[] nums, int target, int[] arr, int index) {  
          if(index == -1) {  
              return true;  
          }  
          int cur = nums[index];  
          for(int i = 0; i < arr.length; i++) {  
              if(arr[i] + cur <= target) {  
                  arr[i] += cur;  
                  if(dfs(nums, target, arr, index - 1)) {  
                      return true;  
                  }  
                  arr[i] -= cur;  
              }  
          }  
          return false;  
      }  
  }  
```    
    
### 个人解读    
  和[473](473_Matchsticks%20to%20Square(Medium).md)类似  
    
tags:    
  -  DFS    
  -  反思效率    
      
