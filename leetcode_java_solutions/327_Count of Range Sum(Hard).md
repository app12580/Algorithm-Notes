### description    
  Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.  
  Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.  
    
  Note:  
  A naive algorithm of O(n2) is trivial. You MUST do better than that.  
    
  Example:  
    
  Input: nums = [-2,5,-1], lower = -2, upper = 2,  
  Output: 3   
  Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.  
### solution    
```    
Runtime: 7 ms, faster than 86.01% of Java online submissions for Count of Range Sum.  
Memory Usage: 39 MB, less than 100.00% of Java online submissions for Count of Range Sum.  
  
  public int countRangeSum(int[] nums, int lower, int upper) {  
          int n = nums.length;  
          long[] sums = new long[n + 1];  
          for (int i = 0; i < n; ++i)  
              sums[i + 1] = sums[i] + nums[i];  
          return countWhileMergeSort(sums, 0, n + 1, lower, upper);  
      }  
    
      //每次排序[start, mid)  和 [mid, end)  
      private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {  
          if (end - start <= 1) return 0;  
          int mid = (start + end) / 2;  
          int count = countWhileMergeSort(sums, start, mid, lower, upper)  
                  + countWhileMergeSort(sums, mid, end, lower, upper);  
          int j = mid, k = mid, t = mid;  
          long[] cache = new long[end - start];  
          for (int i = start, r = 0; i < mid; ++i, ++r) {  
              while (k < end && sums[k] - sums[i] < lower) k++;  
              while (j < end && sums[j] - sums[i] <= upper) j++;  
              while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];  
              // 归并排序用t 如果右半边的t比当前i小，那么就让这个时候的t就是最小的了，把它放在r++的位置上面  
              // 跳出while以后，此时i要小于等于t，所以此时i是最小  
              cache[r] = sums[i];  
              count += j - k;  
          }  
          System.arraycopy(cache, 0, sums, start, t - start);  
          return count;  
      }  
```    
    
### 个人解读    
  思路一：  
  暴力法，获取所有区间和，然后进行计算  
  反思：通过前i个数之和sum[]计算  
  O(N^2)  
    
  参考答案：  
    https://leetcode.com/problems/count-of-range-sum/discuss/77990/Share-my-solution  
   几点总结：  
   1、归并排序和分治法，以及归并排序除了双指针外的写法  
   2、将问题和求逆序对个数的题目进行类比  
   3、各种排序方法真的好关键啊，之前还遇到过桶排序的  
    
    
  ```  
  TLE   
  class Solution {  
     public int countRangeSum(int[] nums, int lower, int upper) {  
          int len = nums.length;  
          if(len == 0) return 0;  
          long[][] dp = new long[len][len];  
          int res = 0;  
          for(int i = 0; i < len; i++) {  
              dp[i][i] = nums[i];  
              if(nums[i] >= lower && nums[i] <= upper) res++;  
          }  
          for(int k = 1; k < len; k++) {  
              for(int i = 0; i < len - k; i++) {  
                  long val = dp[i][i+k-1] + dp[i+k][i+k];  
                  dp[i][i+k] = (int) val;  
                  if(val >= lower && val <= upper) res++;  
              }  
          }  
          return res;  
      }  
        
      或者   
      public int countRangeSum(int[] nums, int lower, int upper) {  
          int n = nums.length;  
          long[] sums = new long[n + 1];  
          for (int i = 0; i < n; ++i)  
              sums[i + 1] = sums[i] + nums[i];  
          int ans = 0;  
          for (int i = 0; i < n; ++i)  
              for (int j = i + 1; j <= n; ++j)  
                  if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper)  
                      ans++;  
          return ans;  
      }  
  }  
  ```  
    
    
    
    
tags:    
  -  归并排序  
