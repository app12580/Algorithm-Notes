### description    
  We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.  
    
  The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].  
    
  The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].  
    
  Return true if and only if the number of global inversions is equal to the number of local inversions.  
    
  Example 1:  
    
  Input: A = [1,0,2]  
  Output: true  
  Explanation: There is 1 global inversion, and 1 local inversion.  
  Example 2:  
    
  Input: A = [1,2,0]  
  Output: false  
  Explanation: There are 2 global inversions, and 1 local inversion.  
  Note:  
    
  A will be a permutation of [0, 1, ..., A.length - 1].  
  A will have length in range [1, 5000].  
  The time limit for this problem has been reduced.  
### solution    
```    
  
// 方法一 这速率不太科学额。。。  
Runtime: 258 ms, faster than 16.88% of Java online submissions for Global and Local Inversions.  
Memory Usage: 40.5 MB, less than 100.00% of Java online submissions for Global and Local Inversions.  
  
  
  class Solution {  
      public boolean isIdealPermutation(int[] A) {  
          int len = A.length;  
          for(int gap = 2; gap < len; gap++) {  
              for(int j = 0; j < len - gap; j++) {  
                  if(A[j] > A[j + gap]) {  
                      return false;  
                  }  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  数学问题，读明白题目以后，会发现，局部是全局的一部分。因此，为了满足条件，题目等价于不存在"跨度倒置"  
    
tags:    
  -  数学  
  -  数组  
