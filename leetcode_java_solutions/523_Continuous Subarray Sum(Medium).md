### description      
  Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.    
      
       
      
  Example 1:    
      
  Input: [23, 2, 4, 6, 7],  k=6    
  Output: True    
  Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.    
  Example 2:    
      
  Input: [23, 2, 6, 4, 7],  k=6    
  Output: True    
  Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.    
       
      
  Note:    
      
  The length of the array won't exceed 10,000.    
  You may assume the sum of all the numbers is in the range of a signed 32-bit integer.    
### solution      
```      
  class Solution {    
      public boolean checkSubarraySum(int[] nums, int k) {    
           int sum = 0;    
          Map<Integer, Integer> map = new HashMap<>();    
          for(int i = 0; i < nums.length; i++) {    
              sum += nums[i];    
                  
              int mod = k == 0 ? sum : sum % k;     // 这一行很关键    
              if(mod == 0 && i > 0) return true;     
              if(map.containsKey(mod)) {    
                  if(i - map.get(mod) > 1) {    
                      return true;    
                  }    
              } else {    
                  map.put(mod, i);    
              }    
          }    
          return false;    
      }    
  }    
```      
      
### 个人解读      
  感觉是一个On2的算法，暴力法好像效率太差了。    
      
  本题可以参考另外那个01相同个数的做法，采用差值法，只要%target的值一致，则表示有结果。    
  最关键的一行：    
  ```    
        int mod = k == 0 ? sum : sum % k;     // 这一行很关键，很精髓  
  ```    
      
tags:      
  - 差值法    
  - 求和区间     
