### description  
  There are a total of n courses you have to take, labeled from 0 to n-1.
  
  Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
  
  Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
  
  There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
  
  Example 1:
  
  Input: 2, [[1,0]] 
  Output: [0,1]
  Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
               course 0. So the correct course order is [0,1] .
  Example 2:
  
  Input: 4, [[1,0],[2,0],[3,1],[3,2]]
  Output: [0,1,2,3] or [0,2,1,3]
  Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
               courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
               So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
### solution  
```  
  //方法一： 
  Runtime: 39 ms, faster than 12.86% of Java online submissions for Course Schedule II.
  Memory Usage: 46.6 MB, less than 49.04% of Java online submissions for Course Schedule II.
  Next challenges:
  class Solution {
      public int[] findOrder(int numCourses, int[][] prerequisites) {
           //        [0,1]  学习0前，需要先学习1    1 -> 0
          if(numCourses <= 0) {
              return new int[0];
          }
          int index = 0;
          int[] res = new int[numCourses];
          int[] inDegreeArr = new int[numCourses];
          for(int i = 0; i < prerequisites.length; i++) {
              inDegreeArr[prerequisites[i][0]]++;
          }
          Queue<Integer> queue = new LinkedList<>();
          for(int i = 0; i < inDegreeArr.length; i++) {
              if(inDegreeArr[i] == 0) {
                  queue.offer(i);  //入度为0的节点
                  res[index++] = i;
              }
          }
          while(!queue.isEmpty()) {
              Integer poll = queue.poll();
              for(int i = 0 ; i < prerequisites.length; i++) {
                  if(prerequisites[i][1] == poll) {
                      inDegreeArr[prerequisites[i][0]]--;
                      if(inDegreeArr[prerequisites[i][0]] == 0) {
                          queue.offer(prerequisites[i][0]);
                          res[index++] = prerequisites[i][0];
                      }
                  }
              }
          }
          for(int i = 0; i < inDegreeArr.length; i++) {
              if (inDegreeArr[i] != 0) {
                  return new int[0];
              }
          }
          return res;
      }
  }
  
  // 方法二： 效率高
```  
  
### 个人解读  
  拓扑排序， 直接用之前[207](207_Course%20Schedule%20(Medium).md)的BFS修改一下，就能得到结果，但是效率很差。
  
tags:  
  -  
