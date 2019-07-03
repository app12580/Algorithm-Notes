### description    
  Given an array A of integers, return true if and only if it is a valid mountain array.  
    
  Recall that A is a mountain array if and only if:  
    
  A.length >= 3  
  There exists some i with 0 < i < A.length - 1 such that:  
  A[0] < A[1] < ... A[i-1] < A[i]  
  A[i] > A[i+1] > ... > A[A.length - 1]  
     
    
  Example 1:  
    
  Input: [2,1]  
  Output: false  
  Example 2:  
    
  Input: [3,5,5]  
  Output: false  
  Example 3:  
    
  Input: [0,3,2,1]  
  Output: true  
     
    
  Note:  
    
  0 <= A.length <= 10000  
  0 <= A[i] <= 10000   
### solution    
```    
  class Solution {  
      public boolean validMountainArray(int[] A) {  
         if(A.length < 3) return false;  
          int cur = 0;  
          for(int i = 1; i < A.length; i++) {  
              if(A[i] > A[i - 1]) {  
                  if(cur == 0) {  
                      cur = 1;  
                  } else if(cur == -1) {  
                      return false;  
                  }  
              } else if(A[i] == A[i - 1]) {  
                  return false;  
              } else {  
                  if(cur == 1) {  
                      cur = -1;  
                  } else if(cur == 0) {  
                      return false;  
                  }  
              }  
          }  
          return cur == -1;  
      }  
  }  
```    
    
### 个人解读    
  遍历，通过两个标记遍历。  
    
  最后整理好思路，因为一定是先上后下的，所以只需要一个标记，然后为了方便，用-1,0,1来表示。  
  注意好细节，最后要return cur == -1;  
    
tags:    
  -  标记法  
