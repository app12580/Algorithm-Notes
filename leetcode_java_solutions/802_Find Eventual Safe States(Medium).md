### description  
  In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.
  
  Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.
  
  Which nodes are eventually safe?  Return them as an array in sorted order.
  
  The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.
  
  Example:
  Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
  Output: [2,4,5,6]
  Here is a diagram of the above graph.
  
  Illustration of graph
  
  Note:
  
  graph will have length at most 10000.
  The number of edges in the graph will not exceed 32000.
  Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
  
  找出不成环的节点。
### solution  
```  

Runtime: 5 ms, faster than 100.00% of Java online submissions for Find Eventual Safe States.
Memory Usage: 64.9 MB, less than 100.00% of Java online submissions for Find Eventual Safe States.


  class Solution {
      //    0:have not been visited
  //1:safe
  //2:unsafe
      public List<Integer> eventualSafeNodes(int[][] graph) {
          List<Integer> res = new ArrayList<>();
          if(graph == null || graph.length == 0)  return res;
  
          int nodeCount = graph.length;
          int[] color = new int[nodeCount];
  
          for(int i = 0;i < nodeCount;i++){
              if(dfs(graph, i, color))    res.add(i);
          }
  
          return res;
      }
  
      //如果safe 返回true
      public boolean dfs(int[][] graph, int start, int[] color){
          if(color[start] != 0)   return color[start] == 1;
  
          color[start] = 2;
          for(int newNode : graph[start]){
              //如果每个都能到达重点，那么就可以在循环外面修改成1
              // 终止条件是那种for循环长度为0的内容
              if(!dfs(graph, newNode, color))    return false;
          }
          color[start] = 1;
          return true;
      }
  }
```  
  
### 个人解读  
  这种与图关联不大，主要是DFS的控制，通过for循环去控制所有的，
  
  通过一个color数组去控制，如果是自己做的话，第一思路可能是多弄几个变量去存储。
  
  悲观法，先假设成不可以的。
  
  总结：
  1、使用color数组
  2、悲观处理法
  3、因为color数组的存在，所以每次只需要一个一个字符处理，不能贪心
  
tags:  
  -  DFS
  
