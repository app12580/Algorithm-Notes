### description    
  Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:  
    
  Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).  
  Paste: You can paste the characters which are copied last time.  
     
    
  Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.  
    
  Example 1:  
    
  Input: 3  
  Output: 3  
  Explanation:  
  Intitally, we have one character 'A'.  
  In step 1, we use Copy All operation.  
  In step 2, we use Paste operation to get 'AA'.  
  In step 3, we use Paste operation to get 'AAA'.  
    
  要么复制全部要么粘贴，求最少操作次数。  
    
### solution    
```    
 // 方法一： 数学法 因式分解  
   
 Runtime: 0 ms, faster than 100.00% of Java online submissions for 2 Keys Keyboard.  
 Memory Usage: 33.2 MB, less than 34.49% of Java online submissions for 2 Keys Keyboard.  
   
     class Solution {  
         public int minSteps(int n) {  
           int res = 0;  
             while(n > 1) {  
                 for(int j = 2; j <= n; j++) {  
                     if(n % j == 0) {  
                         res += j;  
                         n /= j;  
                         break;  
                     }  
                 }  
             }  
             return res;  
         }  
     }  
    
  //方法二： DP法  
  Runtime: 2 ms, faster than 67.00% of Java online submissions for 2 Keys Keyboard.  
  Memory Usage: 33.4 MB, less than 34.49% of Java online submissions for 2 Keys Keyboard.  
    
  class Solution {  
      public int minSteps(int n) {  
          int[] dp = new int[n + 1];  
          int sqrt = (int) Math.sqrt(n);  
          for(int i = 2; i <= n; i++) {  
              dp[i] = i;  
              for(int j = 2; j <= sqrt; j++) {  
                  if(i % j == 0) {  
                      dp[i] = dp[j] + dp[i/j];  
                      break;  
                  }  
              }  
          }  
          return dp[n];  
      }  
  }  
```    
    
### 个人解读    
  思路一： 动态规划，用一个dp[]存储每一个数字的最少操作次数。但是这样很麻烦，而且每一次都需要遍历一遍。  
  dp[i] = dp[j] + dp[i/j]  
    
  思路二：数学法  
  联想到因数分解： 18 = 2 * 3 * 3  
  所以18的结果： 2+3+3 = 8.  
    
tags:    
  -    
