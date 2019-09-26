### description    
  Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  
    
  The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.  
    
  Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.  
    
  Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.  
    
     
    
  Example 1:  
    
  Input: [5,3,4,5]  
  Output: true  
  Explanation:   
  Alex starts first, and can only take the first 5 or the last 5.  
  Say he takes the first 5, so that the row becomes [3, 4, 5].  
  If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.  
  If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.  
  This demonstrated that taking the first 5 was a winning move for Alex, so we return true.  
     
    
  Note:  
    
  2 <= piles.length <= 500  
  piles.length is even.  
  1 <= piles[i] <= 500  
  sum(piles) is odd.  
### solution    
```    
Runtime: 7 ms, faster than 31.04% of Java online submissions for Stone Game.  
Memory Usage: 36.3 MB, less than 100.00% of Java online submissions for Stone Game.  
  
  class Solution {  
      public boolean stoneGame(int[] nums) {  
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
  两边抓石头，这个题目好像做过  
  与[486](486_Predict%20the%20Winner(Medium).md)一模一样的  
    
tags:    
  -  数学  
  -  游戏过程模拟  
