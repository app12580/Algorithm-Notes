### description    
  Given an unsorted array of integers, find the length of the longest consecutive elements sequence.  
    
  Your algorithm should run in O(n) complexity.  
    
  Example:  
    
  Input: [100, 4, 200, 1, 3, 2]  
  Output: 4  
  Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.  
### solution    
```    
  //方法一： 疑似O(n2)，而且还用了递归。。。。  
  class Solution {  
     public int longestConsecutive(int[] nums) {  
      Map<Integer, Integer> countForNum = new HashMap<>();  
      for (int num : nums) {  
          countForNum.put(num, 1);  
      }  
      for (int num : nums) {  
          forward(countForNum, num);  
      }  
      return maxCount(countForNum);  
  }  
    
      // 0表示结束，而非实际意义  
      // 然后返回从num开始（包括num）的个数  
  private int forward(Map<Integer, Integer> countForNum, int num) {  
      if (!countForNum.containsKey(num)) {  
          return 0;  
      }  
      int cnt = countForNum.get(num);  
      if (cnt > 1) {  
          return cnt;  
      }  
      cnt = forward(countForNum, num + 1) + 1;  
      countForNum.put(num, cnt);  
      return cnt;  
  }  
    
  private int maxCount(Map<Integer, Integer> countForNum) {  
      int max = 0;  
      for (int num : countForNum.keySet()) {  
          max = Math.max(max, countForNum.get(num));  
      }  
      return max;  
  }  
  }  
  //整理方法一的步骤： 把数组全部遍历，然后存储每一个数字是否有；然后再从头遍历一遍，将原来的数值从1拓展从该数字开始的长度；最后，再从头遍历一遍，取出来长度最大的那个。  
    
  //方法二  
  public int longestConsecutive(int[] nums) {  
          if(nums.length == 0)  
              return 0;  
            
          Arrays.sort(nums);  
            
          int currentStreak = 1;  
          int longestStreak = 1;  
            
          for(int i = 1; i < nums.length; i++) {  
              if(nums[i] != nums[i - 1]) {  
                  if(nums[i] == nums[i - 1] + 1) {  
                      currentStreak += 1;  
                  } else {  
                      longestStreak = Math.max(longestStreak, currentStreak);  
                      currentStreak = 1;  
                  }  
              }  
          }  
          return Math.max(longestStreak, currentStreak);  
      }  
  // 先排序，排序以后，问题就容易很多了，从左往右遍历，然后一个存当前长度，一个存历史最长  
    
```    
    
### 个人解读    
  如果没有预排序的话，就只能强行暴力算法了，遍历的时候是乱序，存储的中间结果也只能乱序  
  如果可以预排序的话，难度一下子降低了好几个level。  
  然而最大的问题，就是不知道什么时候，可以取巧。将解法的第一步变成把题干的非排序变成已排序。  
    
tags:    
  -  数组  
  -  子序列问题  
  -  预排序操作  
