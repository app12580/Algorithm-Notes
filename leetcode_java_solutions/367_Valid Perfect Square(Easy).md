### description   
  Given a positive integer num, write a function which returns True if num is a perfect square else False.  
    
  Note: Do not use any built-in library function such as sqrt.  
    
  Example 1:  
    
  Input: 16  
  Output: true  
  Example 2:  
    
  Input: 14  
  Output: false  
    
  判断一个数是否是平方数  
### solution    
```  
    //方法一： 通过转换成long，应付超限问题
  class Solution {
      public boolean isPerfectSquare(int num) {
           int i = 0;
          while((long)i * i < (long)num) {
              i++;
          }
          return i * i == num;
      }
  }
  
  //方法二：  然而LeetCode报错，说时间太长
  class Solution {
       public boolean isPerfectSquare(int num) {
          int i = 0;
          int gap = 1;
          while(i < num) {
              i += gap;
              gap += 2;
          }
          return i == num;
      }
  }
  
  //方法三： 与方法二思路一致，但是少了个变量，LeetCode可以通过
  class Solution {
      public boolean isPerfectSquare(int num) {
          int subNum = 1;
          while (num > 0) {
              num -= subNum;
              subNum += 2;
          }
          return num == 0;
      }
  }
  
  //方法四： 效率最高， 类似于二分法，二分法的条件就是看能否找到mid * v == num 的那个点。
    class Solution {
      public boolean isPerfectSquare(int num) {
          int lo = 1;
          int hi = num / 2;
          while (lo + 1 < hi) {
              int mid = (lo + hi) / 2;
              int v = num / mid;        //假设mid=2，num=8， 则8/2=4比2大，说明中值右移
              if (mid > v) {
                  hi = mid;
              } else if (mid < v) {
                  lo = mid;
              } else {
                  if (mid * v == num) {
                      return true;
                  }
                  return false;
              }
          }
          
          return lo * lo == num || hi * hi == num;
      }
  }
  
```  
  
### 个人解读  
  既然不让用sqrt这种函数，那么就从0开始遍历，然后适当的while条件，判断i * i 是否等于num，但是，但是！！！   
  i*i就会产生一个数组int超限的问题，有一种方法是转换成long，但这样并不是长久的办法。  
  然后细化场景找规律：  
  1,4,9,16  
  发现间隔是等差数列：3,5,7。。。那么思路二就来了  
    
tags:    
  -  数学    
  -  跳出常规，找规律    
  -  int超限    
  