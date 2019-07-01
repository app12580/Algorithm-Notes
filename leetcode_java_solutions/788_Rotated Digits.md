### description    
  X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.  
    
  A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.  
    
  Now given a positive number N, how many numbers X from 1 to N are good?  
    
  Example:  
  Input: 10  
  Output: 4  
  Explanation:   
  There are four good numbers in the range [1, 10] : 2, 5, 6, 9.  
  Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.  
  Note:  
    
  N  will be in range [1, 10000].  
    
  自旋转： 0 1 8  
  互相为旋转: 2、5  6、9  
    
  要求n中每个数字旋转后，能成为另外一个不等同于n的有效数字。  
### solution    
```    
  class Solution {  
      public int rotatedDigits(int N) {  
          int count = 0;  
          for(int i = 1; i <= N; i++) {  
              if(check(i)) count++;  
          }  
          return count;  
      }  
    
      private boolean check(int N) {  
          boolean validFound = false;  
          while (N > 0) {  
              if (N % 10 == 2) validFound = true;  
              if (N % 10 == 5) validFound = true;  
              if (N % 10 == 6) validFound = true;  
              if (N % 10 == 9) validFound = true;  
              if (N % 10 == 3) return false;  
              if (N % 10 == 4) return false;  
              if (N % 10 == 7) return false;  
              N = N / 10;  
          }  
          return validFound;  
      }  
  }  
```    
    
### 个人解读    
  从小到大获取所有的，每个数字单独比较，有没有一个办法可以从小到大计算出所有的“好数”。  
    
  还是需要理解好题目描述。  
    
tags:    
  -  题目描述  
