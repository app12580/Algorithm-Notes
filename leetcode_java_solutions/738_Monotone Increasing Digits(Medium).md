### description    
  Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.  
    
  (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)  
    
  Example 1:  
  Input: N = 10  
  Output: 9  
  Example 2:  
  Input: N = 1234  
  Output: 1234  
  Example 3:  
  Input: N = 332  
  Output: 299  
  Note: N is an integer in the range [0, 10^9]  
### solution    
```    
  
// 方法一： 发现递减，但是效率很差  
思路应该没问题  
Runtime: 2 ms, faster than 17.11% of Java online submissions for Monotone Increasing Digits.  
Memory Usage: 33.4 MB, less than 20.00% of Java online submissions for Monotone Increasing Digits.  
  
  class Solution {  
      
    public int monotoneIncreasingDigits(int N) {  
          String s = String.valueOf(N);  
          StringBuilder builder = new StringBuilder();  
          for(int i = 0; i < s.length(); i++) {  
              if(i == 0) {  
                  builder.append(s.charAt(i));  
              } else {  
                  if(s.charAt(i) >= s.charAt(i - 1)) {  
                      builder.append(s.charAt(i));  
                  } else {  
                      //开始回溯，让与i-1相同的第一个值-1。  
                      char cur = s.charAt(i - 1);  
                      int j = i - 1;  
                      while(j >= 1 && builder.charAt(j-1) == cur) {  
                          j--;  
                      }  
                      int c = cur - '0' - 1;  
                      String s2 = builder.toString().substring(0, j);  
                      s2 += cur - '0' - 1;  
                      for(int k = s2.length(); k < s.length(); k++) {  
                          s2 += '9';  
                      }  
                      return Integer.valueOf(s2);  
                  }  
              }  
          }  
          return Integer.valueOf(builder.toString());  
      }  
    
  }  
    
    
  // 优化 只改了拼接9的string，然后效率直接90+    
  Runtime: 1 ms, faster than 94.20% of Java online submissions for Monotone Increasing Digits.  
  Memory Usage: 33.3 MB, less than 20.00% of Java online submissions for Monotone Increasing Digits.  
    
    
  class Solution {  
      
    public int monotoneIncreasingDigits(int N) {  
          String s = String.valueOf(N);  
          StringBuilder builder = new StringBuilder();  
          for(int i = 0; i < s.length(); i++) {  
              if(i == 0) {  
                  builder.append(s.charAt(i));  
              } else {  
                  if(s.charAt(i) >= s.charAt(i - 1)) {  
                      builder.append(s.charAt(i));  
                  } else {  
                      //开始回溯，让与i-1相同的第一个值-1。  
                      char cur = s.charAt(i - 1);  
                      int j = i - 1;  
                      while(j >= 1 && builder.charAt(j-1) == cur) {  
                          j--;  
                      }  
                      int c = cur - '0' - 1;  
                      String s2 = builder.toString().substring(0, j);  
                       s2 += cur - '0' - 1;  
                      int res = Integer.valueOf(s2);  
                      for(int k = s2.length(); k < s.length(); k++) {  
                          res = res * 10 + 9;  
                      }  
                      return res;  
                  }  
              }  
          }  
          return Integer.valueOf(builder.toString());  
      }  
    
  }  
```    
    
### 个人解读    
  感觉需要判断，如果是递增数列则是本身，否则是最高位减一，然后一堆9.  
    
  证明如果前面n个是递增，12345xxx  
  为了最大，一定是1开头，然后12345之后就不行了。  
  那么如果是123444好像也可以啊。  
    
  这么一说，要么无限标记法，记录前面的结果，要么就是用递归了。  
    
  感觉可以用递归，需要两个参数，一个是当前数字，另一是首位最小是多少(初始为0)。  
    
  数字问题一个麻烦的地方就是起始为0的时候很难受。  
    
  要不干脆直接转字符试试。。  
  从前开始遍历，如果发生下降情况，然后回溯，再添上一堆9.  
    
  //先前的思路  
  ```  
  class Solution {  
      
      boolean flag = false;  
      public int monotoneIncreasingDigits(int N) {  
          int helper = helper(N, 1);  
          if(helper == -1) {  
              int size = (N+"").length() - 1;  
              int res = Integer.valueOf((N+"").charAt(0) - '0') - 1;  
              for(int i = 0; i < size; i++) {  
                  res = res * 10 + 9;  
              }  
              return res;  
          }  
          return helper;  
      }  
    
      private int helper(int n, int min) {  
          //先计算首位，然后递归  
          if(flag) {  
              int res = 9;  
              while(n >= 10){  
                  res = res * 10 + 9;  
                  n/=10;  
              }  
              return res;  
          }  
          if(n < 10) {  
              if(min > n) {  
                  return -1;  
              } else {  
                  return n;  
              }  
          }  
          int left = getLeft(n);  
    
          for(int i = getFirst(n); i >= min; i--) {  
              //把n分成最高位数字和剩下的  
              if(i != getFirst(n)) {  
                  flag = true;  
              }  
              int next = helper(left, i);  
              if(next == -1) {  
                  continue;  
              } else {  
                  return Integer.valueOf(i + "" + next);  
              }  
          }  
          return -1;  
      }  
    
      private int getFirst(int n) {  
          while(n >= 10) {  
              n /= 10;  
          }  
          return n % 10;  
      }  
    
      private int getLeft(int n) {  
          if(n < 10) {  
              return -1;  
          }  
          int ss = 1;  
          int t = n;  
          while(t >= 10) {  
              t /= 10;  
              ss *= 10;  
          }  
          return n % ss;  
      }  
  }  
  ```  
   
    
tags:    
  -  数学  
  -  数字  
