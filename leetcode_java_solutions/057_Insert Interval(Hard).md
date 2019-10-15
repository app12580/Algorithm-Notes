### description    
  Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).  
    
  You may assume that the intervals were initially sorted according to their start times.  
    
  Example 1:  
    
  Input: intervals = [[1,3],[6,9]], newInterval = [2,5]  
  Output: [[1,5],[6,9]]  
  Example 2:  
    
  Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]  
  Output: [[1,2],[3,10],[12,16]]  
  Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].  
  NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.  
### solution    
```    
// 方法一； 果然二分法是没有必要的  
Runtime: 1 ms, faster than 99.11% of Java online submissions for Insert Interval.  
Memory Usage: 40.3 MB, less than 90.63% of Java online submissions for Insert Interval.  
  
  class Solution {  
    public int[][] insert(int[][] intervals, int[] newInterval) {  
        if(intervals.length == 0) {  
              int[][] res = new int[1][2];  
              res[0] = newInterval;  
              return res;  
          }  
          int m = intervals.length;  
          int x = newInterval[0];  
          int y = newInterval[1];  
          if(x > intervals[m-1][1]) {  
              int[][] res = new int[m+1][2];  
              System.arraycopy(intervals, 0, res, 0, m);  
              res[m][0] = x;  
              res[m][1] = y;  
              return res;  
          } else if(y < intervals[0][0]) {  
              int[][] res = new int[m+1][2];  
              System.arraycopy(intervals, 0, res, 1, m);  
              res[0][0] = x;  
              res[0][1] = y;  
              return res;  
          }  
          int left = 0;  
          int right = 0;  
          for(int i = 0; i < m; i++) {  
              int curL = intervals[i][0];  
              int curR = intervals[i][1];  
              if(curR >= x) {   //关键点  
                  left = i;  
                  break;  
              }  
          }  
          for(int i = m - 1; i >= 0; i--) {  
              int curL = intervals[i][0];  
              int curR = intervals[i][1];  
              if(curL <= y) {  
                  right = i;  
                  break;  
              }  
          }  
          // 0 1 (2 3) 4  
          int[][] res = new int[m - (right - left)][2];  
          System.arraycopy(intervals, 0, res, 0, left);  
          res[left][0] = Math.min(x, intervals[left][0]);  
          res[left][1] = Math.max(y, intervals[right][1]);  
          for(int i = right + 1, index = left + 1; i < m; i++, index++) {  
              res[index][0] = intervals[i][0];  
              res[index][1] = intervals[i][1];  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  先找到左边相交的区间索引，和右边相交的区间索引，然后合并中间，两边再复制。  
  优化：可以用二分法，但感觉，没必要。。。。  
    
  总结：  
  1、需要特殊处理length==0和最左最右的情况。  
  2、注意判断条件，x<=curR，而不是要做断电在区间里面。因为最左判断过了并且从左开始遍历，所以这种情况一定会相交的。  
    
tags:    
  -  区间  
