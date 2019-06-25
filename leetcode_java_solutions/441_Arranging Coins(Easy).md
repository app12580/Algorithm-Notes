### description    
  You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.  
    
  Given n, find the total number of full staircase rows that can be formed.  
    
  n is a non-negative integer and fits within the range of a 32-bit signed integer.  
    
  Example 1:  
    
  n = 5  
    
  The coins can form the following rows:  
  ¤  
  ¤ ¤  
  ¤ ¤  
    
  Because the 3rd row is incomplete, we return 2.  
  Example 2:  
    
  n = 8  
    
  The coins can form the following rows:  
  ¤  
  ¤ ¤  
  ¤ ¤ ¤  
  ¤ ¤  
    
  Because the 4th row is incomplete, we return 3.  
    
  输入n，然后第一排放一个，第二排放两个。。。。以此类推，看能最多放几行（不包括放不满的）。  
    
### solution    
```    
  //方法一： 超时  
  class Solution {  
      public int arrangeCoins(int n) {  
          int res = 1;  
          while(true) {  
              int sum = 0;  
              if(res % 2 == 0) {  
                  sum = res / 2 * (res + 1);  
              } else {  
                  sum = (res + 1) / 2 * res;  
              }  
              if(sum < n) {  
                  res++;  
                  continue;  
              } else if(sum == n) {  
                  return res ;  
              } else {  
                  return res - 1;  
              }  
          }  
      }  
  }  
    
  //方法二 二分法  
  class Solution {  
      public int arrangeCoins(int n) {  
          int l = 1;  
          int h = n;  
          while(l < h) {  
              int m = l + (h - l + 1) / 2;  
              long sum = (long)m * (m+1)/2;  
    
              if(sum == n) {  
                  return m;  
              } else if(sum <n) {  
                  l = m;  
              } else {  
                  h = m - 1;  
              }  
          }  
          return h;  
      }  
  }  
```    
    
### 个人解读    
  注意两点：  
  使用二分法提高效率  
  int超限问题，使用long解决，注意写法  
  ```  
    long sum = (long)m * (m+1)/2;  
  ```  
    
  二分法解读：应用二，小于等于sum的最右值，然后套模板即可。  
    
    
tags:    
  -  二分法    
  -  int超限    
