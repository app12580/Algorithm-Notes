### description    
  We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.  
    
  XX  <- domino  
    
  XX  <- "L" tromino  
  X  
  Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.  
    
  (In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)  
    
  Example:  
  Input: 3  
  Output: 5  
  Explanation:   
  The five different ways are listed below, different letters indicates different tiles:  
  XYZ XXZ XYY XXY XYY  
  XYZ YYZ XZZ XYY XXY  
  Note:  
    
  N  will be in range [1, 1000].  
### solution    
```    
  class Solution {  
      public int numTilings(int N) {  
          if(N <= 2) return N;  
          long[] dp = new long[N + 1];  
          dp[0] = 1;  
          int big  = (int) (1e9 +7);  
          for(int i = 1; i <= N; i++) {  
              if(i <= 2) {  
                  dp[i] = i;  
              } else {  
                  dp[i] = 2 * dp[i - 1] + dp[i - 3];  
                  dp[i] %= big;  
              }  
          }  
          return (int) dp[N];  
      }  
  }  
```    
    
### 个人解读    
  刚开始想到递归，然后想到斐波那契数列，但是并不可以。  
  因为可以无限追溯前面的结果  
  下面这种格式的无限向前推  
  XYY  
  XZZ  
    
  要想解决这类问题，必须想办法找出规律或者用某种方式去描述。  
    
  思路一：  
  通过两个int记录当前度，然后有七种方式计数  
  {{0,2},{2,0},{1,1}}  
  //如果两个int相等 {{1,2},{2,1}}  
  //如果两个int相差1，则少的那个加2 {1,2},{2,1},}  
    
  但感觉这样子效率会非常非常的低啊。。。  
    
  思路二：  
  还是两个int需要达到N，但是不通过dfs，而是直接想办法通过数学方法去分配。  
  N可以分配给2也可以分配给1，其中1的数量要小于等于隔壁的2的数量。  
    
    
  最后只能靠"找规律"dp[n]=2*d[n-1]+dp[n-3]  
    
tags:    
  -  动态规划  
  -  找规律  
  -  int超限  
