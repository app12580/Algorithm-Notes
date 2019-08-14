### description    
  Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:  
    
  Si % Sj = 0 or Sj % Si = 0.  
    
  If there are multiple solutions, return any subset is fine.  
    
  Example 1:  
    
  Input: [1,2,3]  
  Output: [1,2] (of course, [1,3] will also be ok)  
  Example 2:  
    
  Input: [1,2,4,8]  
  Output: [1,2,4,8]  
### solution    
```    
Runtime: 17 ms, faster than 44.75% of Java online submissions for Largest Divisible Subset.  
Memory Usage: 36.9 MB, less than 100.00% of Java online submissions for Largest Divisible Subset.  
  
  
  class Solution {  
      public List<Integer> largestDivisibleSubset(int[] nums) {  
          Arrays.sort(nums);  
          List<Integer> res = new ArrayList<>();  
          if(nums.length == 0) return res;  
          int[] count = new int[nums.length];  
          int[] preIndex = new int[nums.length];  
          Arrays.fill(preIndex, -1);  
          int max = 0;  
          int maxIndex = -1;  
          for(int i = 1; i < nums.length; i++) {  
              int cur = nums[i];  
              for(int j = 0; j < i; j++) {  
                  if(cur % nums[j] == 0) {  
                      if(count[j] + 1 > count[i]) {  
                          count[i] = count[j] + 1;  
                          preIndex[i] = j;  
                      }  
                  }  
              }  
              if(count[i] > max) {  
                  max = count[i];  
                  maxIndex = i;  
              }  
          }  
          if(maxIndex != -1) {  
              res.add(nums[maxIndex]);  
              while(preIndex[maxIndex] != -1) {  
                  maxIndex = preIndex[maxIndex];  
                  res.add(nums[maxIndex]);  
              }  
          }  
          if(res.size() == 0) res.add(nums[0]);  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  先数组排序，然后从小到大挨个判断，需要回溯。  
  因为数组可能不连续，如果每次都要遍历一大堆，效率很低。  
    
  比起通过List，然后不停的删减回溯，好像直接一个数组就可以直接解决了。  
    
    
tags:    
  -  数字逻辑  
  -  dfs优化  
