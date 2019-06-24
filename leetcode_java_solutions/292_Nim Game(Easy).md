### description    
  You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.  
    
  Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.  
    
  Example:  
    
  Input: 4  
  Output: false   
  Explanation: If there are 4 stones in the heap, then you will never win the game;  
               No matter 1, 2, or 3 stones you remove, the last stone will always be   
               removed by your friend.  
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Nim Game.  
Memory Usage: 33.1 MB, less than 5.54% of Java online submissions for Nim Game. ???内存消耗什么情况。。  
  
  
  class Solution {  
      public boolean canWinNim(int n) {  
          return n % 4 != 0;  
      }  
  }  
```    
    
### 个人解读    
  数学题目，讨论多行的情况才差不多需要考虑。  
    
tags:    
  -  数学  
