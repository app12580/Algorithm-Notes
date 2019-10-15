### description    
  Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.  
    
  Example:  
    
  Input:  
  [  
    ["1","0","1","0","0"],  
    ["1","0","1","1","1"],  
    ["1","1","1","1","1"],  
    ["1","0","0","1","0"]  
  ]  
  Output: 6  
### solution    
```    
  方法一： 转化存成最大矩形问题，然而效率贼低  
  Runtime: 350 ms, faster than 5.43% of Java online submissions for Maximal Rectangle.  
  Memory Usage: 47.9 MB, less than 13.04% of Java online submissions for Maximal Rectangle.  
    
  class Solution {  
     public int maximalRectangle(char[][] matrix) {  
         if(matrix.length == 0) return 0;  
          int res = 0;  
          int m = matrix.length;  
          int n = matrix[0].length;  
          int[][] copy = new int[m][n];  
          for (int i = 0; i < m; i++) {  
              if (i == 0) {  
                  for (int j = 0; j < n; j++) {  
                      copy[i][j] = matrix[i][j] - '0';  
                  }  
                  res = largestRectangleArea(copy[0]);  
              } else {  
                  for(int j = 0; j < n; j++) {  
                      if(matrix[i][j] == '1') {  
                          copy[i][j] += copy[i-1][j] + 1;  
                      }  
                      res = Math.max(res, largestRectangleArea(copy[i]));  
                  }  
              }  
          }  
          return res;  
      }  
    
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
  
  // 方法二： 优化
  class Solution {
      public int maximalRectangle(char[][] matrix) {
          if(matrix.length==0)  return 0;
          
          int row = matrix.length;
          int col = matrix[0].length;
                  
          int[]heights = new int[col];
          for(int i=0;i<col;i++)
              if(matrix[0][i]=='1')
                  heights[i] = 1;
          
          int maxSquare = -1;
          for(int i=1;i<row;i++){
              maxSquare = Math.max(maxSquare,maxArea(heights));
              //update for next loop 
              for(int j=0;j<col;j++){
                  if(matrix[i][j]=='1'){
                      heights[j] = heights[j]+1;
                  }else{
                      heights[j] = 0;
                  }
                  
              }
          }
          //deal with last row
          maxSquare = Math.max(maxSquare,maxArea(heights));
          
          return maxSquare;
              
      }
    
      private int maxArea(int[] heights){
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
  想起了一个求正方形面积的题目，那个题目可以在原来数组上修改，将值改变成"以当前坐标为右下角的最大正方形边长"  
    
  思路：  
  1、先是想到了一种遍历方式，一列一列的从左刷过去，然后为了方便遍历，改成从上到下遍历  
  2、  
  ```  
    [  
      ["1","0","1","0","0"],  
      ["1","0","1","1","1"],  
      ["1","1","1","1","1"],  
      ["1","0","0","1","0"]  
    ]  
    ---------------------  
    [  
      ["*","*","*","*","*"],  
      ["2","0","2","1","1"],  
      ["1","1","1","1","1"],  
      ["1","0","0","1","0"]  
    ]  
    ---------------------  
    [  
      ["*","*","*","*","*"],  
      ["*","*","*","*","*"],  
      ["3","1","3","2","2"],  
      ["1","0","0","1","0"]  
    ]  
  ```  
  然后就变成了，经典的求直方图最大矩形面积的题目了。  
  单调栈的经典 题目  
    
  优化： 需要熟悉[084](084_Largest%20Rectangle%20in%20Histogram(Hard).md)的方法二  
    
tags:    
  -  单调栈     
  -  矩阵     
