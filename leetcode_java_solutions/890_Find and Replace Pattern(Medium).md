### description    
  You have a list of words and a pattern, and you want to know which words in words matches the pattern.  
    
  A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.  
    
  (Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)  
    
  Return a list of the words in words that match the given pattern.   
    
  You may return the answer in any order.  
    
     
    
  Example 1:  
    
  Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"  
  Output: ["mee","aqq"]  
  Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.   
  "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,  
  since a and b map to the same letter.  
     
    
  Note:  
    
  1 <= words.length <= 50  
  1 <= pattern.length = words[i].length <= 20  
### solution    
```    
Runtime: 2 ms, faster than 80.47% of Java online submissions for Find and Replace Pattern.  
Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Find and Replace Pattern.  
  
  class Solution {  
      public List<String> findAndReplacePattern(String[] words, String pattern) {  
          List<String> res = new ArrayList<>();  
          String target = feature(pattern);  
          for(String s: words) {  
              if(target.equals(feature(s))) {  
                  res.add(s);  
              }  
          }  
          return res;  
      }  
    
      private String feature(String str) {  
          Map<Character, Character> map = new HashMap<>();  
          char index = 'a';  
          StringBuilder builder = new StringBuilder();  
          for(char ch: str.toCharArray()) {  
              if(!map.containsKey(ch)) {  
                  map.put(ch, index++);  
              }  
              builder.append(map.get(ch));  
          }  
          return builder.toString();  
      }  
    
  }  
    
    
```    
    
### 个人解读    
  感觉这个题目好像见过啊  
    
  思路一：用一个特征值来描述每个字符串的，然后去找特征值一致的。  
  效率还可以嗷~  
    
tags:    
  -  字符串解析  
