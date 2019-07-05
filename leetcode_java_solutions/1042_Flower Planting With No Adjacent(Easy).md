### description    
  You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.  
    
  paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.  
    
  Also, there is no garden that has more than 3 paths coming into or leaving it.  
    
  Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.  
    
  Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.  
    
     
    
  Example 1:  
    
  Input: N = 3, paths = [[1,2],[2,3],[3,1]]  
  Output: [1,2,3]  
  Example 2:  
    
  Input: N = 4, paths = [[1,2],[3,4]]  
  Output: [1,2,1,2]  
  Example 3:  
    
  Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]  
  Output: [1,2,3,4]  
     
    
  Note:  
    
  1 <= N <= 10000  
  0 <= paths.size <= 20000  
  No garden has 4 or more paths coming into or leaving it.  
  It is guaranteed an answer exists.  
    
  每个花园最多和三个花园相连，一共有四种颜色  
### solution    
```    
  class Solution {  
      public int[] gardenNoAdj(int N, int[][] paths) {  
          Map<Integer, Set<Integer>> graph = new HashMap<>();  
          for (int i = 0; i < N; i++) {  
              graph.put(i, new HashSet<>());  
          }  
          for (int[] path: paths) {  
              int a = path[0] - 1;  
              int b = path[1] - 1;  
              graph.get(a).add(b);  
              graph.get(b).add(a);  
          }  
          int[] res = new int[N];  
          for (int i = 0; i < N; i++) {  
              boolean[] used = new boolean[5];  
              for (int adj: graph.get(i)) {  
                  used[res[adj]] = true;  
              }  
              for (int j = 1; j <= 4; j++) {  
                  if (!used[j]) {  
                      res[i] = j;  
                  }  
              }  
          }  
          return res;  
      }  
  }  
    
  // 优化   
    
  class Solution {  
      public int[] gardenNoAdj(int N, int[][] paths) {  
          int[][] topo = new int[N+1][3] ;  
          for( int[] cur : paths ){  
              int temp = 0 ;  
              while( topo[cur[0]][temp] != 0 ) temp++ ;  
              topo[cur[0]][temp] = cur[1] ;  
              temp = 0 ;  
              while( topo[cur[1]][temp] != 0 ) temp++ ;  
              topo[cur[1]][temp] = cur[0] ;  
          }  
          // topo 存储每个花园和谁相连的信息  
          int[] res1 = new int[N+1] ;  
          int[] res = new int[N] ;  
          for( int i = 1 ; i <= N ; i++ ){  
              int temp = 1 ;  
              while( res1[topo[i][0]] == temp || res1[topo[i][1]] == temp || res1[topo[i][2]] == temp ) temp++ ;  
              res1[i] = temp ;  
          }  
          for( int i = 0 ; i < N ; i++ ) res[i] = res1[i+1] ;  
          return res ;  
      }  
  }  
```    
    
### 个人解读    
  感觉有点四色地图的影子。  
  思路： 使用dfs  
  肯定需要预处理，不然paths很难使用。  
    
  因为每次添加的时候，最多有三个不可重复的选项，而一共有四个选项，所以可以不用回溯。  
    
  看了下优化后的答案，能用数组就用数组，效率一般是最高的。  
    
tags:    
  -  数学法   
  -  优化   
  -  数据结构   
