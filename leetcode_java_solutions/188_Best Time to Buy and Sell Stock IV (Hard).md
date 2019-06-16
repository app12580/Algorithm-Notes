### description    
  Say you have an array for which the ith element is the price of a given stock on day i.  
    
  Design an algorithm to find the maximum profit. You may complete at most k transactions.  
    
  Note:  
  You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).  
    
  Example 1:  
    
  Input: [2,4,1], k = 2  
  Output: 2  
  Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.  
  Example 2:  
    
  Input: [3,2,6,5,0,3], k = 2  
  Output: 7  
  Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.  
               Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.  
                 
  只能进行 k 次的股票交易  
    
### solution    
```    
  public int maxProfit(int k, int[] prices) {  
      int n = prices.length;  
      if (k >= n / 2) {   // 这种情况下该问题退化为普通的股票交易问题  
          int maxProfit = 0;  
          for (int i = 1; i < n; i++) {  
              if (prices[i] > prices[i - 1]) {  
                  maxProfit += prices[i] - prices[i - 1];  
              }  
          }  
          return maxProfit;  
      }  
      int[][] maxProfit = new int[k + 1][n];      
      // 第一维度： 已完成的交易次数  
      // 第二维度： 数组进行到多远  
      for (int i = 1; i <= k; i++) {  
          int localMax = maxProfit[i - 1][0] - prices[0]; // 第i次交易买完的最大值  
          // 疑问一： localMax这种不加ij的变量，要如何去解释给一个好的定义  
          // 因为localMax在循环圈里面，所以可以根据循环圈此时的i和j做定义  
          for (int j = 1; j < n; j++) {  
              maxProfit[i][j] = Math.max(maxProfit[i][j - 1], prices[j] + localMax);  
              localMax = Math.max(localMax, maxProfit[i - 1][j] - prices[j]);  
          }  
      }  
      return maxProfit[k][n - 1];  
  }  
```    
    
### 个人解读    
  进行k次交易： 动态规划，可以二维数组，一个是k次交易，另一维度是index。  
  毕竟Hard，dp的状态转移方程还是要多扣细节。  
    
tags:    
  -  动态规划  
  -  二维  
