### description    
  Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.  
  Example 1:  
  Input: ["23:59","00:00"]  
  Output: 1  
  Note:  
  The number of time points in the given list is at least 2 and won't exceed 20000.  
  The input time is legal and ranges from 00:00 to 23:59.  
    
### solution    
```    
  
//方法一：比较字符串  
Runtime: 25 ms, faster than 26.31% of Java online submissions for Minimum Time Difference.  
Memory Usage: 39.7 MB, less than 60.00% of Java online submissions for Minimum Time Difference.  
  
  class Solution {  
      public int findMinDifference(List<String> timePoints) {  
          Collections.sort(timePoints);  
          int min = Integer.MAX_VALUE;  
          for(int i = 0; i < timePoints.size() - 1; i++) {  
              min = Math.min(min, helper(timePoints.get(i), timePoints.get(i+1)));  
          }  
          if(timePoints.size() > 2) {  
              min = Math.min(min, helper(timePoints.get(0), timePoints.get(timePoints.size() - 1)));  
          }  
          return min;  
      }  
    
      private final int total = 24 * 60;  
      private int helper(String s1, String s2) {  
          if(s1.compareTo(s2) > 0) {  
              return helper(s2, s1);  
          }  
          String[] t1 = s1.split(":");  
          String[] t2 = s2.split(":");  
          int diff = (Integer.valueOf(t2[0]) - Integer.valueOf(t1[0])) * 60 + (Integer.valueOf(t2[1]) - Integer.valueOf(t1[1]));  
          return Math.min(diff, total - diff);  
      }  
  }  
    
  //方法二： 优化， 直接比较数字  
  class Solution {  
       private final int total = 24 * 60;  
    
      public int findMinDifference(List<String> timePoints) {  
          List<Integer> list = new ArrayList<>();  
          for(String s: timePoints) {  
              list.add(helper(s));  
          }  
          Collections.sort(list);  
          int min = Integer.MAX_VALUE;  
          for(int i = 0; i < list.size() - 1; i++) {  
              int cha = list.get(i + 1) - list.get(i);  
              cha = Math.min(cha, total - cha);  
              min = Math.min(min, cha);  
          }  
          int cha = list.get(list.size() - 1) - list.get(0);  
          cha = Math.min(cha, total - cha);  
          min = Math.min(min, cha);  
          return min;  
      }  
    
      private int helper(String time ) {  
          String[] split = time.split(":");  
          return Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);  
      }  
  }  
    
```    
    
### 个人解读    
  先排序，然后逐个比较，记得首尾也需要比较。  
    
tags:    
  -  待优化  
  -  最值  
