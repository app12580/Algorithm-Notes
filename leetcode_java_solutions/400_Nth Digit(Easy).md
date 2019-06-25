### description    
  Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...  
    
  Note:  
  n is positive and will fit within the range of a 32-bit signed integer (n < 231).  
    
  Example 1:  
    
  Input:  
  3  
    
  Output:  
  3  
  Example 2:  
    
  Input:  
  11  
    
  Output:  
  0  
    
  Explanation:  
  The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.  
### solution    
```    
  class Solution {  
      public int findNthDigit(int n) {  
    
          int x = 1;  
          int y = 9;  
    
          while(n > 0) {  
              if(n > x * y && Integer.MAX_VALUE / x > y) {  
                  n -= x * y;  
              } else {  
                  //找出是当前第几个数(类似于找页码的操作)  
                  int page = (n + x - 1) / x ;  
                  int row = n % x == 0 ? x : n % x;  
                  int num = pow(10, x-1) - 1 + page;  
                  //找出num的左数第row个数 12345 2  
                  int tail = (num / pow(10, x - row)) % 10;  
                  return tail;  
              }  
              x++;  
              y *= 10;  
          }  
    
          return -1;  
      }  
    
      private int pow(int num, int n) {  
          int res = 1;  
          for(int i = 0; i < n; i++) {  
              res *= num;  
          }  
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  数学问题，需要不停的while，主要是while里面的处理  
  需要找规律：  
  1 * 9 : 1 2 3... 9   
  2 * 90 : 10 11 99  
  3 * 900 : 100 101 ... 999  
    
  然后一步一步去写，注意细节。  
  本题有int超限问题，通过除法来规避。  
    
tags:    
  -  数学  
  -  int超限  
