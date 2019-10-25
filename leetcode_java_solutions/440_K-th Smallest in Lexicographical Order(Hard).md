### description    
  Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.  
    
  Note: 1 ≤ k ≤ n ≤ 109.  
    
  Example:  
    
  Input:  
  n: 13   k: 2  
    
  Output:  
  10  
    
  Explanation:  
  The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.  
### solution    
```    
  Runtime: 0 ms, faster than 100.00% of Java online submissions for K-th Smallest in Lexicographical Order.  
  Memory Usage: 32.8 MB, less than 100.00% of Java online submissions for K-th Smallest in Lexicographical Order.  
  class Solution {  
      public int findKthNumber(int n, int k) {  
          int curr = 1;  
          k = k - 1;  
          while (k > 0) {  
              int steps = calSteps(n, curr);  
              if (steps <= k) {  
                  curr += 1;  
                  k -= steps;  
              } else {  
                  curr *= 10;  
                  k -= 1;  
              }  
          }  
          return curr;  
      }  
      //use long in case of overflow  
      public int calSteps(int n, long n1) {  
          int steps = 0;  
          long n2 = n1 + 1;  
          while (n1 <= n) {  
              steps += Math.min(n + 1, n2) - n1;  
              // 如果是n+1比较小，那么就是被最大值限制住了  
              // 如果是n2比较小，则说明可以最大限度的数量 1,10,100...  (1, 1[0-9], 1[0-9][0-9])  
              n1 *= 10;  
              n2 *= 10;  
          }  
          return steps;  
      }  
  }  
    
```    
    
### 个人解读    
    
  思路一：  
  1、先获取1开始的数字，然后是2开始的数字  
    
  参考答案中的关于如何去求从cur开始到最大值n的有多少步，这个辅助函数太叼了。  
    
tags:    
  -  重点数学  
  -  数字逻辑  
