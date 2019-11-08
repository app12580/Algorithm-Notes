### description    
  An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.  
    
  The ith edge connects nodes edges[i][0] and edges[i][1] together.  
    
  Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.  
    
  Example 1:  
    
  Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]  
  Output: [8,12,6,10,10,10]  
  Explanation:   
  Here is a diagram of the given tree:  
    0  
   / \  
  1   2  
     /|\  
    3 4 5  
  We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)  
  equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.  
    
  Note: 1 <= N <= 10000  
### solution    
```    
// 方法一： 两次DFS  
Runtime: 29 ms, faster than 31.31% of Java online submissions for Sum of Distances in Tree.  
Memory Usage: 54.7 MB, less than 85.71% of Java online submissions for Sum of Distances in Tree.  
  class Solution {  
      int[] ans, count;  
      List<Set<Integer>> graph;  
      int N;  
      public int[] sumOfDistancesInTree(int N, int[][] edges) {  
          this.N = N;  
          graph = new ArrayList<Set<Integer>>();  
          ans = new int[N];  
          count = new int[N];  
          Arrays.fill(count, 1);  
    
          for (int i = 0; i < N; ++i)  
              graph.add(new HashSet<Integer>());  
          for (int[] edge: edges) {  
              graph.get(edge[0]).add(edge[1]);  
              graph.get(edge[1]).add(edge[0]);  
          }  
          dfs(0, -1);  
          dfs2(0, -1);  
          return ans;  
      }  
    
      public void dfs(int node, int parent) {  
          for (int child: graph.get(node))  
              if (child != parent) {  
                  dfs(child, node);  
                  count[node] += count[child];  
                  ans[node] += ans[child] + count[child];  
              }  
      }  
    
      public void dfs2(int node, int parent) {  
          for (int child: graph.get(node))  
              if (child != parent) {  
                  ans[child] = ans[node] - count[child] + N - count[child];  
                  dfs2(child, node);  
              }  
      }  
  }  
     
```    
    
### 个人解读    
   因为N很大，所以期望一个O(N)的解法  
     
   官方解答就是用的Lee215的方法，而且题目提供者就是Lee215。。。   
   https://leetcode-cn.com/problems/sum-of-distances-in-tree/solution/shu-zhong-ju-chi-zhi-he-by-leetcode/  
     
   这种题目，一眼看下去能有N^2的解答，至于O(N)的解答需要通过设计模型，方程转化这种操作。  
   还有就是需要留意一下这种不带环路的DFS遍历方式。  
   从一个点开始，每次还要带上parent，让它不走回头路  
     
   ```  
   我们指定 0 号节点为树的根节点，对于每个节点 node，设 S(node) 为以 node 为根的子树（包括 node 本身）。我们用 count[node] 表示 S(node) 中节点的个数，并用 stsum[node]（subtree sum，子树和）表示 S(node) 中所有节点到 node 的总距离。  
     
   我们可以使用深度优先搜索计算出所有的 count 和 stsum。对于节点 node，我们计算出它的每个子节点的 count 和 stsum 值，那么就有 count[node] = sum(count[child]) + 1 以及 stsum[node] = sum(stsum[child] + count[child])，其中 sum() 表示对子节点进行累加。  
     
   当所有节点计算完之后，对于根节点，它的答案 ans[root] 即为 stsum[root]。我们再进行一次深度优先搜索，并且根据上文推出的两个相邻节点 ans 值的关系，得到其它节点的 ans 值，即：对于节点 parent（父节点）以及节点 child（子节点），有 ans[child] = ans[parent] - count[child] + (N - count[child])，与上文的 ans[y] = ans[x] - #(Y) + #(X) 相对应。  
     
   当第二次深度优先搜索结束后，我们就得到了所有节点对应的 ans 值。  
     
   ```  
    
tags:    
  -  无环图  
  -  DFS  
