### description    
  Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.  
    
  Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.  
    
  Example 1:  
  Input: [1, 5, 2]  
  Output: False  
  Explanation: Initially, player 1 can choose between 1 and 2.   
  If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).   
  So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.   
  Hence, player 1 will never be the winner and you need to return False.  
  Example 2:  
  Input: [1, 5, 233, 7]  
  Output: True  
  Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.  
  Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.  
  Note:  
  1 <= length of the array <= 20.  
  Any scores in the given array are non-negative integers and will not exceed 10,000,000.  
  If the scores of both players are equal, then player 1 is still the winner.  
### solution    
```    
  
// 方法一： 通过可获取最大值计算  
Runtime: 31 ms, faster than 20.42% of Java online submissions for Predict the Winner.  
Memory Usage: 34 MB, less than 100.00% of Java online submissions for Predict the Winner.  
  
  class Solution {  
       public boolean PredictTheWinner(int[] nums) {  
          return  maxOver(nums, 0, nums.length - 1) >= 0;  
      }  
        
      public int maxOver(int[] nums, int left, int right) {  
          if(left == right) {  
              return nums[left];  
          }  
          int max = nums[left] - maxOver(nums, left + 1, right);  
          max = Math.max(max, nums[right] - maxOver(nums, left, right - 1));  
          return max;  
      }  
  }  
    
  //方法二： 优化  
  class Solution {  
      public boolean PredictTheWinner(int[] nums) {  
          int sum = 0;  
          for (int n : nums) {  
              sum += n;  
          }  
            
          int[][] memo = new int[nums.length][nums.length];  
          int score1 = play(nums, 0, nums.length-1, sum, memo);  
          int score2 = sum - score1;  
          return (score1 >= score2);  
      }  
        
      private int play(int[] nums, int s, int e, int sum, int[][] memo) {  
          if (s == e) {  
              return nums[s];  
          } else if (e -s == 1) {  
              return Math.max(nums[e], nums[s]);  
          } else if (memo[s][e] > 0) {  
              return memo[s][e];  
          } else {  
              int sum1 = sum - play(nums, s+1, e, sum-nums[s], memo);  
              int sum2 = sum - play(nums, s, e-1, sum-nums[e], memo);  
              memo[s][e] = Math.max(sum1, sum2);  
              return memo[s][e];  
          }  
      }  
  }  
```    
    
### 个人解读    
  类似于Can I Win[464](464_Can%20I%20Win(Medium).md)  
  采用DFS+回溯，然而本题可以只用两个变量left和right表示进度，但还是有区别，canIwin那道题只需要固定used的话，目标指数也是固定的，所以只需要固定used，就能保证唯一的结果。然而本题目的话，还有双方玩家累计分数。  
  本题不可能对于每一种情况的分值都进行保存，那样会导致复杂度爆炸。  
    
  优化思路： 整体思路类似，获取最大值，只是多了一个二维数组用来保存已经遍历过的结果。但是由于中间结果需要同时给两个玩家复用，所以就方法的返回值就不能是差值，而是单个的最大值。  
    
tags:    
  -  DFS  
  -  数学  
    
