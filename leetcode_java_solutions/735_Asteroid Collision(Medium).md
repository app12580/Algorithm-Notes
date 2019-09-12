### description    
  We are given an array asteroids of integers representing asteroids in a row.  
    
  For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.  
    
  Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.  
    
  Example 1:  
  Input:   
  asteroids = [5, 10, -5]  
  Output: [5, 10]  
  Explanation:   
  The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.  
  Example 2:  
  Input:   
  asteroids = [8, -8]  
  Output: []  
  Explanation:   
  The 8 and -8 collide exploding each other.  
  Example 3:  
  Input:   
  asteroids = [10, 2, -5]  
  Output: [10]  
  Explanation:   
  The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.  
  Example 4:  
  Input:   
  asteroids = [-2, -1, 1, 2]  
  Output: [-2, -1, 1, 2]  
  Explanation:   
  The -2 and -1 are moving left, while the 1 and 2 are moving right.  
  Asteroids moving the same direction never meet, so no asteroids will meet each other.  
  Note:  
    
  The length of asteroids will be at most 10000.  
  Each asteroid will be a non-zero integer in the range [-1000,   
### solution    
```    
  
Runtime: 13 ms, faster than 41.79% of Java online submissions for Asteroid Collision.  
Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Asteroid Collision.  
  
  
  class Solution {  
     public int[] asteroidCollision(int[] asteroids) {  
          Stack<Integer> stack = new Stack<>();  
          for(int num: asteroids) {  
              if(stack.isEmpty()) {  
                  stack.push(num);  
              } else {  
                  int peek = stack.peek();  
                  if(num > 0) {  
                      stack.push(num);  
                  } else {  
                      //发生碰撞  
                      attack(stack, num);  
                  }  
              }  
          }  
          int[] res = new int[stack.size()];  
          int index = res.length - 1;  
          while(!stack.isEmpty()) {  
              res[index--] = stack.pop();  
          }  
          return res;  
      }  
    
      private void attack(Stack<Integer> stack, int num) {  
          while(num != 0 && !stack.isEmpty() && stack.peek() > 0) {  
              int peekVal = Math.abs(stack.peek());  
              int numVal = Math.abs(num);  
              if(peekVal == numVal) {  
                  num = 0;  
                  stack.pop();  
              } else if(peekVal > numVal) {  
                  num = 0;  
              } else {  
                  stack.pop();  
              }  
          }  
          if(num != 0) {  
              stack.push(num);  
          }  
      }  
  }  
    
    
```    
    
### 个人解读    
  题目读完以后，自然而然的就联想到了使用栈。  
  
  总结：
  流水账，根据题目要求一步一步来就完事了。  
    
tags:    
  -  栈  
  -  流水账  
