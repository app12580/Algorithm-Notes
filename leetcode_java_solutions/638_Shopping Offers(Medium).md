### description    
  In LeetCode Store, there are some kinds of items to sell. Each item has a price.  
    
  However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.  
    
  You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.  
    
  Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.  
    
  You could use any of special offers as many times as you want.  
    
  Example 1:  
  Input: [2,5], [[3,0,5],[1,2,10]], [3,2]  
  Output: 14  
  Explanation:   
  There are two kinds of items, A and B. Their prices are $2 and $5 respectively.   
  In special offer 1, you can pay $5 for 3A and 0B  
  In special offer 2, you can pay $10 for 1A and 2B.   
  You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.  
  Example 2:  
  Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]  
  Output: 11  
  Explanation:   
  The price of A is $2, and $3 for B, $4 for C.   
  You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.   
  You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.   
  You cannot add more items, though only $9 for 2A ,2B and 1C.  
  Note:  
  1、There are at most 6 kinds of items, 100 special offers.  
  2、For each item, you need to buy at most 6 of them.  
  3、You are not allowed to buy more items than you want, even if that would lower the overall price.  
### solution    
```    
  
Runtime: 3 ms, faster than 97.10% of Java online submissions for Shopping Offers.  
Memory Usage: 40.4 MB, less than 97.50% of Java online submissions for Shopping Offers.  
  
  
  class Solution {  
     public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {  
          return helper(price, special, needs, 0);  
      }  
    
      private int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos) {  
          int local_min = directPurchase(price, needs);  
          for (int i = pos; i < special.size(); i++) {  
              List<Integer> offer = special.get(i);  
              List<Integer> temp = new ArrayList<Integer>();  
              for (int j= 0; j < needs.size(); j++) {  
                  if (needs.get(j) < offer.get(j)) { // check if the current offer is valid  
                      temp =  null;  
                      break;  
                  }  
                  temp.add(needs.get(j) - offer.get(j));  
              }  
    
              if (temp != null) { // use the current offer and try next  
                  local_min = Math.min(local_min, offer.get(offer.size() - 1) + helper(price, special, temp, i));  
              }  
          }  
    
          return  local_min;  
      }  
    
      private int directPurchase(List<Integer> price, List<Integer> needs) {  
          int total = 0;  
          for (int i = 0; i < needs.size(); i++) {  
              total += price.get(i) * needs.get(i);  
          }  
    
          return total;  
      }  
  }  
```    
    
### 个人解读    
  根据题目描述，这种题目决策，是否有，很明显的是背包问题。  
  第二条：每种商品只能买6个限定了每次的优化次数  
  第三条：限定了最终的return内容，即使更优惠也不用买，可以说是简化了问题。  
    
  如果用背包问题的话，第一个难点？如何解决N维的动态规划，该怎么定义好？这个问题起名为，动态维度数组？  
    
  虽然Dp比较简单，但是还是DFS更直接一些。  
    
  这题目有点问题啊，随便一个input就expect报错了，而且本题目解法也没有和最多买6个的限制。  
  ```  
  input:  
  [2,5]  
  [[3,0,5],[1,2,10]]  
  [32,2]  
  ```  
    
  然后需要注意的是DFS的设计，首先因为是最值问题，不需要回溯。  
  另外，本解法没有使用全局变量，而是在每次return的时候，保留最值。  
    
    
tags:    
  -  动态维度数组  
  -  DFS  
