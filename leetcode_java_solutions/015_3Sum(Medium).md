### description    
  Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.  
    
  Note:  
    
  The solution set must not contain duplicate triplets.  
    
  Example:  
    
  Given array nums = [-1, 0, 1, 2, -1, -4],  
    
  A solution set is:  
  [  
    [-1, 0, 1],  
    [-1, -1, 2]  
  ]  
### solution    
```    
  class Solution {  
      public List<List<Integer>> threeSum(int[] num) {  
          Arrays.sort(num);  
          List<List<Integer>> res = new LinkedList<>();  
          for (int i = 0; i < num.length-2; i++) {        //只拿出来一个数字，剩下的变成twosum  
              if (i == 0 || (i > 0 && num[i] != num[i-1])) {  // 遇到相同数字，只计算第一个的， 但是后续也需要排除的  
                  int lo = i+1, hi = num.length-1, sum = 0 - num[i];  
                  while (lo < hi) {           //类似于two Sum的逻辑  
                      if (num[lo] + num[hi] == sum) {  
                          res.add(Arrays.asList(num[i], num[lo], num[hi]));  
                          while (lo < hi && num[lo] == num[lo+1]) lo++;       //把lo变成最后一个相等数字的索引  
                          while (lo < hi && num[hi] == num[hi-1]) hi--;  
                          lo++; hi--;  
                      } else if (num[lo] + num[hi] < sum) lo++;  
                      else hi--;  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  两种思路： 上来直接dfs； 先预处理排序，然后再想办法。  
  dfs是N^3，如果可以先排序，肯定是排序更好一些。  
  联想twosum的情景。  
    
  那就只能先排序，然后N^2次遍历了。  
    
  两点：  
  1、先只拿出来一个数字，剩下的变成twoSum  
  2、关于重复数字的处理：   
    比如： 111111  
    循环外只计算一次：第一个1的位置  
    然后没次成功以后，在内部把1的索引平移到最后一个  
    
tags:    
  -  双指针升级： 三指针  
