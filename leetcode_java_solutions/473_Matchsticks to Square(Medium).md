### description    
  Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.  
    
  Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.  
    
  Example 1:  
  Input: [1,1,2,2,2]  
  Output: true  
    
  Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.  
  Example 2:  
  Input: [3,3,3,3,4]  
  Output: false  
    
  Explanation: You cannot find a way to form a square with all the matchsticks.  
  Note:  
  The length sum of the given matchsticks is in the range of 0 to 10^9.  
  The length of the given matchstick array will not exceed 15.  
### solution    
```    
  
// 方法一： DFS  
 为啥效率这么差？？  
Runtime: 762 ms, faster than 7.14% of Java online submissions for Matchsticks to Square.  
Memory Usage: 34.7 MB, less than 66.67% of Java online submissions for Matchsticks to Square.  
  
  class Solution {  
      public boolean makesquare(int[] nums) {  
          if(nums == null || nums.length < 4) {  
              return false;  
          }  
          int sum = 0;  
          for(int num: nums) {  
              sum += num;  
          }  
          if(sum % 4 != 0) {  
              return false;  
          }  
          int target = sum / 4;  
          return dfs(nums, target, new int[4], 0);  
      }  
    
      private boolean dfs(int[] nums, int target, int[] arr, int index) {  
          if(index == nums.length) {  
              return true;  
          }  
          int cur = nums[index];  
          for(int i = 0; i < arr.length; i++) {  
              if(arr[i] + cur <= target) {  
                  arr[i] += cur;  
                  if(dfs(nums, target, arr, index + 1)) {  
                      return true;  
                  }  
                  arr[i] -= cur;  
              }  
          }  
          return false;  
      }  
  }  
    
    
  // 方法二： 只是加了个排序  
  Runtime: 16 ms, faster than 64.62% of Java online submissions for Matchsticks to Square.  
  Memory Usage: 34.4 MB, less than 66.67% of Java online submissions for Matchsticks to Square.  
  class Solution {  
        
      public boolean makesquare(int[] nums) {  
          if(nums == null || nums.length < 4) {  
              return false;  
          }  
          int sum = 0;  
          for(int num: nums) {  
              sum += num;  
          }  
          if(sum % 4 != 0) {  
              return false;  
          }  
          int target = sum / 4;  
          Arrays.sort(nums);  
          return dfs(nums, target, new int[4], nums.length - 1);  
      }  
    
      private boolean dfs(int[] nums, int target, int[] arr, int index) {  
          if(index == -1) {  
              return true;  
          }  
          int cur = nums[index];  
          for(int i = 0; i < arr.length; i++) {  
              if(arr[i] + cur <= target) {  
                  arr[i] += cur;  
                  if(dfs(nums, target, arr, index - 1)) {  
                      return true;  
                  }  
                  arr[i] -= cur;  
              }  
          }  
          return false;  
      }  
  }  
```    
    
### 个人解读    
  把一个int数组进行平分成四个部分。使用DFS。  
    
  然而效率非常差  
    
  没想到没想到，只需要排序，然后从大的数字开始排序就可以达到第一梯队的效率了。  
    
  第一次遇到不需要算法硬要求，而是单纯的效率才去排序的。  
    
tags:    
  -  DFS  
  -  反思效率  
    
