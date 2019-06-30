### description    
  In a given integer array nums, there is always exactly one largest element.  
    
  Find whether the largest element in the array is at least twice as much as every other number in the array.  
    
  If it is, return the index of the largest element, otherwise return -1.  
    
  Example 1:  
    
  Input: nums = [3, 6, 1, 0]  
  Output: 1  
  Explanation: 6 is the largest integer, and for every other number in the array x,  
  6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.  
     
    
  Example 2:  
    
  Input: nums = [1, 2, 3, 4]  
  Output: -1  
  Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.  
     
    
  Note:  
    
  nums will have a length in the range [1, 50].  
  Every nums[i] will be an integer in the range [0, 99].  
### solution    
```    
  class Solution {  
      public int dominantIndex(int[] nums) {  
         int len = nums.length;  
          if(len == 0) {  
              return -1;  
          }  
          if(len == 1) {  
              return 0;  
          }  
          int maxIndex = -1;  
          int max = Integer.MIN_VALUE;  
          int secondMax = Integer.MIN_VALUE;  
          for(int i = 0; i < nums.length; i++) {  
              int num = nums[i];  
              if(num == max || num == secondMax) continue;  
              if(num > max) {  
                  maxIndex = i;  
                  secondMax = max;  
                  max = num;  
              } else if(num > secondMax) {  
                  secondMax = num;  
              }  
          }  
            
          return max >= 2 * secondMax ? maxIndex : -1;  
      }  
  }  
```    
    
### 个人解读    
  比一般的取最大值多了一个要>=第二大的数字的两倍。  
  要么一遍遍历一边判断，要么最后再判断。  
  最后再判断容易一些。  
    
  通过测试用例：  
  ```  
  Your input  [1, 1]  
  Expected  0  
    
  Your input  [1]  
  Expected  0  
  ```  
    
  需要注意curNum == secondMax或max的时候  
    
    
tags:    
  -  数学    
  -  遍历    
