### description    
  Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.  
    
  Example 1:  
  Input:nums = [1,1,1], k = 2  
  Output: 2  
  Note:  
  The length of the array is in range [1, 20,000].  
  The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].  
### solution    
```    
  
Runtime: 13 ms, faster than 94.41% of Java online submissions for Subarray Sum Equals K.  
Memory Usage: 39.4 MB, less than 88.16% of Java online submissions for Subarray Sum Equals K.  
  
  
  class Solution {  
      public int subarraySum(int[] nums, int k) {  
        int res = 0;  
          Map<Integer, Integer> map = new HashMap<>();  
          int sum = 0;  
          for(int num: nums) {  
              sum += num;  
              if(sum == k) res++;  
              if(map.containsKey(sum - k)) {  
                  res += map.get(sum - k);  
              }  
              map.put(sum, map.getOrDefault(sum, 0) +1);  
          }  
          return res;    
      }  
  }  
```    
    
### 个人解读    
  差值法。  
    
  一次成，开心撒，又学会了一种代码模板。  
    
tags:    
  -  差值法  
  -  数组求和  
