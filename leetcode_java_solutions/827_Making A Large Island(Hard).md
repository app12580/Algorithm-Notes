### description    
  In a 2D grid of 0s and 1s, we change at most one 0 to a 1.  
    
  After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).  
    
  Example 1:  
    
  Input: [[1, 0], [0, 1]]  
  Output: 3  
  Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.  
  Example 2:  
    
  Input: [[1, 1], [1, 0]]  
  Output: 4  
  Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.  
  Example 3:  
    
  Input: [[1, 1], [1, 1]]  
  Output: 4  
  Explanation: Can't change any 0 to 1, only one island with area = 4.  
     
    
  Notes:  
    
  1 <= grid.length = grid[0].length <= 50.  
  0 <= grid[i][j] <= 1.  
    
  找出添加一个节点后的最大并查集大小  
### solution    
```    
// 方法一： 并查集  
Runtime: 10 ms, faster than 71.57% of Java online submissions for Making A Large Island.  
Memory Usage: 44.2 MB, less than 66.67% of Java online submissions for Making A Large Island.  
  
  class Solution {  
      public int largestIsland(int[][] grid) {  
          int m = grid.length;  
          int n = grid[0].length;  
          DSU dsu = new DSU(m * n);  
          int[] directions = {0,1,0,-1,0};  
          for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  if(grid[i][j] == 1 ) {  
                      int cur = i * m + j;  
                      for(int k = 0; k < 4; k++) {  
                          int x = i + directions[k];  
                          int y = j + directions[k + 1];  
                          if(x < 0 || y < 0 || x >= m || y >= n) continue;  
                          if(grid[x][y] == 1) {  
                              dsu.union(x * m + y, cur);  
                          }  
                      }  
                  }  
              }  
          }  
    
          int max = 0;  
          for(int s: dsu.sz) {  
              max = Math.max(max, s);  
          }  
           for(int i = 0; i < m; i++) {  
              for(int j = 0; j < n; j++) {  
                  if(grid[i][j] == 0) {  
                      int size = 1;  
                      Set<Integer> set = new HashSet<>();  
                      for(int k = 0; k < 4; k++) {  
                          int x = i + directions[k];  
                          int y = j + directions[k + 1];  
                          if(x < 0 || y < 0 || x >= m || y >= n) continue;  
                          int next = x * m + y;  
                          if(grid[x][y] == 1 && !set.contains(dsu.find(next))) {  
                              size += dsu.sz[dsu.find(next)];  
                              set.add(dsu.find(next));  
                          }  
                      }  
                      max = Math.max(size, max);  
                  }  
              }  
          }  
          return max;  
      }  
    
      class DSU {  
          int[] parent;  
          int[] rank;  
          int[] sz;  
    
          public DSU(int N) {  
              parent = new int[N];  
              for (int i = 0; i < N; ++i)  
                  parent[i] = i;  
              sz = new int[N];  
              rank = new int[N];  
              Arrays.fill(sz, 1);  
          }  
    
          public int find(int x) {  
              if (parent[x] != x) parent[x] = find(parent[x]);  
              return parent[x];  
          }  
    
          public void union(int x, int y) {  
              int xr = find(x), yr = find(y);  
              if (xr == yr) return;  
    
              if (rank[xr] < rank[yr]) {  
                  int tmp = yr;  
                  yr = xr;  
                  xr = tmp;  
              }  
              if (rank[xr] == rank[yr])  
              rank[xr]++;  
    
              parent[yr] = xr;  
              sz[xr] += sz[yr];  
          }  
    
          public int size(int x) {  
              return sz[find(x)];  
          }  
    
          public int top() {  
              return size(sz.length - 1) - 1;  
          }  
      }  
  }  
```    
    
### 个人解读    
  
 需要注意，一般来说，并查集不直接对外提供parent调用函数  
    
tags:    
  -  矩阵  
  -  并查集  
