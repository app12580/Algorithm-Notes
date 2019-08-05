### description    
There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.  
  
Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.  
  
We keep repeating the steps again, alternating left to right and right to left, until a single number remains.  
  
Find the last number that remains starting with a list of length n.  
  
Example:  
  
Input:  
n = 9,  
1 2 3 4 5 6 7 8 9  
2 4 6 8  
2 6  
6  
  
Output: 6  
```    
      public int lastRemaining(int n) {  
          int min = 1, max = n, inc = 1;  
          boolean asc = true;  
          while (min != max) {  
              if (asc) {  
                  min = min + inc;  
                  inc = inc * 2;  
                  max = min + (max - min) / inc * inc;  
              } else {  
                  max = max - inc;  
                  inc = inc * 2;  
                  min = max - (max - min) / inc * inc;  
              }  
              asc = !asc;  
          }  
          return min;  
      }  
```    
    
### 个人解读    
  这种题目要么就是流水账，要么就是有数学上的规律。  
  先尝试下流水账的效率。  
  写到一半感觉不太好，因为用什么数据结构都不好用。一旦n很大，后果很严重。果然流水账直接超时了。  
    
  ```  
  直接超时    
  class Solution {  
      public int lastRemaining(int n) {  
            if(n == 1) return 1;  
          int size = n/2;  
          int[] arr = new int[size];  
          for(int i = 0; i < size; i++) {  
              arr[i] = 2 * (i + 1);  
          }  
          boolean flag = true;  
          while(size > 1) {  
             boolean ff = size % 2 == 0;  
              size /= 2;  
              if(flag) {  
                  //从末尾开始  
                  //0 1 2 3  
                  if(ff) {  
                      for(int i = 0; i < size; i++) {  
                          arr[i] = arr[2 * i];  
                      }  
                  } else {  
                      // 0 1 2 3 4  
                      for(int i = 0; i < size; i++) {  
                          arr[i] = arr[2 * i + 1];  
                      }  
                  }  
              } else {  
                  //从头开始  
                  for(int i = 0; i < size; i++) {  
                      arr[i] = arr[2 * i + 1];  
                  }  
              }  
              flag = !flag;  
    
          }  
          return arr[0];  
      }  
  }  
  ```  
    
  数字规律分为两种，一种是结果驱动，另一种是过程驱动。  
  本题目最核心的规律，就是没经过一次削减，差距就会乘以2。  
  因此引发两种思路，要么控制最值作为变量，每次去求size；要么控制size，每次去求最值。  
    
    
tags:    
  -  重点数学  
  -  数字规律  
