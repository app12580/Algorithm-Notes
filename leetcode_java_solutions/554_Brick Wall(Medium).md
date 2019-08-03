### description    
  There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.  
    
  The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.  
    
  If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.  
    
  You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.  
    
     
    
  Example:  
    
  Input: [[1,2,2,1],  
          [3,1,2],  
          [1,3,2],  
          [2,4],  
          [3,1,2],  
          [1,3,1,1]]  
    
  Output: 2  
    
  Explanation:   
    
     
    
  Note:  
    
  The width sum of bricks in different rows are the same and won't exceed INT_MAX.  
  The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.  
    
### solution    
```    
  
Runtime: 10 ms, faster than 95.73% of Java online submissions for Brick Wall.  
Memory Usage: 46.8 MB, less than 97.22% of Java online submissions for Brick Wall.  
  
  class Solution {  
      public int leastBricks(List<List<Integer>> wall) {  
          int height = wall.size();  
          List<Integer> first = wall.get(0);  
          int width = 0;  
          for(int w: first) {  
              width += w;  
          }  
            
          Map<Integer, Integer> map = new HashMap<>();  
          for(int i = 0; i < wall.size(); i++) {  
              List<Integer> curList = wall.get(i);  
              int curSum = 0;  
              for(int j = 0; j < curList.size() - 1; j++) {  
                  curSum += curList.get(j);  
                  map.put(curSum, map.getOrDefault(curSum, 0) + 1);  
              }  
          }  
          int max = 0;  
          for(int num: map.values()) {  
              max = Math.max(max, num);  
          }  
          return wall.size() - max;  
      }  
  }  
```    
    
### 个人解读    
  本题目可以用反转法，先求最多的缝隙，然后再用总高度减。  
    
  特殊input：[[100000000],[100000000],[100000000]]  
  所以想用数组代替map的思路告破。  
    
tags:    
  -  反转法  
