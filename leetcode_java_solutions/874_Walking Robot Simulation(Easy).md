### description    
  A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:  
    
  -2: turn left 90 degrees  
  -1: turn right 90 degrees  
  1 <= x <= 9: move forward x units  
  Some of the grid squares are obstacles.   
    
  The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])  
    
  If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)  
    
  Return the square of the maximum Euclidean distance that the robot will be from the origin.  
    
     
    
  Example 1:  
    
  Input: commands = [4,-1,3], obstacles = []  
  Output: 25  
  Explanation: robot will go to (3, 4)  
  Example 2:  
    
  Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]  
  Output: 65  
  Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)  
     
    
  Note:  
    
  0 <= commands.length <= 10000  
  0 <= obstacles.length <= 10000  
  -30000 <= obstacle[i][0] <= 30000  
  -30000 <= obstacle[i][1] <= 30000  
  The answer is guaranteed to be less than 2 ^ 31.  
### solution    
```    
  
Runtime: 32 ms, faster than 47.74% of Java online submissions for Walking Robot Simulation.  
Memory Usage: 50.8 MB, less than 83.62% of Java online submissions for Walking Robot Simulation.  
  class Solution {  
      
      private int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};  
      private int max = 0;  
    
      public int robotSim(int[] commands, int[][] obstacles) {  
          Set<String> set = new HashSet<>();  
          for(int[] o: obstacles) {  
              set.add(o[0]+":"+o[1]);  
          }  
          int[] place = {0, 0};  
          int[] direction = {0, 1};  
          for(int c: commands) {  
              if(c == -1 || c == -2) {  
                  changeDirction(direction, c);  
              } else {  
                  move(place, direction, c, set);  
              }  
          }  
          return max;  
      }  
    
      private void move(int[] place, int[] direction, int c, Set<String> obstacles) {  
          for(int i = 0; i < c; i++) {  
              int[] t = new int[]{place[0] + direction[0], place[1] + direction[1]};  
              if (obstacles.contains(t[0]+":"+t[1])) {  
                  return;  
              }  
                
              place[0] = t[0];  
              place[1] = t[1];  
              max = Math.max(place[0] * place[0] + place[1] * place[1], max);  
          }  
      }  
    
      //    -2：向左转 90 度  
      //    -1：向右转 90 度  
      private void changeDirction(int[] direction, int c) {  
          int index = -1;  
          for(int i = 0; i < directions.length; i++) {  
              if(directions[i][0] == direction[0] && directions[i][1] == direction[1]) {  
                  index = i;  
                  break;  
              }  
          }  
          if(c == -1) {  
              int[] t = directions[(index + 1) % 4];  
              direction[0] = t[0];  
              direction[1] = t[1];  
          } else {  
              int[] t = directions[(index + 3) % 4];  
              direction[0] = t[0];  
              direction[1] = t[1];  
          }  
      }  
  }  
    
    
```    
    
### 个人解读    
  需要两个辅助方法，一个控制方向，另一个判断移动到哪里。  
  需要注意返回值是途中的最大值，而不是最终的结果。  
    
tags:    
  -  功能分解  
  -  数学  
