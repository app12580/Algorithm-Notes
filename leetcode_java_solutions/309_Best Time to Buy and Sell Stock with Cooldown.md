### description    
  Say you have an array for which the ith element is the price of a given stock on day i.  
    
  Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:  
    
  You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).  
  After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)  
  Example:  
    
  Input: [1,2,3,0,2]  
  Output: 3   
  Explanation: transactions = [buy, sell, cooldown, buy, sell]  
    
  股票买卖，无限次交易，卖了之后需要隔一天。  
### solution    
```    
  class Solution {  
      public int maxProfit(int[] prices) {  
            //买->s1->卖->s2->买  
          //s1和s2可能不止一天  
          if(prices == null || prices.length == 0) return 0;  
          int len = prices.length;  
          int[] buys = new int[len];  
          int[] sells = new int[len];  
          int[] s1 = new int[len];  
          int[] s2 = new int[len];  
          buys[0] = -prices[0];  
          s1[0] = -prices[0];  
    
          for(int i = 1; i < len; i++) {  
              buys[i] = s2[i - 1] - prices[i];      // 因为1天的cd，所以s2必须是i-1的时候  
              s1[i] = Math.max(buys[i-1], s1[i-1]);  
              sells[i] = s1[i] + prices[i];  //因为卖不用等，所以si可以直接i  
              s2[i] = Math.max(s2[i-1], sells[i-1]);  
          }  
          return Math.max(sells[len - 1], s2[len - 1]);  
      }  
  }  
```    
    
### 个人解读    
  买->卖->冷却一天->买  
  弄4个数组，存放各种种状态，buys[i]，表示第i天的最后操作是买操作。  
  优化空间的话，可以把数组变成单个变量。  
    
tags:    
  -  动态规划  
  -  多状态分类  
