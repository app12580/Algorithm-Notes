### description    
  Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.  
    
  Note:  
    
  The same word in the dictionary may be reused multiple times in the segmentation.  
  You may assume the dictionary does not contain duplicate words.  
  Example 1:  
    
  Input: s = "leetcode", wordDict = ["leet", "code"]  
  Output: true  
  Explanation: Return true because "leetcode" can be segmented as "leet code".  
  Example 2:  
    
  Input: s = "applepenapple", wordDict = ["apple", "pen"]  
  Output: true  
  Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".  
               Note that you are allowed to reuse a dictionary word.  
  Example 3:  
    
  Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]  
  Output: false  
    
    
### solution    
```    
  class Solution {  
      public boolean wordBreak(String s, List<String> wordDict) {  
            boolean[] dp = new boolean[s.length() + 1];  
          dp[0] = true;  
          for(int j = 1; j <= s.length(); j++) {  
              for(String word: wordDict) {  
                      if(j < word.length()) continue;  
                      if(word.equals(s.substring(j-word.length(), j)))  
                      dp[j] = dp[j] || dp[j-word.length()];  
              }  
          }  
          return dp[s.length()];  
      }  
  }  
```    
    
### 个人解读    
  完全背包问题  
  List<String>里面每一个String作为元素，判断他们有没有。  
  背包问题的dp[j] = dp[j] || dp[j-w]类型。  
  外层dp内层元素循环问题。  
    
tags:    
  -  动态规划  
  -  完全背包  
  -  问题变种  
