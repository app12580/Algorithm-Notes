### description  
  Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
  
  B.length >= 3
  There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
  (Note that B could be any subarray of A, including the entire array A.)
  
  Given an array A of integers, return the length of the longest mountain. 
  
  Return 0 if there is no mountain.
  
  Example 1:
  
  Input: [2,1,4,7,3,2,5]
  Output: 5
  Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
  Example 2:
  
  Input: [2,2,2]
  Output: 0
  Explanation: There is no mountain.
  Note:
  
  0 <= A.length <= 10000
  0 <= A[i] <= 10000
  Follow up:
  
  Can you solve it using only one pass?
  Can you solve it in O(1) space?
### solution  
```  
Runtime: 2 ms, faster than 99.85% of Java online submissions for Longest Mountain in Array.
Memory Usage: 38.2 MB, less than 100.00% of Java online submissions for Longest Mountain in Array.

  class Solution {
      public int longestMountain(int[] A) {
           int res = 0;
          int len = A.length;
          for (int i = 0; i < len;) {
              //先找上坡路
               int start = i;
              while (i < len - 1 && A[i + 1] > A[i]) {
                  i++;
              }
              if(i >= len - 1) break;
              if(i == start || A[i + 1] == A[i]) {
                  i++;
                  continue;
              }
              int mid = i;
              while(i < len - 1 && A[i + 1] < A[i]) {
                  i++;
              }
              if(i == mid) continue;
              res = Math.max(res, i - start + 1);
          }
          return res;
      }
  }
```  
  
### 个人解读  
  采用数组的分治法，可以省去一大堆的标记。
  但是需要注意各种细节，if条件和i++的时机。
  
tags:  
  -  数组
  -  数组分治法
