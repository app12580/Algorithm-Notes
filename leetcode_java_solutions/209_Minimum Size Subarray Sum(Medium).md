### description    
  Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.  
    
  Example:   
    
  Input: s = 7, nums = [2,3,1,2,4,3]  
  Output: 2  
  Explanation: the subarray [4,3] has the minimal length under the problem constraint.  
  Follow up:  
  If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).   
### solution    
```    
  
//方法一： 问题在于右不停的加减是无效的操作  
  
Runtime: 2 ms, faster than 29.72% of Java online submissions for Minimum Size Subarray Sum.  
Memory Usage: 37.5 MB, less than 40.80% of Java online submissions for Minimum Size Subarray Sum.  
  
  class Solution {  
      public int minSubArrayLen(int s, int[] nums) {  
         int res = 0;  
          int curSum = 0;  
          int i = 0;  
          int j = 0;  
          while(i < nums.length && j < nums.length) {  
             curSum += nums[j];  
             if(curSum >= s) {  
                 res = res == 0 ? j - i + 1 : Math.min(res, j - i + 1);  
                 curSum -= nums[i];  
                 i++;  
                 curSum -= nums[j]; //这个地方出现了后退的情形  
             } else {  
                 j++;  
             }  
          }  
    
          return res;  
      }  
  }  
    
  // 优化  
  class Solution {  
      public int minSubArrayLen(int s, int[] nums) {  
          if(nums.length == 0) return 0;  
          int il = 0;  
          int sum = 0;  
          int Len = nums.length + 1;        //第一点，使用大的值作为初始结果  
          for(int i = 0; i < nums.length; i++){ //永不后退  
              sum += nums[i];       //每次加上最右边的  
              if(sum >= s) {  
                  Len = Math.min(i-il+1, Len);  
                  while(sum >= s){      //做减法的同时更新值  
                      Len = Math.min(i-il+1, Len);  
                      sum -= nums[il];  
                      il++;  
                  }  
              }  
          }  
          if(Len == nums.length+1) return 0;  
          else return Len;  
      }  
  }  
```    
    
### 个人解读    
  curLen问题，使用while比for的好处，不用每次都是固定的i++，更容易控制一些。  
    
  关于优化的思路：  
  这种curLen问题，尽量不要后退，一次遍历通过。  
  本问题的核心点在于：  
  ```  
    while(sum >= s){      //做减法的同时更新值  
        Len = Math.min(i-il+1, Len);  
        sum -= nums[il];  
        il++;  
    }  
  ```  
    
tags:    
  -  curLen问题  
