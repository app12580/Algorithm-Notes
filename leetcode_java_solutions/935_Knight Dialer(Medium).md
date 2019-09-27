### description    
  A chess knight can move as indicated in the chess diagram below:  
    
   .             
    
     
    
  This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.  
    
  Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.  
    
  How many distinct numbers can you dial in this manner?  
    
  Since the answer may be large, output the answer modulo 10^9 + 7.  
    
     
    
  Example 1:  
    
  Input: 1  
  Output: 10  
  Example 2:  
    
  Input: 2  
  Output: 20  
  Example 3:  
    
  Input: 3  
  Output: 46  
     
    
  Note:  
    
  1 <= N <= 5000  
### solution    
```    
Runtime: 49 ms, faster than 35.42% of Java online submissions for Knight Dialer.  
Memory Usage: 36.7 MB, less than 26.67% of Java online submissions for Knight Dialer.  
  
  class Solution {  
      private Map<Integer, int[]> map = new HashMap<>();  
      public int knightDialer(int N) {  
          int big = (int)1e9+7;  
          map.put(1,  new int[]{6,8});  
          map.put(2,  new int[]{7,9});  
          map.put(3,  new int[]{4,8});  
          map.put(4,  new int[]{3,9,0});  
          map.put(5,  new int[]{});  
          map.put(6,  new int[]{1,7,0});  
          map.put(7,  new int[]{2,6});  
          map.put(8,  new int[]{1,3});  
          map.put(9,  new int[]{2,4});  
          map.put(0,  new int[]{4,6});  
    
          long[][] dp = new long[N][10];  
          for(int i = 0; i < N; i++) {  
              for(int j = 0; j < 10; j++) {  
                  if(i == 0) {  
                      dp[i][j] = 1;  
                  } else {  
                      int[] next = map.get(j);  
                      for(int n: next) {  
                          dp[i][j] += dp[i-1][n];  
                          dp[i][j] %= big;  
                      }  
                  }  
              }  
          }  
    
          long sum = 0;  
          for(long s: dp[N - 1]) {  
              sum += s;  
              sum %= big;  
          }  
          return (int) sum;  
      }  
  }  
```    
    
### 个人解读    
  本题目需要使用多维DP，然后遍历即可。  
    
tags:    
  -  大数字  
  -  数学  
  -  多维DP  
    
