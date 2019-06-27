### description    
  Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.  
    
  For example, with A = "abcd" and B = "cdabcdab".  
    
  Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").  
    
  Note:  
  The length of A and B will be between 1 and 10000.  
    
    
### solution    
```    
  class Solution {  
      public int repeatedStringMatch(String A, String B) {  
          int count = 0;  
          StringBuilder sb = new StringBuilder();  
          while (sb.length() < B.length()) {  
              sb.append(A);  
              count++;  
          }  
          if(sb.toString().contains(B)) return count;  
          if(sb.append(A).toString().contains(B)) return ++count;  
          return -1;  
      }  
  }  
```    
    
### 个人解读    
  第一感觉有点无从下手啊。  
  必须要有一个关于B中字符在A中位置的描述，不光要有次数，还要有位置的相关信息。需要遍历B的String，  
    
  然而题目还是理解偏差，原题目的substring 是指连续子字符串，而我以为的是非连续子字符串。  
  其实如果是非连续子字符串的话，好像双指针就能解决了。  
    
  下面分析一下，这一行代码是为什么。  
  ```  
        if(sb.append(A).toString().contains(B)) return ++count;  
  ```  
  可以用拼格来辅助理解，  
  |----|----|----|----|----|  
    ``````````````````````   
  多一个是极限了。再多了，就会把前面的扔掉了。  
    
tags:    
  -  数学  
  -  字符串  
