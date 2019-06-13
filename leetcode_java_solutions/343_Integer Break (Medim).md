### description    
    
  Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.  
    
  Example 1:  
    
  Input: 2  
  Output: 1  
  Explanation: 2 = 1 + 1, 1 × 1 = 1.  
  Example 2:  
    
  Input: 10  
  Output: 36  
  Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.  
  Note: You may assume that n is not less than 2 and not larger than 58.  
    
  分割整数的最大乘积  
### solution    
```    
// 方法一： 数学法  
  class Solution {  
     public int integerBreak(int n) {  
          if(n <= 3) {  
              return n - 1;  
          }  
          int res = 1;  
          if(n % 3 == 2) {  
              return 2 * helper(n - 2);  
          } else if(n %3 == 0) {  
              return helper(n);  
          } else {  
              return 4 * helper(n - 4);  
          }  
      }  
        
      public int helper(int n) {  
          int res = 1;  
          while(n > 0) {  
              n -= 3;  
              res *= 3;  
          }  
          return res;  
            
      }  
  }  
    
  // 方法二： dp法，类似于穷举法  
  class Solution {  
     public int integerBreak(int n) {  
      int[] dp = new int[n + 1];  
      dp[1] = 1;  
      for (int i = 2; i <= n; i++) {  
          for (int j = 1; j <= i - 1; j++) {  
              dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));          
              // dp[i - j] 为至少两个数字； (i - j)为只有一个数字  
          }  
      }  
      return dp[n];  
  }  
  }  
```    
    
### 个人解读    
  总感觉用穷举法不太靠谱。先写几个找找规律。  
  1: 1   0          0  
  2: 1 * 1          1  
  3: 1 * 2          2  
  4: 2 * 2          4  
  5: 2 * 3          6  
  6: 3 * 3          9  
  7: (223)          12  
  8: (332)          18  
  9: (333)          27  
  10: (334)          36  
  11: (3332)          54  
    
  然后发现数学规律： 所以说能3则3。  
    
tags:    
  -  数组  
  -  动态规划  
  -  数学  
