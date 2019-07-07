### description    
  The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)  
    
  P   A   H   N  
  A P L S I I G  
  Y   I   R  
  And then read line by line: "PAHNAPLSIIGYIR"  
    
  Write the code that will take a string and make this conversion given a number of rows:  
    
  string convert(string s, int numRows);  
  Example 1:  
    
  Input: s = "PAYPALISHIRING", numRows = 3  
  Output: "PAHNAPLSIIGYIR"  
  Example 2:  
    
  Input: s = "PAYPALISHIRING", numRows = 4  
  Output: "PINALSIGYAHRPI"  
  Explanation:  
    
  P     I    N  
  A   L S  I G  
  Y A   H R  
  P     I  
### solution    
```    
  class Solution {  
      public String convert(String s, int numRows) {  
          if(numRows == 1) return s;  
          StringBuilder[] builders = new StringBuilder[numRows];  
          for(int i = 0; i < builders.length; i++) {  
              builders[i] = new StringBuilder();  
          }  
          StringBuilder res = new StringBuilder();  
          int len = s.length();  
          int j = 0;  
          int addFlag = 1;  //用来记录方向  
          for(int i = 0; i < len; i++) {  
              char cur = s.charAt(i);  
              builders[j].append(cur);  
              if(j == 0 && addFlag == -1) {  
                  addFlag = 1;  
              } else if(j == numRows - 1 && addFlag == 1) {  
                  addFlag = -1;  
              }  
              j += addFlag;  
          }  
          for(StringBuilder b: builders) {  
              res.append(b.toString());  
          }  
          return res.toString();  
      }  
  }  
```    
    
### 个人解读    
  第一反应：需要横纵坐标两个值去存储信息。  
  然后发现，只需要纵向的4个StringBuilder就可以了，而且此时的添加方式也可以很容易写：1234321234321  
    
  ```  
    不能这样初始化的吗？？  
    StringBuilder[] builders = new StringBuilder[numRows];  
    for(StringBuilder b: builders) {  
        b = new StringBuilder();  
    }  
  ```  
    
tags:    
  -  自动变向  
