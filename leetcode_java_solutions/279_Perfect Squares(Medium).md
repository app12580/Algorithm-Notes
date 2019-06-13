### description    
  Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.  
    
  Example 1:  
    
  Input: n = 12  
  Output: 3   
  Explanation: 12 = 4 + 4 + 4.  
  Example 2:  
    
  Input: n = 13  
  Output: 2  
  Explanation: 13 = 4 + 9.  
    
  按平方数来分割整数  
### solution    
```    
  class Solution {  
   public int numSquares(int n) {  
          int[] dp = new int[n + 1];  
          dp[0] = 0;  
          List<Integer> list = getList(n);  
          for(int i = 1; i <= n; i++) {  
              int min = Integer.MAX_VALUE;  
              for(int j = 0; j < list.size(); j++) {  
                  int s = list.get(j);  
                  if(s > i) {  
                      break;  
                  }  
                  min = Math.min(min, dp[i - s] + 1);  
              }  
              dp[i] = min;  
          }  
          return dp[n];  
      }  
        
      private List<Integer> getList(int n) {  
          List<Integer> list = new ArrayList<>();  
          int i = 1;  
          int diff = 3;  
          while(i <= n) {  
              list.add(i);  
              i += diff;  
              diff += 2;  
          }  
          return list;  
      }  
  }  
```    
    
### 个人解读    
  想过回溯法，但是很棘手，而且效率有问题。  
  想到动态规划的两种使用方法，联想到使用数组。  
    
    
tags:    
  -  动态规划  
  -  动态规划使用方法  
