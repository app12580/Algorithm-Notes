### description    
  Given a string representing an expression of fraction addition and subtraction, you need to return the calculation result in string format. The final result should be irreducible fraction. If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1. So in this case, 2 should be converted to 2/1.  
    
  Example 1:  
  Input:"-1/2+1/2"  
  Output: "0/1"  
  Example 2:  
  Input:"-1/2+1/2+1/3"  
  Output: "1/3"  
  Example 3:  
  Input:"1/3-1/2"  
  Output: "-1/6"  
  Example 4:  
  Input:"5/3+1/3"  
  Output: "2/1"  
  Note:  
  The input string only contains '0' to '9', '/', '+' and '-'. So does the output.  
  Each fraction (input and output) has format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.  
  The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1,10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.  
  The number of given fractions will be in the range [1,10].  
  The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.  
### solution    
```    
  // 方法一：  
    
  Runtime: 44 ms, faster than 7.52% of Java online submissions for Fraction Addition and Subtraction.  
  Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Fraction Addition and Subtraction.  
    
  class Solution {  
     public String fractionAddition(String expression) {  
          String[] fracs = expression.split("(?=[-+])"); // splits input string into individual fractions  
          String res = "0/1";  
          for (String frac : fracs) res = add(res, frac); // add all fractions together  
          return res;  
      }  
    
      public String add(String frac1, String frac2) {  
          int[] f1 = Stream.of(frac1.split("/")).mapToInt(Integer::parseInt).toArray(),  
                  f2 = Stream.of(frac2.split("/")).mapToInt(Integer::parseInt).toArray();  
          int numer = f1[0] * f2[1] + f1[1] * f2[0], denom = f1[1] * f2[1];  
          String sign = "";  
          if (numer < 0) {  
              sign = "-";  
              numer *= -1;  
          }  
          return sign + numer / gcd(numer, denom) + "/" + denom / gcd(numer, denom); // construct reduced fraction  
      }  
    
      // Computes gcd using Euclidean algorithm  
      public int gcd(int x, int y) {  
          return y == 0 ? x : gcd(y, x % y);  
      }  
  }  
    
  //方法二：  
  Runtime: 2 ms, faster than 80.70% of Java online submissions for Fraction Addition and Subtraction.  
  Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Fraction Addition and Subtraction.  
  class Solution {  
       
      public String fractionAddition(String expression) {  
          Map<Integer,Integer> map=new HashMap<>();  
          int i=0;  
          boolean end=false;  
          while(!end){  
              int idxDiv= expression.indexOf("/");  
              String s1=expression.substring(0,idxDiv);  
              int n1=Integer.parseInt(s1);  
              expression=expression.substring(idxDiv+1);  
    
              int tplus = expression.indexOf("+");  
              int tminus = expression.indexOf("-");  
              int n2;  
              if (tplus < 0 && tminus < 0) {  
                  n2 = Integer.parseInt(expression.substring(0));  
                  end=true;  
              } else if (tminus < 0) {  
                  n2 = Integer.parseInt(expression.substring(0, tplus));  
                  expression = expression.substring(tplus);  
              } else if (tplus < 0) {  
                  n2 = Integer.parseInt(expression.substring(0,tminus));  
                  expression = expression.substring(tminus);  
              } else {  
                  n2 = Integer.parseInt(expression.substring(0, Math.min(tplus, tminus)));  
                  expression = expression.substring(Math.min(tminus, tplus) );  
              }  
              //n2是分母， n1是分子  
              map.put(n2, map.getOrDefault(n2, 0) + n1);  
          }  
          int denominator=1;  
          for(int x:map.keySet() ) {if(map.get(x)!=0) denominator*=x;} //总分母  
          int numerator=0;  
          for(int y:map.keySet()) {  
              if(map.get(y)!=0)  
                  numerator+=denominator/y*map.get(y);  
          }  
          int nn=gcd(numerator,denominator);  
          if(numerator<0&&denominator<0)  
              return Math.abs(numerator/nn)+"/"+Math.abs(denominator/nn);  
          else if(numerator<0||denominator<0)  
              return "-"+Math.abs(numerator/nn)+"/"+Math.abs(denominator/nn);  
          else return numerator/nn+"/"+denominator/nn;  
      }  
    
      public int gcd(int x, int y) {  
          return y == 0 ? x : gcd(y, x % y);  
      }  
  }  
```    
    
### 个人解读    
  自己思路： 通过最大公约数进行分数计算，通过while来获取数字。  
    
  方法一：用了很多java的新特性  
  通过split获取每个带符号的运算符；然后通过字符串拆分再进行计算。  
    
  方法二：通过一个HashMap，先统一算相同分母的结果，把分母相同的放在一起。  
    
tags:    
  -  java新特性  
  -  抓住重复元素，然后使用hash  
  -  字符串转int计算  
