### description    
  Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.  
    
  To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.  
    
  Example:  
    
  Input:  
  A = [ 1, 2]  
  B = [-2,-1]  
  C = [-1, 2]  
  D = [ 0, 2]  
    
  Output:  
  2  
    
  Explanation:  
  The two tuples are:  
  1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0  
  2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0  
### solution    
```    
  class Solution {  
      public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {  
          if(A.length == 0) return 0;  
          int len = A.length;  
          int res = 0;  
          Map<Integer, Integer> map = new HashMap<>();  
          for(int i = 0; i < len; i++) {  
              for(int j = 0; j < len; j++) {  
                  int sum = A[i] + B[j];  
                  map.put(sum, map.getOrDefault(sum, 0) + 1);  
              }  
          }  
          for(int i = 0; i < len; i++) {  
              for(int j = 0; j < len; j++) {  
                  int sum = C[i] + D[j];  
                  sum *= -1;  
                  res += map.getOrDefault(sum, 0);  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  首先第一个问题：是否需要预处理排序？  
  答：需要的，因为肯定有优化的余地。  
    
  然后经历了暴力法、sum2+sum2、等思路后，想到了一个，先让ijkl都为0，然后比较sum和0的关系，  
    
  四指针法，一开始通过递归去实现，然而遇到了重复计算的情况，通过Set来排除重复，还是 超时了。  
    
  不是用函数，而是通过for循环去实现，只要控制好了应该就不会重复了。  
  ```  
  class Solution {  
       public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {  
          if(A.length == 0) return 0;  
          int len = A.length;  
          int res = 0;  
          Arrays.sort(A);  
          Arrays.sort(B);  
          Arrays.sort(C);  
          Arrays.sort(D);  
          for(int i = 0; i < len; i++) {  
              int sum1 = A[i] + B[0] + C[0] + D[0];  
              if(sum1 > 0) {  
                  break;  
              }  
              for(int j = 0; j < len; j++) {  
                  int sum2 = A[i] + B[j] + C[0] + D[0];  
                  if(sum2 > 0) {  
                      break;  
                  }  
                  for(int k = 0; k < len; k++) {  
                      int sum3 = A[i] + B[j] + C[k] + D[0];  
                      if(sum3 > 0) {  
                          break;  
                      }  
                      for(int l = 0; l < len; l++) {  
                          int sum4 = A[i] + B[j] + C[k] + D[l];  
                          if(sum4 == 0) {  
                              res++;  
                          } else if(sum4 > 0) {  
                              break;  
                          }  
                      }  
                  }  
              }  
          }  
          return res;  
      }  
  }  
  ```  
    
  这都要超时的话，难道要用二分法了么。。。。  
  想要省空间看来不行了，只能弄一个Map存储所有的求和结果了，虽然空间多，但是时间快，而且还不用排序了。  
    
    
tags:    
  -  Hash表  
