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
  class Trie {
  
      private class Node {
          Node[] childs = new Node[26];
          boolean isLeaf;
      }
  
      private Node root = new Node();
  
      public Trie() {
      }
  
      public void insert(String word) {
          insert(word, root);
      }
  
      private void insert(String word, Node node) {
          if (node == null) return;
          if (word.length() == 0) {
              node.isLeaf = true;
              return;
          }
          int index = indexForChar(word.charAt(0));
          if (node.childs[index] == null) {
              node.childs[index] = new Node();
          }
          insert(word.substring(1), node.childs[index]);
      }
  
      public boolean search(String word) {
          return search(word, root);
      }
  
      private boolean search(String word, Node node) {
          if (node == null) return false;
          if (word.length() == 0) return node.isLeaf;
          int index = indexForChar(word.charAt(0));
          return search(word.substring(1), node.childs[index]);
      }
  
      public boolean startsWith(String prefix) {
          return startWith(prefix, root);
      }
  
      private boolean startWith(String prefix, Node node) {
          if (node == null) return false;
          if (prefix.length() == 0) return true;
          int index = indexForChar(prefix.charAt(0));
          return startWith(prefix.substring(1), node.childs[index]);
      }
  
      private int indexForChar(char c) {
          return c - 'a';
      }
  }
```  
  
### 个人解读  
  在计算机科学中，Trie，又称字典树、单词查找树或键树，是一种树形结构，是一种哈希树的变种。典型应用是用于统计，排序和保存大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。它的优点是：利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较，查询效率比哈希树高。
  
tags:  
  -  字典树  
  -  模拟  
