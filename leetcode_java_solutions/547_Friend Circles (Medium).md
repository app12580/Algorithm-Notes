### description    
  There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.  
    
  Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.  
    
  Example 1:  
  Input:   
  [[1,1,0],  
   [1,1,0],  
   [0,0,1]]  
  Output: 2  
  Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.   
  The 2nd student himself is in a friend circle. So return 2.  
  Example 2:  
  Input:   
  [[1,1,0],  
   [1,1,1],  
   [0,1,1]]  
  Output: 1  
  Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,   
  so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.  
  Note:  
  N is in range [1,200].  
  M[i][i] = 1 for all students.  
  If M[i][j] = 1, then M[j][i] = 1.  
    
    
### solution    
```    
  // 方法一： 十字湮灭法  
    
  Runtime: 20 ms, faster than 5.54% of Java online submissions for Friend Circles.  
  Memory Usage: 47 MB, less than 7.99% of Java online submissions for Friend Circles.  
    
  class Solution {  
       public int findCircleNum(int[][] M) {  
    
          if(M == null || M.length == 0 || M[0].length == 0) {  
              return 0;  
          }  
    
          int res = 0;  
          int m = M.length;  
          int n = M[0].length;  
    
          for(int i = 0; i < m; i++) {  
              boolean flag = false;  
              for(int j = 0; j < n; j++) {  
                  if(M[i][j] == 1) {  
                      if(!flag) {  
                          res++;  
                          flag = true;  
                      }  
                      dfs(M, i, j);  
                  }  
              }  
          }  
          return res;  
    
      }  
    
      private void dfs(int[][] M, int m, int n) {  
          if(m < 0 || m >= M.length || n < 0 || n >= M[0].length) {  
              return;  
          }  
          M[m][n] = 0;  
          //湮灭m行  
          for(int i = 0; i < M[0].length; i++) {  
              if(M[m][i] == 1) {  
                  M[m][i] = 0;  
                  dfs(M, m, i);  
              }  
          }  
           
          //湮灭n列  
          for(int i = 0; i < M.length; i++) {  
              if(M[i][n] == 1) {  
                  M[i][n] = 0;  
                  dfs(M, i, n);  
              }  
          }  
      }  
  }  
    
  //方法二： 优化  
  遍历范围减半，并且采用标记法，判断该人是否计算过。  
  采用DFS  
    
  Runtime: 3 ms, faster than 48.01% of Java online submissions for Friend Circles.  
  Memory Usage: 44.8 MB, less than 50.29% of Java online submissions for Friend Circles.  
    
  class Solution {  
      public int findCircleNum(int[][] M) {  
          if(M == null || M.length == 0 || M[0].length == 0) {  
              return 0;  
          }  
          int m = M.length;  
          int res = 1;  
          boolean[] flag = new boolean[m];  
          Stack<Integer> stack = new Stack<>();  
          stack.push(0);  
          flag[0] = true;  
          while(!stack.isEmpty()) {  
              Integer pop = stack.pop();  
              for(int i = 0; i < m; i++) {  
                  int v = M[pop][i];  
                  if(v == 1 && !flag[i]) {  
                      flag[i] = true;  
                      stack.push(i);  
                  }  
              }  
              //如果栈顶没有其他朋友了  
            if(stack.isEmpty()) {  
                  for(int i = 0; i < m; i++) {  
                      if(!flag[i]) {  
                          stack.push(i);  
                          flag[i] = true;  
                          res++;  
                          break;  
                      }  
                  }  
              }  
          }  
          return res;  
      }     
  }  
```    
    
### 个人解读    
  DFS  
  朋友圈，所以遍历时候需要双向湮灭  
  //第一次提交失败，思路错误了  
  1001  
  0110  
  0111  
  1011  
  expect result： 1。  
  所以不能简单的直接分区。  
  发现我TM智障啊，湮灭不是湮灭相邻的，而是湮灭同一行和同一列的。所以需要十字湮灭。  
  然而最后想了想，还是用DFS+标记法最方便，因为本题本质上只是一维数组的计算，以人为研究对象。  
    
tags:    
  -  矩阵  
  -  DFS  
  -  十字湮灭  
