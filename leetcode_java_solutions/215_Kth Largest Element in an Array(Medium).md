### description    
  Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.  
    
  Example 1:  
    
  Input: [3,2,1,5,6,4] and k = 2  
  Output: 5  
  Example 2:  
    
  Input: [3,2,3,1,2,4,5,5,6] and k = 4  
  Output: 4  
  Note:   
  You may assume k is always valid, 1 ≤ k ≤ array's length.  
    
  找出数组中第K大的元素  
### solution    
```    
  Runtime: 24 ms, faster than 36.19% of Java online submissions for Kth Largest Element in an Array.  
  Memory Usage: 37.5 MB, less than 93.89% of Java online submissions for Kth Largest Element in an Array.  
  Next challenges:  
    
  class Solution {  
       public int findKthLargest(int[] nums, int k) {  
          return quickSort(nums, 0, nums.length - 1, k);  
      }  
    
      private int quickSort(int[] nums, int start, int end, int k) {  
          if(start < end) {  
              int middle = getMiddle(nums, start, end);  
              int curK = end - middle + 1;  
              if(curK == k) {  
                  return nums[middle];  
              } else if(curK < k) {  
                  return quickSort(nums, start, middle - 1, k - curK);  
              } else {  
                  return quickSort(nums, middle + 1, end, k);  
              }  
          }  
          return nums[start];  
      }  
    
    // 从小到大排序， 返回当前的index，然后根据index去获取是第几大的
      private int getMiddle(int[] nums, int start, int end) {  
          int tmp = nums[start];  
          while(start < end) {  
              while(start < end && nums[end] > tmp) {  
                  end--;  
              }  
              nums[start] = nums[end];  
               while(start < end && nums[start] < tmp) {  
                  start++;  
              }  
              nums[end--] = nums[start];  
          }  
          nums[start] = tmp;  
          return start;  
      }  
  }  
```    
    
### 个人解读    
  前置条件：对快速排序有一定的了解。  
  利用快速排序，然后根据CurK去进行下一步遍历。  
  看了一下LeetCode的各种排行，感觉思路都差不多。  
    
    
tags:    
  -  待优化  
  -  数组  
  -  快速排序  
  -  Kth  
