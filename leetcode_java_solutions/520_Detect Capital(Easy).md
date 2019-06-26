### description    
  Given a word, you need to judge whether the usage of capitals in it is right or not.  
    
  We define the usage of capitals in a word to be right when one of the following cases holds:  
    
  All letters in this word are capitals, like "USA".  
  All letters in this word are not capitals, like "leetcode".  
  Only the first letter in this word is capital, like "Google".  
  Otherwise, we define that this word doesn't use capitals in a right way.  
     
    
  Example 1:  
    
  Input: "USA"  
  Output: True  
     
    
  Example 2:  
    
  Input: "FlaG"  
  Output: False  
     
    
  Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.  
### solution    
```    
  class Solution {  
      public boolean detectCapitalUse(String word) {  
          if(word.length() == 1) {  
              return true;  
          }  
          boolean first = word.charAt(0) <= 'Z';  
          boolean second = word.charAt(1) <= 'Z';  
          if(!first && second) {  
              return false;  
          }  
          for(int i = 2; i < word.length(); i++) {  
              char ch = word.charAt(i);  
              if(ch <= 'Z') {  
                  //如果当前是大写  
                  if(!second || !first) {  
                      return false;  
                  }  
              } else {  
                  //如果当前是小写  
                  if(second) {  
                      return false;  
                  }  
              }  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  分类讨论，需要想好各种细节。  
  经分析，发现word类型只和前两位有关。并且，前两位确定后，就能明确唯一的类型了。  
    
tags:    
  -  数学  
  -  分类讨论  
