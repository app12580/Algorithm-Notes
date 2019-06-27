### description    
  Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.  
    
  Your algorithm's runtime complexity must be in the order of O(log n).  
    
  If the target is not found in the array, return [-1, -1].  
    
  Example 1:  
    
  Input: nums = [5,7,7,8,8,10], target = 8  
  Output: [3,4]  
  Example 2:  
    
  Input: nums = [5,7,7,8,8,10], target = 6  
  Output: [-1,-1]  
    
  在排序数组中返回target的所在区间。  
### solution    
```    
  class Solution {  
        public int[] searchRange(int[] nums, int target) {  
            int first = binarySearch(nums, target);  
            int last = binarySearch(nums, target + 1);  
            if(first == nums.length || nums[first] != target) {  
                return new int[]{-1,-1};  
            }  
            if(last == nums.length - 1 && nums[last] == target) last++;
            return new int[]{first, Math.max(first, last - 1)};  
        }  
          
        public int binarySearch(int[] nums, int key) {  
            int l = 0, h = nums.length - 1;  
            while (l < h) {  
                int m = l + (h - l) / 2;  
                if (nums[m] >= key) {  
                    h = m;      //如果nums[m] == key，  
                } else {  
                    l = m + 1;  
                }  
            }  
            return l;  
        }  
    }  
```    
    
### 个人解读    
  疑问？ 能不能值通过一套循环，同时把最左和最右求出来。  
  看了下答案，并不能，那么只能分两次了，一次找最左，一次找最右。  
    
  如果不比较first和last最大值，  
  ```  
    inout  [1]  
            1  
    output  [0,-1]  
             
    output  [0,0]  
             
  ```  
    
tags:    
  -  二分法  
