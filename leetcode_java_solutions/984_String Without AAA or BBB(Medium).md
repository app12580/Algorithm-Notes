### description    
  Given two integers A and B, return any string S such that:  
    
  S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;  
  The substring 'aaa' does not occur in S;  
  The substring 'bbb' does not occur in S.  
     
    
  Example 1:  
    
  Input: A = 1, B = 2  
  Output: "abb"  
  Explanation: "abb", "bab" and "bba" are all correct answers.  
  Example 2:  
    
  Input: A = 4, B = 1  
  Output: "aabaa"  
     
    
  Note:  
    
  0 <= A <= 100  
  0 <= B <= 100  
  It is guaranteed such an S exists for the given A and B.  
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for String Without AAA or BBB.  
Memory Usage: 34 MB, less than 100.00% of Java online submissions for String Without AAA or BBB.  
  
  class Solution {  
     public String strWithout3a3b(int A, int B) {  
          int lessCount = A;  
          int moreCount = B;  
          char less = 'a';  
          char more = 'b';  
          if (B < A) {  
              lessCount = B;  
              moreCount = A;  
              less = 'b';  
              more = 'a';  
          }  
          int len = lessCount + 1;  
          int twoSize = moreCount - len;  
          StringBuilder builder = new StringBuilder();  
          for(int i = 0; i < twoSize; i++) {  
              if(i != 0) builder.append(less);  
              builder.append(more).append(more);  
          }  
         while(builder.length() < A + B) {  
              if(builder.length() != 0)  
              builder.append(less);  
              if(builder.length() < A + B)  
              builder.append(more);  
          }  
          return builder.toString();  
      }  
  }  
```    
    
### 个人解读    
  数学问题，需要先区分哪个多哪个少。  
  然后少的个数为x，多的为y。  
  一共x+1短。  
  然后计算出需要多少个yy,剩下的就是多少个y  
    
  while里面的两个if很关键，因为不知道前面的运行结果。  
    
tags:    
  -  数学  
