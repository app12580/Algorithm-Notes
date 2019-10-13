### description    
  Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.  
    
    
  The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!  
    
  Example:  
    
  Input: [0,1,0,2,1,0,1,3,2,1,2,1]  
  Output: 6  
### solution    
```    
//方法一： 单调栈  
一周目时候能做出来就不错了。。。  
Runtime: 5 ms, faster than 17.61% of Java online submissions for Trapping Rain Water.  
Memory Usage: 37.5 MB, less than 97.95% of Java online submissions for Trapping Rain Water.  
  
  class Solution {  
      public int trap(int[] height) {  
          int res = 0;  
          Stack<Integer> stack = new Stack<>();  
          for (int i = 0; i < height.length; i++) {  
              int cur = height[i];  
              if (cur == 0) continue;  
              if (stack.isEmpty() || cur < height[stack.peek()]) {  
                  if(!stack.isEmpty()) {          
                      res += cur * (i - stack.peek() - 1);  
                  }  
                  stack.push(i);  
              } else {  
                  int ground = 0;  
                  while (!stack.isEmpty() && height[stack.peek()] <= cur) {  
                      int pop = stack.pop();  
                      res += (height[pop] - ground) * (i - pop - 1);  
                      ground = height[pop];  
                  }  
                //关键： 要注意单调减中的缝隙也要计算  
                  if(!stack.isEmpty()) {  
                      res += (cur - ground) * (i - stack.peek() - 1);  
                  }  
                  stack.push(i);  
              }  
    
          }  
           //注意最终时刻，还需要对那些单调减的进行计算。  
          //思考了以后，决定把这一部分放在弹栈时候进行，因为只有那个时候，才保留了ground的数据  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  看到这个题目，然后有以下几种思路：  
  1、双指针从两边到中间  
  2、递归  
  3、栈->单调栈  
    
  因为发现，本题目的分割点0其实与是不是0并不关键，也可以101与212其实是差不多的。  
    
  然后将思路选择在单调栈上面，然后看向32123这种，就会发现，要单调减的。如果遇到相等的也需要弹栈。  
    
tags:    
  -  单调栈  
  -  细节  
