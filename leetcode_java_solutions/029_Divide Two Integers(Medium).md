### description    
  Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.  
    
  Return the quotient after dividing dividend by divisor.  
    
  The integer division should truncate toward zero.  
    
  Example 1:  
    
  Input: dividend = 10, divisor = 3  
  Output: 3  
  Example 2:  
    
  Input: dividend = 7, divisor = -3  
  Output: -2  
  Note:  
    
  Both dividend and divisor will be 32-bit signed integers.  
  The divisor will never be 0.  
  Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.  
    
  不使用乘除法计算除法。  
### solution    
```    
  
//参考答案  
Runtime: 1 ms, faster than 100.00% of Java online submissions for Divide Two Integers.  
Memory Usage: 33.6 MB, less than 5.07% of Java online submissions for Divide Two Integers.  
  
  
  class Solution {  
  	int sign = 1;  
  	if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))  
  		sign = -1;  
  	long ldividend = Math.abs((long) dividend);  
  	long ldivisor = Math.abs((long) divisor);  
  	  
  	//下面这两行优雅写法，蕴含了很多判断  
  	if (ldivisor == 0) return Integer.MAX_VALUE;  
  	if ((ldividend == 0) || (ldividend < ldivisor))	return 0;     
  	  
  	long lans = ldivide(ldividend, ldivisor);  
  	  
  	int ans;  
  	if (lans > Integer.MAX_VALUE){    
  		ans = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;  
  	} else {  
  		ans = (int) (sign * lans);  
  	}  
  	return ans;  
  }  
    
  //二分法递归计算乘积  
  private long ldivide(long ldividend, long ldivisor) {  
  	if (ldividend < ldivisor) return 0;  
  	  
  	long sum = ldivisor;  
  	long multiple = 1;  
  	while ((sum+sum) <= ldividend) {  
  		sum += sum;  
  		multiple += multiple;  
  	}  
  	return multiple + ldivide(ldividend - sum, ldivisor);  
  }  
  }  
```    
    
### 个人解读    
  注意int超限问题  
    
  ```  
  Input  -2147483648  -1  
  Output  0    
  Expected  2147483647  
    
  不光和可能超限，res也可能超限的啊。。。。  
  ```  
    
  // 错误解法  
  ```  
  class Solution {  
      public int divide(int dividend, int divisor) {  
         boolean isNegative = true;  
          if(dividend > 0 ^ divisor > 0) {  
              isNegative = false;  
          }  
          dividend = Math.abs(dividend);  
          divisor = Math.abs(divisor);  
          if(dividend < divisor) {  
              return 0;  
          }  
          int res = 0;  
          int sum = 0;  
          while(sum <= dividend) {  
              int t = sum + divisor;  
              if(t < sum) return isNegative ? res - 1 : 1 - res;  
              sum = t;  
              int tt = res + 1;  
              if(tt < res) return Integer.MAX_VALUE;  
              res++;  
          }  
          return isNegative ? res - 1 : 1 - res;  
      }  
  }  
  ```  
    
  不光sum计算时候超限，光是一个Math.abs(num)就惹出不少麻烦。。。  
    
  反思一： 忽略了int超限最基本的解决办法：long，反而一直在想比较的方式。  
  反思二：while条件的糟糕习惯。  
  ```  
    	while ((sum+sum) <= ldividend)   
    	和  
    	while (sum <= ldividend)   
    	前者一定会运行到最后一步；而后者可能会多运行一步，明显前者更优雅一些。  
  
  ```  
  反思二：递归计算应该想到的。  
  
    
tags:    
  -  运算模拟  
  -  int超限处理  
