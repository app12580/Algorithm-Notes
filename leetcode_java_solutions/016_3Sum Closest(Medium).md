### description    
  Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.  
    
  Example:  
    
  Given array nums = [-1, 2, 1, -4], and target = 1.  
    
  The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).  
### solution    
```    
  
Runtime: 6 ms, faster than 45.00% of Java online submissions for 3Sum Closest.  
Memory Usage: 37 MB, less than 99.58% of Java online submissions for 3Sum Closest.  
  
  
  class Solution {  
      public int threeSumClosest(int[] nums, int target) {  
           Integer res = null;  
          Arrays.sort(nums);  
          for(int i = 0; i < nums.length - 2; i++) {  
              if(i == 0 || nums[i] != nums[i - 1]) {  
                  int sum = target - nums[i];  
                  int l = i + 1;  
                  int h = nums.length - 1;  
                  while(l < h) {  
                      int cur = nums[l] + nums[h];  
                      if(res == null) {  
                          res = cur + nums[i];  
                      } else if(Math.abs(cur + nums[i] - target) < Math.abs(res - target)) {  
                          res = cur + nums[i];  
                      }  
                      if(cur == sum) {  
                          return target;  
                      } else if(cur < sum) {  
                          l++;  
                           
                      } else {  
                          h--;  
                      }  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  类似于3sum，每次都求和，然后进行比较，返回最接近target的res值。  
    
tags:    
  -  数组  
  -  双指针  
  -  三指针  
