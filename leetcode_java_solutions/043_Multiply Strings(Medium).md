### description    
  Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.  
    
  Example 1:  
    
  Input: num1 = "2", num2 = "3"  
  Output: "6"  
  Example 2:  
    
  Input: num1 = "123", num2 = "456"  
  Output: "56088"  
  Note:  
    
  The length of both num1 and num2 is < 110.  
  Both num1 and num2 contain only digits 0-9.  
  Both num1 and num2 do not contain any leading zero, except the number 0 itself.  
  You must not use any built-in BigInteger library or convert the inputs to integer directly.  
    
  使用字符串计算乘法。  
### solution    
```    
  
// 方法一： 可以说是一步一步debug出来的结果，而且可读性也并不好。  
  class Solution {  
      public String multiply(String num1, String num2) {  
          if("0".equals(num1) || "0".equals(num2)) return "0";  
          StringBuilder builder = new StringBuilder();  
          int offset = 0;  
          for(int i = num2.length() - 1; i >= 0; i--) {  
              int carry = 0;  
              int plus = num2.charAt(i) - '0';  
              for(int j = num1.length() - 1; j >= 0; j--) {  
                  //每次用num2的最后一位去乘以完整的num1  
                  int cur = num1.charAt(j) - '0';  
                  int sum = cur * plus + carry;  
                  int ss = offset + num1.length() - j - 1;      //这个ss理解起来比较费劲  
                  if(builder.length() - 1 >= ss ) {  
                      sum += builder.charAt(ss) - '0';  
                      builder.setCharAt(ss, (char)(sum % 10 + '0'));  
                  } else {  
                      builder.append((char)(sum % 10 + '0'));  
                  }  
                  carry = sum / 10;  
              }  
              if(carry > 0) builder.append(carry);  
              offset++;  
          }  
    
          return builder.reverse().toString();  
      }  
  }  
    
  //方法二  
    
  × Close  
  sample 2 ms submission  
  class Solution {  
      public String multiply(String num1, String num2) {  
          char[] sc = num1.toCharArray();  
          char[] tc = num2.toCharArray();  
            
          int[] ans = new int[num1.length() + num2.length() + 1];  
          // ans[i + j] += a[i] * b[j]  //i位数乘以j位数：要么是i+j位要么是i+j-1位  
            
          for (int i = 0; i < sc.length; i++) {     //ans的  
              for (int j = 0; j < tc.length; j++) {  
                  int x = sc[sc.length - 1 - i] - '0';  
                  int y = tc[tc.length - 1 - j] - '0';  
                  ans[i + j] += x * y; //ans表示后面含有几个0的权重  
              }  
          }  
            
          for (int i = 0; i < ans.length - 1; i++) { //必须从小到大的顺序，不然大的算完之后，后面又加上来了。  
              ans[i + 1] += ans[i] / 10;  
              ans[i] = ans[i] % 10;  
          }  
            
          int i = ans.length - 1;       //找到最高位  
          while(i >= 0 && ans[i] == 0) {  
              i--;  
          }  
            
          StringBuilder sb = new StringBuilder();  
          while(i >= 0) {  
              sb.append(ans[i]);  
              i--;  
          }  
            
          return sb.length() == 0 ? "0" : sb.toString();  
      }  
  }  
```    
    
### 个人解读    
  先写一个竖式找找感觉。  
    
      123  
    X 456  
    --------  
      738  
     615  
    492  
   ------------  
    56088    
      
  一行一行的计算，然后给StringBuider增加字符串。需要定义好辅助方法。    
  需要从后往前计算，所以最后需要StringBuilder反转。  
    
  看了下方法二，发现自己最大的问题就是题目说的：  
  ```  
    You must not use any built-in BigInteger library or convert the inputs to integer directly.  
  ```  
  我直接默认的是，能不用int就不要用int，所以我是完完全全舍弃了int的加法，只保留了个位数的乘法，和两位数的加法运算。
  
  不是能力不行，而是自己给自己加了太多的限制。    
    
tags:    
  -  题目理解误区  
  -  模拟乘法  
