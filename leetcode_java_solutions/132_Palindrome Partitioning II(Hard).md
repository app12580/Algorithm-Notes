### description    
  Given a string s, partition s such that every substring of the partition is a palindrome.  
    
  Return the minimum cuts needed for a palindrome partitioning of s.  
    
  Example:  
    
  Input: "aab"  
  Output: 1  
  Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.  
### solution    
```    
// 方法一： 二维DP  
Runtime: 7 ms, faster than 71.19% of Java online submissions for Palindrome Partitioning II.  
Memory Usage: 35.9 MB, less than 70.83% of Java online submissions for Palindrome Partitioning II.  
  
  class Solution {  
      public int minCut(String s) {  
         int len = s.length();  
          boolean[][] dp = new boolean[len][len];  
          int[] min = new int[len];  
          for(int i = 0; i < len; i++) {  
              min[i] = i;  
              for(int j = 0; j <= i; j++) {  
                  if(s.charAt(i) == s.charAt(j) && (i < j + 2 || dp[i - 1][j + 1])) {  
                      //如果能够回文  
                      dp[i][j] = true;  
                      min[i] = j == 0 ? 0 : Math.min(min[i], min[j - 1] + 1);  
                  }  
              }  
          }  
          return min[len - 1];  
      }  
    
     
  }  
```    
    
### 个人解读    
  思路一：贪婪算法，左边尽可能的大，但是经过脑内推算和实际距离，发现并不是这么简单的。  
  aabaabaaaab  
  aabaabaa aa b  
  aabaa baaaab  
  所以感觉要修正成，要么左边最大，要么右边最大，最好再结合一下递归  
    
  思路二：  
  用数学方法并不靠谱，还是老老实实遍历把。应用一个二维数组存储回文判断结果  
    
tags:    
  -  多维DP  
  -  字符串  
  -  回文  
