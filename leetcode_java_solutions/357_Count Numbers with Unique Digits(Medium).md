### description    
  Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.  
    
  Example:  
    
  Input: 2  
  Output: 91   
  Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,   
               excluding 11,22,33,44,55,66,77,88,99  
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Numbers with Unique Digits.  
Memory Usage: 32.9 MB, less than 12.76% of Java online submissions for Count Numbers with Unique Digits.  
  
  class Solution {  
      public int countNumbersWithUniqueDigits(int n) {  
          if(n == 0) return 1;  
         if(n == 1) {  
              return 10;  
          }  
          if(n >= 11) {  
              return countNumbersWithUniqueDigits(10);  
          }  
          int count = 9;  
          int digit = n;  
    
          int res = 0;  
          int temp = 1;  
          //不含有0的情况下，有多少个不出现重复的数字  
          for(int i = 0; i < n; i++) {  
              temp *= count--;  
          }  
          res += temp;  
          count = 9;  
          temp = n - 1;  
          //含有0的情况下：  
          for(int i = 0; i < n - 1; i++) {  
              temp *= count--;  
          }  
          res += temp;  
          return res + countNumbersWithUniqueDigits(n - 1);  
      }  
  }  
```    
    
### 个人解读    
  这个题目类似于那个把123456789101112...，然后去找第n个数字是多少的题目。  
  n=1: 10  
  n=2: 100 - 9  
  n=3: 1000 - 9 * 9  
  ...  
  n=10: 0  
    
  需要用到反转法，统计有多少个出了重复数字的数  
    n=1: 0  
    n=2: 9  
    n=3: 9 + 9 * (10 + 9 * 与最高为重复的情形)  
      
  9 * (fn-1 + 9 * fn-2 + 9 * 8 * )  
  想不下去了。。。  
    
  这题目说白了，其实就是高中数学的排列组合填空题的水准。  
  如果直接从递归关系开始考虑。首先最高位有9种情况，然后剩下的位还需要分为有无0的情况。记住之后还需要加上Fn-1  
  如果不含0：  剩下的位数进行排列组合  
  如果含有0： 剩下的位数先有0，然后再进行计算其他。  
  所以需要的变量：剩余多少位，除了0以外还有多少个数字。  
    
  本题目需要认识到是一个纯的数学问题，要记住以前高中时候的经验。  
    
tags:    
  -  重点数学  
