### description    
  Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.  
    
  Example 1:  
  Input:  
  nums = [1,3,1]  
  k = 1  
  Output: 0   
  Explanation:  
  Here are all the pairs:  
  (1,3) -> 2  
  (1,1) -> 0  
  (3,1) -> 2  
  Then the 1st smallest distance pair is (1,1), and its distance is 0.  
  Note:  
  2 <= len(nums) <= 10000.  
  0 <= nums[i] < 1000000.  
  1 <= k <= len(nums) * (len(nums) - 1) / 2.  
### solution    
```    
  // 方法一： 二分法+滑动窗口  
  Runtime: 171 ms, faster than 17.35% of Java online submissions for Find K-th Smallest Pair Distance.  
  Memory Usage: 39.1 MB, less than 100.00% of Java online submissions for Find K-th Smallest Pair Distance.  
    
  class Solution {  
      // Returns number of pairs with absolute difference less than or equal to mid.  
      private int countPairs(int[] a, int mid) {  
          int n = a.length, res = 0;  
          for (int i = 0; i < n; ++i) {  
              int j = i;  
              while (j < n && a[j] - a[i] <= mid) j++;  
              res += j - i - 1;  
          }  
          return res;  
      }  
    
      public int smallestDistancePair(int a[], int k) {  
          int n = a.length;  
          Arrays.sort(a);  
    
          // Minimum absolute difference  
          int low = a[1] - a[0];  
          for (int i = 1; i < n - 1; i++)  
              low = Math.min(low, a[i + 1] - a[i]);  
    
          // Maximum absolute difference  
          int high = a[n - 1] - a[0];  
    
          // Do binary search for k-th absolute difference  
          while (low < high) {  
              int mid = low + (high - low) / 2;  
              if (countPairs(a, mid) < k)  
                  low = mid + 1;  
              else  
                  high = mid;  
          }  
    
          return low;  
      }  
  }  
    
  // 方法二： 方法一的优化，用二分法替代滑动窗口  
  Runtime: 12 ms, faster than 34.70% of Java online submissions for Find K-th Smallest Pair Distance.  
  Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Find K-th Smallest Pair Distance.  
    
  class Solution {  
      // Returns index of first index of element which is greater than key  
      private int upperBound(int[] a, int low, int high, int key) {  
          if (a[high] <= key) return high + 1;  
          while (low < high) {  
              int mid = low + (high - low) / 2;  
              if (key >= a[mid]) {  
                  low = mid + 1;  
              } else {  
                  high = mid;  
              }  
          }  
          return low;  
      }  
        
      // Returns number of pairs with absolute difference less than or equal to mid.  
      private int countPairs(int[] a, int mid) {  
          int n = a.length, res = 0;  
          for (int i = 0; i < n; i++) {  
              res += upperBound(a, i, n - 1, a[i] + mid) - i - 1;  
          }  
          return res;  
      }  
    
      public int smallestDistancePair(int a[], int k) {  
          int n = a.length;  
          Arrays.sort(a);  
    
          // Minimum absolute difference  
          int low = a[1] - a[0];  
          for (int i = 1; i < n - 1; i++)  
              low = Math.min(low, a[i + 1] - a[i]);  
    
          // Maximum absolute difference  
          int high = a[n - 1] - a[0];  
    
          // Do binary search for k-th absolute difference  
          while (low < high) {  
              int mid = low + (high - low) / 2;  
              if (countPairs(a, mid) < k)  
                  low = mid + 1;  
              else  
                  high = mid;  
          }  
    
          return low;  
      }  
  }  
```    
    
### 个人解读    
  Kth问题，复合模式下的第k个位置，要第一时间想到，可不可以给出一个数字，然后找出所有小于这个距离的个数，然后就可以使用二分法。  
    
  思路一：  
  二分法，通过HashMap计数。  
  写到一半时候发现HashMap有局限，还是用滑动窗口比较方便  
    
tags:    
  -  Kth问题  
  -  二分法  
