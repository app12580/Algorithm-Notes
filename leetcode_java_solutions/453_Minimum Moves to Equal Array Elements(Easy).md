### description    
  Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.  
    
  Example:  
    
  Input:  
  [1,2,3]  
    
  Output:  
  3  
    
  Explanation:  
  Only three moves are needed (remember each move increments two elements):  
    
  [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]  
### solution    
```    
  class Solution {  
      public int minMoves(int[] nums) {  
          int sum = 0;  
          int min = Integer.MAX_VALUE;  
          for(int num: nums) {  
              sum += num;  
              min = Math.min(min, num);  
          }  
          return sum - min * nums.length;  
      }  
  }  
```    
    
### 个人解读    
  数学问题，需要好好弄清题目。  
  把n-1个元素提升1，等价于把一个元素减一，把这个想清楚就轻松多了。  
    
    
tags:    
  -  数学  
