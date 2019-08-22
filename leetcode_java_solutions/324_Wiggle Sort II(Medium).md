### description    
  Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....  
    
  Example 1:  
    
  Input: nums = [1, 5, 1, 1, 6, 4]  
  Output: One possible answer is [1, 4, 1, 5, 1, 6].  
  Example 2:  
    
  Input: nums = [1, 3, 2, 2, 3, 1]  
  Output: One possible answer is [2, 3, 1, 3, 1, 2].  
  Note:  
  You may assume all input has valid answer.  
    
  Follow Up:  
  Can you do it in O(n) time and/or in-place with O(1) extra space?  
### solution    
```    
Runtime: 3 ms, faster than 100.00% of Java online submissions for Wiggle Sort II.  
Memory Usage: 41.6 MB, less than 100.00% of Java online submissions for Wiggle Sort II.  
  
  class Solution {  
       public void wiggleSort(int[] nums) {  
         int[] copy = Arrays.copyOf(nums, nums.length);  
          Arrays.sort(copy);  
          int left = (nums.length + 1)/ 2 - 1;  
          int right = nums.length - 1;  
          for(int i = 0; i < nums.length; i++) {  
              if(i % 2 == 0) {  
                  nums[i] = copy[left--];  
              } else {  
                  nums[i] = copy[right--];         
              }  
          }  
      }  
  }  
```    
    
### 个人解读    
  先试一下预处理排序的办法  
  本题目的核心关键在于找出数组的中间值，然后所有偏小的放在偶数位，偏大的放在奇数位。  
  使用Kth的方法，找出来medium效率并不高，甚至还不如直接排序的结果。  
    
tags:    
  -  预处理  
  -  Kth  
