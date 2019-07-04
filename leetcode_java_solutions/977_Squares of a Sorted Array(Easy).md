### description    
  Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.  
    
     
    
  Example 1:  
    
  Input: [-4,-1,0,3,10]  
  Output: [0,1,9,16,100]  
  Example 2:  
    
  Input: [-7,-3,2,3,11]  
  Output: [4,9,9,49,121]  
     
    
  Note:  
    
  1 <= A.length <= 10000  
  -10000 <= A[i] <= 10000  
  A is sorted in non-decreasing order.    
    
### solution    
```    
  class Solution {  
      public int[] sortedSquares(int[] A) {  
          int[] res = new int[A.length];  
          int index = 0;  
    
          int l = 0;  
          int h = A.length - 1;  
          while(l < h) {  
              int m = l + (h - l) / 2;  
              int num = A[m];  
              if(num < 0) {  
                  l = m + 1;  
              } else {  
                  h = m;  
              }  
          }  
    
          int left = l - 1;  
          int right = l;  
          while(left >= 0 || right <= A.length - 1) {  
              int aa = left >= 0 ? Math.abs(A[left]) : Integer.MAX_VALUE;  
              int bb = right <= A.length - 1 ? Math.abs(A[right]) : Integer.MAX_VALUE;  
              if(aa < bb) {  
                  res[index++] = aa * aa;  
                  left--;  
              } else {  
                  res[index++] = bb * bb;  
                  right++;  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
 　因为负数的存在，所以要排序需要费一番功夫。  
  打算先用二分法找到0的位置。然后再向两端扩展。  
  因为0不一定存在，所以使用二分法应用二，找到大于0的最左值  
    
tags:    
  -  数学  
  -  二分法  
