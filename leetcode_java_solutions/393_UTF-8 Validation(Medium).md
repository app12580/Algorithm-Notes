### description    
  A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:  
    
  For 1-byte character, the first bit is a 0, followed by its unicode code.  
  For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.  
  This is how the UTF-8 encoding would work:  
    
     Char. number range  |        UTF-8 octet sequence  
        (hexadecimal)    |              (binary)  
     --------------------+---------------------------------------------  
     0000 0000-0000 007F | 0xxxxxxx  
     0000 0080-0000 07FF | 110xxxxx 10xxxxxx  
     0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx  
     0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx  
  Given an array of integers representing the data, return whether it is a valid utf-8 encoding.  
    
  Note:  
  The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.  
    
  Example 1:  
    
  data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.  
    
  Return true.  
  It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.  
  Example 2:  
    
  data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.  
    
  Return false.  
  The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.  
  The next byte is a continuation byte which starts with 10 and that's correct.  
  But the second continuation byte does not start with 10, so it is invalid.  
### solution    
```    
  
// 方法一： 流水账  
Runtime: 1 ms, faster than 99.72% of Java online submissions for UTF-8 Validation.  
Memory Usage: 38.5 MB, less than 81.82% of Java online submissions for UTF-8 Validation.  
  
  
  class Solution {  
        public boolean validUtf8(int[] data) {  
          for(int i = 0; i < data.length; i++) {  
              int count = countOne(data[i]);  
              if(count == 0) {  
                  continue;  
              } else if(count <= 4 && count > 1 && i + count - 1 < data.length) {  
                  while(--count > 0) {  
                      if(countOne(data[++i]) != 1) return false;  
                  }  
              } else {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
      private int countOne(int num) {  
          int s = 128;  
          int res = 0;  
          while((num & s) > 0) {  
              res++;  
              s >>= 1;  
          }  
          return res;  
    
      }  
    
  }  
```    
    
### 个人解读    
  流水账，先判断第一个数字有几个1，然后再去判断后面的几个。  
    
  题目描述刚开始没有看太懂，遇到data.length>4的时候怎么算？  
  根据题目描述，input的data是由若干个UTF-8字符组合的，如果>4了，先判断前面的是否满足条件，然后再看剩下的是否依然是满足条件。  
    
tags:    
  -  位运算   
  -  数组   
