### description    
  We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.  
    
  We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.  
    
  Example:  
  Input:   
  routes = [[1, 2, 7], [3, 6, 7]]  
  S = 1  
  T = 6  
  Output: 2  
  Explanation:   
  The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.  
  Note:  
    
  1 <= routes.length <= 500.  
  1 <= routes[i].length <= 500.  
  0 <= routes[i][j] < 10 ^ 6.  
### solution    
```    
// 方法二： 官方答案： 转化成图论  
Runtime: 35 ms, faster than 89.65% of Java online submissions for Bus Routes.  
Memory Usage: 52.4 MB, less than 100.00% of Java online submissions for Bus Routes.  
    
  // 方法一： 暴力BFS  
  Runtime: 715 ms, faster than 5.01% of Java online submissions for Bus Routes.  
  Memory Usage: 66.8 MB, less than 76.47% of Java online submissions for Bus Routes.  
    
  class Solution {      
       public int numBusesToDestination(int[][] routes, int S, int T) {  
          if(S == T) return 0;  
          Set<Integer>[] setArr = new Set[routes.length];  
          for(int i = 0; i < setArr.length; i++) {  
              setArr[i] = new HashSet<>();  
          }  
          for(int i = 0; i < routes.length; i++) {  
              int[] route = routes[i];  
              Set<Integer> curSet = setArr[i];  
              for(int r: route) {  
                  curSet.add(r);  
              }  
          }  
          Set<Integer> seen = new HashSet<>();  
          int res = 0;  
          seen.add(S);  
          Queue<Integer> queue = new LinkedList<>();  
          queue.offer(S);  
          while (!queue.isEmpty()) {  
              res++;  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  Integer poll = queue.poll();  
                  for(Set<Integer> set: setArr) {  
                      if(!set.contains(poll)) continue;  
                      for(int s: set) {  
                          if(s == T) return res;  
                          if(!seen.contains(s)) {  
                              seen.add(s);  
                              queue.offer(s);  
                          }  
                      }  
                  }  
    
              }  
          }  
          return -1;  
      }  
    
    
  }  
```    
    
### 个人解读    
  先转换数据结构，然后采用BFS。  
    
  思路一：  
  BFS，结果超时。。。。9w的input。。。。    
    
  创建graph时候开销太大，换个方法试试 。 通过了，但是效率很低。  
    
  思路二：  
  官方解答：  
  把公交线路换成节点，然后用seen表示已经经过的节点，用target表示目标所在的节点。  
  需要注意距离的掌控，初始包含S的节点为0，交叉的节点另deep+1,如果遇到T了，需要return deep+1  
    
  至于怎么想到这里的，主要还是对于图论的敏感程度吧  
    
  ```  
  TLE   
  class Solution {      
      public int numBusesToDestination(int[][] routes, int S, int T) {  
          if(S == T) return 0;  
          Map<Integer, Set<Integer>> graph = new HashMap<>();  
          for(int[] r: routes) {  
              for(int i = 0; i < r.length; i++) {  
                  int cur = r[i];  
                  if(!graph.containsKey(cur)) {  
                      graph.put(cur, new HashSet<>());  
                  }  
                  Set<Integer> set = graph.get(cur);  
                  for(int j = 0; j < r.length; j++) {  
                      if(i == j) continue;  
                      set.add(r[j]);  
                  }  
              }  
          }  
          Set<Integer> seen = new HashSet<>();  
          int res = 0;  
          seen.add(S);  
          Queue<Integer> queue = new LinkedList<>();  
          queue.offer(S);  
          while (!queue.isEmpty()) {  
              res++;  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  Integer poll = queue.poll();  
                  Set<Integer> set = graph.get(poll);  
                  for(int s: set) {  
                      if(s == T) return res;  
                      if(!seen.contains(s)) {  
                          seen.add(s);  
                          queue.offer(s);  
                      }  
                  }  
              }  
          }  
          return -1;  
      }  
    
  }  
  ```  
    
tags:    
  -  图论  
