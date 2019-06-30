### description    
  Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate  
    
  Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.  
    
  It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.  
    
  The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.  
    
  Example 1:  
  Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]  
  Output: "steps"  
  Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".  
  Note that the answer is not "step", because the letter "s" must occur in the word twice.  
  Also note that we ignored case for the purposes of comparing whether a letter exists in the word.  
  Example 2:  
  Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]  
  Output: "pest"  
  Explanation: There are 3 smallest length words that contains the letters "s".  
  We return the one that occurred first.  
  Note:  
  licensePlate will be a string with length in range [1, 7].  
  licensePlate will contain digits, spaces, or letters (uppercase or lowercase).  
  words will have a length in the range [10, 1000].  
  Every words[i] will consist of lowercase letters, and have length in range [1, 15].  
### solution    
```    
  class Solution {  
       public String shortestCompletingWord(String licensePlate, String[] words) {  
          int count = 0;  
          int[] target = new int[26];  
          for(char ch: licensePlate.toCharArray()) {  
              ch = Character.toLowerCase(ch);  
              if(ch >= 'a' && ch <= 'z') {  
                  count++;  
                  target[ch - 'a']++;  
              }  
          }  
          String res = null;  
          for(String word: words) {  
              if(words.length >= count) {  
                  if(res != null && word.length() >= res.length()) {  
                      continue;  
                  }  
                  if(check(word, target)) {  
                      res = word;  
                  }  
                    
              }  
          }  
          return res;  
      }  
    
      private boolean check(String word, int[] target) {  
          int[] copy = new int[target.length];  
          int i = 0;  
          for(int t: target) {  
              copy[i++] = t;  
          }  
            
          for(char ch: word.toCharArray()) {  
              int index = Character.toLowerCase(ch) - 'a';  
              if(index >= 0 && index <= 26) {  
                  copy[index]--;  
              }  
          }  
          for(int c: copy) {  
              if(c > 0) return false;  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  先构建一个char[]来描述达标条件，然后开始遍历数组。  
  只比较小写字母。  
  注意各种细节就好。比如copy数组，比如判定是否合格条件。  
    
    
tags:    
  -  字符串  
  -  遍历  
