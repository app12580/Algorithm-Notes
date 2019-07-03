### description    
  Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.  
    
  Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.  
    
  You may return any answer array that satisfies this condition.  
    
     
    
  Example 1:  
    
  Input: [4,2,5,7]  
  Output: [4,5,2,7]  
  Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.  
     
    
  Note:  
    
  2 <= A.length <= 20000  
  A.length % 2 == 0  
  0 <= A[i] <= 1000  
    
### solution    
```    
  class Solution {  
      public int[] sortArrayByParityII(int[] A) {  
          int i = 0;  
          int j = 1;  
          while(i < A.length && j < A.length) {  
              if(A[i] % 2 == 0) {  
                  i += 2;  
              } else if(A[j] % 2 == 1) {  
                  j += 2;  
              } else {  
                  int t = A[i];  
                  A[i] = A[j];  
                  A[j] = t;  
                  i += 2;  
                  j += 2;  
              }  
          }  
          return A;  
      }  
  }  
```    
    
### 个人解读    
  双指针，一个指针在偶数，一个指针在奇数。  
    
tags:    
  -  双指针  
