### description    
  Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.  
    
  Note:  
  The length of num is less than 10002 and will be ≥ k.  
  The given num does not contain any leading zero.  
  Example 1:  
    
  Input: num = "1432219", k = 3  
  Output: "1219"  
  Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.  
  Example 2:  
    
  Input: num = "10200", k = 1  
  Output: "200"  
  Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.  
  Example 3:  
    
  Input: num = "10", k = 2  
  Output: "0"  
  Explanation: Remove all the digits from the number and it is left with nothing which is 0.  
### solution    
```    
// 方法一：字符串硬拼接  
Runtime: 20 ms, faster than 19.77% of Java online submissions for Remove K Digits.  
Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Remove K Digits.  
  
  class Solution {  
    public String removeKdigits(String num, int k) {  
          while(k-- > 0) {  
              num = removeOneDigits(num);  
          }  
          return num;  
      }  
    
      public String removeOneDigits(String num) {  
          if(num.length() == 1) return "0";  
          int i = 0;  
          while(i < num.length() - 1 && num.charAt(i+1) != '0' && num.charAt(i+1) >= num.charAt(i)) {  
              i++;  
          }  
          String newStr = i == num.length() - 1 ? num.substring(0, i) : num.substring(0, i) + num.substring(i+1);  
          return removePreZero(newStr);  
    
      }  
    
      public String removePreZero(String num) {  
          int zero = 0;  
          while(zero < num.length() && num.charAt(zero) == '0') {  
              zero++;  
          }  
          if(zero == num.length()) {  
              return "0";  
          }  
          return num.substring(zero);  
      }  
  }  
    
  // 方法二：  
  class Solution {  
   public String removeKdigits(String num, int k) {  
          char[] stack = new char[num.length()];  
          int top = 0, digits = num.length() - k;  
    
          for(int i = 0; i < num.length(); ++i) {  
              char c = num.charAt(i);  
              while(top > 0 && stack[top-1] > c && k > 0) {  
                  top -= 1;  
                  k -= 1;  
              }  
    
              stack[top++] = c;  
          }  
          //然后再处理0  
          int index = 0;  
    
          while(index < digits && stack[index] == '0') {  
              index++;  
          }  
    
          return index == digits ? "0" : new String(stack, index, digits - index);  
      }  
  }  
```    
    
### 个人解读    
  第一问题：能不能使用递归：好像是可以的。  
  对于abcda这种形式：要移除d，所以移除一个，需要移除从左到右递增的最后一个数字。此时移除后面的数字没有意义，因为abcd一定大于abca。  
  对于abc0这种形式： 还是移除abc中c比较好，所以可以理解0是最大的那个。  
  然而好像有问题啊，当k特别大的时候，没必要一个一个删除，而是可以直接把所有大的数字全干掉。  
  无法通过，测试用例是一个特别特别大的数字，debug都不知道从何入手。。。。  
    
  通过排查，不是递归的问题，而是对于0的处理不够，没有处理连续0的情况。  
    
  方法二的优化：  
  1、不用String拼接  
  2、因为根据大于的原则，0不会被剃掉，所以最后0都在最前面，可以统一之后处理。  
    
tags:    
  -  数学陷阱   
  -  数字规律   
