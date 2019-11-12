### description    
  Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.  
    
  Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].  
    
     
    
  Example 1:  
    
  Input: [1,17,8]  
  Output: 2  
  Explanation:   
  [1,8,17] and [17,8,1] are the valid permutations.  
  Example 2:  
    
  Input: [2,2,2]  
  Output: 1  
     
    
  Note:  
    
  1 <= A.length <= 12  
  0 <= A[i] <= 1e9  
### solution    
```    
  // 方法一： DFS+回溯  
  Runtime: 2 ms, faster than 54.20% of Java online submissions for Number of Squareful Arrays.  
  Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Number of Squareful Arrays.  
  class Solution {  
      Map<Integer, Integer> count;  
      Map<Integer, List<Integer>> graph;  
      public int numSquarefulPerms(int[] A) {  
          int N = A.length;  
          count = new HashMap();  
          graph = new HashMap();  
    
          // count.get(v) : 数组 A 中值为 v 的节点数量  
          for (int x: A)  
              count.put(x, count.getOrDefault(x, 0) + 1);  
    
          // graph.get(v) : 在 A 中的值 w 满足 v + w 是完全平方数  
          //                (ie., "vw" is an edge)  
          for (int x: count.keySet())  
              graph.put(x, new ArrayList());  
    
          for (int x: count.keySet())  
              for (int y: count.keySet()) {  
                  int r = (int) (Math.sqrt(x + y));  
                  if (r * r == x + y)  
                      graph.get(x).add(y);  
              }  
    
          // 增加从 x 开始的可行路径数量  
          int ans = 0;  
          for (int x: count.keySet())  
              ans += dfs(x, N - 1);  
          return ans;  
      }  
    
      public int dfs(int x, int todo) {  
          count.put(x, count.get(x) - 1);  
          int ans = 1;    
          if (todo != 0) {  
              ans = 0;  
              for (int y: graph.get(x)) if (count.get(y) != 0) {  
                  ans += dfs(y, todo - 1);  
              }  
          }  
          count.put(x, count.get(x) + 1);  
          return ans;  
      }  
  }   
    
  // 方法二： 通过位标记 效率反而低  
  Runtime: 12 ms, faster than 11.71% of Java online submissions for Number of Squareful Arrays.  
  Memory Usage: 36.1 MB, less than 16.67% of Java online submissions for Number of Squareful Arrays.  
    
  class Solution {  
   int N;  
      Map<Integer, List<Integer>> graph;  
      Integer[][] memo;  
    
      public int numSquarefulPerms(int[] A) {  
          N = A.length;  
          graph = new HashMap();  
          memo = new Integer[N][1 << N];  
    
          for (int i = 0; i < N; ++i)  
              graph.put(i, new ArrayList());  
    
          for (int i = 0; i < N; ++i)  
              for (int j = i+1; j < N; ++j) {  
                  int r = (int) (Math.sqrt(A[i] + A[j]));  
                  if (r * r == A[i] + A[j]) {  
                      graph.get(i).add(j);  
                      graph.get(j).add(i);  
                  }  
              }  
    
    
          int[] factorial = new int[20];  
          factorial[0] = 1;   //存储阶乘数组  
          for (int i = 1; i < 20; ++i)  
              factorial[i] = i * factorial[i-1];  
    
          int ans = 0;  
          for (int i = 0; i < N; ++i)  
              ans += dfs(i, 1 << i);  
    
          Map<Integer, Integer> count = new HashMap();  
          for (int x: A)  
              count.put(x, count.getOrDefault(x, 0) + 1);  
          for (int v: count.values())  
              ans /= factorial[v];  
    
          return ans;  
      }  
    
      public int dfs(int node, int visited) {  
          if (visited == (1 << N) - 1)  
              return 1;  
          if (memo[node][visited] != null)  
              return memo[node][visited];  
    
          int ans = 0;  
          for (int nei: graph.get(node))  
              if (((visited >> nei) & 1) == 0)  
                  ans += dfs(nei, visited | (1 << nei));  
          memo[node][visited] = ans;  
          return ans;  
      }  
  }   
```    
    
### 个人解读    
  转化成图论问题。  
  根据两个数字和为平方数，来判断是否连通。  
  之后就变成了，多点同步，获取所有可能的结果  
    
  因为总长度不超过12，所以可以考虑使用位标记  
    
    
  方法一： O(N^N)   
  方法二： O(N * 2^N)  
    
tags:    
  -  图论  
  -  DFS+回溯  
  -  动态规划  
  -  位标记  
