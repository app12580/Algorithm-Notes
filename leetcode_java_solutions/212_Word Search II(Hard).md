### description    
  Given a 2D board and a list of words from the dictionary, find all words in the board.  
    
  Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.  
    
     
    
  Example:  
    
  Input:   
  board = [  
    ['o','a','a','n'],  
    ['e','t','a','e'],  
    ['i','h','k','r'],  
    ['i','f','l','v']  
  ]  
  words = ["oath","pea","eat","rain"]  
    
  Output: ["eat","oath"]  
     
    
  Note:  
    
  All inputs are consist of lowercase letters a-z.  
  The values of words are distinct.  
### solution    
```    
Runtime: 10 ms, faster than 90.53% of Java online submissions for Word Search II.  
Memory Usage: 46.5 MB, less than 64.44% of Java online submissions for Word Search II.  
  
  import java.util.HashSet;  
  import java.util.LinkedList;  
  import java.util.List;  
  import java.util.Set;  
    
  public class Solution {  
      public List<String> findWords(char[][] board, String[] words) {  
          List<String> res = new ArrayList<>();  
          TrieNode trie = buildTrie(words);  
          for (int i = 0; i < board.length; i++) {  
              for (int j = 0; j < board[0].length; j++) {  
                  dfs(board, i, j, trie, res);  
              }  
          }  
          return res;  
      }  
    
      private void dfs(char[][] board, int i, int j, TrieNode trie, List<String> res) {  
          char ch = board[i][j];  
          if (ch == '#' || trie.next[ch - 'a'] == null) return;  
          trie = trie.next[ch - 'a'];  
          if (trie.word != null) {  
              res.add(trie.word);  
              trie.word = null;  
          }  
    
          board[i][j] = '#';  
          if (i > 0) dfs(board, i - 1, j, trie, res);  
          if (j > 0) dfs(board, i, j - 1, trie, res);  
          if (i < board.length - 1) dfs(board, i + 1, j, trie, res);  
          if (j < board[0].length - 1) dfs(board, i, j + 1, trie, res);  
          board[i][j] = ch;  
      }  
    
      private TrieNode buildTrie(String[] words) {  
          TrieNode root = new TrieNode();  
          for (String w : words) {  
              TrieNode cur = root;  
              for (char ch : w.toCharArray()) {  
                  int index = ch - 'a';  
                  if (cur.next[index] == null) {  
                      cur.next[index] = new TrieNode();  
                  }  
                  cur = cur.next[index];  
              }  
              cur.word = w;  
          }  
          return root;  
      }  
    
      class TrieNode {  
          TrieNode[] next = new TrieNode[26];  
          String word;  
      }  
  }  
     
```    
    
### 个人解读    
  DFS不可避免，问题在于正常的DFS只能存储单向的信息，如何在多个DFS中同时将所有信息汇总在一块，因为dfs只能一个一个遍历，所以就变成了一次遍历时候，要关联到所有word的信息。  
  那么有没有一种数据结构可以把多个String给整合到一起呢？答案就是Trie  
    
tags:    
  -  矩阵  
  -  字符串  
  -  DFS  
  -  自主Trie  
