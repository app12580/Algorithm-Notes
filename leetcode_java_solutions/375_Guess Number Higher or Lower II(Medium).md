### description    
  We are playing the Guess Game. The game is as follows:  
    
  I pick a number from 1 to n. You have to guess which number I picked.  
    
  Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.  
    
  However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.  
    
  Example:  
    
  n = 10, I pick 8.  
    
  First round:  You guess 5, I tell you that it's higher. You pay $5.  
  Second round: You guess 7, I tell you that it's higher. You pay $7.  
  Third round:  You guess 9, I tell you that it's lower. You pay $9.  
    
  Game over. 8 is the number I picked.  
    
  You end up paying $5 + $7 + $9 = $21.  
  Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.  
### solution    
```    
  
Runtime: 6 ms, faster than 65.25% of Java online submissions for Guess Number Higher or Lower II.  
Memory Usage: 33.2 MB, less than 16.67% of Java online submissions for Guess Number Higher or Lower II.  
  
  
  class Solution {  
      public int getMoneyAmount(int n) {  
          int[][] dp = new int[n + 1][n + 1];  
           return helper(1, n, dp);  
      }  
    
      private int helper(int l, int h, int[][] dp) {  
          if(l >= h) return 0;  
          if(dp[l][h] > 0) return dp[l][h];  
          int res = Integer.MAX_VALUE;  
          int temp = 0;  
          for(int i = l; i <= h; i++) {  
              temp = Math.max(helper(l, i - 1, dp), helper(i + 1, h, dp)) +i;  
              res = Math.min(res, temp);  
    
          }  
          dp[l][h] = res;  
          return res;  
      }  
  }  
```    
    
### 个人解读    
思路一：首先坚定二分法绝不动摇，然后每次都要做最坏打算，猜的次数要最多，并且每次都是贵的那一边。  
然而这样并不行，所以必须要有一种把大的数字要避免掉。  
思路二：对于l和h，猜测的m要满足条件sum（l, m -1）与sum(m+1, h)要尽可能的接近。  
  对于n=6， 如果上来猜1234的话，无论如何，是避免不了56的抉择，所以5是必须要猜的。  
    
思路三：对于任意一个数n，n-3,n-2,n-1,n，如果第一次猜n-3，那么之后只需要n-1即可判断这四个点。  
所以有没有可能是n-3 + Math.max(n-1, dp[n-4])?  
这样也不行。。。  
  
最后翻了翻答案：  
具体是这样的，在1-n个数里面，我们任意猜一个数(设为i)，保证获胜所花的钱应该为 i + max(w(1 ,i-1), w(i+1 ,n))，这里w(x,y))表示猜范围在(x,y)的数保证能赢应花的钱，则我们依次遍历 1-n作为猜的数，求出其中的最小值即为答案，即最小的最大值问题  
原文链接：https://blog.csdn.net/adfsss/article/details/51951658  
  
竟然没有高级的数学解法么。。。。。  
  
```  
超时解法：  
class Solution {  
   public int getMoneyAmount(int n) {  
         return helper(1, n);  
    }  
  
    private int helper(int l, int h) {  
         int res = Integer.MAX_VALUE;  
        if(l >= h) {  
            return 0;  
        } else if(h == l+1) {  
            return l;  
        } else if(h == l + 2) {  
            return l + 1;  
        } else {  
           for(int i = l; i <= h; i++) {  
                int temp = i + Math.max(helper(l, i - 1), helper(i + 1, h));  
                res = Math.min(res, temp);  
            }  
            return res;  
        }  
    }  
}  
```  
  
超时了，因为出现了dp的指数扩张。  
    
总结：  
1、不是所有的题目都可以用数学方法解释的  
2、dp从前往后和从后往前的可读性差别很大。    
    
tags:    
  -  DP  
  -  数学  
