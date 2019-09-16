### description    
  There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:  
    
  Serve 100 ml of soup A and 0 ml of soup B  
  Serve 75 ml of soup A and 25 ml of soup B  
  Serve 50 ml of soup A and 50 ml of soup B  
  Serve 25 ml of soup A and 75 ml of soup B  
  When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as we can.  We stop once we no longer have some quantity of both types of soup.  
    
  Note that we do not have the operation where all 100 ml's of soup B are used first.    
    
  Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time.  
    
     
    
  Example:  
  Input: N = 50  
  Output: 0.625  
  Explanation:   
  If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time. For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.  
    
  Notes:  
    
  0 <= N <= 10^9.   
  Answers within 10^-6 of the true value will be accepted as correct.  
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Soup Servings.  
Memory Usage: 33.4 MB, less than 50.00% of Java online submissions for Soup Servings.  
  class Solution {  
       public double soupServings(int N) {  
           if(N > 4800) return 1;  
          int n = (N + 24) / 25;  
          double[][] dp = new double[n+1][n+1];  
          return dfs(dp, n, n);  
      }  
    
      private double dfs(double[][] dp, int a, int b) {  
          if(a <= 0 && b <= 0) return 0.5; //表示ab同时完成  
          if(a <= 0) return 1; //a先完成  
          if(b <= 0) return 0;  
          if(dp[a][b] > 0) return dp[a][b];  
          dp[a][b] = 0.25 * (dfs(dp, a - 4, b) +dfs(dp, a - 3, b-1) +dfs(dp, a - 2, b-2) +dfs(dp, a - 1, b-3));  
          return dp[a][b];  
      }  
  }  
```    
    
### 个人解读    
  感觉可以用到那个期望的和等于和的期望，赋予每一种情况一个期望。  
  如果没有好的数学方法，只能使用遍历+数组存储了。  
    
  因为AB的数据都影响结果，所以需要一个二维数组。  
    
  小tips：  
  N>4800时候，直接返回1.  
    
tags:    
  -  DFS  
  -  中间结果  
