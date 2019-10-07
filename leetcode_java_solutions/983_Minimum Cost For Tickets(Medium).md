### description    
  In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.  
    
  Train tickets are sold in 3 different ways:  
    
  a 1-day pass is sold for costs[0] dollars;  
  a 7-day pass is sold for costs[1] dollars;  
  a 30-day pass is sold for costs[2] dollars.  
  The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.  
    
  Return the minimum number of dollars you need to travel every day in the given list of days.  
    
     
    
  Example 1:  
    
  Input: days = [1,4,6,7,8,20], costs = [2,7,15]  
  Output: 11  
  Explanation:   
  For example, here is one way to buy passes that lets you travel your travel plan:  
  On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.  
  On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.  
  On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.  
  In total you spent $11 and covered all the days of your travel.  
  Example 2:  
    
  Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]  
  Output: 17  
  Explanation:   
  For example, here is one way to buy passes that lets you travel your travel plan:  
  On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.  
  On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.  
  In total you spent $17 and covered all the days of your travel.  
     
    
  Note:  
    
  1 <= days.length <= 365  
  1 <= days[i] <= 365  
  days is in strictly increasing order.  
  costs.length == 3  
  1 <= costs[i] <= 1000  
### solution    
```    
Runtime: 2 ms, faster than 42.46% of Java online submissions for Minimum Cost For Tickets.  
Memory Usage: 35.6 MB, less than 100.00% of Java online submissions for Minimum Cost For Tickets.  
  
  class Solution {  
      public int mincostTickets(int[] days, int[] costs) {  
          int len = days.length;  
          int lastDay = days[len - 1];  
          int[] dp = new int[lastDay + 1];  
          Set<Integer> set = new HashSet<>();  
          for(int d: days) {  
              set.add(d);  
          }  
          for(int i = 1; i <= lastDay; i++) {  
              if(!set.contains(i)) {  
                  dp[i] = dp[i - 1];  
                  continue;  
              }  
              dp[i] = costs[0] + dp[i - 1];  
              dp[i] = Math.min(costs[1] + dp[Math.max(i - 7, 0)], dp[i]); // 7-day  
              dp[i] = Math.min(costs[2] + dp[Math.max(i - 30, 0)], dp[i]); // 30-day  
          }  
          return dp[lastDay];  
      }  
  }  
```    
    
### 个人解读    
  读完题目后，就觉得像是背包问题。  
  数显回顾背包问题的应用场景关键词： 不断优化。  
    
  因为days数组最多365，所以可以创建全部365数组即使有额外开销。  
    
  感觉背包问题并不适用，因为本题目不是连续，导致优化不是连续的。  
  背包问题还是理解不够深，这种题目感觉用背包问题模板有点勉强了。（因为days不连续，处理空窗期消耗太多）  
    
  思路二：  
  DP+贪婪  
  问题一：是不是有优化越早用越好？不是，主要看区间内覆盖的天数，越多越好。  
  问题二：需要一个数据结构来存储当前的存储信息  
  问题三：有7用7，有30用30？好像不太可以。还是看覆盖多少天比较关键。  
    
  发现了，主要问题还是在于如何去描述7天有效期。。  
    
  总结：  
  1、受了背包问题影响太深，总想着一个个的去优化，然而其实可以一天天的优化。  
  2、这种优化与问题三的贪婪思想不是一回事。  
  3、因为天数是从小到大开始的，所以此时30天的信息都是前面那些已经解决的。  
  4、需要将本题目重点和背包问题相互比较。  
    
tags:    
  -  DP  
  -  动态规划  
  -  重点数学  
