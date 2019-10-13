### description    
  Given an unsorted integer array, find the smallest missing positive integer.  
    
  Example 1:  
    
  Input: [1,2,0]  
  Output: 3  
  Example 2:  
    
  Input: [3,4,-1,1]  
  Output: 2  
  Example 3:  
    
  Input: [7,8,9,11,12]  
  Output: 1  
  Note:  
    
  Your algorithm should run in O(n) time and uses constant extra space.  
### solution    
```    
  Runtime: 0 ms, faster than 100.00% of Java online submissions for First Missing Positive.  
  Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for First Missing Positive.  
    
  class Solution {  
      public int firstMissingPositive(int[] A) {  
          int k = partition(A);  
          int max = k + 1;  
          //索引是[0,k]，数字范围是[1,k+1]  
          for (int i = 0; i <= k; i++) {  
              int cur = Math.abs(A[i]);  
             //error: 假设不存在重复的  
              //然而真的有重复的。。。  
              if (cur <= max && A[cur-1] > 0) {  
                  A[cur - 1] = -A[cur - 1];  
              }  
          }  
          int res = max + 1;  
          for (int i = 0; i <= k; i++) {  
              int cur = A[i];  
              if (cur > 0) {  
                  return i + 1;  
              }  
          }  
    
          return res;  
      }  
    
      // 把正数全放在前面，返回最后一个正数的索引  
      public int partition(int[] A) {  
          int q = -1;  
          for (int i = 0; i < A.length; i++) {  
              if (A[i] > 0) {  
                  swap(A, i, ++q);  
              }  
          }  
          return q;  
      }  
    
      public void swap(int[] A, int i, int j) {  
          if (i != j) {  
              int temp = A[i];  
              A[i] = A[j];  
              A[j] = temp;  
          }  
      }  
  }  
```    
    
### 个人解读    
  为什么要混进去负数啊，坟蛋！如果不含有负数的话，就可以用自然数组+源数组*-1的标记法了。通过length作为mod数值标记。  
    
  思路没有错误，主要是一开始被吓傻了。只需要先把负数和0的影响全去掉，就会easy的题目一样了。  
    
  总结：  
  1、并没有什么特别难的，也可以说不过如此。套路是一致的，对于这种自然数组，要第一时间想到值与下标的映射，以及源数组标记法。  
    
tags:    
  -  自然数组  
