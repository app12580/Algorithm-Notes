### description    
  Implement a trie with insert, search, and startsWith methods.  
    
  Example:  
    
  Trie trie = new Trie();  
    
  trie.insert("apple");  
  trie.search("apple");   // returns true  
  trie.search("app");     // returns false  
  trie.startsWith("app"); // returns true  
  trie.insert("app");     
  trie.search("app");     // returns true  
  Note:  
    
  You may assume that all inputs are consist of lowercase letters a-z.  
  All inputs are guaranteed to be non-empty strings.  
### solution    
```    
  
Runtime: 75 ms, faster than 76.14% of Java online submissions for Implement Trie (Prefix Tree).  
Memory Usage: 50.3 MB, less than 100.00% of Java online submissions for Implement Trie (Prefix Tree).  
  
  
  class Trie {  
    
      private Trie[] children = new Trie[26];  
      private String item = "";  
        
      /** Initialize your data structure here. */  
      public Trie() {  
          Trie[] children = new Trie[26];  
          String item = "";  
      }  
    
      /** Inserts a word into the trie. */  
      public void insert(String word) {  
          Trie node = this;  
          for(int i = 0; i < word.length(); i++) {  
              char c = word.charAt(i);  
              if(node.children[c - 'a'] == null) {  
                  node.children[c - 'a'] = new Trie();  
              }  
              node = node.children[c-'a'];  
          }  
          node.item = word;  
      }  
    
      /** Returns if the word is in the trie. */  
      public boolean search(String word) {  
          return match(this, 0, word, "1");  
      }  
    
      /** Returns if there is any word in the trie that starts with the given prefix. */  
      public boolean startsWith(String prefix) {  
          return match(this, 0, prefix, "2");  
      }  
    
      public boolean match(Trie node, int index, String word, String type) {  
          if(index == word.length()) {  
              if(type.equals("1")) {  
                  return !node.item.equals("");  
              } else {  
                  return true;  
              }  
          }   
          char c = word.charAt(index);  
          if(c == '.') {  
              for(int i = 0; i < 26; i++) {  
                  if(node.children[i] != null) {  
                      if(match(node.children[i], index + 1, word, type)) {  
                          return true;  
                      }  
                  }  
              }  
          } else {  
              if(node.children[c - 'a'] == null) {  
                  return false;  
              } else {  
                  return match(node.children[c - 'a'], index + 1, word, type);  
              }  
          }  
          return false;  
      }  
  }  
    
  /**  
   * Your Trie object will be instantiated and called as such:  
   * Trie obj = new Trie();  
   * obj.insert(word);  
   * boolean param_2 = obj.search(word);  
   * boolean param_3 = obj.startsWith(prefix);  
   */  
```    
    
### 个人解读    
  Trie  
    
tags:    
  -  Trie  
