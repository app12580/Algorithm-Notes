### description  
  Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
  
  Example 1:
  
  Input: 121
  Output: true
  Example 2:
  
  Input: -121
  Output: false
  Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
  Example 3:
  
  Input: 10
  Output: false
  Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
### solution  
```  
  class Solution {
      public boolean isPalindrome(int x) {
          if (x == 0) {
          return true;
      }
          if(x < 0 || x % 10 == 0) {  //%10==0 第二次漏掉的边界条件
              return false;
          }
          int right = 0;
          while(right < x) {
              right = right * 10 + x % 10;
              x /= 10;
          }
          return right == x || x == right / 10;  //第一次漏掉的边界条件
      }
  }
```  
  
### 个人解读  
  判断数字回文和判断字符串是有区别的，字符串可以直接获得开头和结尾，而数字只能走到头了，才能获取到开头的数字。
  另外值得注意的是，数字还有一个超限的情况，本题有两种解决办法，要么判断超出最大值限制直接返回false（比较麻烦），另一种办法，只比较一般。
  
tags:  
  -  Integer
  -  数字对半处理
  
