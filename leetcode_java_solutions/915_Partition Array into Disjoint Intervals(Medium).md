### description    
  Given an array A, partition it into two (contiguous) subarrays left and right so that:  
    
  Every element in left is less than or equal to every element in right.  
  left and right are non-empty.  
  left has the smallest possible size.  
  Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.  
    
     
    
  Example 1:  
    
  Input: [5,0,3,8,6]  
  Output: 3  
  Explanation: left = [5,0,3], right = [8,6]  
  Example 2:  
    
  Input: [1,1,1,0,6,12]  
  Output: 4  
  Explanation: left = [1,1,1,0], right = [6,12]  
     
    
  Note:  
    
  2 <= A.length <= 30000  
  0 <= A[i] <= 10^6  
  It is guaranteed there is at least one way to partition A as described.     
### solution    
```    
Runtime: 2 ms, faster than 75.80% of Java online submissions for Partition Array into Disjoint Intervals.  
Memory Usage: 38.8 MB, less than 66.67% of Java online submissions for Partition Array into Disjoint Intervals.  
  
  class Solution {  
       public int partitionDisjoint(int[] A) {  
          int len = A.length;  
          int[] minArr = new int[len];  
          int min = A[len - 1];  
          minArr[len - 1] = min;  
          for (int i = A.length - 2; i >= 0; i--) {  
              min = Math.min(A[i], min);  
              minArr[i] = min;  
          }  
          int res = 0;  
          int max = Integer.MIN_VALUE;  
          for (; res < len - 1; res++) {  
              max = Math.max(max, A[res]);  
              if(max <= minArr[res+1]) return res + 1;  
          }  
          return -1;  
      }  
  }  
```    
    
### 个人解读    
  需要找到一个点，使得max(左边)<=min(右边)。  
  所以思路是构建两个数组，一个是max(从0到i)，另一个是min(从右到0)。  
  因为要找最左面的，所以前一个数字可以用单个数字来替代。  
    
tags:    
  -  预处理  
