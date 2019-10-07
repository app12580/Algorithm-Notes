### description    
  On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.  
    
  Now, a move consists of removing a stone that shares a column or row with another stone on the grid.  
    
  What is the largest possible number of moves we can make?  
    
     
    
  Example 1:  
    
  Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]  
  Output: 5  
  Example 2:  
    
  Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]  
  Output: 3  
  Example 3:  
    
  Input: stones = [[0,0]]  
  Output: 0  
     
    
  Note:  
    
  1 <= stones.length <= 1000  
  0 <= stones[i][j] < 10000  
### solution    
```    
Runtime: 36 ms, faster than 31.64% of Java online submissions for Most Stones Removed with Same Row or Column.  
Memory Usage: 45.4 MB, less than 21.43% of Java online submissions for Most Stones Removed with Same Row or Column.  
  
  
  class Solution {  
      private int[] f;  
      private int count;  
      public int removeStones(int[][] stones) {  
          int len = stones.length;  
          f = new int[len];  
          count = 0;  
          for(int i = 0; i < len; i++) {  
              f[i] = i;  
          }  
          for(int i = 0; i < len; i++) {  
              for(int j = 0; j < len; j++) {  
                  if(i == j) continue;  
                  if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {  
                      union(i, j);  
                  }  
              }  
          }  
          return count;  
      }  
      private int find(int x) {  
          if(x != f[x]) {  
              f[x] = find(f[x]);  
          }  
          return f[x];  
      }  
      private void union(int x, int y) {  
          x = find(x); y = find(y);  
          if (x != y) {  
              f[x] = y;  
              count++;  
          }  
      }  
  }  
```    
    
### 个人解读    
  把题目理清以后，就会突然出现一个东西：并查集。  
  剩下的就可以套模板了  
    
  总结：  
  1、需要想到并查集  
  2、注意，最好还是暴力遍历， 如果union一次就跑了，那么会有特例发生  
  例如这种情况:如果中间那个只union一次就出问题了。  
  ```  
    +  
   +++  
    +  
  ```  
    
tags:    
  -  并查集  
