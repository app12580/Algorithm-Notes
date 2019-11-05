### description    
  We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.  
    
  We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.  
    
  Return an array representing the number of bricks that will drop after each erasure in sequence.  
    
  Example 1:  
  Input:   
  grid = [[1,0,0,0],[1,1,1,0]]  
  hits = [[1,0]]  
  Output: [2]  
  Explanation:   
  If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.  
  Example 2:  
  Input:   
  grid = [[1,0,0,0],[1,1,0,0]]  
  hits = [[1,1],[1,0]]  
  Output: [0,0]  
  Explanation:   
  When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.  
     
    
  Note:  
    
  The number of rows and columns in the grid will be in the range [1, 200].  
  The number of erasures will not exceed the area of the grid.  
  It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.  
  An erasure may refer to a location with no brick - if it does, no bricks drop.  
### solution    
```    

// 方法一： 逆向思维 + 并查集  
Runtime: 10 ms, faster than 50.60% of Java online submissions for Bricks Falling When Hit.  
Memory Usage: 52.1 MB, less than 90.00% of Java online submissions for Bricks Falling When Hit.  
  
  class Solution {  
      public int[] hitBricks(int[][] grid, int[][] hits) {  
          int R = grid.length, C = grid[0].length;  
          int[] dr = {1, 0, -1, 0};  
          int[] dc = {0, 1, 0, -1};  
    
          int[][] A = new int[R][C];  
          for (int r = 0; r < R; ++r)  
              A[r] = grid[r].clone();  
          for (int[] hit: hits)  
              A[hit[0]][hit[1]] = 0;  
    
          DSU dsu = new DSU(R*C + 1);  
          for (int r = 0; r < R; ++r) {  
              for (int c = 0; c < C; ++c) {  
                  if (A[r][c] == 1) {  
                      int i = r * C + c;  
                      if (r == 0)  
                          dsu.union(i, R*C);  
                      if (r > 0 && A[r-1][c] == 1)  
                          dsu.union(i, (r-1) *C + c);  
                      if (c > 0 && A[r][c-1] == 1)  
                          dsu.union(i, r * C + c-1);  
                  }  
              }  
          }  
          int t = hits.length;  
          int[] ans = new int[t--];  
    
          while (t >= 0) {  
              int r = hits[t][0];  
              int c = hits[t][1];  
              int preRoof = dsu.top();  
              if (grid[r][c] == 0) {  
                  t--;  
              } else {  
                  int i = r * C + c;  
                  for (int k = 0; k < 4; ++k) {  
                      int nr = r + dr[k];  
                      int nc = c + dc[k];  
                      if (0 <= nr && nr < R && 0 <= nc && nc < C && A[nr][nc] == 1)  
                          dsu.union(i, nr * C + nc);  
                  }  
                  if (r == 0)  
                      dsu.union(i, R*C);  
                  A[r][c] = 1;  
                  ans[t--] = Math.max(0, dsu.top() - preRoof - 1);  
              }  
          }  
    
          return ans;  
      }  
  }  
    
  class DSU {  
      int[] parent;  
      int[] rank;  
      int[] sz;  
    
      public DSU(int N) {  
          parent = new int[N];  
          for (int i = 0; i < N; ++i)  
              parent[i] = i;  
          rank = new int[N];  
          sz = new int[N];  
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
     
```    
    
### 个人解读    
  题目描述解读：打掉某些砖块后，如果不和顶部(row==0)相连，就会下落。  
  敲代码前的猜测：下落了就和消失是一回事  
  [[1,0,0,0],[1,1,1,0]]：  
    
  排列方式一：  
  1  0  0  0   这一行是顶部(top)    ∧   这个叫掉落(drop  drop)  
  1  1  1  0                        ||  
    
  排列方式二：  
  1  1  1  0                        ||   这个叫掉落(drop  drop)  
  1  0  0  0   这一行是顶部(top)    ∨              
    
  首先把矩阵中的节点拼在一起的过程就和并查集很类似。  
  接下来由于并查集的拼接很容易，拆开很难，所以就考虑要不要逆向思维。  
    
  参考答案： 官方答案  
  https://leetcode-cn.com/problems/bricks-falling-when-hit/solution/da-zhuan-kuai-by-leetcode/  
    
  定向并查集，通过rank来让所有的并查集向不可移动的方向靠近  
    
tags:    
  -  矩阵  
  -  逆向思维  
  -  并查集  
