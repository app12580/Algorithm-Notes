### description    
  We have a sorted set of digits D, a non-empty subset of {'1','2','3','4','5','6','7','8','9'}.  (Note that '0' is not included.)  
    
  Now, we write numbers using these digits, using each digit as many times as we want.  For example, if D = {'1','3','5'}, we may write numbers such as '13', '551', '1351315'.  
    
  Return the number of positive integers that can be written (using the digits of D) that are less than or equal to N.  
    
     
    
  Example 1:  
    
  Input: D = ["1","3","5","7"], N = 100  
  Output: 20  
  Explanation:   
  The 20 numbers that can be written are:  
  1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.  
  Example 2:  
    
  Input: D = ["1","4","9"], N = 1000000000  
  Output: 29523  
  Explanation:   
  We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,  
  81 four digit numbers, 243 five digit numbers, 729 six digit numbers,  
  2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.  
  In total, this is 29523 integers that can be written using the digits of D.  
     
    
  Note:  
    
  D is a subset of digits '1'-'9' in sorted order.  
  1 <= N <= 10^9  
### solution    
```    
// 方法一： 数学逻辑 一位一位的比较。  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Numbers At Most N Given Digit Set.  
Memory Usage: 33.9 MB, less than 100.00% of Java online submissions for Numbers At Most N Given Digit Set.  
  
  class Solution {  
     public int atMostNGivenDigitSet(String[] D, int N) {  
          int res = 0;  
          String str = N + "";  
          int digits = str.length();  
          int len = D.length;  
          for(int i = 1; i < digits; i++) {  
              res += Math.pow(len, i);  
          }  
          int[] d = new int[len];  
          for(int i = 0; i < len; i++) {  
              d[i] = Integer.valueOf(D[i]);  
          }  
          // N: 12345 ， 先看万位，然后千。  
          int i = 0;  
          while(i < digits){  
              //i表示索引位置与N相同的部分。  
              int count = 0;  
              while(count < len && d[count] < str.charAt(i) - '0') {  
                  count++;  
              }  
              if(count > 0) res += count * Math.pow(len, digits - i - 1);  
              if(count < len && i < digits && d[count] == str.charAt(i) - '0') {    //需要重点注意这里的条件，错了好几回  
                  i++;  
              } else {  
                  break;  
              }          
          }  
          if(i == digits) res++;  
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  一步一步考虑即可，假设n是6位数，先考虑  
    
tags:    
  -  数学  
  -  数字逻辑  
  -  流水账  
