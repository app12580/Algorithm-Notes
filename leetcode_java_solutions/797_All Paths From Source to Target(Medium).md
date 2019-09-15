### description    
  Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.  
    
  The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.  
    
  Example:  
  Input: [[1,2], [3], [3], []]   
  Output: [[0,1,3],[0,2,3]]   
  Explanation: The graph looks like this:  
  0--->1  
  |    |  
  v    v  
  2--->3  
  There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.  
  Note:  
    
  The number of nodes in the graph will be in the range [2, 15].  
  You can print different paths in any order, but you should keep the order of nodes inside one path.  
### solution    
```    
  
Runtime: 2 ms, faster than 99.54% of Java online submissions for All Paths From Source to Target.  
Memory Usage: 41.9 MB, less than 58.33% of Java online submissions for All Paths From Source to Target.  
  
  
  class Solution {  
      public List<List<Integer>> allPathsSourceTarget(int[][] graph) {  
          List<List<Integer>> res = new ArrayList<>();  
          List<Integer> cur = new ArrayList<>();  
          cur.add(0);  
          int max = graph.length - 1;  
          dfs(cur, res, 0, max, graph);  
          return res;  
      }  
    
      private void dfs(List<Integer> cur, List<List<Integer>> res, int start, int max, int[][] graph) {  
          if(start == max) {  
              res.add(new ArrayList<>(cur));  
              return;  
          }  
          int[] next = graph[start];  
          for(int i = 0; i < next.length; i++) {  
              int n = next[i];  
              cur.add(n);  
              dfs(cur, res, n, max, graph);  
              cur.remove(cur.size() - 1);  
          }  
      }  
  }  
```    
    
### 个人解读    
  感觉好像直接DFS+回溯就可以做到了。。。  
    
tags:    
  -  DFS  
  -  回溯  
