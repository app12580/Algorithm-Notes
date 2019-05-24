### description  
Say you have an array for which the ith element is the price of a given stock on day i.  
  
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).  
  
Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).  
  
Example 1:  
  
Input: [7,1,5,3,6,4]  
Output: 7  
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.  
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.  
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
### solution  
```  
class Solution {  
    public int maxProfit(int[] prices) {  
         if(prices == null || prices.length == 0) {  
            return 0;  
        }  
        int maxProfit = 0;  
        for(int i = 1; i < prices.length; i++) {  
            if(prices[i] > prices[i-1]) {  
                maxProfit += prices[i] - prices[i-1];  
                  
            }  
        }  
        return maxProfit;  
    }  
}  
```  
  
### 个人解读  
对于每一笔交易[a, b]，如果ab不相邻，即[a,c,b]，如果c < a，那么ab不如cb赚钱，如果c>b，那么不如ac赚钱，如果a<c<b，那么不妨分成ac和cb两次  
综上，所以只要相邻的去做生意，肯定是获得最大收益的  
  
tags:  
  - 数组  
  - 贪心算法  
