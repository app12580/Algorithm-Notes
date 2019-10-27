### description    
  Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.  
    
  Examples:  
  [2,3,4] , the median is 3  
    
  [2,3], the median is (2 + 3) / 2 = 2.5  
    
  Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.  
    
  For example,  
  Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.  
    
  Window position                Median  
  ---------------               -----  
  [1  3  -1] -3  5  3  6  7       1  
   1 [3  -1  -3] 5  3  6  7       -1  
   1  3 [-1  -3  5] 3  6  7       -1  
   1  3  -1 [-3  5  3] 6  7       3  
   1  3  -1  -3 [5  3  6] 7       5  
   1  3  -1  -3  5 [3  6  7]      6  
  Therefore, return the median sliding window as [1,-1,-1,3,5,6].  
    
  Note:  
  You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.  
### solution    
```    
// 排序中间弹出数据结构(排序中出问题)  
Runtime: 26 ms, faster than 80.26% of Java online submissions for Sliding Window Median.  
Memory Usage: 40.9 MB, less than 100.00% of Java online submissions for Sliding Window Median.  
  
  public class Solution {  
      PriorityQueue<Integer> rightHeap = new PriorityQueue<Integer>();  //右边的  
      PriorityQueue<Integer> leftHeap = new PriorityQueue<Integer>(Collections.reverseOrder());    //左边的  
    
      // 另right的长度较大  
      public double[] medianSlidingWindow(int[] nums, int k) {  
          int n = nums.length - k + 1;  
          if (n <= 0) return new double[0];  
          double[] result = new double[n];  
    
          for (int i = 0; i <= nums.length; i++) {  
              if (i >= k) {  
                  result[i - k] = getMedian();  
                  remove(nums[i - k]);  
              }  
              if (i < nums.length) {  
                  add(nums[i]);  
              }  
          }  
    
          return result;  
      }  
    
      private void add(int num) {  
          if (num < getMedian()) {  
              leftHeap.add(num);  
          } else {  
              rightHeap.add(num);  
          }  
          if (leftHeap.size() > rightHeap.size()) {  
              rightHeap.add(leftHeap.poll());  
          }  
          if (rightHeap.size() - leftHeap.size() > 1) {  
              leftHeap.add(rightHeap.poll());  
          }  
      }  
    
      private void remove(int num) {  
          if (num < getMedian()) {  
              leftHeap.remove(num);  
          } else {  
              rightHeap.remove(num);  
          }  
          if (leftHeap.size() > rightHeap.size()) {  
              rightHeap.add(leftHeap.poll());  
          }  
          if (rightHeap.size() - leftHeap.size() > 1) {  
              leftHeap.add(rightHeap.poll());  
          }  
      }  
    
      private double getMedian() {  
          if (leftHeap.isEmpty() && rightHeap.isEmpty()) return 0;  
    
          if (leftHeap.size() == rightHeap.size()) {  
              return ((double) leftHeap.peek() + (double) rightHeap.peek()) / 2.0;  
          } else {  
              return (double) rightHeap.peek();  
          }  
      }  
    
  }  
```    
    
### 个人解读    
  之前有一个滑动窗口取最大值的，这题目换成了中位数。  
  之前那个可以删去小的没有的数字，但是这题目感觉没啥能删的。  
    
  思路一：  
  维护一套数据结构，存储所有的数字，重点是用一个游标指向中间变量，每次增加数据时候，进行更新。  
    
  思路二：  
  排序结构取中间值，可以用两个PriorityQueue模拟，共同组成一个队列，每次从中间弹出数据。  
    
tags:    
  -  排序中出问题  
  -  数据结构  
