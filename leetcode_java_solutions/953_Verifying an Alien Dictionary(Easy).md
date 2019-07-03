### description    
  In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.  
    
  Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.  
    
     
    
  Example 1:  
    
  Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"  
  Output: true  
  Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.  
  Example 2:  
    
  Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"  
  Output: false  
  Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.  
  Example 3:  
    
  Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"  
  Output: false  
  Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).  
     
    
  Note:  
    
  1 <= words.length <= 100  
  1 <= words[i].length <= 20  
  order.length == 26  
  All characters in words[i] and order are english lowercase letters.  
    
  判断words是否按照order的顺序。  
### solution    
```    
  class Solution {  
        public boolean isAlienSorted(String[] words, String order) {  
          int[] index = new int[26];  
          for(int i = 0; i < order.length(); i++) {  
              index[order.charAt(i) - 'a'] = i;      
          }  
            
          for(int i = 0; i < words.length - 1; i++) {  
              if(!check(words[i], words[i+1], index)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
      private boolean check(String word1, String word2, int[] index) {  
          for(int i = 0; i < word1.length(); i++) {  
              char c1 = word1.charAt(i);  
              if(i == word2.length()) {       
                  return false;  
              }  
              char c2 = word2.charAt(i);  
              int i1 = index[c1 - 'a'];  
              int i2 = index[c2 - 'a'];  
              if(i1 < i2) {  
                  return true;  
              } else if(i1 > i2) {  
                  return false;  
              }  
    
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  需要先转化order成map，方便通过字符找到位置。然后比较，写个辅助函数。  
  注意两个细节：  
  ```  
   if(i == word2.length()) {       
        return false;  
    }  
    
  return true;  
  ```  
  这两个地方是互相匹配的，运行出for后，说明word1和word2前面都一样，并且word1较短，  
    
tags:    
  -  字符串  
