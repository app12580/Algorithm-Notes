### description    
  Given an unsorted array, find the maximum difference between the successive elements in its sorted form.  
    
  Return 0 if the array contains less than 2 elements.  
    
  Example 1:  
    
  Input: [3,6,9,1]  
  Output: 3  
  Explanation: The sorted form of the array is [1,3,6,9], either  
               (3,6) or (6,9) has the maximum difference 3.  
  Example 2:  
    
  Input: [10]  
  Output: 0  
  Explanation: The array contains less than 2 elements, therefore return 0.  
  Note:  
    
  You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.  
  Try to solve it in linear time/space.  
### solution    
```    
// 方法一： 预处理，先排序  
  class Solution {  
      public int maximumGap(int[] nums) {  
          if(nums.length <= 1) return 0;  
          Arrays.sort(nums);  
          int res = 0;  
          for(int i = 1; i < nums.length; i++) {  
              res = Math.max(res, nums[i] - nums[i - 1]);  
          }  
          return res;  
      }  
  }  
    
  // 方法二： 桶排序  
  Runtime: 2 ms, faster than 98.17% of Java online submissions for Maximum Gap.  
  Memory Usage: 37.8 MB, less than 75.86% of Java online submissions for Maximum Gap.  
    
  class Solution {  
        public int maximumGap(int[] nums) {  
          if(nums == null || nums.length < 2) return 0;  
          int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, n = nums.length;  
          for(int num : nums) {  
              max = Math.max(max, num);  
              min = Math.min(min, num);  
          }  
    
          int gap = (max - min) / n + 1;  
          int size = (max - min) / gap + 1;  
          int[] max_busket = new int[size];  
          int[] min_busket = new int[size];  
          Arrays.fill(max_busket, Integer.MIN_VALUE);  
          Arrays.fill(min_busket, Integer.MAX_VALUE);  
    
          for(int num : nums) {  
              int index = (num - min) / gap;  
              max_busket[index] = Math.max(max_busket[index], num);  
              min_busket[index] = Math.min(min_busket[index], num);  
          }  
          int prev = 0, res = 0;  
          for(int i = 1; i < size; i++) {  
              if(max_busket[i] == Integer.MIN_VALUE || min_busket[i] == Integer.MAX_VALUE) continue;  
              res = Math.max(min_busket[i] - max_busket[prev], res);  
              prev = i;  
          }  
          return res;  
      }  
    
  }  
    
```    
    
### 个人解读    
    
  这种明显需要遍历全部数组，并且需要效率优化的问题，可以先考虑各种已有的排序算法，然后在过程中加点操作。  
    
  本题目：桶排序。  
  个人理解：通过桶排序来类似模拟带排序功能的HashMap。  
    
  代码中的一个疑问，为什么只比较不同桶之间的，而相同的桶没有内部比较。  
  解答： 因为最大值最小值的关系。  
    
tags:    
  -  桶排序  
  -  数学  
    
