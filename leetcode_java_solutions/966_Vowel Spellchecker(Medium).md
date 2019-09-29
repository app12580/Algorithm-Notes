### description    
  Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.  
    
  For a given query word, the spell checker handles two categories of spelling mistakes:  
    
  Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.  
  Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"  
  Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"  
  Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"  
  Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.  
  Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"  
  Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)  
  Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)  
  In addition, the spell checker operates under the following precedence rules:  
    
  When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.  
  When the query matches a word up to capitlization, you should return the first such match in the wordlist.  
  When the query matches a word up to vowel errors, you should return the first such match in the wordlist.  
  If the query has no matches in the wordlist, you should return the empty string.  
  Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].  
    
     
    
  Example 1:  
    
  Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]  
  Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]  
     
    
  Note:  
    
  1 <= wordlist.length <= 5000  
  1 <= queries.length <= 5000  
  1 <= wordlist[i].length <= 7  
  1 <= queries[i].length <= 7  
  All strings in wordlist and queries consist only of english letters.  
### solution    
```    
Runtime: 28 ms, faster than 53.44% of Java online submissions for Vowel Spellchecker.  
Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Vowel Spellchecker.  
  
  class Solution {  
       private Set<Character> vowelSet = new HashSet<Character>(Arrays.asList(new Character[]{'a','A','e','E','i','I','o','O','u','U',}));  
      public String[] spellchecker(String[] wordlist, String[] queries) {  
          Set<String> sameSet = new HashSet<>();  
          Map<String, String> capMap = new HashMap<>();  
          Map<String, String> vowelMap = new HashMap<>();  
          for(String word: wordlist) {  
              sameSet.add(word);  
              String cap = toLowerCase(word);  
              if(!capMap.containsKey(cap)) {  
                  capMap.put(cap, word);  
              }  
              String v = helper2(word);  
              if(!vowelMap.containsKey(v)) {  
                  vowelMap.put(v, word);  
              }  
          }  
          String[] res = new String[queries.length];  
          for(int i = 0; i < queries.length; i++) {  
              String q = queries[i];  
              if(sameSet.contains(q)) {  
                  res[i] = q;  
                  continue;  
              }  
              if(capMap.containsKey(toLowerCase(q))) {  
                  res[i] = capMap.get(toLowerCase(q));  
                  continue;  
              }  
              if(vowelMap.containsKey(helper2(q))) {  
                  res[i] = vowelMap.get(helper2(q));  
                  continue;  
              }  
              res[i] = "";  
          }  
          return res;  
      }  
    
      private String toLowerCase(String word) {  
          char[] chars = word.toCharArray();  
          for(int i = 0; i < chars.length; i++) {  
              chars[i] = Character.toLowerCase(chars[i]);  
          }  
          return new String(chars);  
      }  
    
      private String helper2(String word) {  
          char[] chars = word.toCharArray();  
          for(int i = 0; i < chars.length; i++) {  
              if(vowelSet.contains(chars[i])) {  
                  chars[i] = '_';  
              } else {  
                  chars[i] = Character.toLowerCase(chars[i]);  
              }  
          }  
          return new String(chars);  
      }  
  }  
```    
    
### 个人解读    
  流水账试一下。  
    
  思路一： 通过三个Map来进行三种规则的匹配。  
    
    
tags:    
  -  流水账  
