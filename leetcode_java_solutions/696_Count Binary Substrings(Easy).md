### description    
  Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.  
    
  Substrings that occur multiple times are counted the number of times they occur.  
    
  Example 1:  
  Input: "00110011"  
  Output: 6  
  Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".  
    
  Notice that some of these substrings repeat and are counted the number of times they occur.  
    
  Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.  
  Example 2:  
  Input: "10101"  
  Output: 4  
  Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.  
  Note:  
    
  s.length will be between 1 and 50,000.  
  s will only consist of "0" or "1" characters.  
    
  翻译：找出给定字符串符合条件的连续子字符串数量：  
    子字符串需要满足的条件：1、0和1的数量相同 2、要么先一堆0再一堆1，要么先一堆1再一堆0  
### solution    
```    
  class Solution {  
    public int countBinarySubstrings(String s) {  
      int preLen = 0, curLen = 1, count = 0;  
      for (int i = 1; i < s.length(); i++) {  
          if (s.charAt(i) == s.charAt(i - 1)) {  
              curLen++;  
          } else {  
              preLen = curLen;  
              curLen = 1;  
          }  
    
          if (preLen >= curLen) {       // 这一步是关键的步骤，可以不用各种标记就能得到结果。  
              count++;  
          }  
      }  
      return count;  
  }  
    
  }  
```    
    
### 个人解读    
  如果是我自己做的话，还是习惯于在遍历时候，做一堆标记，当前是0还是1,0的数量和1的数量。  
  参考答案的做法，巧妙的利用了只有两种情况，然后交替使用。这样就可以省下一堆标记和判断。  
  定义了三个数字，一个是结果数据。一个是先前周期的数量(另一个数字)，一个是当前周期的数量(当前数字)。  
    
tags:    
  -  数组  
  -  连续子序列  
  -  通过单遍历，拿到连续子序列结果  
