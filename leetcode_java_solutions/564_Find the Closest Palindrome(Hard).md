### description    
  Given an integer n, find the closest integer (not including itself), which is a palindrome.  
    
  The 'closest' is defined as absolute difference minimized between two integers.  
    
  Example 1:  
  Input: "123"  
  Output: "121"  
  Note:  
  The input n is a positive integer represented by string, whose length will not exceed 18.  
  If there is a tie, return the smaller one as answer.  
### solution    
```    
Runtime: 3 ms, faster than 48.26% of Java online submissions for Find the Closest Palindrome.  
Memory Usage: 35.2 MB, less than 100.00% of Java online submissions for Find the Closest Palindrome.  
  
  class Solution {  
      public String nearestPalindromic(String n) {  
          char[] arr = n.toCharArray();  
          for (int i = 0, j = arr.length - 1; i < j; i++, j--) arr[j] = arr[i];  
    
          String curP = String.valueOf(arr);  
          String preP = nearestPalindrom(curP, false);  
          String nextP = nearestPalindrom(curP, true);  
    
          long num = Long.valueOf(n);  
          long cur = Long.valueOf(curP);  
          long pre = Long.valueOf(preP);  
          long next = Long.valueOf(nextP);  
    
          long d1 = Math.abs(num - pre);  
          long d2 = Math.abs(num - cur);  
          long d3 = Math.abs(num - next);  
    
          if (num == cur) {  
              return d1 <= d3 ? preP : nextP;  
          } else if (num > cur) {  
              return d2 <= d3 ? curP : nextP;  
          } else {  
              return d1 <= d2 ? preP : curP;  
          }  
      }  
    
      private String nearestPalindrom(String curP, boolean dir) {  
          // len = 3， k = 1, p = 2;  
          int k = curP.length() / 2, p = curP.length() - k;  
          int l = Integer.valueOf(curP.substring(0, p));  
          l += (dir ? 1 : -1);  
    
          //特殊处理  
          if (l == 0) return k == 0 ? "0" : "9";  
    
          StringBuilder left = new StringBuilder(String.valueOf(l));  
          StringBuilder right = new StringBuilder(left).reverse();  
          if (k > left.length()) right.append("9"); //如果left发生减法退位了，左边少了一个，同时导致右边也少了一位  
          // 为什么是<k而不是<p这就与奇偶性质有关 : 1000 -> 99 ; 100 -> 99 偶数时候才需要补位  
    
          return left.append(right.substring(right.length() - k)).toString(); //如果进位了，舍弃一个'0'  
      }  
  }  
```    
    
### 个人解读    
  思路一：  
  要么是相同位数，要么就是多一位的10000001这种。  
  多一位的前提是string以9开头。95开头？好像10001这种的不存在啊。。。。  
  1299: 1221 和 1331  
  100： 101 99  
  两种算法比较：一个是直接回文后半部分，另一个是在中间提高1  
    
  ```  
  not including itself    
  不包括自身。。。  
  ```  
    
  思路没有错，都是中间不变和+-1  
  主要是在+-1时候会发生进位和退位的问题，  
  1、通过int整体++，而不要通过string进行判断  
  2、关于nearestPalindrom，可以说里面的k、p两个参数用的很巧妙了，目前还是主要通过举例子理解。  
    
tags:    
  -  回文  
  -  数字逻辑  
  -  数学  
