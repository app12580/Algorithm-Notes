### description    
  Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.  
    
  For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".  
    
  Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.  
    
  We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?  
    
  Example 1:  
    
  Input: ["tars","rats","arts","star"]  
  Output: 2  
  Note:  
    
  A.length <= 2000  
  A[i].length <= 1000  
  A.length * A[i].length <= 20000  
  All words in A consist of lowercase letters only.  
  All words in A have the same length and are anagrams of each other.  
  The judging time limit has been increased for this question.  
### solution    
```    
// 方法一： 并查集  
Runtime: 205 ms, faster than 61.25% of Java online submissions for Similar String Groups.  
Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Similar String Groups.  
  class Solution {  
     int[] f;  
      int count;  
      public int numSimilarGroups(String[] A) {  
          Set<String> set = new HashSet<>();  
          for(String a: A) {  
              set.add(a);  
          }  
          A = set.toArray(new String[set.size()]);  
          int len = A.length;  
          f = new int[len];  
          count = len;  
          for(int i = 0; i < len; i++) f[i] = i;  
          for(int i = 0; i < len; i++) {  
              for(int j = i + 1; j < len; j++) {  
                  if(isDiff(i, j) && similar(A[i], A[j])) {  
                      union(i, j);  
                  }  
              }  
          }  
          return count;  
      }  
    
      private boolean similar(String s1, String s2) {  
          int c1 = -1;  
          boolean first = false;  
          boolean second = false;  
          for(int i = 0; i < s1.length(); i++) {  
              if(s1.charAt(i) != s2.charAt(i)) {  
                  if(!first) {  
                      first = true;  
                      c1 = i;  
                  } else if(!second) {  
                      second = true;  
                      if(s1.charAt(i) != s2.charAt(c1) || s1.charAt(c1) != s2.charAt(i)) {  
                          return false;  
                      }  
                  } else {  
                      return false;  
                  }  
              }  
          }  
          return first && second;  
      }  
    
      private boolean isDiff(int x, int y) {  
          x = find(x); y = find(y);  
          return x != y;  
      }  
    
      public int find(int x) {  
          if (x != f[x]) {  
              f[x] = find(f[x]);  
          }  
          return f[x];  
      }  
      public void union(int x, int y) {  
          x = find(x); y = find(y);  
          if (x != y) {  
              f[x] = y;  
              count--;  
          }  
      }  
  }  
```    
    
### 个人解读    
  并查集题目，O(n^2)复杂度  
    
  注意细节：  
  1、输入所有单词长度相等  
  2、输入的可能出现重复单词  
    
tags:    
  -  并查集  
