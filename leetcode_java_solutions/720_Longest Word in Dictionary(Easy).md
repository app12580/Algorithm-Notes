### description    
  Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.  
    
  If there is no answer, return the empty string.  
  Example 1:  
  Input:   
  words = ["w","wo","wor","worl", "world"]  
  Output: "world"  
  Explanation:   
  The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".  
  Example 2:  
  Input:   
  words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]  
  Output: "apple"  
  Explanation:   
  Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".  
  Note:  
    
  All the strings in the input will only contain lowercase letters.  
  The length of words will be in the range [1, 1000].  
  The length of words[i] will be in the range [1, 30].  
    
  注：每次只是在末尾加单词。  
### solution    
```    
  
效率还行吧  
Runtime: 13 ms, faster than 66.46% of Java online submissions for Longest Word in Dictionary.  
Memory Usage: 37.9 MB, less than 97.51% of Java online submissions for Longest Word in Dictionary.  
    
  class Solution {  
      public String longestWord(String[] words) {  
         Arrays.sort(words, new Comparator<String>() {  
              @Override  
              public int compare(String o1, String o2) {  
                  if(o1.length() != o2.length()) return o1.length() - o2.length();  
                  return o1.compareTo(o2) * -1;  
              }  
          });  
          Set<String> set = new HashSet<>();  
          String res = null;  
          for(String word: words) {  
              if(word.length() == 1 || set.contains(word.substring(0, word.length() - 1))) {  
                  res = word;  
                  set.add(word);  
              }   
          }  
          return res;  
      }    
  }  
    
```    
    
### 个人解读    
  
傻逼题目描述，还好机智，每次都要测试用例去反推题目描述。  
 be built one character at a time by other words in words. 这句话有说要在末尾加字符吗？？？？  
```  
  Your input  ["w","aw"]  
  Expected  "w"  
  
```  
    
  思路：需要靠一个数据结构去存储遍历结果。  
  顺序怎么办，难道要先排序么。  
    
tags:    
  -  题目描述  
  -  预处理  
