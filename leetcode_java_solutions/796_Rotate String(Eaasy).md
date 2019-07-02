### description    
  We are given two strings, A and B.  
    
  A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.  
    
  Example 1:  
  Input: A = 'abcde', B = 'cdeab'  
  Output: true  
    
  Example 2:  
  Input: A = 'abcde', B = 'abced'  
  Output: false  
  Note:  
    
  A and B will have length at most 100.  
### solution    
```    
  class Solution {  
   public boolean rotateString(String A, String B) {  
       if(A.length() != B.length()) return false;  
    
          return (A + A).contains(B);  
      }  
  }  
```    
    
### 个人解读    
  明确题目描述后，String操作就相当于每个char[i]跑到i+k的位置。  
    
  想到了String倍加，然后是否等于另外一个。  
  注释：需要注意长度不相等的情况。  
    
tags:    
  -  数学  