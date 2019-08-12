### Trie
```
 // 基本结构
 public class TrieNode {
        char val;
        TrieNode[] children;
        boolean isWord;

        public TrieNode(char val) {
            this.val = val;
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }
    
    // BuildTrie
     private TrieNode buildTrie(List<String> dict) {
            TrieNode root = new TrieNode(' ');
            for (String word : dict) {
                TrieNode temp = root;
                for (char c : word.toCharArray()) {
                    if (temp.children[c - 'a'] == null) {
                        temp.children[c - 'a'] = new TrieNode(c);
                    }
                    temp = temp.children[c - 'a'];
                }
                temp.isWord = true;
            }
            return root;
        }
```