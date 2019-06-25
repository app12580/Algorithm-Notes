### description    
  Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).  
    
  Example 1:  
  Input: [3, 2, 1]  
    
  Output: 1  
    
  Explanation: The third maximum is 1.  
  Example 2:  
  Input: [1, 2]  
    
  Output: 2  
    
  Explanation: The third maximum does not exist, so the maximum (2) is returned instead.  
  Example 3:  
  Input: [2, 2, 3, 1]  
    
  Output: 1  
    
  Explanation: Note that the third maximum here means the third maximum distinct number.  
  Both numbers with value 2 are both considered as second maximum.  
### solution    
```    
  class Solution {  
      public int thirdMax(int[] nums) {  
         Integer firstMax = null;  
          Integer secondMax = null;  
          Integer thirdMax = null;  
          for(int i = 0; i < nums.length; i++) {  
              int val = nums[i];  
              if(Objects.equals(val, firstMax) || Objects.equals(val, secondMax) || Objects.equals(val, thirdMax) ){  
                  continue;  
              }  
              if(firstMax == null || val > firstMax) {  
                  thirdMax = secondMax;  
                  secondMax = firstMax;  
                  firstMax = val;  
              } else if(secondMax == null || val > secondMax) {  
                  thirdMax = secondMax;  
                  secondMax = val;  
              } else if(thirdMax == null || val > thirdMax) {  
                  thirdMax = val;  
              }  
          }  
          return thirdMax == null ? firstMax : thirdMax;   
      }  
  }  
```    
    
### 个人解读    
  创建三个变量用来存储结果  
    
tags:    
  -  Kth  
