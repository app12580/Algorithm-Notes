### description    
  In this problem, a tree is an undirected graph that is connected and has no cycles.  
    
  The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.  
    
  The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.  
    
  Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.  
    
  Example 1:  
  Input: [[1,2], [1,3], [2,3]]  
  Output: [2,3]  
  Explanation: The given undirected graph will be like this:  
    1  
   / \  
  2 - 3  
  Example 2:  
  Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]  
  Output: [1,4]  
  Explanation: The given undirected graph will be like this:  
  5 - 1 - 2  
      |   |  
      4 - 3  
  Note:  
  The size of the input 2D-array will be between 3 and 1000.  
  Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.  
  
### solution    
```    
//方法一： 内存最少  内存小可以把变量放进全局变量里面  
  Runtime: 8 ms, faster than 21.66% of Java online submissions for Redundant Connection.  
  Memory Usage: 37.3 MB, less than 99.64% of Java online submissions for Redundant Connection.  
    
  class Solution {  
      Set<Integer> seen = new HashSet();  
      int MAX_EDGE_VAL = 1000;  
    
      public int[] findRedundantConnection(int[][] edges) {  
          ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];    
          for (int i = 0; i <= MAX_EDGE_VAL; i++) {  
              graph[i] = new ArrayList();  
          }  
    
          for (int[] edge: edges) {  
              seen.clear();  
              if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&  
                      dfs(graph, edge[0], edge[1])) {  
                  return edge;  
              }  
              graph[edge[0]].add(edge[1]);  
              graph[edge[1]].add(edge[0]);  
          }  
          throw new AssertionError();  
      }  
      public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {  
          if (!seen.contains(source)) {  
              seen.add(source);  
              if (source == target) return true;  
              for (int nei: graph[source]) {  
                  if (dfs(graph, nei, target)) return true;  
              }  
          }  
          return false;  
      }  
  }  
  官方解答  
    
  //java 10行解答方法  
  public int[] findRedundantConnection(int[][] edges) {  
          int[] parent = new int[2001];  
          for (int i = 0; i < parent.length; i++) parent[i] = i;  
    
          for (int[] edge: edges){  
              int f = edge[0], t = edge[1];  
              //如果f和t有相同的祖先，那么本次的边就是结果  
              if (find(parent, f) == find(parent, t)) return edge;  
              else parent[find(parent, f)] = find(parent, t);     //让f的祖先降级，改成t的祖先  
          }  
    
          return new int[2];  
      }  
    
      // 找到f的祖先  
      private int find(int[] parent, int f) {  
          if (f != parent[f]) {  
              parent[f] = find(parent, parent[f]);  
          }  
          return parent[f];  
      }  
```    
    
### 个人解读    
  并查集应用。根据祖先节点来判断是否想通。  
  延伸，如果是有向图的话，能否用同样的思路解答。参考[685](685_Redundant%20Connection%20II(Hard).md)  
  根据find的解法，与其他的解法一个很大的不同在于：find解法是以并查集作为研究单元，而其他做法往往是以点作为眼界单元。
    
tags:    
  -  并查集  
  -  通用做法  
