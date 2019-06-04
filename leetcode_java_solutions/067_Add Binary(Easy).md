### description    
  Given two binary strings, return their sum (also a binary string).  
    
  The input strings are both non-empty and contains only characters 1 or 0.  
    
  Example 1:  
    
  Input: a = "11", b = "1"  
  Output: "100"  
  Example 2:  
    
  Input: a = "1010", b = "1011"  
  Output: "10101"  
### solution    
```    
  class Solution {  
      public String addBinary(String a, String b) {  
          StringBuilder sb = new StringBuilder();  
          int carry = 0;  
          int i = a.length() - 1;  
          int j = b.length() - 1;  
          while(i >= 0 || j >= 0) {  
              int sum = 0;  
              if(i >= 0 && a.charAt(i) == '1') {  
                  sum++;  
              }  
              if(j >= 0 && b.charAt(j) == '1') {  
                  sum++;  
              }  
              sb.append((carry + sum) % 2);  
              carry = (sum + carry) / 2;  
              i--;j--;  
          }  
          if(carry == 1) {  
              sb.append("1");  
          }  
          return sb.reverse().toString();  
      }  
  }  
```    
    
### 个人解读    
  典型的双指针问题，这种有两种代码模板，一种是单指针完事后整体迁移，另一种是一直在一个while里面。  
  本题由于carry的原因，导致每次循环都需要判断一下，所以不适合整体迁移的那种哦写法。  
  有种优化，把sum和carry合并成一个变量，但是感觉没有必要。  
  对于字符串每循环一次就拼接一次这种问题，有时候会不清楚是 t = t + 当前循环，还是t=当前循环+t,这个时候需要想到的是当前循环是什么样的，当前循环两是该放在结果的前面还是后面，这样就方便多了。  
    
tags:    
  -  字符串  
  -  双指针  
