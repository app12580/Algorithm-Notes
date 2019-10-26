### description    
  Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.  
  A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.  
    
  Example:  
  Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]  
    
  Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]  
    
  Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";   
   "dogcatsdog" can be concatenated by "dog", "cats" and "dog";   
  "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".  
  Note:  
  The number of elements of the given array will not exceed 10,000  
  The length sum of elements in the given array will not exceed 600,000.  
  All the input string will only include lower case letters.  
  The returned elements order does not matter.  
### solution    
```    
// 方法一： 应用Trie， 需要注意""空字符串的处理  
Runtime: 43 ms, faster than 89.62% of Java online submissions for Concatenated Words.  
Memory Usage: 52.5 MB, less than 57.14% of Java online submissions for Concatenated Words.  
  
  class Solution {  
     class Trie{  
          boolean isWord;  
          Trie[] next = new Trie[26];  
      }  
      public List<String> findAllConcatenatedWordsInADict(String[] words) {  
          List<String> res = new ArrayList<>();  
          if(words.length == 0) return res;  
          Trie root = new Trie();  
          for(String w: words) {  
              if("".equals(w)) continue;  
              build(root, w);  
          }  
          for(String w: words) {  
              if("".equals(w)) continue;  
              if(dfs(w.toCharArray(), root, 0)) {  
                  res.add(w);  
              }  
          }  
          return res;  
      }  
    
      // 如果从index开始能匹配到，返回true。  
      private boolean dfs(char[] chars, Trie root, int index) {  
          if(index == chars.length) {  
              return true;  
          }  
          Trie node = root;  
          for(int i = index; i < chars.length; i++) {  
              int cur = chars[i] - 'a';  
              if(node.next[cur] == null) {  
                  return false;  
              }   
              node = node.next[cur];  
              if(node.isWord) {  
                  if(index == 0 && i == chars.length - 1) return false;  
                  if(dfs(chars, root, i + 1)) return true;  
              }   
          }  
          return false;  
      }  
      private void build(Trie root, String w) {  
          Trie node = root;  
          for(char ch: w.toCharArray()) {  
              if(node.next[ch - 'a'] == null) {  
                  node.next[ch - 'a'] = new Trie();  
              }  
              node = node.next[ch - 'a'];  
          }  
          node.isWord = true;  
      }  
  }  
```    
    
### 个人解读    
  如果暴力法遍历的话，复杂度太高，然后联想到把很多string赛在一起，就想到了Trie。  
    
  总结：  
  1、注意DFS如何规避出i==0,end==len-1这种特殊情况的  
  2、注意""空字符串的处理，直接跳过循环即可。  
    
tags:    
  -  Trie  
  -  DFS  
