### description    
  Given a collection of intervals, merge all overlapping intervals.  
    
  Example 1:  
    
  Input: [[1,3],[2,6],[8,10],[15,18]]  
  Output: [[1,6],[8,10],[15,18]]  
  Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].  
  Example 2:  
    
  Input: [[1,4],[4,5]]  
  Output: [[1,5]]  
  Explanation: Intervals [1,4] and [4,5] are considered overlapping.  
  NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.  
    
  Accepted  
### solution    
```    
  
Runtime: 6 ms, faster than 88.10% of Java online submissions for Merge Intervals.  
Memory Usage: 41.4 MB, less than 66.29% of Java online submissions for Merge Intervals.  
  
  
  class Solution {  
      public int[][] merge(int[][] intervals) {  
          if(intervals == null || intervals.length == 0) return new int[0][];  
          Arrays.sort(intervals, new Comparator<int[]>() {  
              @Override  
              public int compare(int[] o1, int[] o2) {  
                  if(o1[0] != o2[0]) return o1[0] - o2[0];  
                  return o1[1] - o2[1];  
              }  
          });  
          List<int[]> list = new ArrayList<>();  
          int right = intervals[0][1];  
          int[] cur = new int[]{intervals[0][0], intervals[0][1]};  
          for(int i = 1; i < intervals.length; i++) {  
              int[] tt = intervals[i];  
              if(tt[0] <= cur[1]) {  
                  cur[1] = Math.max(cur[1],tt[1]);  
              } else {  
                  list.add(new int[]{cur[0], cur[1]});  
                  cur[0] = tt[0];  
                  cur[1] = tt[1];  
              }  
          }  
          list.add(new int[]{cur[0], cur[1]});  
          return list.toArray(new int[list.size()][]);  
      }  
  }  
```    
    
### 个人解读    
  区间问题，排序+贪心算法就完事了。  
  只排右端点就足够了，本题并不影响。  
  还是有影响的，判断时候用。  
    
  反思：思路彻底错误，因为太轻敌了。和那个最多不重复子区间个数并不是一样的题目，犯了想当然的错误。    
    
  变成左端点排序。  
  在错误的代码基础上，稍微改改就出来答案了。  
    
  总结：区间左排序是最少，右排序是最多。  
  因为右排序的时候，跨越的那个区间还不如左边跨越更小的。  
    
tags:    
  -  区间排序  
  -  贪心算法  
