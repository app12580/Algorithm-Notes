### description    
  On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).  
    
  Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.  
    
  You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?  
    
  Example 1:  
    
  Input: [[0,2],[1,3]]  
  Output: 3  
  Explanation:  
  At time 0, you are in grid location (0, 0).  
  You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.  
    
  You cannot reach point (1, 1) until time 3.  
  When the depth of water is 3, we can swim anywhere inside the grid.  
  Example 2:  
    
  Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]  
  Output: 16  
  Explanation:  
   0  1  2  3  4  
  24 23 22 21  5  
  12 13 14 15 16  
  11 17 18 19 20  
  10  9  8  7  6  
    
  The final route is marked in bold.  
  We need to wait until time 16 so that (0, 0) and (4, 4) are connected.  
  Note:  
    
  2 <= N <= 50.  
  grid[i][j] is a permutation of [0, ..., N*N - 1].  
### solution    
```    
// 方法一： VFS 值优先遍历  
Runtime: 39 ms, faster than 49.30% of Java online submissions for Swim in Rising Water.  
Memory Usage: 43 MB, less than 30.00% of Java online submissions for Swim in Rising Water.  
  
  class Solution {  
     class Square {  
          int x;  
          int y;  
          int val;  
          public Square(int x, int y, int val) {  
              this.x = x;  
              this.y = y;  
              this.val = val;  
          }  
      }  
       public int swimInWater(int[][] grid) {  
          PriorityQueue<Square> pq = new PriorityQueue<>((s1, s2) -> s1.val - s2.val);  
          int len = grid.length;  
          pq.add(new Square(0, 0, grid[0][0]));  
          int max = Integer.MIN_VALUE;  
          int[] directions = {0,1,0,-1,0};  
          boolean[][] flags = new boolean[len][len];  
          flags[0][0] = true;  
          while(true) {  
              Square poll = pq.poll();  
              max = Math.max(max, poll.val);  
              if(poll.x == len - 1 && poll.y == len - 1) {  
                  break;  
              }  
              for(int i = 0; i < 4; i++) {  
                  int nextX = poll.x + directions[i];  
                  int nextY = poll.y + directions[i + 1];  
                  if(nextX >= 0 && nextX < len && nextY >= 0 && nextY < len && !flags[nextX][nextY]) {  
                      flags[nextX][nextY] = true;  
                      pq.add(new Square(nextX, nextY, grid[nextX][nextY]));  
                  }  
              }  
          }  
          return max;  
      }  
    
  }  
```    
    
### 个人解读    
  题目：找出一条通路，求所有通路的最大值中，最小的是多少  
    
  VFS，值优先遍历  
    
  思路一：  
  1、先是想到每次出路时候，先算最小的，等走到头了再回溯  
  2、然后想到用一个PQ去存放需要回溯的节点  
  3、然后又想到，先算最小的，可能会破坏整体平衡，16 -> 18 然后要回溯，还需要知道回溯前的最值是多少。  
     所以与其看当前点的最小出路，还不如看所有点的最小出路。  
  4、由此，引发出了VFS的概念。  
    
  思路二：  
  最大值中的最小值，采用二分法。  
  效率会更高，因为比起思路一，少了将队列中节点排序的步骤  
    
tags:    
  -  VFS  
  -  目标值问题  
