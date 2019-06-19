### description    
  Given a sorted array consisting of only integers where every element appears exactly twice except for one element which appears exactly once. Find this single element that appears only once.  
    
     
    
  Example 1:  
    
  Input: [1,1,2,3,3,4,4,8,8]  
  Output: 2  
  Example 2:  
    
  Input: [3,3,7,7,10,11,11]  
  Output: 10  
     
     
   一个有序数组只有一个数不出现两次，找出这个数。  
### solution    
```    
  class Solution {  
      public int singleNonDuplicate(int[] nums) {  
         if(nums.length == 1) {  
              return nums[0];  
          }  
          int l = 0;  
          int h = nums.length - 1;  
          while(l < h) {  
              int m = l + (h - l) / 2;  
              if(m % 2 == 1)m--;        // --是关键，因为题目是求满足条件的最左边值，让m--往小了不会错过答案  
    
              if(nums[m] != nums[m+1]){  
                  h = m;  
              } else {  
                  l = m + 2;  
              }  
          }  
          return nums[l];  
      }  
  }  
```    
    
### 个人解读    
  主要问题在于题目对时间做出了要求，所以不能遍历或者异或操作。  
  Input: [1, 1, 2, 3, 3, 4, 4, 8, 8]  
  Output: 2  
  分析题目可知，最终结果一定是个偶数  
  在结果前面的偶数i有 nums[i] = nums[i + 1]  
  在结果及结果后面的偶数i有nums[i] != nums[i + 1]  
  因为数组长度 为奇数，最后一个偶数有i+1>len的情况  
  然后考虑把偶数换成奇数  
    
  在结果前面的奇数i有 nums[i] != nums[i + 1]  
  在结果及结果后面的奇数i有nums[i] == nums[i + 1]  
  所以找出第一个满足该条件的奇数，然后return (奇数+1)  
    
  本题奇偶会来回变，所以需要m--操作。  
    
tags:    
  -  二分法  
  -  奇偶下标  
