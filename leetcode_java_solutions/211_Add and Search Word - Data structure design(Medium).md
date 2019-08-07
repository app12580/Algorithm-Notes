### description    
  Favorite  
    
  Share  
  Design a data structure that supports the following two operations:  
    
  void addWord(word)  
  bool search(word)  
  search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.  
    
  Example:  
    
  addWord("bad")  
  addWord("dad")  
  addWord("mad")  
  search("pad") -> false  
  search("bad") -> true  
  search(".ad") -> true  
  search("b..") -> true  
  Note:  
  You may assume that all words are consist of lowercase letters a-z.  
### solution    
```    
  class WordDictionary {  
  public class Trie{  
          public Trie[] children = new Trie[26];  
          public String item = "";  
      }  
    
      private Trie root;  
      public WordDictionary() {  
          root = new Trie();  
      }  
    
      public void addWord(String word) {  
          Trie node = root;  
          for(int i = 0; i < word.length(); i++) {  
              char c = word.charAt(i);  
              if(node.children[c - 'a'] == null) {  
                  node.children[c - 'a'] = new Trie();  
              }  
              node = node.children[c-'a'];  
          }  
          node.item = word;  
      }  
    
      public boolean search(String word) {  
          return match(root, 0, word);  
      }  
    
      public boolean match(Trie node, int index, String word) {  
          if(index == word.length()) return !node.item.equals("");  
          char c = word.charAt(index);  
          if(c == '.') {  
              for(int i = 0; i < 26; i++) {  
                  if(node.children[i] != null) {  
                      if(match(node.children[i], index + 1, word)) {  
                          return true;  
                      }  
                  }  
              }  
          } else {  
              if(node.children[c - 'a'] == null) {  
                  return false;  
              } else {  
                  return match(node.children[c - 'a'], index + 1, word);  
              }  
          }  
          return false;  
      }  
  }  
    
  /**  
   * Your WordDictionary object will be instantiated and called as such:  
   * WordDictionary obj = new WordDictionary();  
   * obj.addWord(word);  
   * boolean param_2 = obj.search(word);  
   */  
```    
    
### 个人解读    
  使用一个叫做Trie的数据结构。这种需要牢记数据结构的定义，具体方法实现可以慢慢考虑。  
    
tags:    
  -  Trie  
