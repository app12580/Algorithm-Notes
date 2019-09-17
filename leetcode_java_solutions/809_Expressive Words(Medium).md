### description    
  Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".  
    
  For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.  
    
  For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.  
    
  Given a list of query words, return the number of words that are stretchy.   
    
     
    
  Example:  
  Input:   
  S = "heeellooo"  
  words = ["hello", "hi", "helo"]  
  Output: 1  
  Explanation:   
  We can extend "e" and "o" in the word "hello" to get "heeellooo".  
  We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.  
     
    
  Notes:  
    
  0 <= len(S) <= 100.  
  0 <= len(words) <= 100.  
  0 <= len(words[i]) <= 100.  
  S and all words in words consist only of lowercase letters  
### solution    
```    
  
Runtime: 1 ms, faster than 100.00% of Java online submissions for Expressive Words.  
Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Expressive Words.  
  
  class Solution {  
    public int expressiveWords(String S, String[] words) {  
          int res = 0;  
          for(String word: words) {  
              if(check(word, S)) {  
                  res++;  
              }  
          }  
          return res;  
      }  
    
      // hello    heeeelllllooj  
      private boolean check(String word, String max) {  
          if(word.length() > max.length()) return false;  
          int j = 0;  
          for(int i = 0; i < word.length(); i++, j++) {  
              if(j >= max.length()) {  
                  return false;  
              }  
              char ch1 = word.charAt(i);  
              char ch2 = max.charAt(j);  
              if(ch1 == ch2) {  
                  continue;  
              } else if(i == 0) {  
                  return false;  
              } else if(ch2 == max.charAt(j - 1)) {  
                  //判断是否长度为三个  
                  char same = ch2;  
                  while(j > 0 && max.charAt(j - 1) == same) j--;  
                  int count = 1;  
                  while(j < max.length() - 1 && max.charAt(j + 1) == same) {  
                      j++;  
                      count++;  
                  }  
                  if(count < 3) return false;  
                  i--;  
              } else {  
                  return false;  
              }  
          }  
          if(j == max.length()) return true;  
          //j后面至少有两个和j-1是一样的  
          char same = max.charAt(j);  
          while(j > 0 && max.charAt(j - 1) == same) j--;  
          int count = 1;  
          while(j < max.length() - 1 && max.charAt(j + 1) == same) {  
              j++;  
              count++;  
          }  
          return j == max.length() - 1 && count >= 3;  
      }  
    
    
  }  
```    
    
### 个人解读    
  判断字符串是否可以通过扩展单个字符成为目标字符串，使用双指针即可  
    
  反思：  
  那个相同字符长度达到3，是指包括前面的也算进去。  
    
  各种细节拉满了。。。  
    
tags:    
  -  字符串  
  -  双指针  
  -  细节  
