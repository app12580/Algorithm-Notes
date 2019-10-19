### description    
  Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.  
    
  Note:  
    
  The same word in the dictionary may be reused multiple times in the segmentation.  
  You may assume the dictionary does not contain duplicate words.  
  Example 1:  
    
  Input:  
  s = "catsanddog"  
  wordDict = ["cat", "cats", "and", "sand", "dog"]  
  Output:  
  [  
    "cats and dog",  
    "cat sand dog"  
  ]  
  Example 2:  
    
  Input:  
  s = "pineapplepenapple"  
  wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]  
  Output:  
  [  
    "pine apple pen apple",  
    "pineapple pen apple",  
    "pine applepen apple"  
  ]  
  Explanation: Note that you are allowed to reuse a dictionary word.  
  Example 3:  
    
  Input:  
  s = "catsandog"  
  wordDict = ["cats", "dog", "sand", "and", "cat"]  
  Output:  
  []  
### solution    
```    
Runtime: 4 ms, faster than 90.67% of Java online submissions for Word Break II.  
Memory Usage: 38.1 MB, less than 80.33% of Java online submissions for Word Break II.  
  
  class Solution {  
     public List<String> wordBreak(String s, List<String> wordDict) {  
          Map<String, List<String>> map = new HashMap<>();  
          return dfs(s, wordDict, map);  
      }  
      List<String> dfs(String s, List<String> wordDict, Map<String, List<String>>map) {  
          if (map.containsKey(s))  
              return map.get(s);  
    
          LinkedList<String>res = new LinkedList<String>();  
          if (s.length() == 0) {  
              res.add("");  
              map.put("", res);  
              return res;  
          }  
          for (String word : wordDict) {  
              if (s.startsWith(word)) {  
                  List<String>sublist = dfs(s.substring(word.length()), wordDict, map);  
                  for (String sub : sublist)  
                      res.add(word + (sub.isEmpty() ? "" : " ") + sub);  
              }  
          }  
          map.put(s, res);      //如果无法进行，这里会变成null  
          return res;  
      }  
  }  
```    
    
### 个人解读    
    
  思路一：  
  一个标准的DFS+回溯问题  
  然而TLE  
  ```  
  aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"  
  ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]  
  ```  
    
  思路二：  
  怎么DFS+回溯总是超时，难道要万物都是DP么！！！  
  如果用DP的话，关键是中间结果变量怎么存储额。。。很容易就弄出来一堆List出来  
  默然回首，发现难道出问题的在于那个b??  
  难道需要优化的并不是如何提高效率，而是要如何去回避不可能的结果？  
  1、i从大到小遍历  
  2、遇到不可捕捉情况，全局终止  
    
    
  思路三：  
  别想什么骚操作了，效率不够就是Dp的事情，不存在什么提前终止的操作。  
  dp：根据位置很难dp，那么就根据初始字符串进行存储。  
    
  总结：  
  1、非索引遍历。用startWith代替  
  2、如果遇到不能继续的，让当前list为null  
    
tags:    
  -  DFS  
  -  字符串分段  
  -  重点题目  
