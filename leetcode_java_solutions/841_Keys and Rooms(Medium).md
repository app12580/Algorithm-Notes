### description    
  There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.   
    
  Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.  
    
  Initially, all the rooms start locked (except for room 0).   
    
  You can walk back and forth between rooms freely.  
    
  Return true if and only if you can enter every room.  
    
  Example 1:  
    
  Input: [[1],[2],[3],[]]  
  Output: true  
  Explanation:    
  We start in room 0, and pick up key 1.  
  We then go to room 1, and pick up key 2.  
  We then go to room 2, and pick up key 3.  
  We then go to room 3.  Since we were able to go to every room, we return true.  
  Example 2:  
    
  Input: [[1,3],[3,0,1],[2],[0]]  
  Output: false  
  Explanation: We can't enter the room with number 2.  
  Note:  
    
  1 <= rooms.length <= 1000  
  0 <= rooms[i].length <= 1000  
  The number of keys in all rooms combined is at most 3000.  
### solution    
```    
Runtime: 1 ms, faster than 92.32% of Java online submissions for Keys and Rooms.  
Memory Usage: 42.7 MB, less than 82.76% of Java online submissions for Keys and Rooms.  
  
  class Solution {  
      public boolean canVisitAllRooms(List<List<Integer>> rooms) {  
          int len = rooms.size();  
          boolean[] flags = new boolean[len];  
          Queue<Integer> queue = new LinkedList<>();  
          queue.offer(0);  
          flags[0] = true;  
          while (!queue.isEmpty()) {  
              List<Integer> list = rooms.get(queue.poll());  
              for(int next: list) {  
                  if(!flags[next]) {  
                      queue.offer(next);  
                      flags[next] = true;  
                  }  
              }  
          }  
          for(boolean f: flags) {  
              if(!f) {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  感觉是一个BFS题目，获取当前可以去的房间，然后通过一个Queue去判断。  
  使用一个boolean[]作为标记  
    
tags:    
  -  BFS  
