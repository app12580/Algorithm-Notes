### description    
  Your are given an array of positive integers nums.  
    
  Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.  
    
  Example 1:  
  Input: nums = [10, 5, 2, 6], k = 100  
  Output: 8  
  Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].  
  Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.  
  Note:  
    
  0 < nums.length <= 50000.  
  0 < nums[i] < 1000.  
  0 <= k < 10^6.  
### solution    
```    
  
Runtime: 8 ms, faster than 98.39% of Java online submissions for Subarray Product Less Than K.  
Memory Usage: 51.4 MB, less than 100.00% of Java online submissions for Subarray Product Less Than K.  
  
  class Solution {  
      public int numSubarrayProductLessThanK(int[] nums, int k) {  
          int left = 0;  
          int res = 0;  
          int curPro = nums[0];  
          if(curPro < k) res++;  
          for(int right = 1; right < nums.length; right++) {  
              curPro *= nums[right];  
              while(curPro >= k && left <= right) {  
                  curPro /= nums[left++];  
              }  
              res += right - left + 1;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  N2遍历，需要存储中间结果  
  感觉好像可以用滑动窗口方法，每次加上当前滑动窗口  
    
  总结：  
  按照模板做起来还是很easy的  
    
tags:    
  -  滑动窗口  
