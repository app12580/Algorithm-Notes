### description      
  Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.    
      
  Example 1:    
      
  Input: ["abcd","dcba","lls","s","sssll"]    
  Output: [[0,1],[1,0],[3,2],[2,4]]     
  Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]    
  Example 2:    
      
  Input: ["bat","tab","cat"]    
  Output: [[0,1],[1,0]]     
  Explanation: The palindromes are ["battab","tabbat"]    
### solution      
```      
  // 方法一： 基于HashMap    
  Runtime: 66 ms, faster than 57.98% of Java online submissions for Palindrome Pairs.    
  Memory Usage: 44.8 MB, less than 87.81% of Java online submissions for Palindrome Pairs.    
      
      
  public class Solution {    
      public List<List<Integer>> palindromePairs(String[] words) {    
          List<List<Integer>> res = new ArrayList<List<Integer>>();    
          if(words == null || words.length == 0){    
              return res;    
          }    
          //build the map save the key-val pairs: String - idx    
          HashMap<String, Integer> map = new HashMap<>();    
          for(int i = 0; i < words.length; i++){    
              map.put(words[i], i);    
          }    
      
          //special cases: "" can be combine with any palindrome string    
          if(map.containsKey("")){    
              int blankIdx = map.get("");    
              for(int i = 0; i < words.length; i++){    
                  if(isPalindrome(words[i])){    
                      if(i == blankIdx) continue;    
                      res.add(Arrays.asList(blankIdx, i));    
                      res.add(Arrays.asList(i, blankIdx));    
                  }    
              }    
          }    
      
          //find all string and reverse string pairs    
          for(int i = 0; i < words.length; i++){    
              String cur_r = reverseStr(words[i]);    
              if(map.containsKey(cur_r)){    
                  int found = map.get(cur_r);    
                  if(found == i) continue;    
                  res.add(Arrays.asList(i, found));    
              }    
          }    
      
          //find the pair s1, s2 that     
          //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)    
          //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)    
          for(int i = 0; i < words.length; i++){    
              String cur = words[i];    
              for(int cut = 1; cut < cur.length(); cut++){    
                  if(isPalindrome(cur.substring(0, cut))){    
                      String cut_r = reverseStr(cur.substring(cut));    
                      if(map.containsKey(cut_r)){    
                          int found = map.get(cut_r);    
                          if(found == i) continue;    
                          res.add(Arrays.asList(found, i));    
                      }    
                  }    
                  if(isPalindrome(cur.substring(cut))){    
                      String cut_r = reverseStr(cur.substring(0, cut));    
                      if(map.containsKey(cut_r)){    
                          int found = map.get(cut_r);    
                          if(found == i) continue;    
                          res.add(Arrays.asList(i, found));    
                      }    
                  }    
              }    
          }    
      
          return res;    
      }    
      
      public String reverseStr(String str){    
          StringBuilder sb= new StringBuilder(str);    
          return sb.reverse().toString();    
      }    
      
      public boolean isPalindrome(String s){    
          int i = 0;    
          int j = s.length() - 1;    
          while(i <= j){    
              if(s.charAt(i) != s.charAt(j)){    
                  return false;    
              }    
              i++;    
              j--;    
          }    
          return true;    
      }    
  }    
      
      
  //方法二 Trie    
  Runtime: 28 ms, faster than 85.34% of Java online submissions for Palindrome Pairs.    
  Memory Usage: 42.2 MB, less than 100.00% of Java online submissions for Palindrome Pairs.    
      
  public class Solution {    
       private static class TrieNode {    
          TrieNode[] next;    
          int index;    
          List<Integer> list;    
      
          TrieNode() {    
              next = new TrieNode[26];    
              index = -1;    
              list = new ArrayList<>();    
          }    
      }    
      
      public List<List<Integer>> palindromePairs(String[] words) {    
          List<List<Integer>> res = new ArrayList<>();    
      
          TrieNode root = new TrieNode();    
      
          for (int i = 0; i < words.length; i++) {    
              addWord(root, words[i], i);    
          }    
      
          for (int i = 0; i < words.length; i++) {    
              search(words, i, root, res);    
          }    
      
          return res;    
      }    
      
      private void addWord(TrieNode root, String word, int index) {    
          for (int i = word.length() - 1; i >= 0; i--) {    
              int j = word.charAt(i) - 'a';    
      
              if (root.next[j] == null) {    
                  root.next[j] = new TrieNode();    
              }    
      
              if (isPalindrome(word, 0, i)) {    
                  root.list.add(index);    
              }    
      
              root = root.next[j];    
          }    
      
          root.list.add(index);    
          root.index = index;    
      }    
      
      private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {    
          for (int j = 0; j < words[i].length(); j++) {	    
              if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {    
                  res.add(Arrays.asList(i, root.index));    
              }    
      
              root = root.next[words[i].charAt(j) - 'a'];    
              if (root == null) return;    
          }    
      
          for (int j : root.list) {    
              if (i == j) continue;    
              res.add(Arrays.asList(i, j));    
          }    
      }    
      
      private boolean isPalindrome(String word, int i, int j) {    
          while (i < j) {    
              if (word.charAt(i++) != word.charAt(j--)) return false;    
          }    
      
          return true;    
      }    
  }    
```      
      
### 个人解读      
    
方法一： 字符串分割法    
https://leetcode-cn.com/problems/palindrome-pairs/solution/shi-jian-kong-jian-100-cjie-fa-by-ilyee-2/    
1、若该字符串为回文字符串且字典里有空字符串，添加两次（一正一反）    
2、若该字符串不为回文字符串且字典里有该字符串的反转，添加    
3、如果某个字符串能找到一个分割点，分割点前的部分是回文，后半部分翻转后也在数组里，添加    
4、如果某个字符串后部分是回文，前半部分翻转后在数组里，添加    
容易想到    
    
方法二： 基于Trie    
讲道理，将若干个字符串塞进一个数据结构里面，是比较容易想到Trie的。    
与方法一基本一致，通过反向构建Trie来代替String的Reverse操作。      
      
tags:      
  -  字符串  
  -  回文拆解  
