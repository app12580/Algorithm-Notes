### description    
  This question is the same as "Max Chunks to Make Sorted" except the integers of the given array are not necessarily distinct, the input array could be up to length 2000, and the elements could be up to 10**8.  
    
  Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.  
    
  What is the most number of chunks we could have made?  
    
  Example 1:  
    
  Input: arr = [5,4,3,2,1]  
  Output: 1  
  Explanation:  
  Splitting into two or more chunks will not return the required result.  
  For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.  
  Example 2:  
    
  Input: arr = [2,1,3,4,4]  
  Output: 4  
  Explanation:  
  We can split into two chunks, such as [2, 1], [3, 4, 4].  
  However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.  
  Note:  
    
  arr will have length in range [1, 2000].  
  arr[i] will be an integer in range [0, 10**8].  
### solution    
```    
// 方法一： 辅助数组，比较目前最大值与右边最小值进行比较  
Runtime: 1 ms, faster than 99.51% of Java online submissions for Max Chunks To Make Sorted II.  
Memory Usage: 39 MB, less than 100.00% of Java online submissions for Max Chunks To Make Sorted II.  
  
  class Solution {  
      public int maxChunksToSorted(int[] arr) {  
          int len = arr.length;  
          int[] rightMin = new int[len];  // [i, len-1]的最小值  
          int min = Integer.MAX_VALUE;  
          for(int i = len - 1; i >= 0; i--) {  
              min = Math.min(min, arr[i]);  
              rightMin[i] = min;  
          }  
          int res = 0;  
          int max = Integer.MIN_VALUE;  
          for(int i = 0; i < len; i++) {  
              max = Math.max(max, arr[i]);  
              if(i == len - 1 || max <= rightMin[i + 1]) {  
                  res++;  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  把题目读明白以后，就可以把问题转换成：把数组分块，假设为An，则要求Ai满足：    
    所有Ai < 所有 Ai+1    
    即max(Ai) < min(Ai+1)    
    //max(Ai)表示A数组内的最大值    
    求最大的数组个数    
    
  思路一：  
  创建一个数组，索引i表示[i, len-1]的最小值，  
  然后从左往右遍历，记录curMax，如果curMax <=  这个最小值，意味着可以分块  
    
tags:    
  -  数组  
