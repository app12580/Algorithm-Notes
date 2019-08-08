### description    
  Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.  
    
  Formally the function should:  
    
  Return true if there exists i, j, k   
  such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.  
  Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.  
    
  Example 1:  
    
  Input: [1,2,3,4,5]  
  Output: true  
  Example 2:  
    
  Input: [5,4,3,2,1]  
  Output: false  
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Increasing Triplet Subsequence.  
Memory Usage: 38.4 MB, less than 95.35% of Java online submissions for Increasing Triplet Subsequence.  
  
  
  class Solution {  
      public boolean increasingTriplet(int[] nums) {  
          if(nums == null || nums.length < 3) return false;  
          int min = nums[0];  
          int secondMin = Integer.MAX_VALUE;  
          int tempMin = Integer.MAX_VALUE;  
          for(int i = 1; i < nums.length; i++) {  
              int cur = nums[i];  
              if(tempMin < min) {  
                  if(cur > secondMin) {  
                      return true;  
                  } else if(cur == tempMin) {  
                        
                  } else if(cur > tempMin) {  
                      secondMin = cur;  
                      min = tempMin;  
                      tempMin = Integer.MAX_VALUE;  
                  } else {  
                      tempMin = cur;  
                  }  
              } else {  
                  if(cur > secondMin) {  
                      return true;  
                  } else if(cur > min) {  
                      secondMin = cur;  
                  } else if(cur == min){  
    
                  } else {  
                      tempMin = cur;  
                  }  
              }  
          }  
          return false;  
      }  
  }  
    
  // 精简后  
   if(nums == null || nums.length < 3) return false;  
          int min = nums[0];  
          int secondMin = Integer.MAX_VALUE;  
          int tempMin = Integer.MAX_VALUE;  
          for(int i = 1; i < nums.length; i++) {  
              int cur = nums[i];  
              if(cur > secondMin) {  
                  return true;  
              }  
              if(tempMin < min) {  
                  if(cur > tempMin) {  
                      secondMin = cur;  
                      min = tempMin;  
                      tempMin = Integer.MAX_VALUE;  
                  } else if(cur < tempMin){  
                      tempMin = cur;  
                  }  
              } else {  
                  if(cur > min) {  
                      secondMin = cur;  
                  } else if(cur < min) {  
                      tempMin = cur;  
                  }  
              }  
          }  
          return false;  
```    
    
### 个人解读    
  curLen问题，注意结果是可以不连续的，需要将问题转化。  
  思路是弄一个变量存储最小值，还有一个存储倒数第二小的。但是这样子会遇到顺序问题。  
  所以还需要做的变量是:某一个最小值A，在这个最小值右面的第二小值B，超过最小值的索引并且比最小值还要小的值C，只有之后D比B小时，才会把CD更新到AB上  
    
tags:    
  -  curLen  
  -  细节  
  -  重点数学  
  -  各种if else  
