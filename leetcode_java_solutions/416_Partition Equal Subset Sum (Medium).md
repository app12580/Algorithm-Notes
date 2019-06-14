### description    
  Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.  
    
  Note:  
    
  Each of the array element will not exceed 100.  
  The array size will not exceed 200.  
     
    
  Example 1:  
    
  Input: [1, 5, 11, 5]  
    
  Output: true  
    
  Explanation: The array can be partitioned as [1, 5, 5] and [11].  
     
    
  Example 2:  
    
  Input: [1, 2, 3, 5]  
    
  Output: false  
    
  Explanation: The array cannot be partitioned into equal sum subsets.  
    
  划分数组为和相等的两部分  
### solution    
```    
  class Solution {  
      public boolean canPartition(int[] nums) {  
            int sum = 0;  
          for(int num: nums) {  
              sum += num;  
          }  
          if(sum % 2 != 0) {  
              return false;  
          }  
          sum /= 2;  
            
          boolean[] dp = new boolean[sum + 1];  
          dp[0] = true;  
          for(int num: nums) {  
              for(int j = sum; j >= 1; j--) {  
                   if(num > j) {  
                      break;  
                  }  
                  dp[j] = dp[j] || dp[j - num];  
              }  
          }  
          return dp[sum];  
      }  
  }  
```    
    
### 个人解读    
  如果不考虑背包问题的联想话，第一反应就是先排序，然后从小到大遍历，获取到所有的可能的值，最后返回集合中是否有sum/2，但是这样效率太差，复杂度不止n^2了而是2^n了。  
  背包问题思路：   
    在看CYC的参考答案时候，突然意识到，背包问题的本质：以一维数组的方式，通过某种优化条件，将原本2^n的问题，转化成n^M的问题。  
    //2^n的意思是指每个n有或者没有，有2^n种情况  
    //n * M表示：外层循环是0~n遍历每一个元素，内层循环是M~0表示总价值从高的到低的。  
    
    
tags:    
  -  动态规划    
  -  背包问题    
    
