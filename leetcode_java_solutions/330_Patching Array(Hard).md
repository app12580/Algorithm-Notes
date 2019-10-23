### description  
  Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
  
  Example 1:
  
  Input: nums = [1,3], n = 6
  Output: 1 
  Explanation:
  Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
  Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
  Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
  So we only need 1 patch.
  Example 2:
  
  Input: nums = [1,5,10], n = 20
  Output: 2
  Explanation: The two patches can be [2, 4].
  Example 3:
  
  Input: nums = [1,2,2], n = 5
  Output: 0
  
### solution  
```  
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Patching Array.
  Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for Patching Array.
  
  public class Solution {
      public int minPatches(int[] nums, int n) {
          int patches = 0, i = 0;
          long miss = 1; // use long to avoid integer overflow error
          while (miss <= n) {
              if (i < nums.length && nums[i] <= miss) // miss is covered
                  miss += nums[i++];
              else { // patch miss to the array
                  miss += miss;
                  patches++; // increase the answer
              }
          }
          return patches;
      }
  }

```  
  
### 个人解读  
https://leetcode-cn.com/problems/patching-array/solution/an-yao-qiu-bu-qi-shu-zu-by-leetcode/
  是一个数学+贪婪算法的问题，从小到大每次遇到miss的数字，添加miss即可。
  并且，在添加完数字后，可选范围从[1,miss) 变成了[1,miss+miss)。      
  关键点： [1,miss) 再加上一个数字k，就会变成[1,miss) 和 [k, k+miss)，一旦k<=miss，就会是两个区间合并。
  
tags:  
  -  数字逻辑  
  -  贪婪算法  
