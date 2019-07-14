### description    
  Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.  
    
  Example 1:  
    
  Input: nums = [1,2,3,1], k = 3, t = 0  
  Output: true  
  Example 2:  
    
  Input: nums = [1,0,1,1], k = 1, t = 2  
  Output: true  
  Example 3:  
    
  Input: nums = [1,5,9,1,5,9], k = 2, t = 3  
  Output: false  
    
  是否存在索引不超过k的两个索引上的值差距小于t  
### solution    
```    
  
Runtime: 364 ms, faster than 13.25% of Java online submissions for Contains Duplicate III.  
Memory Usage: 36.4 MB, less than 99.76% of Java online submissions for Contains Duplicate III.  
  class Solution {  
       public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {  
          int left = 0;  
          for(int i = 1; i < nums.length; i++) {  
              if(i <= k) {  
                  if(helper(nums, 0, i - 1, t, nums[i])) {  
                      return true;  
                  }  
              } else {  
                  if(helper(nums, ++left, i - 1, t, nums[i])) {  
                      return true;  
                  }  
              }  
          }  
          return false;  
      }  
    
      // start 和 end中间的数字和num的差距是否小于等于t  
      private boolean helper(int[] nums, int start, int end, int t, int num) {  
          for(int i = start; i <= end; i++) {  
              long sub = (long)nums[i] - num;  
              if(Math.abs(sub) <= (long)t) {  
                  return true;  
              }  
          }  
          return false;  
      }  
  }  
    
    // 方法二： 利用除法分段进行优化  
      private long getID(long x, long w) {  
          return x < 0 ? (x + 1) / w - 1 : x / w;  
      }  
    
      public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {  
          if (t < 0) return false;  
          Map<Long, Long> d = new HashMap<>();  
          long w = (long)t + 1;  
          for (int i = 0; i < nums.length; ++i) {  
              long m = getID(nums[i], w);  
              if (d.containsKey(m))  
                  return true;  
              if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)  
                  return true;  
              if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)  
                  return true;  
              d.put(m, (long)nums[i]);  
              if (i >= k) d.remove(getID(nums[i - k], w));  
          }  
          return false;  
      }  
  
```    
    
### 个人解读    
  这种问题好像都可以统一用curLen问题概括。  
  curLen的最终奥义是永不后退hiahiahia。  
    
  以这个为例：Input: nums = [1,5,9,1,5,9], k = 2, t = 3  
  先比较1,5,9，然后去掉1，新加入1，这个时候在比较5,9,1， 时间复杂度O(n * k)  
    
  需要注意辅助方法的int超限情况。  
    
  看了下优化，基础思路是一样子的，区别只在于如何判断前面是否出现了接近的数字。  
  利用除法  
    
tags:    
  -  curLen  
