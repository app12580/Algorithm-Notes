### description
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

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
        int min = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            int t = prices[i] - min;
            if(t > maxProfit) {
                maxProfit = t;
            }
            if(prices[i] < min) {
                min = prices[i];
            }
        }
        return maxProfit;
    }
}

被优化写法：
public int maxProfit(int[] prices) {
    int n = prices.length;
    if (n == 0) return 0;
    int soFarMin = prices[0];
    int max = 0;
    for (int i = 1; i < n; i++) {
        if (soFarMin > prices[i]) soFarMin = prices[i];
        else max = Math.max(max, prices[i] - soFarMin);
    }
    return max;
}
优化思路：考虑到替换min的时候不可能发生更赚钱的情况
```

### 个人解读
感觉明明是标准的Dp啊，不知道为什么会被CS-NOTES归类为贪婪算法啊

tags:
  - 数组
  - 动态规划
  - 贪心算法？
