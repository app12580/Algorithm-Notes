### description    
  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.  
    
  (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).  
    
  Find the minimum element.  
    
  You may assume no duplicate exists in the array.  
    
  Example 1:  
    
  Input: [3,4,5,1,2]   
  Output: 1  
  Example 2:  
    
  Input: [4,5,6,7,0,1,2]  
  Output: 0  
    
    
  旋转数组的最小数字  
### solution    
```    
//应用二 另高位不变就完事了  
  class Solution {  
      public int findMin(int[] nums) {  
           int l = 0;  
          int h = nums.length - 1;  
          while(l < h) {  
              int m = l + (h-l) /2;  
              if(nums[m] > nums[h]){  
                  l = m + 1;  
              } else {  
                  h = m;  
              }  
          }  
          return nums[l];  
      }  
  }  
```    
    
### 个人解读    
  想到二分法，然后主要问题在于如何判断是最小值前面的，还是后面的。  
  [4,5,6,7,0,1,2]  
  nums[i]要么和nums[i-1]要么和nums[i+1]比较，还要考虑下标越界的问题  
  感觉+1-1区别不大，如果越界说明l和h重合了，直接return。  
    
  然而是错误想法，下一个思路，比较nums[l],nums[m],nums[h]之间的关系：  
  nums[m]和nums[h]比较  
  如果nums[m] > nums[h]，l=m+1;  
  如果nums[m] < nums[h], h=m-1  
  然后考虑一下相邻情况时的状况  
    
  二分法应用一遇见障碍，考虑应用二的情形。  
  数组中第一个中值小于右值的位置  
  本题不存在等于的情况  
    
tags:    
  -  二分法  
