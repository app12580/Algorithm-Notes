### description  
  Given an integer, write a function to determine if it is a power of three.   
     
  Example 1:   
     
  Input: 27   
  Output: true   
  Example 2:   
     
  Input: 0   
  Output: false   
  Example 3:   
     
  Input: 9   
  Output: true   
  Example 4:   
     
  Input: 45   
  Output: false   
  Follow up:   
  Could you do it without using any loop / recursion?   
### solution  
```  
    //方法一： 找出特殊值
  public boolean isPowerOfThree(int n) {
      return n > 0 && (1162261467 % n == 0);
  }
  
  //方法二： 利用对数运算，但是有问题，精度那里试错了好多回
   public boolean isPowerOfThree(int n) {
       double v = Math.log(n) / Math.log(3);
          double abs = Math.abs(v % 1);
          return abs < 0.0000000000001 || abs > 0.99999999999999;
  }
  
  //方法三： 无限循环除以3，然后再判断，比方法二还要快。。。。
      public boolean isPowerOfThree(int n) {
          if (n < 1) {
              return false;
          }
  
          while (n % 3 == 0) {
              n /= 3;
          }
  
          return n == 1;
      }
```  
  
### 个人解读    
  最取巧的办法，找到int范围内3的最大次幂x，然后判断x%n == 0。  
  方法二： 思路上老说，对于代码什么样子的完全没印象，但是我只记得，一句话，"求指数，想办法找对数"。  
  3^x == num   
  xlog3(3) == log3(num)  
  x == log3(num)  
    
  换底公式： loga(b) = logc(b)/logc(a)  
  x == lg(num)/lg(3)  
    
  方法三：  
  每次除以3，一直除下去，看是不是等于1.  
tags:    
  -  魔法数    
  