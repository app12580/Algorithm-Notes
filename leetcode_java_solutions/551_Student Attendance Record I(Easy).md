### description    
  You are given a string representing an attendance record for a student. The record only contains the following three characters:  
  'A' : Absent.  
  'L' : Late.  
  'P' : Present.  
  A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).  
    
  You need to return whether the student could be rewarded according to his attendance record.  
    
  Example 1:  
  Input: "PPALLP"  
  Output: True  
  Example 2:  
  Input: "PPALLL"  
  Output: False  
    
  如果字符串超过1个A或者2个L，就返回false，否则返回true  
    
### solution    
```    
  class Solution {  
      public boolean checkRecord(String s) {  
          int aCount = 0;  
          int lCount = 0;  
          for(char ch: s.toCharArray()) {  
              if(ch == 'L') {  
                  lCount++;  
              } else {  
                  lCount = 0;  
              }  
              if(ch == 'A') {  
                  aCount++;  
              }   
              if(lCount > 2 || aCount > 1){  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  遍历。  
  之前吃过一次亏，在for循环里面做了太多事情反而速度更慢。  
    
  ```  
  ????? 题目什么意思 ？？？？  
  Input  "LALL"  
  Output  false  
  Expected   true  
    
  more than two continuous 'L' (late).  
  好尴尬。。。这句话是指连续的两次迟到。。  
    
  ```  
    
tags:    
  -  数学  
  -  遍历  
