### description   
  Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.  
     
  You may assume the array's length is at most 10,000.   
     
  Example:   
     
  Input:   
  [1,2,3]   
     
  Output:   
  2   
     
  Explanation:   
  Only two moves are needed (remember each move increments or decrements one element):   
     
  [1,2,3]  =>  [2,2,3]  =>  [2,2,2]   
### solution     
```  
  class Solution {
      public int minMoves2(int[] nums) {
           Arrays.sort(nums);
          int midIndex = nums.length / 2;
          int mid = nums[midIndex];
          int res = 0;
          for(int i = 0; i < midIndex; i++) {
              res += mid - nums[i];
          }
          for(int i = midIndex + 1; i < nums.length; i++) {
              res += nums[i] - mid;
          }
          return res;
      }
  }
  
  //稍微优化一下： 不需要把中间值找出来，只需要最大减最小就OK了
  public int minMoves2(int[] nums) {
      Arrays.sort(nums);
      int move = 0;
      int l = 0, h = nums.length - 1;
      while (l <= h) {
          move += nums[h] - nums[l];
          l++;
          h--;
      }
      return move;
  }
  
  
  // 使用快速选择找到中位数，时间复杂度 O(N)
  public int minMoves2(int[] nums) {
      int move = 0;
      int median = findKthSmallest(nums, nums.length / 2);
      for (int num : nums) {
          move += Math.abs(num - median);
      }
      return move;
  }
  
  private int findKthSmallest(int[] nums, int k) {
      int l = 0, h = nums.length - 1;
      while (l < h) {
          int j = partition(nums, l, h);
          if (j == k) {
              break;
          }
          if (j < k) {
              l = j + 1;
          } else {
              h = j - 1;
          }
      }
      return nums[k];
  }
  
  private int partition(int[] nums, int l, int h) {
      int i = l, j = h + 1;
      while (true) {
          while (nums[++i] < nums[l] && i < h) ;
          while (nums[--j] > nums[l] && j > l) ;
          if (i >= j) {
              break;
          }
          swap(nums, i, j);
      }
      swap(nums, l, j);
      return j;
  }
  
  private void swap(int[] nums, int i, int j) {
      int tmp = nums[i];
      nums[i] = nums[j];
      nums[j] = tmp;
  }
  
```  
  
### 个人解读  
  我的第一想法很快，分两个步骤：1、找到中位数 2、遍历一遍，找到每个数字与中位数的差的绝对值。  
  找到中位数的方法可以单纯用排序解决，也可以改造排序算法，只找到中位数时候就停止剩余排序行为。  
    
tags:    
  -   算法高级应用    
