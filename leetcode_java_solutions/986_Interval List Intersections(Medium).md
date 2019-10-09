### description    
  Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.  
    
  Return the intersection of these two interval lists.  
    
  (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)  
    
     
    
  Example 1:  
    
    
    
  Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]  
  Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]  
  Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.  
     
    
  Note:  
    
  0 <= A.length < 1000  
  0 <= B.length < 1000  
  0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9  
  NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.  
### solution    
```    
Runtime: 3 ms, faster than 84.74% of Java online submissions for Interval List Intersections.  
Memory Usage: 45.5 MB, less than 70.27% of Java online submissions for Interval List Intersections.  
  
  class Solution {  
      public int[][] intervalIntersection(int[][] A, int[][] B) {  
          List<int[]> res = new ArrayList<>();  
          int m = A.length;  
          int n = B.length;  
          int i = 0, j = 0;  
          while(i < m && j < n) {  
              int[] a = A[i];  
              int[] b = B[j];  
              if(a[0] <= b[1] && b[0] <= a[1]) {  
                  res.add(new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])});  
              }  
              if(a[1] < b[1]) {  
                  i++;  
              } else {  
                  j++;  
              }  
          }  
          int[][] r = new int[res.size()][2];  
          return res.toArray(r);  
      }  
  }  
```    
    
### 个人解读    
  找出两个区间列表的交集。  
    
  流水账问题，使用双指针，然后去获取结果。主要考察对代码的控制力。  
    
tags:    
  -  双指针  
  -  区间  
