### description  
  Implement a MapSum class with insert, and sum methods.
  
  For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
  
  For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.
  
  Example 1:
  Input: insert("apple", 3), Output: Null
  Input: sum("ap"), Output: 3
  Input: insert("app", 2), Output: Null
  Input: sum("ap"), Output: 5
### solution  
```  
Runtime: 48 ms, faster than 51.32% of Java online submissions for Map Sum Pairs.
Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Map Sum Pairs.

  class MapSum {
    private TrieNode root;
  
      public MapSum() {
          root = new TrieNode(' ');
      }
  
      public void insert(String key, int val) {
          TrieNode temp = root;
          for(int i = 0; i < key.length(); i++) {
              char c = key.charAt(i);
              if(temp.children[c - 'a'] == null) {
                  temp.children[c - 'a'] = new TrieNode(c);
              }
              temp = temp.children[c - 'a'];
          }
          temp.count = val;
      }
  
      public int sum(String prefix) {
          TrieNode temp = root;
          for(int i = 0; i < prefix.length(); i++) {
              char cur = prefix.charAt(i);
              if(temp.children[cur - 'a'] == null) {
                  return 0;
              } else {
                  temp = temp.children[cur - 'a'];
              }
          }
          return totalSum(temp);
      }
  
      private int totalSum(TrieNode temp) {
          if(temp == null) return 0;
          int res = temp.count;
          for(int i = 0; i < 26; i++) {
              res += totalSum(temp.children[i]);
          }
          return res;
      }
  
  
      public class TrieNode {
          char val;
          TrieNode[] children;
          int count;  //每一个点都要加
  
          public TrieNode(char val) {
              this.val = val;
              this.children = new TrieNode[26];
          }
      }
  }
  
  /**
   * Your MapSum object will be instantiated and called as such:
   * MapSum obj = new MapSum();
   * obj.insert(key,val);
   * int param_2 = obj.sum(prefix);
   */
```  
  
### 个人解读  
  遇到这种字符串的问题，基本上都是使用Trie结构。
  
tags:  
  -  Trie
  -  模擬
