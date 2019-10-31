### description    
  There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.  
    
  Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.  
    
  Example:  
    
  Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]  
  Output: 3  
  Explanation:   
  There're totally 4 courses, but you can take 3 courses at most:  
  First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.  
  Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.   
  Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.   
  The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.  
     
    
  Note:  
    
  The integer 1 <= d, t, n <= 10,000.  
  You can't take two courses simultaneously.  
### solution    
```    
Runtime: 89 ms, faster than 23.75% of Java online submissions for Course Schedule III.  
Memory Usage: 59.5 MB, less than 28.57% of Java online submissions for Course Schedule III.  
  
  public class Solution {  
      public int scheduleCourse(int[][] courses) {  
          int sum = 0;  
          PriorityQueue<Integer> priorityQueue=new PriorityQueue<>((a,b)->b-a);  
          Arrays.sort(courses, (p1, p2) -> p1[1] - p2[1]);  
          for(int[] c: courses) {  
              sum += c[0];  
              priorityQueue.add(c[0]);  
              if(sum > c[1]) sum -= priorityQueue.poll();  
          }  
          return priorityQueue.size();  
       }  
  }  
```    
    
### 个人解读    
  类似于区间问题，但是感觉贪婪算法并不可以，感觉需要DFS+回溯了  
    
  思路一：DFS+回溯  
  TLE了  
  超时了以后就要想办法DP或者贪婪了。  
    
  思路二： 贪婪算法  
  计算已经消耗的全部时间，如果超过截止日期了，就把耗时最多的给取消掉。  
    
    
    
  ```  
  TLE  
  class Solution {  
     public int scheduleCourse(int[][] courses) {  
          Arrays.sort(courses, (p1, p2) -> p1[1] - p2[1]);  
          return dfs(courses, 0, 0);  
      }  
    
      private int dfs(int[][] courses, int index, int startTime) {  
          if(index == courses.length) return 0;  
          int max = 0;  
          for(int i = index; i < courses.length; i++) {  
              int[] cur = courses[i];  
              if(startTime + cur[0] <= cur[1]) {  
                  max = Math.max(max, 1 + dfs(courses, i + 1, startTime + cur[0]));  
              }  
          }  
          return max;  
      }  
  }  
  ```  
    
tags:    
  -  区间问题  
  -  优先队列  
  -  数学  
