### description  
  Implement a magic directory with buildDict, and search methods.
  
  For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
  
  For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.
  
  Example 1:
  Input: buildDict(["hello", "leetcode"]), Output: Null
  Input: search("hello"), Output: False
  Input: search("hhllo"), Output: True
  Input: search("hell"), Output: False
  Input: search("leetcoded"), Output: False
  Note:
  You may assume that all the inputs are consist of lowercase letters a-z.
  For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
  Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
### solution  
```  
Runtime: 46 ms, faster than 94.52% of Java online submissions for Implement Magic Dictionary.
Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for Implement Magic Dictionary.


  class MagicDictionary {
  
     private Map<Integer, List<String>> map = new HashMap<>();
      public MagicDictionary() {
          
      }
  
      public void buildDict(String[] dict) {
          for(String s: dict) {
              int len = s.length();
              if(!map.containsKey(len)) {
                  map.put(len, new ArrayList<String>());
              }
              map.get(len).add(s);
          }
      }
  
      public boolean search(String word) {
          int len = word.length();
          if(!map.containsKey(len)) return false;
          List<String> strings = map.get(len);
          for(String s: strings) {
              if(check(s, word)) {
                  return true;
              }
          }
          return false;
      }
  
      private boolean check(String s, String word) {
          boolean hasOne = false;
          for(int i = 0; i < s.length(); i++) {
              if(s.charAt(i) != word.charAt(i)) {
                  if(hasOne) {
                      return false;
                  } else {
                      hasOne = true;
                  }
              }
          }
          return hasOne;
      }
  }
  

```  
  
### 个人解读  
  流水账算法，竟然效率这么高。。。。
  通过map存储单词，然后挨个比较。
  
  
tags:  
  -  模拟
  -  流水账
