### description    
  Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.  
    
  Each person may dislike some other people, and they should not go into the same group.   
    
  Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.  
    
  Return true if and only if it is possible to split everyone into two groups in this way.  
    
     
    
  Example 1:  
    
  Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]  
  Output: true  
  Explanation: group1 [1,4], group2 [2,3]  
  Example 2:  
    
  Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]  
  Output: false  
  Example 3:  
    
  Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]  
  Output: false  
     
    
  Note:  
    
  1 <= N <= 2000  
  0 <= dislikes.length <= 10000  
  1 <= dislikes[i][j] <= N  
  dislikes[i][0] < dislikes[i][1]  
  There does not exist i != j for which dislikes[i] == dislikes[j].  
### solution    
```    
  class Solution {  
      public boolean possibleBipartition(int N, int[][] dislikes) {  
          Map<Integer, List<Integer>> map = new HashMap<>();  
          for (int[] arr : dislikes) {  
              int a = arr[0];  
              int b = arr[1];  
              if (!map.containsKey(a)) {  
                  map.put(a, new ArrayList<>());  
              }  
              if (!map.containsKey(b)) {  
                  map.put(b, new ArrayList<>());  
              }  
              map.get(a).add(b);  
              map.get(b).add(a);  
          }  
          int[] color = new int[N + 1];   //0未分配， 1 2分组的  
          for (int i = 1; i <= N; i++) {  
              if (!dfs(i, color, map, 1) && !dfs(i, color, map, 2)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
      private boolean dfs(int cur, int[] color, Map<Integer, List<Integer>> map, int start) {  
          if (color[cur] > 0) return color[cur] == start;  
          List<Integer> list = map.get(cur);  
          if(list == null) {  
              color[cur] = start;  
              return true;  
          }  
          boolean flag1 = false;  
          boolean flag2 = false;  
          for (int dislike : list) {  
                      //这一步是可以优化在下面的  
              if (color[dislike] == 1) {  
                  flag1 = true;  
              }  
              if (color[dislike] == 2) {  
                  flag2 = true;  
              }  
          }  
          if (flag1 && flag2) {  
              return false;  
          } else if ((flag1 && start == 1) || (flag2 && start == 2)) {  
              return false;  
          } else {  
              //这时候是可以填充的  
              color[cur] = start;  
              for (int d : list) {  
                  if (!dfs(d, color, map, 3 - start)) {  
                      return false;  
                  }  
              }  
              return true;  
          }    
      }  
  }  
    
  // 方法二 优化代码  
    
  class Solution {  
      public boolean possibleBipartition(int N, int[][] dislikes) {  
          int[][] graph = new int[N][N];  
          for (int[] d : dislikes) {  
              graph[d[0] - 1][d[1] - 1] = 1;  
              graph[d[1] - 1][d[0] - 1] = 1;  
          }  
          int[] group = new int[N];  
          for (int i = 0; i < N; i++) {  
              if (group[i] == 0 && !dfs(graph, group, i, 1)) {      //这一行为什么只判断了1而没有判断-1  
              //是因为-1在1的时候都给dfs了么？  
              //因为当前节点一定是不与前面节点成环的，  
                  return false;  
              }  
          }  
          return true;  
      }  
      private boolean dfs(int[][] graph, int[] group, int index, int g) {  
          group[index] = g;  
          for (int i = 0; i < graph.length; i++) {  
              if (graph[index][i] == 1) {  
                  if (group[i] == g) {  
                      return false;  
                  }  
                  if (group[i] == 0 && !dfs(graph, group, i, -g)) {  
                      return false;  
                  }  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
    
  遇到麻烦了，如果遍历过程中，遇到两组分哪里都可以的时候，就不可避免的需要回溯了。那么感觉还是一开始就以回溯法为主要方式。  
    
  递归、回溯、color标记。  
  本题目其实也是图的数据结构  
    
tags:    
  -  DFS  
  -  图  
