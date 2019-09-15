### description    
  Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.  
    
  Example :  
  Input:   
  S = "abcde"  
  words = ["a", "bb", "acd", "ace"]  
  Output: 3  
  Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".  
  Note:  
    
  All words in words and S will only consists of lowercase letters.  
  The length of S will be in the range of [1, 50000].  
  The length of words will be in the range of [1, 5000].  
  The length of words[i] will be in the range of [1, 50].  
### solution    
```    
// 方法一 流水账  
Runtime: 409 ms, faster than 21.60% of Java online submissions for Number of Matching Subsequences.  
Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for Number of Matching Subsequences.  
  
  
  class Solution {  
       public int numMatchingSubseq(String S, String[] words) {  
          int res = 0;  
          for(String word: words) {  
              if(check(S, word)) res++;  
          }  
          return res;  
      }  
    
      private boolean check(String s, String word) {  
          int i = 0;  
          int j = 0;  
          while(i < s.length() && j < word.length()) {  
              while(i < s.length() && s.charAt(i) != word.charAt(j)) {  
                  i++;  
              }  
              if(i == s.length()) return false;  
              j++;  
              i++;  
          }  
          return j == word.length();  
      }  
  }  
```    
    
### 个人解读    
  这个题目有点意义不明啊。。。不知道有什么优化的地方。  
    
  看了下优化，只是用了一个map存储已经算过的word，前提是input中含有大量重复内容，不然没必要。  
    
tags:    
  -  字符串  
  -  双指针  
