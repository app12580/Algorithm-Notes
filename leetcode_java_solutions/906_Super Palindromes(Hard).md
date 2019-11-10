### description  
  Let's say a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a palindrome.
  
  Now, given two positive integers L and R (represented as strings), return the number of superpalindromes in the inclusive range [L, R].
  
   
  
  Example 1:
  
  Input: L = "4", R = "1000"
  Output: 4
  Explanation: 4, 9, 121, and 484 are superpalindromes.
  Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
   
  
  Note:
  
  1 <= len(L) <= 18
  1 <= len(R) <= 18
  L and R are strings representing integers in the range [1, 10^18).
  int(L) <= int(R)
   
### solution  
```  
// 方法一： 通过nextP 实现回文数的搜索
Runtime: 258 ms, faster than 27.56% of Java online submissions for Super Palindromes.
Memory Usage: 44.4 MB, less than 25.00% of Java online submissions for Super Palindromes.

  class Solution {
      public int superpalindromesInRange(String L, String R) {
          Long l = Long.valueOf(L), r = Long.valueOf(R);
          int result = 0;
          for (long i = (long)Math.sqrt(l); i * i <= r;) {
              long p = nextP(i);
              if (p * p <= r && isP(p * p)) {
                  result++;
              }
              i = p + 1;
          }
          return result;
      }
      
      private long nextP(long l) {
          String s = "" + l;
          int len = s.length();
          List<Long> cands = new LinkedList<>();
          cands.add((long)Math.pow(10, len) - 1);
          String half = s.substring(0, (len + 1) / 2);
          String nextHalf = "" + (Long.valueOf(half) + 1);
          String reverse = new StringBuilder(half.substring(0, len / 2)).reverse().toString();
          String nextReverse = new StringBuilder(nextHalf.substring(0, len / 2)).reverse().toString();
          cands.add(Long.valueOf(half + reverse));
          cands.add(Long.valueOf(nextHalf + nextReverse));
          long result = Long.MAX_VALUE;
          for (long i : cands) {
              if (i >= l) {
                  result = Math.min(result, i);
              }
          }
          return result;
      }
      
      private boolean isP(long l) {
          String s = "" + l;
          int i = 0, j = s.length() - 1;
          while (i < j) {
              if (s.charAt(i++) != s.charAt(j--)) {
                  return false;
              }
          }
          return true;
      }
  }
```  
  
### 个人解读  
  思路一： 因为平方数要比回文数少很多，所以可以一个一个平方数的去找，看是不是回文。
  然后问题就转变成了，求[L,R]范围内的所有回文数
  
  下一个回文数的搜索：
  以137463为例
  1： 137731
  2： 138831
  3： 999999
  
  如果999这种
  1： 999
  2： 1001   // 这里需要对100进行截取
  3： 999
  
  直接获取L，R区间内的所有回文数比较麻烦，但是一个一个获取，每次只获取较大的那一个就轻松多了，虽然这样效率不高，用一个PQ存起来可能更好一些。
  
tags:  
  -  回文数
  -  数字逻辑
