### description    
  Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.  You create a playlist so that:  
    
  Every song is played at least once  
  A song can only be played again only if K other songs have been played  
  Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.  
    
     
    
  Example 1:  
    
  Input: N = 3, L = 3, K = 1  
  Output: 6  
  Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].  
  Example 2:  
    
  Input: N = 2, L = 3, K = 0  
  Output: 6  
  Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]  
  Example 3:  
    
  Input: N = 2, L = 3, K = 1  
  Output: 2  
  Explanation: There are 2 possible playlists. [1, 2, 1], [2, 1, 2]  
    
  K是指没两首歌之间的间隔。  
     
### solution    
```    
// 方法一： DP  
Runtime: 6 ms, faster than 25.81% of Java online submissions for Number of Music Playlists.  
Memory Usage: 35.4 MB, less than 100.00% of Java online submissions for Number of Music Playlists.  
  
  class Solution {  
      public int numMusicPlaylists(int N, int L, int K) {  
          int MOD = 1_000_000_007;  
    
          long[][] dp = new long[L+1][N+1];   //表示前i个歌曲里面，包含j首歌的数量  
          dp[0][0] = 1;  
          for (int i = 1; i <= L; ++i)    //循环顺序，优先滆湖循环  
              for (int j = 1; j <= N; ++j) {  
                  dp[i][j] += dp[i-1][j-1] * (N-j+1);         //如果是前面不存在的， 那么就要从剩下的里面选一个  
                  dp[i][j] += dp[i-1][j] * Math.max(j-K, 0);  //如果当前i位置新加的歌曲在前面i-1里面，那么就需要排除K个，剩下的都可以  
                  dp[i][j] %= MOD;  
              }  
    
          return (int) dp[L][N];  
      }  
  }  
     
```    
    
### 个人解读    
  动态规划，读明白题目后，其实发现，并没有多么难。  
    
tags:    
  -  动态规划  
