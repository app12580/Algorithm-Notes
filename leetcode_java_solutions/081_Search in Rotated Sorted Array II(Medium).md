### description    
    
### solution    
```    
  class Solution {  
      public boolean search(int[] nums, int target) {  
          int l = 0;  
          int h = nums.length - 1;  
          while(l <= h) {  
              int m = l + (h - l) / 2 ;  
              if(nums[m] == target) {  
                  return true;  
              } else if(nums[l] < nums[m] || nums[h] < nums[m]) {  
                  //如果左边是单调增区间或者右边非单调(左边相同数字)  
                  if(target >= nums[l] && target <= nums[m]) {  
                      h = m - 1;  
                  } else {  
                      l = m + 1;  
                  }  
              } else if(nums[m] < nums[h] || nums[m] < nums[l]) {  
                  // 右边是单调增区间或者左边非单调  
                  if(target >= nums[m] && target <= nums[h]) {  
                      l = m + 1;  
                  } else {  
                      h = m - 1;  
                  }  
              } else {  
                  //如果左边全是相等的  
                  l++;  
              }  
          }  
    
          return false;  
      }  
  }  
```    
    
### 个人解读    
  二分法应用一的变种，二分法的核心思路在于下一次搜索是左边还是右边。然而在本题中，并不能单纯的根据大小关系判断。  
  二分法：只要能找到一种确定方向的情况，就是很有收获的。  
  二分法应用一。  
    
  问题在于13111这种时候，0,4,2三个位置都相等，如何去找位置。  
  分为三种情况：  
  l=m=h  
  l,m,h只有一个地方相等  
  l,m,h都不相等。  
    
  本题还是欠一些思考啊。  
    
tags:    
  -  旋转数组  
  -  二分法  
