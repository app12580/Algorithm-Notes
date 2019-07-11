### description    
  Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?  
    
  Example:  
    
  Input: 3  
  Output: 5  
  Explanation:  
  Given n = 3, there are a total of 5 unique BST's:  
    
     1         3     3      2      1  
      \       /     /      / \      \  
       3     2     1      1   3      2  
      /     /       \                 \  
     2     1         2                 3  
### solution    
```    
  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Binary Search Trees.  
Memory Usage: 32.9 MB, less than 5.20% of Java online submissions for Unique Binary Search Trees.  
  
  
  class Solution {  
      public int numTrees(int n) {  
          if(n <= 2) {  
              return n;  
          }  
          int[] dp = new int[n + 1];  
          dp[0] = 1;  
          dp[1] = 1;  
          dp[2] = 2;  
          for(int i = 3; i <= n; i++) {  
              for(int j = 0; j <= i - 1; j++) {  
                 dp[i] += dp[j] * dp[i-1-j];  
    
              }  
          }  
          return dp[n];  
      }  
  }  
```    
    
### 个人解读    
  最蠢的方式是一点点遍历出来，一定会有一个数学规律的。  
  疑似和递归有关。  
  1： 1  
  2： 2  
  3： 5  
    
  dp[n] = 2 * dp[n-1] + ?  
  根据左右子树去参考  
  然后的思考：先减去根节点，然后   
  3： (2,0)+(1,1)+(0,2)  
  4： (3,0)+(2,1)+(1,2)+(0,3) : 5 + 2 + 2 + 5 = 14    
  需要用乘法 dp[j] * dp[i-1-j]，需要注意dp[0]要是1。  
    
tags:    
  -  BST  
  -  递归  
