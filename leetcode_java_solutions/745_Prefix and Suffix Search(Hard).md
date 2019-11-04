### description    
  Given many words, words[i] has weight i.  
    
  Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.  
    
  Examples:  
    
  Input:  
  WordFilter(["apple"])  
  WordFilter.f("a", "e") // returns 0  
  WordFilter.f("b", "") // returns -1  
     
    
  Note:  
    
  words has length in range [1, 15000].  
  For each test case, up to words.length queries WordFilter.f may be made.  
  words[i] has length in range [1, 10].  
  prefix, suffix have lengths in range [0, 10].  
  words[i] and prefix, suffix queries consist of lowercase letters only.  
### solution    
```    
  // 方法一： trie  
    
  将前缀和后缀拼在一起，共同组建trie  
  Runtime: 179 ms, faster than 98.45% of Java online submissions for Prefix and Suffix Search.  
  Memory Usage: 100.7 MB, less than 12.50% of Java online submissions for Prefix and Suffix Search.  
  class TrieNode {  
      TrieNode[] children;  
      int weight;  
      public TrieNode() {  
          children = new TrieNode[27]; // 'a' - 'z' and '{'. 'z' and '{' are neighbours in ASCII table  
          weight = 0;  
      }  
  }  
    
  public class WordFilter {  
      TrieNode root;  
      public WordFilter(String[] words) {  
          root = new TrieNode();  
          for (int weight = 0; weight < words.length; weight++) {  
              String word = words[weight] + "{";  
              for (int i = 0; i < word.length(); i++) {  
                  TrieNode cur = root;  
                  cur.weight = weight;  
      // add "apple{apple", "pple{apple", "ple{apple", "le{apple", "e{apple", "{apple" into the Trie Tree  
                  for (int j = i; j < 2 * word.length() - 1; j++) {  
                      int k = word.charAt(j % word.length()) - 'a';  
                      if (cur.children[k] == null)  
                          cur.children[k] = new TrieNode();  
                      cur = cur.children[k];  
                      cur.weight = weight;  
                  }  
              }  
          }  
      }  
      public int f(String prefix, String suffix) {  
          TrieNode cur = root;  
          for (char c: (suffix + '{' + prefix).toCharArray()) {  
              if (cur.children[c - 'a'] == null) {  
                  return -1;  
              }  
              cur = cur.children[c - 'a'];  
          }  
          return cur.weight;  
      }  
  }  
    
  // 方法一： startWith 暴力法  
  // 从大到小通过startWith来判断每个word是否符合要求  
  Runtime: 1739 ms, faster than 7.42% of Java online submissions for Prefix and Suffix Search.  
  Memory Usage: 71.4 MB, less than 87.50% of Java online submissions for Prefix and Suffix Search.  
  class WordFilter {  
      String[] input;  
      public WordFilter(String[] words) {  
          input = words;  
      }  
      public int f(String prefix, String suffix) {  
          for(int i = input.length-1; i >= 0; i--){  
              if(input[i].startsWith(prefix) && input[i].endsWith(suffix)) return i;  
          }  
          return -1;  
      }  
  }  
```    
    
### 个人解读    
   大概率使用Trie。  
   前序好办，问题是逆序怎么说？可能是要创建两个Trie，一个正向，一个逆向  
   在Trie里面用一个List存储经过该节点的所有权重。最后两个取交集。  
     
   https://leetcode.com/problems/prefix-and-suffix-search/discuss/144432/Java-Beat-95-just-small-modifications-in-implementing-Trie.  
     
   Take "apple" as an example, we will insert add "apple{apple", "pple{apple", "ple{apple", "le{apple", "e{apple", "{apple" into the Trie Tree.  
   If the query is: prefix = "app", suffix = "le", we can find it by querying our trie for  
   "le { app".  
   We use '{' because in ASCii Table, '{' is next to 'z', so we just need to create new TrieNode[27] instead of 26. Also, compared with tradition Trie, we add the attribute weight in class TrieNode.  
     
   总结：  
   1、原本思路因为查两遍+查完以后还要筛选结果，导致复杂度很大  
   2、所以说，要想办法查一遍即可，并且直接能返回权重。  
   3、因此想出来了将前后缀拼一块计算的情况  
   4、这样子会倍增构建trie的开销，但是将指数筛选变成了O(1)操作  
     
   ```  
   // TLE 没有解决，大量重复字符串的情况，比如1000个"apple"，就要构建1000次。  
   class WordFilter {  
       class Trie {  
           List<Integer> list;  
           Trie[] children;  
     
           public Trie() {  
               list = new ArrayList<>();  
               children = new Trie[26];  
           }  
       }  
     
       Trie forwardRoot;  
       Trie reverseRoot;  
     
       public WordFilter(String[] words) {  
           forwardRoot = new Trie();  
           reverseRoot = new Trie();  
           for(int i = 0; i < words.length; i++) {  
               String word = words[i];  
               buildTrie(word, true, i);  
               buildTrie(word, false, i);  
     
           }  
       }  
     
       private void buildTrie(String word, boolean isReverse, int rate) {  
           Trie root = forwardRoot;  
           if(isReverse) root = reverseRoot;  
           root.list.add(rate);  
           int len = word.length();  
           for(int i = 0; i < word.length(); i++) {  
               int cur = word.charAt(i) - 'a';  
               if(isReverse) {  
                   cur = word.charAt(len - i - 1) - 'a';  
               }  
               if(root.children[cur] == null) {  
                   root.children[cur] = new Trie();  
               }  
               root = root.children[cur];  
               root.list.add(rate);  
           }  
       }  
     
       public int f(String prefix, String suffix) {  
           List<Integer> list1 = search(forwardRoot, prefix);  
           List<Integer> list2 = search(reverseRoot, suffix);  
           if(list1 == null || list2 == null) return -1;  
           int res = -1;  
           for(int i: list1) {  
               if(list2.contains(i)) {  
                   res = Math.max(res, i);  
               }  
           }  
           return res;  
       }  
     
        private List<Integer> search(Trie root, String prefix) {  
           Trie node = root;  
           if(root == reverseRoot) prefix = new StringBuilder(prefix).reverse().toString();  
           for(int i = 0; i < prefix.length(); i++) {  
               int cur = prefix.charAt(i) - 'a';  
               if(node.children[cur] == null) return null;  
               node = node.children[cur];  
           }  
           return node.list;  
       }  
   }  
   ```  
    
tags:    
  -    
