### description    
  Given an array of characters, compress it in-place.  
    
  The length after compression must always be smaller than or equal to the original array.  
    
  Every element of the array should be a character (not int) of length 1.  
    
  After you are done modifying the input array in-place, return the new length of the array.  
    
     
  Follow up:  
  Could you solve it using only O(1) extra space?  
    
     
  Example 1:  
    
  Input:  
  ["a","a","b","b","c","c","c"]  
    
  Output:  
  Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]  
    
  Explanation:  
  "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".  
    
  字符串压缩  
### solution    
```    
  
// 方法一： 遇到不同的情况才处理，所以跳出循环后还需要处理一步  
  class Solution {  
    public int compress(char[] chars) {  
          if(chars == null || chars.length == 0) {  
              return 0;  
          }  
          int res = 0;  
          int count = 1;  
          char cur = chars[0];  
          int slow = 0;  
          for(int i = 1; i < chars.length; i++) {  
              char ch = chars[i];  
              if(ch == cur) {  
                  count++;  
              } else {  
                  if(count == 1) {  
                      res++;  
                      chars[slow++] = cur;  
                      cur = ch;  
                  } else {  
                      int t = count;  
                      res ++;  
                      chars[slow++] = cur;  
                      String s = String.valueOf(t);  
                      for(char c: s.toCharArray()) {  
                          chars[slow++] = c;  
                          res ++;  
                      }  
                      cur = ch;  
                      count = 1;  
                  }  
              }  
          }  
          if(count == 1) {  
              res++;  
              chars[slow++] = cur;  
    
          } else {  
              int t = count;  
              res ++;  
              chars[slow++] = cur;  
              String s = String.valueOf(t);  
              for(char c: s.toCharArray()) {  
                  chars[slow++] = c;  
                  res ++;  
              }  
          }  
    
          return res;  
      }  
  }  
    
  // 方法二 每次计算字符和count，然后统一处理，   
  // 这样好处：不用跳出循环还处理；  
  class Solution {  
      public int compress(char[] chars) {  
          int indexAns = 0, index = 0;  
          while(index < chars.length) {  
              char currentChar  = chars[index];  
              int count = 0;  
              while(index < chars.length && chars[index] ==currentChar) {  
                  index++;  
                  count++;  
              }  
              chars[indexAns++] = currentChar;  
              if(count != 1)   
                  for(char c: Integer.toString(count).toCharArray())  
                      chars[indexAns++] = c;  
          }  
          return indexAns;  
      }  
  }  
```    
    
### 个人解读    
  指针问题，需要处理好每一处的细节。  
    
tags:    
  -  数组  
  -  指针  
  -  字符串  
