### description    
  Say you have an array for which the ith element is the price of a given stock on day i.  
    
  Design an algorithm to find the maximum profit. You may complete at most two transactions.  
    
  Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).  
    
  Example 1:  
    
  Input: [3,3,5,0,0,3,1,4]  
  Output: 6  
  Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.  
               Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.  
  Example 2:  
    
  Input: [1,2,3,4,5]  
  Output: 4  
  Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.  
               Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are  
               engaging multiple transactions at the same time. You must sell before buying again.  
  Example 3:  
    
  Input: [7,6,4,3,1]  
  Output: 0  
  Explanation: In this case, no transaction is done, i.e. max profit = 0.  
    
  只能进行两次的股票交易  
### solution    
```    
  // 方法一： 分成四种状态  
  // 写完都没有想过竟然真的能一次通过。。。。  
  class Solution {  
      public int maxProfit(int[] prices) {  
           if(prices == null || prices.length == 0) {  
              return 0;  
          }  
          int hasBuy1 = Integer.MIN_VALUE;    //买了第一个的最大收益  
          int hasSold1 = 0;  
          int hasBuy2 = Integer.MIN_VALUE;  
          int hasSold2 = 0;  
    
          for(int price: prices) {  
              int t1 = hasBuy1;  
              int t2 = hasSold1;  
              int t3 = hasBuy2;  
              hasBuy1 = Math.max(hasBuy1, -price);        
              hasSold1 = Math.max(hasSold1, t1 + price);  //如果不用临时值，就会出现当天买当天卖的情况，从结果来说，并不影响最终结果  
              hasBuy2 = Math.max(hasBuy2, t2 - price);      //这一步有可能第一步没有交易的情况  
              hasSold2 = Math.max(hasSold2, t3 + price);  
          }  
          return hasSold2;  
      }  
  }  
    
  // 方法二: 不用临时值  这样计算也是OK的  
  class Solution {  
      public int maxProfit(int[] prices) {  
           if(prices == null || prices.length == 0) {  
              return 0;  
          }  
          int hasBuy1 = Integer.MIN_VALUE;    //买了第一个的最大收益  
          int hasSold1 = 0;  
          int hasBuy2 = Integer.MIN_VALUE;  
          int hasSold2 = 0;  
    
          for(int price: prices) {  
              hasBuy1 = Math.max(hasBuy1, -price);  
              hasSold1 = Math.max(hasSold1, hasBuy1 + price);  
              hasBuy2 = Math.max(hasBuy2, hasSold1 - price);  
              hasSold2 = Math.max(hasSold2, hasBuy2 + price);  
          }  
          return hasSold2;  
      }  
  }  
    
```    
    
### 个人解读    
  仿照[714](714_Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee.md)的解法，提升格局  
  可以采用临时值，也可以忽略临时支。  
    
tags:    
  -  动态规划  
  -  解法优化  
