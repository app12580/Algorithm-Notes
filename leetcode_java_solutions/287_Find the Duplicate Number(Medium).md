### description    
  Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.  
    
  Example 1:  
    
  Input: [1,3,4,2,2]  
  Output: 2  
  Example 2:  
    
  Input: [3,1,3,4,2]  
  Output: 3  
  Note:  
    
  You must not modify the array (assume the array is read only).  
  You must use only constant, O(1) extra space.  
  Your runtime complexity should be less than O(n2).  
  There is only one duplicate number in the array, but it could be repeated more than once.  
### solution    
```    
  class Solution {  
       public int findDuplicate(int[] nums) {  
          for(int n: nums) {  
              if(nums[Math.abs(n) - 1] > 0) {  
                  nums[Math.abs(n) - 1] *= -1;  
              } else {  
                  return Math.abs(n);  
              }  
          }  
          return -1;  
      }  
  }  
```    
    
### 个人解读    
  类比[645](645_Set%20Mismatch%20(Easy).md)，Medium和Easy一模一样的算法  
  关键都在于，特殊的nums属性，从1,2,3,4...n。  
  可以通过数组自身作为存储结果，保存处理过的标记  
    
tags:    
  -   数组    
  -   自然数组     
