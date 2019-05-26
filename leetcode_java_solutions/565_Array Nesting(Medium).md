### description    
  A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.  
    
  Suppose the first element in S starts with the selection of element A[i] of index = i, the next element in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.  
    
     
    
  Example 1:  
    
  Input: A = [5,4,0,3,1,6,2]  
  Output: 4  
  Explanation:   
  A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.  
    
  One of the longest S[K]:  
  S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}  
     
    
  Note:  
    
  N is an integer within the range [1, 20,000].  
  The elements of A are all distinct.  
  Each element of A is an integer within the range [0, N-1].  
### solution    
```    
  class Solution {  
     public int arrayNesting(int[] nums) {  
          int res = 0;  
          for(int i = 0; i < nums.length; i++) {  
              int cnt = 0;  
              int j = i;  
              while(nums[j] != -1) {  
                  cnt++;  
                  int t = nums[j];  
                  nums[j] = -1;  
                  j = t;  
                  res = Math.max(res, cnt);  //可以优化一下，把这一行放在while外面  
              }  
                
          }  
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  这题的主要收获是知道“数组嵌套”(Array Nesting)这个学名    
  个人感觉，基本上所有的自然数组，都可能应用到这种操作  
    
  这道题的解法呢，从头到尾遍历一遍数组就完事了    
    
tags:    
  -   数组  
  -   数组嵌套  
