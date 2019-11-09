### description    
  There are G people in a gang, and a list of various crimes they could commit.  
    
  The i-th crime generates a profit[i] and requires group[i] gang members to participate.  
    
  If a gang member participates in one crime, that member can't participate in another crime.  
    
  Let's call a profitable scheme any subset of these crimes that generates at least P profit, and the total number of gang members participating in that subset of crimes is at most G.  
    
  How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.  
    
     
    
  Example 1:  
    
  Input: G = 5, P = 3, group = [2,2], profit = [2,3]  
  Output: 2  
  Explanation:   
  To make a profit of at least 3, the gang could either commit crimes 0 and 1, or just crime 1.  
  In total, there are 2 schemes.  
  Example 2:  
    
  Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]  
  Output: 7  
  Explanation:   
  To make a profit of at least 5, the gang could commit any crimes, as long as they commit one.  
  There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).  
     
   给定不同项目，每个项目有获利和人数，根据已知总人数，获取获利不少于P的数量。  
    
  Note:  
    
  1 <= G <= 100  
  0 <= P <= 100  
  1 <= group[i] <= 100  
  0 <= profit[i] <= 100  
  1 <= group.length = profit.length <= 100  
    
### solution    
```    
  // 方法一： 背包问题  
  Runtime: 16 ms, faster than 81.04% of Java online submissions for Profitable Schemes.  
  Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Profitable Schemes.  
    
  class Solution {  
      public int profitableSchemes(int G, int P, int[] group, int[] profit) {  
          int[][] dp = new int[P + 1][G + 1];   //dp[i][j]表示获取利润i(>=P的统统记为P)，同时需要j人的方案有多少种  
          dp[0][0] = 1;// 非常关键  
          int res = 0, mod = (int)1e9 + 7;  
          for (int k = 0; k < group.length; k++) {  
              int g = group[k], p = profit[k];  
              for (int i = P; i >= 0; i--)  //因为一个方案只能用一次，所以从大开始遍历  
                  for (int j = G - g; j >= 0; j--)  
                      dp[Math.min(i + p, P)][j + g] = (dp[Math.min(i + p, P)][j + g] + dp[i][j]) % mod;  
                      // 解读：  
                      // 每次k的循环相当于多了一种方案在i+p,j+g的状态下，因为新加入的p,g这种方案，所以和dp[i][j]构成了联系  
          }  
          for (int x : dp[P]) res = (res + x) % mod;  
          return res;  
      }  
  }  
```    
    
### 个人解读    
    
  思路一： DFS。区间从0开始，进入dfs，然后从1开始。。。但是这样会遇到问题，不同的数量之间做乘法，就算是long也能超限，而且乘法太多效率低。  
    
  遍历不行要考虑dp。本题目的人员和利润十分类似于背包问题里的重量和价值  
    
tags:    
  -  大数统计  
