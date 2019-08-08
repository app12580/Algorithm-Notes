### description  
  For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
  
  Format
  The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
  
  You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
  
  Example 1 :
  
  Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
  
          0
          |
          1
         / \
        2   3 
  
  Output: [1]
  Example 2 :
  
  Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
  
       0  1  2
        \ | /
          3
          |
          4
          |
          5 
  
  Output: [3, 4]
  Note:
  
  According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
  The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
### solution  
```  
  class Solution {
      public List<Integer> findMinHeightTrees(int n, int[][] edges) {
              List<List<Integer>> myGraph = new ArrayList<List<Integer>>();
          List<Integer> res = new ArrayList<Integer>();
          if (n==1) {
              res.add(0);
              return res;
          }
          int[] degree = new int[n];
          for(int i=0; i<n; i++) {
              myGraph.add(new ArrayList<Integer>());
          }
          for(int i=0; i<edges.length; i++) {
              myGraph.get(edges[i][0]).add(edges[i][1]);
              myGraph.get(edges[i][1]).add(edges[i][0]);
              degree[edges[i][0]]++;
              degree[edges[i][1]]++;
          }
          Queue<Integer> myQueue = new ArrayDeque<Integer>();
  
          for(int i=0; i<n; i++) 
              if (degree[i]==0) 
                  return res;
              else if (degree[i]==1) {
                  myQueue.offer(i);
              }
  
          while (!myQueue.isEmpty()) {
              res = new ArrayList<Integer>();
              int count = myQueue.size();
  
              for(int i=0; i<count; i++){
                  int curr = myQueue.poll();
                  res.add(curr);
                  degree[curr]--;
                  for(int k=0; k<myGraph.get(curr).size(); k++) {
                      int next = myGraph.get(curr).get(k);
                      if (degree[next]==2) {
                          myQueue.offer(next);
                      }
                      degree[next]--;
                  }
              }      	
          }
          return res;
      }
  }
```  
  
### 个人解读  
https://leetcode.com/problems/minimum-height-trees/discuss/76129/Share-my-BFS-JAVA-code-using-degree-with-explanation-which-beats-more-than-95
  Basically my code starts from the leaf nodes.
  
  For leaf nodes, their degree = 1, which means each of them is only connected to one node.
  
  In our loop, each time we delete the leaf nodes from our graph(just by putting their degrees to 0), and meanwhile we add the new leaf nodes after deleting them(just add their connected nodes with degree as 2) to the queue.
  
  So basically in the end, the nodes in the queue would be connected to no other nodes but each other. They should be the answer.
  
tags:  
  -  图
  -  拓扑排序
