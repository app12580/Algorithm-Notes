### description    
  Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.  
    
  Note: You should try to optimize your time and space complexity.  
    
  Example 1:  
    
  Input:  
  nums1 = [3, 4, 6, 5]  
  nums2 = [9, 1, 2, 5, 8, 3]  
  k = 5  
  Output:  
  [9, 8, 6, 5, 3]  
  Example 2:  
    
  Input:  
  nums1 = [6, 7]  
  nums2 = [6, 0, 4]  
  k = 5  
  Output:  
  [6, 7, 6, 0, 4]  
  Example 3:  
    
  Input:  
  nums1 = [3, 9]  
  nums2 = [8, 9]  
  k = 3  
  Output:  
  [9, 8, 9]  
### solution    
```    
Runtime: 9 ms, faster than 69.50% of Java online submissions for Create Maximum Number.  
Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Create Maximum Number.  
  
  class Solution {  
      public int[] maxNumber(int[] nums1, int[] nums2, int k) {  
      int n = nums1.length;  
      int m = nums2.length;  
      int[] ans = new int[k];  
      for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {  
          int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);  
          if (greater(candidate, 0, ans, 0)) ans = candidate;  
      }  
          return ans;  
      }  
      private int[] merge(int[] nums1, int[] nums2, int k) {  
          int[] ans = new int[k];  
          for (int i = 0, j = 0, r = 0; r < k; ++r)  
              ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];  
          return ans;  
      }  
        
    // true: nums1 的数字比nums2的大  
      public boolean greater(int[] nums1, int i, int[] nums2, int j) {  
          while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {  
              i++;  
              j++;  
          }  
          return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);  
      }  
      public int[] maxArray(int[] nums, int k) {  
          int n = nums.length;  
          int[] ans = new int[k];  
          for (int i = 0, j = 0; i < n; ++i) {  
              //j表示numsde  
              while (n - i + index > k && index > 0 && ans[index - 1] < nums[i]) {  
                  // n - i + index > k n-i表示还有多少个数字没有遍历到 + index 表示最多可达的数组数量  
                  // index >0 是为了后续的index--  
                  // ans[index - 1] < nums[i] 表示上一个数字小于当前数字，进行数字的替换  
                  index--;  
              }  
              if (index < k) ans[index++] = nums[i];  
          }  
          return ans;  
      }  
  }  
```    
    
### 个人解读    
  贪婪算法，每次都把最大的交给首位，直到终点或者剩下的数字不够了。  
    
  思路：分两个阶段，第一个阶段，在数量允许范围内，尽可能找大的，然后放在左边；第二个阶段：双指针排序  
  但问题在于，第一个阶段，如果两边都有9，要哪一个？难道要DFS+回溯么？  
  感觉可以把问题交给递归。  
    
  参考答案：  
  暴力法：遍历出来所有的可能结果，然后从中选取最大值  
    
tags:    
  -  伪暴力法  
