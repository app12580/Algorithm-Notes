### description    
  Given an array nums of integers, you can perform operations on the array.  
    
  In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.  
    
  You start with 0 points. Return the maximum number of points you can earn by applying such operations.  
    
  Example 1:  
    
  Input: nums = [3, 4, 2]  
  Output: 6  
  Explanation:   
  Delete 4 to earn 4 points, consequently 3 is also deleted.  
  Then, delete 2 to earn 2 points. 6 total points are earned.  
     
    
  Example 2:  
    
  Input: nums = [2, 2, 3, 3, 3, 4]  
  Output: 9  
  Explanation:   
  Delete 3 to earn 3 points, deleting both 2's and the 4.  
  Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.  
  9 total points are earned.  
     
    
  Note:  
    
  The length of nums is at most 20000.  
  Each element nums[i] is an integer in the range [1, 10000].  
     
### solution    
```    
  
Runtime: 3 ms, faster than 47.82% of Java online submissions for Delete and Earn.  
Memory Usage: 39.2 MB, less than 50.00% of Java online submissions for Delete and Earn.  
  
  
  class Solution {  
   public int deleteAndEarn(int[] nums) {  
          if(nums.length == 0) return 0;  
          Arrays.sort(nums);  
          int res = 0;  
          List<Integer> list = new ArrayList<>();  
          int preVal = nums[0] - 1;  
          for(int i = 0; i < nums.length; i++) {  
              int count = 1;  
              int cur = nums[i];  
              while(i < nums.length - 1 && nums[i + 1] == cur) {  
                  i++;  
                  count++;  
              }  
              int curSum = cur * count;  
              if(cur != preVal + 1) {  
                  res += helper(list);  
              }  
              list.add(curSum);  
              preVal = cur;  
          }  
          if(!list.isEmpty()) {  
              res += helper(list);  
          }  
          return res;  
      }  
    
      private int helper(List<Integer> list) {  
          int res = 0;  
          int pre2 = 0;  
          int pre1 = 0;  
          for(int i = 0; i < list.size(); i++) {  
              int cur = list.get(i);  
              res = Math.max(pre2 + cur, pre1);  
              pre2 = pre1;  
              pre1 = res;  
          }  
          list.clear();  
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  感觉类似那个偷钱的题目，需要动态规划计算。  
    
  方法中的helper方法与那个[198](198_House%20Robber%20(Easy).md)一毛一样。  
    
tags:    
  -  动态规划  
  -  数组  
