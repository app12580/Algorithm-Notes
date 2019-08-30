### description    
  On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).  
    
  A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.  
    
     
    
    
    
     
    
  Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.  
    
  The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.  
    
     
    
  Example:  
    
  Input: 3, 2, 0, 0  
  Output: 0.0625  
  Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.  
  From each of those positions, there are also two moves that will keep the knight on the board.  
  The total probability the knight stays on the board is 0.0625.  
     
    
  Note:  
    
  N will be between 1 and 25.  
  K will be between 0 and 100.  
  The knight always initially starts on the board.  
### solution    
```    
  class Solution {  
    private final double fact = 0.125;  
      private int[][] directions = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};  
      private double[][][] dp;  
    
      public double knightProbability(int N, int K, int r, int c) {  
          dp = new double[N][N][K+1];  
          return find(N,K,r,c);  
      }  
    
      private double find(int N, int K, int r, int c) {  
          if(r < 0 || c < 0 || r>= N || c >= N) {       //注意这两个if的先后关系，很关键  
              return 0;  
          }  
          if(K == 0) {  
              return 1;  
          }  
            
          if(dp[r][c][K] != 0) {  
              return dp[r][c][K];  
          }  
          double res = 0;  
          for(int[] d: directions) {  
              res += fact * find(N, K-1, r + d[0], c + d[1]);  
          }  
          dp[r][c][K] = res;  
          return res;  
      }  
    
    
  }  
```    
    
### 个人解读    
  明显可以递归。  
  因为最终是留在棋盘的概率，所以可以无限递归，然后直接乘法就OK了。  
    
  需要辅助方法，计算留在棋盘的所有坐标集合。  
    
  ```  
  方法一：超时  
  class Solution {  
     private double fact = 0.125;  
      private Map<String, List<int[]>> map = new HashMap<>();  
      public double knightProbability(int N, int K, int r, int c) {  
          if(K == 0) {  
              return 1;  
          } else if(K == 1) {  
              List<int[]> left = getLeft(N, r, c);  
              return fact * left.size();  
          } else {  
              List<int[]> left = getLeft(N, r, c);  
              double res = 0;  
              for(int[] next: left) {  
                  res += fact * knightProbability(N, K - 1, next[0], next[1]);  
              }  
              return res;  
          }  
      }  
    
      private int[][] directions = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};  
      private List<int[]> getLeft(int N, int r, int c) {  
          if(map.containsKey(r+":"+c)) {  
              return map.get(r+":"+c);  
          }  
          List<int[]> res = new ArrayList<>();  
          for(int[] d: directions) {  
              int x = r + d[0];  
              int y = c + d[1];  
              if(x >= 0 && x < N && y >= 0 && y < N) {  
                  res.add(new int[]{x, y});  
              }  
          }  
          map.put(r+":"+c, res);  
          return res;  
      }  
  }  
  ```  
    
    
  超时了以后，优化找落点数据还不够，那就只能把前面每次的结果都存下来。  
    
  总结：  
  基本上，凡是大数据量操作的TLE问题，都可以通过DP解决  
    
    
tags:    
  -  DP  
  -  矩阵  
  -  递归  
