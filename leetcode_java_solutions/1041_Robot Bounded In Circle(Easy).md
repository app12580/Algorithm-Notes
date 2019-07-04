### description    
  On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:  
    
  "G": go straight 1 unit;  
  "L": turn 90 degrees to the left;  
  "R": turn 90 degress to the right.  
  The robot performs the instructions given in order, and repeats them forever.  
    
  Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.  
    
     
    
  Example 1:  
    
  Input: "GGLLGG"  
  Output: true  
  Explanation:   
  The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).  
  When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.  
  Example 2:  
    
  Input: "GG"  
  Output: false  
  Explanation:   
  The robot moves north indefinitely.  
  Example 3:  
    
  Input: "GL"  
  Output: true  
  Explanation:   
  The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...  
     
    
  Note:  
    
  1 <= instructions.length <= 100  
  instructions[i] is in {'G', 'L', 'R'}  
### solution    
```    
  class Solution {  
      public boolean isRobotBounded(String instructions) {  
          int x = 0, y = 0, i = 0, d[][] = {{0, 1}, {1, 0}, {0, -1}, { -1, 0}};  
       for (int j = 0; j < instructions.length(); ++j)  
           if (instructions.charAt(j) == 'R')  
               i = (i + 1) % 4;  
           else if (instructions.charAt(j) == 'L')  
               i = (i + 3) % 4;  
           else {  
               x += d[i][0]; y += d[i][1];  
           }  
       return x == 0 && y == 0 || i > 0;   
      }  
  }  
```    
    
### 个人解读    
  转向要用一个优雅的写法。  
    
  注意转向了也算不同的。    
    
tags:    
  -  转向  
