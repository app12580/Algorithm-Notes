### description    
  Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.  
    
  You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)  
    
  Return the maximum profit you can make.  
    
  Example 1:  
  Input: prices = [1, 3, 2, 8, 4, 9], fee = 2  
  Output: 8  
  Explanation: The maximum profit can be achieved by:  
  Buying at prices[0] = 1  
  Selling at prices[3] = 8  
  Buying at prices[4] = 4  
  Selling at prices[5] = 9  
  The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.  
  Note:  
    
  0 < prices.length <= 50000.  
  0 < prices[i] < 50000.  
  0 <= fee < 50000.  
    
  需要交易费用的股票交易  
  每买卖一次，都要支付一定的费用。  
### solution    
```    
  //方法一： 无脑DP，把所有的状态全部列出来，并且计算步骤会发生冗余  
  public int maxProfit(int[] prices, int fee) {  
          if(prices == null || prices.length == 0) {  
              return 0;  
          }  
          int len = prices.length;  
          int[] buys = new int[len];  
          int[] afterBuy = new int[len];  
          int[] sells = new int[len];  
          int[] afterSell = new int[len];  
          buys[0] = afterBuy[0] = -prices[0];  
          for(int i = 1; i < len; i++) {  
              buys[i] = Math.max(sells[i-1], afterSell[i-1]) - prices[i];  
              afterBuy[i] = Math.max(buys[i-1], afterBuy[i-1]);  
              sells[i] = afterBuy[i] + prices[i] - fee;  
              afterSell[i] = Math.max(sells[i], afterSell[i-1]);  
          }  
    
          return Math.max(sells[len - 1], afterSell[len - 1]);  
      }  
        
      //方法二： 本题没有对买卖进行限制，可以用贪心算法解决  
      public int maxProfit(int[] prices, int fee) {  
              long noGoods = 0;       //手里没股票的最大收益  
              long hasGoods = Integer.MIN_VALUE;  //手里有股票  
              for (int price : prices) {  
                  long old = noGoods;  
                  noGoods = Math.max(noGoods, hasGoods + price - fee);   //表示已经完成交易  
                  hasGoods = Math.max(hasGoods, old - price); //  表示当前如果是after买的话，最大收益  
              }  
              return (int)noGoods;  
          }  
```    
    
### 个人解读    
  方法一  
  无脑DP，把所有状态分列出来一个数组中，这样算完效率低并且冗余  
    
  方法二  
  贪婪算法  
  错误思路一：因为手续的问题，不能赚一笔就开启下一笔，而需要利润最大时，才能再进行下一笔买卖。  
    
  整理思路：主要问题在于什么情况下是分两次交易，什么情况下是一次交易。  
  提高格局：类似于并查集一样，把目光从买卖等CD这样的元素状态提高到整体收益的状态，本题目因为没有CD，所以只有两种状态，手里有股票和手里没有股票的。这样最后返回手里没有股票的即可。  
    
    
tags:    
  -  动态规划  
  -  解法优化  
