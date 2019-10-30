### description      
  Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.    
      
  Find the maximum coins you can collect by bursting the balloons wisely.    
      
  Note:    
      
  You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.    
  0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100    
  Example:    
      
  Input: [3,1,5,8]    
  Output: 167     
  Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []    
               coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167    
### solution      
```     
 Runtime: 5 ms, faster than 86.12% of Java online submissions for Burst Balloons.    
     Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Burst Balloons.    
  class Solution {    
     public int maxCoins(int[] iNums) {    
      int[] nums = new int[iNums.length + 2];    
      int n = 1;    
      for (int x : iNums) if (x > 0) nums[n++] = x;    
      nums[0] = nums[n++] = 1;    
      
      
      int[][] dp = new int[n][n];    
      for (int k = 2; k < n; ++k)    
          for (int left = 0; left < n - k; ++left) {    
              int right = left + k;    
              for (int i = left + 1; i < right; ++i)    
                  dp[left][right] = Math.max(dp[left][right],     
                  nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);    
          }    
      
      return dp[0][n - 1];    
  }    
  }    
```      
      
### 个人解读      
  思路一：    
  贪恋思想，每次中间从小到大射击就对了。    
  射击一个双向链表，然后和源数组对应起来。通过双向链表进行数据的删减。同时还需要一个排序功能来和源数组进行映射。    
  但是感觉太麻烦了，姑且尝试一下把。    
  ```    
  [9,76,64,21,97,60]    
  ```    
  贪婪算法有误，如果无脑从小到大的话，当21,64,76弹出以后，97就和9相乘了，这样不好。    
  如果继续这种贪婪思路的话，需要首尾特殊处理。但是好像也很难处理，如果保留了76，那么76就会成为新的病原体。    
      
  思路二：    
  单调栈    
  首先数组分为山峰和山谷，如果是山谷，就弄谷底。    
  如果是山峰呢？ 。。。4567654。。。从山腰开始，最后剩下474。    
      
  所以。。看到这里了，感觉不是要弄单调栈，而是一个A形栈？？    
  因为4567这种的顺序是先6后5，将来会不会有8所以7也要保留。    
  [1,10,20,10,1]这种的就要先把山峰刨去。贪婪算法又失败了    
  [2,3,4,3,2]    
  43322: 36 + 18 + 18 + 4 + 2 = 78    
  33422: 24 + 24 + 16 + 4 + 2 = 70    
  10,11,12,11,10    
      
  12 11 11: 1452+ 1210 + 1210  = 3872    
  11 11 12: 1320 + 1320 + 1200 = 3840    
      
  abcde:  b < d < c    
      
  cdb: bcd +bde + abe    
  bdc: abc + cde + ace    
  上减下： bc(d-a) + de(b - c) + ae(b - c)    
   山峰的权重：bc(d-a)  侧翼的权重(c-b)(a+d)e    
   为了让山峰尽可能的小，另    
       
   1 2 20 2 1：    
   20 2 2 : 80 + 4 + 4 = 88    
   2 2 20 : 40 + 40 + 20 = 100    
   这个贪婪算法宣告破产。    
       
   怀疑一件事情，是不是后面hard题目就没有那么多的数学方法了么。。。    
       
   瞄了一眼discuss区，发现里面好像已经达成共识了，基本都是O(N^3)的DP解法    
   然后回头一看，发现谷底的贪婪算法也是有问题的额。。。。    
       
   参考答案： Dp    
       
   选取一个i作为不爆的点，然后将区间分成两个状态无关的子区间，然后使用DP。    
   看完了那个DP解释，最疑惑的就是，就算用一个不爆的把区间分割了，但是选取的这个的左边是谁？右边是谁？发现根本没有人说清楚这件事情啊！！！那些人到底是真懂了还是想当然啊？！    
       
   经过思考后想清楚了。    
   首先关于DP的可行性，有以下几条原则：    
   1、在创建Dp[i][j]的时候，同时还要计算ij外(区间外)的值    
   2、最左的1和最右的1不能被爆破    
   3、dp[i][j]的含义：当只爆破区间内的气球时候的最大值。    
       
   因为无论是底层的小区间还是大区间进行比较时候，到考虑到了区间外的情况，所以使得DP的实现得以成功。    
       
   然后是关于这个逆向思维的思考了：    
       
tags:      
  -  重点数学    
  -  多维DP    
  -  逆向思维    
