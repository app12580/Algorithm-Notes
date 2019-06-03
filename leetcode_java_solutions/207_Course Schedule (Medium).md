### description      
  There are a total of n courses you have to take, labeled from 0 to n-1.    
      
  Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]    
      
  Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?    
      
  Example 1:    
      
  Input: 2, [[1,0]]     
  Output: true    
  Explanation: There are a total of 2 courses to take.     
               To take course 1 you should have finished course 0. So it is possible.    
  Example 2:    
      
  Input: 2, [[1,0],[0,1]]    
  Output: false    
  Explanation: There are a total of 2 courses to take.     
               To take course 1 you should have finished course 0, and to take course 0 you should    
               also have finished course 1. So it is impossible.    
  Note:    
      
  The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.    
  You may assume that there are no duplicate edges in the input prerequisites.    
### solution      
```      
  // 解法一： 拓扑排序步骤+BFS    
  这种方法的缺陷在于：每次只是入度--，效率太低了  
   Runtime: 36 ms, faster than 24.06% of Java online submissions for Course Schedule.    
    Memory Usage: 45 MB, less than 89.02% of Java online submissions for Course Schedule.    
    Next challenges:    
  class Solution {    
      public boolean canFinish(int numCourses, int[][] prerequisites) {    
          //        [0,1]  学习0前，需要先学习1    1 -> 0    
          if(numCourses <= 0) {    
              return false;    
          }    
          int[] inDegreeArr = new int[numCourses];    
          for(int i = 0; i < prerequisites.length; i++) {    
              inDegreeArr[prerequisites[i][0]]++;           
          }    
          Queue<Integer> queue = new LinkedList<>();    
          for(int i = 0; i < inDegreeArr.length; i++) {    
              if(inDegreeArr[i] == 0) {    
                  queue.offer(i);  //入度为0的节点    
              }    
          }    
          while(!queue.isEmpty()) {    
              Integer poll = queue.poll();    
              for(int i = 0 ; i < prerequisites.length; i++) {    
                  if(prerequisites[i][1] == poll) {    
                      inDegreeArr[prerequisites[i][0]]--;    
                      if(inDegreeArr[prerequisites[i][0]] == 0) {    
                          queue.offer(prerequisites[i][0]);    
                      }    
                  }    
              }    
          }    
          for(int i = 0; i < inDegreeArr.length; i++) {    
              if (inDegreeArr[i] != 0) {    
                  return false;    
              }    
          }    
          return true;    
      }    
  }    
     
      
  //方法二： 跑圈思想，每次有两个标记变量，全局标记（用来防止重复计算），当前标记(用来终止条件的)    
  //因为这两个变量都是全局共享的，所以当前标记为先置位true，等跑完圈以后，就再改成false    
  Runtime: 2 ms, faster than 100.00% of Java online submissions for Course Schedule.    
  Memory Usage: 42.1 MB, less than 97.25% of Java online submissions for Course Schedule.    
  Next challenges:    
      
  class Solution {    
    public boolean canFinish(int numCourses, int[][] prerequisites) {    
      List<Integer>[] graphic = new List[numCourses];    
      for (int i = 0; i < numCourses; i++) {    
          graphic[i] = new ArrayList<>();       //创建了这么多个List，结果内存消耗并不高    
      }    
      for (int[] pre : prerequisites) {    
          graphic[pre[0]].add(pre[1]);      //表示pre[0]这个课程所有的前驱课程添加进来    
      }    
      boolean[] globalMarked = new boolean[numCourses];  //两个标记数组    
      boolean[] localMarked = new boolean[numCourses];    
      for (int i = 0; i < numCourses; i++) {    
          if (hasCycle(globalMarked, localMarked, graphic, i)) {    
              return false;    
          }    
      }    
      return true;    
  }    
      
  private boolean hasCycle(boolean[] globalMarked, boolean[] localMarked,    
                           List<Integer>[] graphic, int curNode) {    
      
      if (localMarked[curNode]) {       //如果重复跑圈了，则返回true    
          return true;    
      }    
      if (globalMarked[curNode]) {      //之前跑过了，这个点不成环。    
          return false;    
      }    
      globalMarked[curNode] = true;     //全局标记，如果为true，则说明之前跑过这一节点了，并且以false结尾。    
      localMarked[curNode] = true;      //当前环正在跑    
      for (int nextNode : graphic[curNode]) {       //当前节点的所有前驱课程    
          if (hasCycle(globalMarked, localMarked, graphic, nextNode)) {     //即使用了递归，效率也是最快的    
              return true;    
          }    
      }    
      localMarked[curNode] = false;    
      return false;    
  }    
  }    
      
```      
      
### 个人解读      
  题目读完还是没读懂，有不明确的地方。    
  ```    
  for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]    
  ```    
  这句话，有关很关键的信息：你想学习课程0，只可能出现1个前提课程，不会出现[0,1,2]这种情况。而是[0,1]，[0,2]    
  明白题目描述后，本题可以理解成，判断有向图是否成环。    
  应用拓扑排序的处理思想    
  在预处理数据的时候，有两种选择： 整理每个节点的前驱节点；整理每个节点的后向节点。因为本题题干的限定条件是保证前驱，所以即使拿到了所有的后向节点也并无卵用。  
      
tags:      
  -  题目描述不明确    
  -  拓扑排序