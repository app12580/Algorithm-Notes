### description    
  You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.  
    
  Find out how many ways to assign symbols to make sum of integers equal to target S.  
    
  Example 1:  
  Input: nums is [1, 1, 1, 1, 1], S is 3.   
  Output: 5  
  Explanation:   
    
  -1+1+1+1+1 = 3  
  +1-1+1+1+1 = 3  
  +1+1-1+1+1 = 3  
  +1+1+1-1+1 = 3  
  +1+1+1+1-1 = 3  
    
  There are 5 ways to assign symbols to make the sum of nums be target 3.  
  Note:  
  The length of the given array is positive and will not exceed 20.  
  The sum of elements in the given array will not exceed 1000.  
  Your output answer is guaranteed to be fitted in a 32-bit integer.  
    
  改变一组数的正负号使得它们的和为一给定数  
### solution    
```    
  class Solution {  
      public int findTargetSumWays(int[] nums, int S) {  
      int sum = computeArraySum(nums);  
      if (sum < S || (sum + S) % 2 == 1) {  
          return 0;  
      }  
      int W = (sum + S) / 2;  
      int[] dp = new int[W + 1];  
      dp[0] = 1;  
      for (int num : nums) {  
          for (int i = W; i >= num; i--) {  
              dp[i] = dp[i] + dp[i - num];  
          }  
      }  
      return dp[W];  
  }  
    
  private int computeArraySum(int[] nums) {  
      int sum = 0;  
      for (int num : nums) {  
          sum += num;  
      }  
      return sum;  
  }  
  }  
  
  
      //方法二 DFS
      public int findTargetSumWays(int[] nums, int S) {
          return findTargetSumWays(nums, 0, S);
      }
      
      // 这里的start可以理解成index，每次index从0推进到start结束， 需要明确不进return的条件：[0, start)，index等于start的时候就会return  
      private int findTargetSumWays(int[] nums, int start, int S) {
          if (start == nums.length) {
              return S == 0 ? 1 : 0;
          }
          return findTargetSumWays(nums, start + 1, S + nums[start])
                  + findTargetSumWays(nums, start + 1, S - nums[start]);
      }
```    
    
### 个人解读    
  有n个元素，判断哪些需要处理，哪些不需要处理，属于背包问题，判断定值的那个位置是否可以到达。  
  因为sum可能很大，并且target可能是负数，所以dp的长度确定比较麻烦。  
  然后看了参考答案，菜发现问题可以进行数学方面的问题转化。  
  ```  
  该问题可以转换为 Subset Sum 问题，从而使用 0-1 背包的方法来求解。  
    
  可以将这组数看成两部分，P 和 N，其中 P 使用正号，N 使用负号，有以下推导：  
    
                    sum(P) - sum(N) = target  
  sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)  
                         2 * sum(P) = target + sum(nums)  
  因此只要找到一个子集，令它们都取正号，并且和等于 (target + sum(nums))/2，就证明存在解。  
  ```  
  注意两点错误情况： (target + sum(nums))/2不为偶数；sum小于target。  
    
    
tags:    
  -  DP  
  -  背包问题  
