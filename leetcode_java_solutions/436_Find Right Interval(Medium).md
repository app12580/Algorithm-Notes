### description    
  Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.  
    
  For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.  
    
  Note:  
    
  You may assume the interval's end point is always bigger than its start point.  
  You may assume none of these intervals have the same start point.  
     
    
  Example 1:  
    
  Input: [ [1,2] ]  
    
  Output: [-1]  
    
  Explanation: There is only one interval in the collection, so it outputs -1.  
     
    
  Example 2:  
    
  Input: [ [3,4], [2,3], [1,2] ]  
    
  Output: [-1, 0, 1]  
    
  Explanation: There is no satisfied "right" interval for [3,4].  
  For [2,3], the interval [3,4] has minimum-"right" start point;  
  For [1,2], the interval [2,3] has minimum-"right" start point.  
     
    
  Example 3:  
    
  Input: [ [1,4], [2,3], [3,4] ]  
    
  Output: [-1, 2, -1]  
    
  Explanation: There is no satisfied "right" interval for [1,4] and [3,4].  
  For [2,3], the interval [3,4] has minimum-"right" start point.  
  NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.  
    
  对于区间数组arr，返回一个数组，数组元素为每一个区间的特征值：  
  特征值：如果在数组中存在当前区间元素的右区间，那么特征值为这些右区间中(右值)最小的数组索引；如果不存在，则返回-1。  
    
### solution    
```    
Runtime: 16 ms, faster than 73.39% of Java online submissions for Find Right Interval.  
Memory Usage: 45.2 MB, less than 84.21% of Java online submissions for Find Right Interval.  
  
  class Solution {  
      public int[] findRightInterval(int[][] intervals) {  
           int[] result = new int[intervals.length];  
          java.util.NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();  
    
          for (int i = 0; i < intervals.length; ++i) {  
              intervalMap.put(intervals[i][0], i);  
          }  
    
          for (int i = 0; i < intervals.length; ++i) {  
              Map.Entry<Integer, Integer> entry = intervalMap.ceilingEntry(intervals[i][1]);  
              result[i] = (entry != null) ? entry.getValue() : -1;  
          }  
    
          return result;  
      }  
  }  
```    
    
### 个人解读    
  关于题目描述：官方的中文翻译和鸟语一样，根本看不出来在说什么东西。  
    
  本题的问题在于效率问题，感觉需要先排序。  
    
  本题可以善于利用TreeMap的性质，并且效率可以保证。  
    
tags:    
  -  TreeMap  
  -  排序优化  
