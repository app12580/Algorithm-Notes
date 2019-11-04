### description    
  Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.  
    
     
    
  Note:  
    
  Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.  
    
     
    
  Example:  
    
  Given the following 3x6 height map:  
  [  
    [1,4,3,1,3,2],  
    [3,2,1,3,2,4],  
    [2,3,3,2,3,1]  
  ]  
    
  Return 4.  
    
    
  The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.  
    
  After the rain, water is trapped between the blocks. The total volume of water trapped is 4.  
### solution    
```    
Runtime: 49 ms, faster than 10.86% of Java online submissions for Trapping Rain Water II.  
Memory Usage: 39.2 MB, less than 100.00% of Java online submissions for Trapping Rain Water II.  
  
  class Solution {  
        public int trapRainWater(int[][] heightMap) {  
          if (heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2)  
              return 0;  
          PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt((Cell cell) -> cell.height));  
          int m = heightMap.length;  
          int n = heightMap[0].length;  
          boolean[][] visited = new boolean[m][n];  
          for (int i = 0; i < m; i++) {  
              visited[i][0] = visited[i][n-1] = true;  
              queue.add(new Cell(i, 0, heightMap[i][0]));  
              queue.add(new Cell(i, n-1, heightMap[i][n-1]));  
          }  
          for (int i = 1; i < n-1; i++) {  
              visited[0][i] = visited[m-1][i] = true;  
              queue.add(new Cell(0, i, heightMap[0][i]));  
              queue.add(new Cell(m-1, i, heightMap[m-1][i]));  
          }  
          int result = 0;  
          int[][] bounds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  
          while (!queue.isEmpty()) {  
              Cell cell = queue.poll();  
              for (int[] bound : bounds) {  
                  int row = cell.row + bound[0];  
                  int col = cell.col + bound[1];  
                  if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {  
                      result += Math.max(0, cell.height - heightMap[row][col]);  
                      visited[row][col] = true;  
                      queue.add(new Cell(row, col, Math.max(cell.height, heightMap[row][col])));  
                  }  
              }  
          }  
          return result;  
      }  
    
      private static class Cell {  
          private int row;  
          private int col;  
          private int height;  
    
          public Cell(int row, int col, int height) {  
              this.row = row;  
              this.col = col;  
              this.height = height;  
          }  
      }  
  }  
```    
    
### 个人解读    
  联动题目[042](042_Trapping%20Rain%20Water(Hard).md)，那个题目是一维的，我自己使用的是单调栈算法。  
  感觉这道题要想做出来，还要多多想想42的其他解法。  
    
  从边缘向中间收缩，注意从小到大，这样的话，只要新来的比前面的小，就可以直接+=差值了。因为这个差值一定是新来的周围中最小的差值。  
    
tags:    
  -  重点数学  
