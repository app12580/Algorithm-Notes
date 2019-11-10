### description    
  Starting with an undirected graph (the "original graph") with nodes from 0 to N-1, subdivisions are made to some of the edges.  
    
  The graph is given as follows: edges[k] is a list of integer pairs (i, j, n) such that (i, j) is an edge of the original graph,  
    
  and n is the total number of new nodes on that edge.   
    
  Then, the edge (i, j) is deleted from the original graph, n new nodes (x_1, x_2, ..., x_n) are added to the original graph,  
    
  and n+1 new edges (i, x_1), (x_1, x_2), (x_2, x_3), ..., (x_{n-1}, x_n), (x_n, j) are added to the original graph.  
    
  Now, you start at node 0 from the original graph, and in each move, you travel along one edge.   
    
  Return how many nodes you can reach in at most M moves.  
    
     
    
  Example 1:  
    
  Input: edges = [[0,1,10],[0,2,1],[1,2,2]], M = 6, N = 3  
  Output: 13  
  Explanation:   
  The nodes that are reachable in the final graph after M = 6 moves are indicated below.  
    
  Example 2:  
    
  Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], M = 10, N = 4  
  Output: 23  
     
    
  Note:  
    
  0 <= edges.length <= 10000  
  0 <= edges[i][0] < edges[i][1] < N  
  There does not exist any i != j for which edges[i][0] == edges[j][0] and edges[i][1] == edges[j][1].  
  The original graph has no parallel edges.  
  0 <= edges[i][2] <= 10000  
  0 <= M <= 10^9  
  1 <= N <= 3000  
  A reachable node is a node that can be travelled to using at most M moves starting from node 0.  
    
  题目描述又说了一堆非人话。  
    
  edges[i] 数组共有三个量，下面以0为例  
  edges[0][2] 表示 edges[0][0] 和 edges[0][1]这两个val节点中间有多少个中途点(不包括两端)  
  返回M步里面一共可以访问多少个节点  
    
### solution    
```    
// 方法一： BFS ，效率高并且容易理解  
Runtime: 63 ms, faster than 85.64% of Java online submissions for Reachable Nodes In Subdivided Graph.  
Memory Usage: 56.9 MB, less than 100.00% of Java online submissions for Reachable Nodes In Subdivided Graph.  
  
  class Solution {  
      public int reachableNodes(int[][] edges, int M, int N) {  
          HashMap<Integer, HashMap<Integer, Integer>> e = new HashMap<>();  
          for (int i = 0; i < N; ++i) e.put(i, new HashMap<>());  
          for (int[] v : edges) {  
              e.get(v[0]).put(v[1], v[2]);  
              e.get(v[1]).put(v[0], v[2]);  
          }  
          PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] - a[0])); //根据[0]从大到小排列 这个排序很关键，控制着重复的情况  
          pq.offer(new int[] {M, 0}); // pq存储内容： 还能走多少步， 在哪个节点  
          HashMap<Integer, Integer> seen = new HashMap<>();  
          while (!pq.isEmpty()) {  
              int moves = pq.peek()[0], i = pq.peek()[1];  
              pq.poll();  
              if (!seen.containsKey(i)) {  
                  seen.put(i,moves);  
                  for (int j : e.get(i).keySet()) {  
                      int moves2 = moves - e.get(i).get(j) - 1;   // 假设内部有4个点，那么从i到j需要减去5步才行  
                      if (!seen.containsKey(j) && moves2 >= 0)  
                          pq.offer(new int[] { moves2, j});  
                  }  
              }  
          }  
          int res = seen.size();  
          for (int[] v : edges) {  
              int a = seen.getOrDefault(v[0],0);  
              int b = seen.getOrDefault(v[1],0);  
              res += Math.min(a + b, v[2]);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  思路是BFS，问题是如何去描述已经访问过的节点状态。  
    
  总结  
  1、通过int[还能走多少步， 哪个节点]这样一个数据描述。  
  2、通过PQ来进行决策，每次都执行最多步数的那个，这样子就不需要判断每次第一次到达i节点的一定是最高效的走法  
    
tags:    
  -  迪克斯特拉算法  
  -  图论  
  -  数据结构  
