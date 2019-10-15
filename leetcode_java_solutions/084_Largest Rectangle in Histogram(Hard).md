### description    
  Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.  
    
     
    
    
  Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].  
    
     
    
    
  The largest rectangle is shown in the shaded area, which has area = 10 unit.  
    
     
    
  Example:  
    
  Input: [2,1,5,6,2,3]  
  Output: 10  
### solution    
```    
//方法一： 单调栈  
Runtime: 13 ms, faster than 71.82% of Java online submissions for Largest Rectangle in Histogram.  
Memory Usage: 41.3 MB, less than 63.64% of Java online submissions for Largest Rectangle in Histogram.  
  
  class Solution {  
      public int largestRectangleArea(int[] heights) {  
          Stack<Integer> stack = new Stack<>();  
          int res = 0;  
          int i = 0;  
          int len = heights.length;  
          while(i < len) {  
              int cur = heights[i];  
              if (stack.isEmpty() || cur >= heights[stack.peek()]) {  
                  stack.push(i++);  
              } else {  
                  //弹出的高度，以此时栈的peek为起点向右画矩形  
                  int top = stack.pop();  
                  int curArea = heights[top]*(stack.isEmpty()?i:(i-stack.peek()-1));  
                  res = Math.max(curArea,res);  
              }  
          }  
          while(!stack.isEmpty()) {  
              int top = stack.pop();  
              int curArea = heights[top]*(stack.isEmpty()?i:(i-stack.peek()-1));  
              res = Math.max(res,curArea);  
          }  
          return res;  
      }  
  }  
  
  // 方法二： 
  class Solution {
      public int largestRectangleArea(int[] heights) {
         if (heights == null || heights.length == 0) {
              return 0;
          }
          int length = heights.length;
          
          int[] lastFromLeft = new int[length];
          int[] lastFromRight = new int[length];
          int p;
          lastFromLeft[0] = -1;
          lastFromRight[length - 1] = length;
          for (int i = 1; i < length; i ++) {
              p = i -1;
              while (p >= 0 && heights[p] >= heights[i]){
                  p = lastFromLeft[p];
              }
              lastFromLeft[i] = p;
          }
          
          for (int i = length - 2; i >= 0; i --) {
              p = i + 1;
              while (p < length && heights[p] >= heights[i]) {
                  p = lastFromRight[p];
              }
              lastFromRight[i] = p;
          }
          int max = -1;
          for (int i = 0; i < length; i ++) {
              int width = lastFromRight[i]-lastFromLeft[i]-1;
              max = Math.max(max, heights[i]* width);
          }
                  
          return max;
      }
  }
```    
    
### 个人解读    
  可以说是我对单调栈认知的起源了，重点记忆，甚至直接背答案。  
    
  总结：  
  1、最好画图先  
  2、因为每次弹栈都需要计算，所以最好每次只弹一次，不要用while弹多个，不容易控制。弹栈时不要i++即可  
    
  总结：
  ！！！！  
  最大矩形算法，还有比单调栈更还的算法！    
    
tags:    
  -  单调栈  
  -  重点题目  
