### description    
  There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].  
    
  Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.  
    
     
    
  Example 1:  
    
  Input: [[10,20],[30,200],[400,50],[30,20]]  
  Output: 110  
  Explanation:   
  The first person goes to city A for a cost of 10.  
  The second person goes to city A for a cost of 30.  
  The third person goes to city B for a cost of 50.  
  The fourth person goes to city B for a cost of 20.  
    
  The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.  
     
    
  Note:  
    
  1 <= costs.length <= 100  
  It is guaranteed that costs.length is even.  
  1 <= costs[i][0], costs[i][1] <= 1000  
    
    
### solution    
```    
  class Solution {  
        public int twoCitySchedCost(int[][] costs) {  
          int N = costs.length / 2;  
          int[][] dp = new int[N + 1][N + 1];  
          for (int i = 1; i <= N; i++) {  
              dp[i][0] = dp[i - 1][0] + costs[i - 1][0];  
          }  
          for (int j = 1; j <= N; j++) {  
              dp[0][j] = dp[0][j - 1] + costs[j - 1][1];  
          }  
          for (int i = 1; i <= N; i++) {  
              for (int j = 1; j <= N; j++) {  
                  dp[i][j] = Math.min(dp[i - 1][j] + costs[i + j - 1][0], dp[i][j - 1] + costs[i + j - 1][1]);  
              }  
          }  
          return dp[N][N];  
      }  
  }  
```    
    
### 个人解读    
  想了想，没啥更好的办法，就用动态规划吧。  
    
  即使有更方便的算法，感觉大概率从N和2N的关系入手。  
    
  写到一半有种背包问题的感觉。  
    
  动态规划，核心重点在于dp模型的建立，本题目如果将dp[i][j]定义成对于所有的cost来说最小的开销，那么题目会很麻烦，也因此有了背包问题的感觉。  
  然而，如果给DP加上前(i+j)个cost的限定的话，那么题目迎刃而解。  
    
tags:    
  -  动态规划  
  -  给dp加限定  
