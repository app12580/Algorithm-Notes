### description    
  In a given grid, each cell can have one of three values:  
    
  the value 0 representing an empty cell;  
  the value 1 representing a fresh orange;  
  the value 2 representing a rotten orange.  
  Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.  
    
  Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.  
    
     
    
  Example 1:  
    
    
    
  Input: [[2,1,1],[1,1,0],[0,1,1]]  
  Output: 4  
  Example 2:  
    
  Input: [[2,1,1],[0,1,1],[1,0,1]]  
  Output: -1  
  Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.  
  Example 3:  
    
  Input: [[0,2]]  
  Output: 0  
  Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.  
     
    
  Note:  
    
  1 <= grid.length <= 10  
  1 <= grid[0].length <= 10  
  grid[i][j] is only 0, 1, or 2.  
    
    
### solution    
```    
  class Solution {  
        
        
          private int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};  
    
        
      public int orangesRotting(int[][] grid) {  
            
            boolean hasFresh = false;  
            
          Queue<int[]> queue = new LinkedList<>();  
          for(int i = 0; i < grid.length; i++) {  
              for(int j = 0; j < grid[0].length; j++) {  
                  if(grid[i][j] == 2) {  
                      queue.add(new int[]{i, j});  
                  }  
                  if(grid[i][j] == 1) {  
                      hasFresh = true;  
                  }  
              }  
          }  
          if(!hasFresh) {  
              return 0;         //关键点  
          }  
    
          int length = -1;  
            
          while(!queue.isEmpty()) {  
              int size = queue.size();  
              length++;  
              for(int k = 0; k < size; k++) {  
                  int[] poll = queue.poll();  
                  for(int[] d:directions) {  
                      int i = poll[0] + d[0];  
                      int j = poll[1] + d[1];  
                      if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {  
                          grid[i][j] = 2;  
                          queue.offer(new int[]{i, j});  
                      }  
                  }  
              }  
          }  
    
          for(int i = 0; i < grid.length; i++) {  
              for(int j = 0; j < grid[0].length; j++) {  
                  if(grid[i][j] == 1) {  
                      return -1;  
                  }  
              }  
          }  
          return length;  
      }  
  }  
```    
    
### 个人解读    
  第一反应：根据题目是一个BFS的问题。  
    
  问题一： 当从某一个烂橘子BFS运行过程中，遇到了另外一个烂橘子怎么办？  
  想法一： 修改当前的level， 然而不行，因为BFS已经扩散，只修改当下已经来不及了。  
    
  想法二： 修改遍历方式，不要遇到了烂橘子就立刻遍历，而是先把所有烂橘子找出来再遍历。 (反思：还是矩阵的BFS遇到的太少了，不然不会出现这个问题一)  
    
  使用Queue进行BFS，然后再一次遍历，看有没有完好的橘子。  
    
  注意边界条件的细节：  
  ```  
  //如果没有新鲜橙子，return 0.  
   if(!hasFresh) {  
       return 0;       
   }  
     
   //如果没有腐烂橙子的情况，length=-1，然后直接return  
    
  ```  
tags:    
  -  矩阵  
  -  BFS  
