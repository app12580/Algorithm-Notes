### description  
  Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
  
  Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
  
  Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
  
   
  
  Example 1:
  
  Input: [1,-2,3,-2]
  Output: 3
  Explanation: Subarray [3] has maximum sum 3
  Example 2:
  
  Input: [5,-3,5]
  Output: 10
  Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
  Example 3:
  
  Input: [3,-1,2,-1]
  Output: 4
  Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
  Example 4:
  
  Input: [3,-2,2,-3]
  Output: 3
  Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
  Example 5:
  
  Input: [-2,-3,-1]
  Output: -1
  Explanation: Subarray [-1] has maximum sum -1
   
  
  Note:
  
  -30000 <= A[i] <= 30000
  1 <= A.length <= 30000
### solution  
```  

Runtime: 6 ms, faster than 80.65% of Java online submissions for Maximum Sum Circular Subarray.
Memory Usage: 42.6 MB, less than 100.00% of Java online submissions for Maximum Sum Circular Subarray.

  class Solution {
   public int maxSubarraySumCircular(int[] A) {
          int total = 0, maxSum = -30000, curMax = 0, minSum = 30000, curMin = 0;
          for (int a : A) {
              curMax = Math.max(curMax + a, a);
              maxSum = Math.max(maxSum, curMax);
              curMin = Math.min(curMin + a, a);
              minSum = Math.min(minSum, curMin);
              total += a;
          }
          return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
      }
  }
```  
  
### 个人解读  
  循环数组，只需要重复一遍构造新数组即可。
  然后就是一个普通数组求最大和问题。可以用类似preSum的来进行遍历。
  与[053](053_Maximum%20Subarray%20(Easy).md)类似
  
  总结：
  问题转化：掐头去尾等于total-min。
  需要注意sum<0的情况。
  
  ```
    错误算法
    public int maxSubarraySumCircular(int[] A) {
            int len = A.length;
            int preSum = 0;
            int res = Integer.MIN_VALUE;
            int left = 0;
            for(int i = 0; i < 2 * len; i++){
                if(left >= len) break;
                if(i - left >= len) {
                    //需要调整左边
                    preSum -= A[left++];
                    //先将left向右边移动一个，然后while移动
                    while(left < len && A[left] < 0) {
                        preSum -= A[left++];
                    }
                }
                int cur = A[i % len];
                if(cur >= 0) {
                    preSum += cur;
                    res = Math.max(res, preSum);
                } else {
                    res = Math.max(res, cur);
                    if(preSum + cur >= 0) {
                        left = i + 1;
                    }
                    preSum = Math.max(0, preSum + cur);
                }
            }
            return res;
        }
        
        估计是left修正出问题了。。。
  
  ```
  
tags:  
  -  重点数学
