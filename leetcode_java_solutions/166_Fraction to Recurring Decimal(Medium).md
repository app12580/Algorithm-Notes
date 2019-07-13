### description    
  Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.  
    
  If the fractional part is repeating, enclose the repeating part in parentheses.  
    
  Example 1:  
    
  Input: numerator = 1, denominator = 2  
  Output: "0.5"  
  Example 2:  
    
  Input: numerator = 2, denominator = 1  
  Output: "2"  
  Example 3:  
    
  Input: numerator = 2, denominator = 3  
  Output: "0.(6)"  
### solution    
```    
  class Solution {  
      public String fractionToDecimal(int numerator, int denominator) {  
        if(numerator == 0) return "0";  
          StringBuilder builder = new StringBuilder();  
    
          if(numerator < 0 ^ denominator < 0) {  
              builder.append("-");  
          }  
          long numerator2 = Math.abs((long)numerator);  
          long denominator2 = Math.abs((long)denominator);  
    
          long shang = numerator2 / denominator2;  
          long yushu = numerator2 % denominator2;  
          if(yushu == 0) {  
              builder.append(shang);  
              return builder.toString();  
          }  
          builder.append(shang).append(".");  
          List<Long> list = new ArrayList<>(); // 余数  
          StringBuilder xiaoshu = new StringBuilder(); // 小数部分  
          int circleIndex = 0;  
          while(yushu != 0) {  
              if(list.contains(yushu)) {  
                  circleIndex = list.indexOf(yushu);  
                  break;  
              }  
              list.add(yushu);  
              long t = yushu * 10;  
              xiaoshu.append(t / denominator2);  
              yushu = t % denominator2;  
          }  
          if(yushu == 0) {  
              builder.append(xiaoshu.toString());  
          } else {  
              builder.append(xiaoshu.substring(0, circleIndex));  
              builder.append("(");  
              builder.append(xiaoshu.substring(circleIndex, xiaoshu.length()));  
              builder.append(")");  
          }  
          return builder.toString();  
      }  
  }  
```    
    
### 个人解读    
  使用一个List存储小数点后的内容，如果遇到重复的就构建循环小数，否则把整个list放进结果里。  
    
  Input  -1  -2147483648  
  Output  "0.0000000000000000000000000000001"    
  Expected  "0.0000000004656612873077392578125"  
    
  被这个超限吓到了。。。。  
    
  这个题目被搞的有点心态爆炸了。。。  
    
tags:    
  -  int超限  
