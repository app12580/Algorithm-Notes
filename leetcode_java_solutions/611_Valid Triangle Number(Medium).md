### description    
  Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.  
  Example 1:  
  Input: [2,2,3,4]  
  Output: 3  
  Explanation:  
  Valid combinations are:   
  2,3,4 (using the first 2)  
  2,3,4 (using the second 2)  
  2,2,3  
  Note:  
  The length of the given array won't exceed 1000.  
  The integers in the given array are in the range of [0, 1000].  
### solution    
```    
// 方法一： 先排序，然后暴力法  
Runtime: 212 ms, faster than 8.69% of Java online submissions for Valid Triangle Number.  
Memory Usage: 56.7 MB, less than 10.00% of Java online submissions for Valid Triangle Number.  
  class Solution {  
      public int triangleNumber(int[] nums) {  
          Arrays.sort(nums);  
          int res = 0;  
          int len = nums.length;  
          for(int i = 0; i < len; i++) {  
              for(int j = i + 1; j < len; j++) {  
                  for(int k = j + 1; k < len; k++) {  
                      if(nums[i] + nums[j] > nums[k]) res++;  
                  }  
              }  
          }  
          return res;  
      }  
  }  
    
  // 方法二： 优化  
  class Solution {  
       public int triangleNumber(int[] nums) {  
          Arrays.sort(nums);  
          int res = 0;  
          int len = nums.length;  
          for (int i = nums.length-1; i>=2; i--) {  
              int left = 0;  
              int right = i-1;  
              while (left < right) {  
                  if (nums[left] + nums[right] > nums[i]) {  
                      res += right - left;  
                      right--;  
                  } else {  
                      left++;  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  先排序，然后暴力法，因为有了大小关系，所以比起N^3来说快了一点点。  
    
  方法二的优化： 先找大的值，然后根据每个大的值去求符合要求的区间。  
    
tags:    
  -  数学  
  -  模型优化  
