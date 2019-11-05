### description    
  We are given non-negative integers nums[i] which are written on a chalkboard.  Alice and Bob take turns erasing exactly one number from the chalkboard, with Alice starting first.  If erasing a number causes the bitwise XOR of all the elements of the chalkboard to become 0, then that player loses.  (Also, we'll say the bitwise XOR of one element is that element itself, and the bitwise XOR of no elements is 0.)  
    
  Also, if any player starts their turn with the bitwise XOR of all the elements of the chalkboard equal to 0, then that player wins.  
    
  Return True if and only if Alice wins the game, assuming both players play optimally.  
    
  Example:  
  Input: nums = [1, 1, 2]  
  Output: false  
  Explanation:   
  Alice has two choices: erase 1 or erase 2.   
  If she erases 1, the nums array becomes [1, 2]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 2 = 3. Now Bob can remove any element he wants, because Alice will be the one to erase the last element and she will lose.   
  If Alice erases 2 first, now nums becomes [1, 1]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 1 = 0. Alice will lose.  
    
  Notes:  
    
  1 <= N <= 1000.   
  0 <= nums[i] <= 2^16.  
### solution    
```    
// 方法一： 数学规律  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Chalkboard XOR Game.  
Memory Usage: 40.5 MB, less than 100.00% of Java online submissions for Chalkboard XOR Game.  
  
  class Solution {  
      public boolean xorGame(int[] nums) {  
        int x = 0;  
        for (int v : nums) x ^= v;  
        return x == 0 || nums.length % 2 == 0;  
      }  
  }   
    
    
```    
    
### 个人解读    
  题目：  
  博弈，双方轮流擦除一个数字，如果某位玩家擦除之后，剩下的数字异或为0,或者擦除的就是最后一个数字，那么该玩家输掉游戏。  
    
  首先，需要证明偶数必胜，然后，奇数的情况，除非先手一击制胜不然就是输的局面。  
    
  https://leetcode-cn.com/problems/chalkboard-xor-game/solution/hei-ban-yi-huo-you-xi-by-leetcode/  
    
  对于所有数的异或S，取走一个数k的剩下所有数字异或，等价于 S ^ k。因为 任意一个数字A ^ k ^ k = A  
  所以少了和多了是一样的。  
    
    
tags:    
  -  重点数学  
  -  数字逻辑  
  -  机器博弈  
