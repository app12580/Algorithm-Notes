### description    
  Given a string, your task is to count how many palindromic substrings in this string.  
    
  The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.  
    
  Example 1:  
    
  Input: "abc"  
  Output: 3  
  Explanation: Three palindromic strings: "a", "b", "c".  
     
    
  Example 2:  
    
  Input: "aaa"  
  Output: 6  
  Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".  
     
    
  Note:  
    
  The input string length won't exceed 1000.  
### solution    
```    
  class Solution {  
          private int cnt = 0;  
    
      public int countSubstrings(String s) {  
          for(int i = 0; i < s.length(); i++) {  
              extendSubstrings(s, i, i);     // 奇数长度  
              extendSubstrings(s, i, i + 1); // 偶数长度  
          }  
          return cnt;  
      }  
        
      private void extendSubstrings(String s, int start, int end) {  
          while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {  
              start--;  
              end++;  
              cnt++;  
          }  
      }  
  }  
```    
    
### 个人解读    
  讲道理，这种数组的连续子序列问题是最头疼的，因为总觉的时间复杂度会非常高，所以不知道，甚至无法想象那种低时间的解决办法是什么样子的。  
  如果无脑DP的话，那么每次序列扩展一格，都需要从最后一个往前回推看能有多少个满足条件的算法，大概是一种通用解法了吧。  
  看了答案以后的方法优化：无脑DP的方法有一个缺陷，就是每次数组拓展的时候，新的起始点都是在边缘位置，而本题的回文字符串有一个特性，就是从字符串的中间扩展，比从字符串的边缘去拓展要方便的多。  
  DP优化的思路：主要是当DP的思路，转变成了一条路走下来就能获得结果：遍历过程中，获取所以已当前遍历单元为中心的回文字符串数量。  
    
    
tags:    
  -   数组  
  -   连续子序列  
  -   DP优化  
