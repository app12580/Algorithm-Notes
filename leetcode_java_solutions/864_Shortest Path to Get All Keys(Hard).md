### description    
  We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.  
    
  We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.  
    
  For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.  
    
  Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.  
    
     
    
  Example 1:  
    
  Input: ["@.a.#","###.#","b.A.B"]  
  Output: 8  
  Example 2:  
    
  Input: ["@..aA","..B#.","....b"]  
  Output: 6  
     
    
  Note:  
    
  1 <= grid.length <= 30  
  1 <= grid[0].length <= 30  
  grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'  
  The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.  
    
  走迷宫，需要获取所有钥匙，迷宫中有墙和锁  
  小写的是钥匙，大写的是锁  
### solution    
```    
  // 方法二： BFS 易于理解，并且效率还OK  
  class Solution {  
     class State {  
          int keys, i, j;  
          State(int keys, int i, int j) {  
              this.keys = keys;  
              this.i = i;  
              this.j = j;  
          }  
      }  
      public int shortestPathAllKeys(String[] grid) {  
          int x = -1, y = -1, m = grid.length, n = grid[0].length(), max = -1;  
          for (int i = 0; i < m; i++) {  
              for (int j = 0; j < n; j++) {  
                  char c = grid[i].charAt(j);  
                  if (c == '@') {  
                      x = i;  
                      y = j;  
                  }  
                  if (c >= 'a' && c <= 'f') {  
                      max = Math.max(c - 'a' + 1, max);  
                  }  
              }  
          }  
          State start = new State(0, x, y);  
          Queue<State> q = new LinkedList<>();  
          Set<String> visited = new HashSet<>();  
          visited.add(0 + " " + x + " " + y);  
          q.offer(start);  
          int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};  
          int step = 0;  
          while (!q.isEmpty()) {  
              int size = q.size();  
              while (size-- > 0) {  
                  State cur = q.poll();  
                  if (cur.keys == (1 << max) - 1) {  
                      return step;  
                  }  
                  for (int[] dir : dirs) {  
                      int i = cur.i + dir[0];  
                      int j = cur.j + dir[1];  
                      int keys = cur.keys;  
                      if (i >= 0 && i < m && j >= 0 && j < n) {  
                          char c = grid[i].charAt(j);  
                          if (c == '#') {  
                              continue;  
                          }  
                          if (c >= 'a' && c <= 'f') {  
                              keys |= 1 << (c - 'a');  
                          }  
                          if (c >= 'A' && c <= 'F' && ((keys >> (c - 'A')) & 1) == 0) {  
                              continue;  
                          }  
                          if (!visited.contains(keys + " " + i + " " + j)) {  
                              visited.add(keys + " " + i + " " + j);  
                              q.offer(new State(keys, i, j));  
                          }  
                      }  
                  }  
              }  
              step++;  
          }  
          return -1;  
      }  
  }  
    
    
    
    
 // 方法一： 暴力法  
 Runtime: 354 ms, faster than 5.19% of Java online submissions for Shortest Path to Get All Keys.  
 Memory Usage: 39.7 MB, less than 100.00% of Java online submissions for Shortest Path to Get All Keys.  
   
 import java.awt.Point;  
   
 class Solution {  
     int INF = Integer.MAX_VALUE;  
     String[] grid;  
     int R, C;  
     Map<Character, Point> location;  
     int[] dr = new int[]{-1, 0, 1, 0};  
     int[] dc = new int[]{0, -1, 0, 1};  
   
     public int shortestPathAllKeys(String[] grid) {  
         this.grid = grid;  
         R = grid.length;  
         C = grid[0].length();  
   
         //location['a'] = the coordinates of 'a' on the grid, etc.  
         location = new HashMap();  
         for (int r = 0; r < R; ++r)  
             for (int c = 0; c < C; ++c) {  
                 char v = grid[r].charAt(c);  
                 if (v != '.' && v != '#')  
                     location.put(v, new Point(r, c));  
             }  
   
         int ans = INF;  
         int num_keys = location.size() / 2;  
         String[] alphabet = new String[num_keys];  
         for (int i = 0; i < num_keys; ++i)  
             alphabet[i] = Character.toString((char)('a' + i));  
         //alphabet = ["a", "b", "c"], if there were 3 keys  
   
         search: for (String cand: permutations(alphabet, 0, num_keys)) {  
             //bns : the built candidate answer, consisting of the sum  
             //of distances of the segments from '@' to cand[0] to cand[1] etc.  
             int bns = 0;  
             for (int i = 0; i < num_keys; ++i) {  
                 char source = i > 0 ? cand.charAt(i-1) : '@';  
                 char target = cand.charAt(i);  
   
                 //keymask : an integer with the 0-th bit set if we picked up  
                 // key 'a', the 1-th bit set if we picked up key 'b', etc.  
                 int keymask = 0;  
                 for (int j = 0; j < i; ++j)  
                     keymask |= 1 << (cand.charAt(j) - 'a');  
                 int d = bfs(source, target, keymask);  
                 if (d == INF) continue search;  
                 bns += d;  
                 if (bns >= ans) continue search;  
             }  
             ans = bns;  
         }  
   
         return ans < INF ? ans : -1;  
     }  
   
     public int bfs(char source, char target, int keymask) {  
         int sr = location.get(source).x;  
         int sc = location.get(source).y;  
         int tr = location.get(target).x;  
         int tc = location.get(target).y;  
         boolean[][] seen = new boolean[R][C];  
         seen[sr][sc] = true;  
         int curDepth = 0;  
         Queue<Point> queue = new LinkedList();  
         queue.offer(new Point(sr, sc));  
         queue.offer(null);  
   
         while (!queue.isEmpty()) {  
             Point p = queue.poll();  
             if (p == null) {  
                 curDepth++;  
                 if (!queue.isEmpty())  
                     queue.offer(null);  
                 continue;  
             }  
             int r = p.x, c = p.y;  
             if (r == tr && c == tc) return curDepth;  
             for (int i = 0; i < 4; ++i) {  
                 int cr = r + dr[i];  
                 int cc = c + dc[i];  
                 if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){  
                     char cur = grid[cr].charAt(cc);  
                     if (cur != '#') {  
                         if (Character.isUpperCase(cur) && (((1 << (cur - 'A')) & keymask) <= 0))  
                             continue; // at lock and don't have key  
   
                         queue.offer(new Point(cr, cc));  
                         seen[cr][cc] = true;  
                     }  
                 }  
             }  
         }  
   
         return INF;  
     }  
   
     public List<String> permutations(String[] alphabet, int used, int size) {  
         List<String> ans = new ArrayList();  
         if (size == 0) {  
             ans.add(new String(""));  
             return ans;  
         }  
   
         for (int b = 0; b < alphabet.length; ++b)  
             if (((used >> b) & 1) == 0)  
                 for (String rest: permutations(alphabet, used | (1 << b), size - 1))  
                     ans.add(alphabet[b] + rest);  
         return ans;  
     }  
 }   
```    
    
### 个人解读    
   Hard题目一个比一个难。。唉，难受。     
     
  官方解答：  
  1、暴力法  
  2、显然钥匙，锁，起点是图中的关键节点。先用深度优先搜索计算所有关键节点之间的距离，再用Dijkstra 算法找到每一种状态下的最小代价。  
    
  一个较好的解答：  
  同步BFS  
  类似于[847](847_Shortest%20Path%20Visiting%20All%20Nodes(Hard).md)   
  通过二进制位来记录当前经过的点，容易理解，并且效率也OK。  
    
tags:    
  -  同步BFS  
  -  图论F  
