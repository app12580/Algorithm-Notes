### description    
  Implement a MapSum class with insert, and sum methods.  
    
  For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.  
    
  For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.  
    
  Example 1:  
  Input: insert("apple", 3), Output: Null  
  Input: sum("ap"), Output: 3  
  Input: insert("app", 2), Output: Null  
  Input: sum("ap"), Output: 5  
  实现一个 Trie，用来求前缀和  
### solution    
```    
  class MapSum {  
    
      private class Node {  
          Node[] child = new Node[26];  
          int value;  
      }  
    
      private Node root = new Node();  
    
      public MapSum() {  
    
      }  
    
      public void insert(String key, int val) {  
          insert(key, root, val);  
      }  
    
      private void insert(String key, Node node, int val) {  
          if (node == null) return;  
          if (key.length() == 0) {  
              node.value = val;  
              return;  
          }  
          int index = indexForChar(key.charAt(0));  
          if (node.child[index] == null) {  
              node.child[index] = new Node();  
          }  
          insert(key.substring(1), node.child[index], val);  
      }  
    
      public int sum(String prefix) {  
          return sum(prefix, root);  
      }  
    
      private int sum(String prefix, Node node) {  
          if (node == null) return 0;  
          if (prefix.length() != 0) {  
              int index = indexForChar(prefix.charAt(0));  
              return sum(prefix.substring(1), node.child[index]);  
          }  
          int sum = node.value;  
          for (Node child : node.child) {  
              sum += sum(prefix, child);  
          }  
          return sum;  
      }  
    
      private int indexForChar(char c) {  
          return c - 'a';  
      }  
  }  
```    
    
### 个人解读    
  关键点在于只在叶子节点存储数量的信息。    
  private 的实际应用。  
    
tags:    
  -  字典树    
  -  模拟    
