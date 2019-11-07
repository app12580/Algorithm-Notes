### description    
  An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.  
    
  graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.  
    
  Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.  
    
     
    
  Example 1:  
    
  Input: [[1,2,3],[0],[0],[0]]  
  Output: 4  
  Explanation: One possible path is [1,0,2,0,3]  
  Example 2:  
    
  Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]  
  Output: 4  
  Explanation: One possible path is [0,1,4,2,3]  
     
    
  Note:  
    
  1 <= graph.length <= 12  
  0 <= graph[i].length < graph.length  
### solution    
```    
  // 方法一： 多点同时BFS  
  Runtime: 6 ms, faster than 98.28% of Java online submissions for Shortest Path Visiting All Nodes.  
  Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Shortest Path Visiting All Nodes.  
  class Solution {  
      public int shortestPathLength(int[][] graph) {  
          int len=graph.length;  
          if(graph==null || graph.length==0){  
              return 0;  
          }  
          boolean[][] visited=new boolean[len][1<<len]; // 标记是否访问过,用于避免重复访问  
          int finishState=(1<<len)-1;  // 用于检查是否访问完所有的节点,每个位代表一个节点的状态,形如1111  
          Queue<int[]> queue=new LinkedList<>(); // 队列里的数组,第一个记录的是标号,第二个是状态  
          for(int i=0; i<len; i++){  
              queue.offer(new int[]{i,1<<i});  
          }  
          int step=0;  
          while(!queue.isEmpty()){  
              for(int i=queue.size(); i>0; i--){  
                  int[] node=queue.poll();  
                  if(finishState==node[1]){ // 如果标记的节点访问状态是结束,那么返回步长  
                      return step;  
                  }  
                  for(int next:graph[node[0]]){  
                      int nextState=node[1]|(1<<next); // 2个节点相或,标记着访问了这条边的2个点  
                      if(visited[next][nextState]){  
                          continue;  
                      }  
                      visited[next][nextState]=true;  
                      queue.offer(new int[]{next,nextState}); // 将该节点和边的信息加入bfs对列  
                  }  
              }  
              step++;  
          }  
          return step;  
      }  
  }  
     
```    
    
### 个人解读    
  最短步骤，优先考虑。  
  多个点同时BFS。  
  dp[i][j]: i表示起点是哪一个，j表示此时经过各个点的状态，使用二进制来表示，(前提： 1 <= graph.length <= 12)  
    
tags:    
  -  BFS  
  -  同步BFS  
  -  图论  
