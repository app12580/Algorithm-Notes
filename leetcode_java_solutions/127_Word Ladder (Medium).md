### description    
  Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:  
    
  Only one letter can be changed at a time.  
  Each transformed word must exist in the word list. Note that beginWord is not a transformed word.  
  Note:  
    
  Return 0 if there is no such transformation sequence.  
  All words have the same length.  
  All words contain only lowercase alphabetic characters.  
  You may assume no duplicates in the word list.  
  You may assume beginWord and endWord are non-empty and are not the same.  
  Example 1:  
    
  Input:  
  beginWord = "hit",  
  endWord = "cog",  
  wordList = ["hot","dot","dog","lot","log","cog"]  
    
  Output: 5  
    
  Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",  
  return its length 5.  
  Example 2:  
    
  Input:  
  beginWord = "hit"  
  endWord = "cog"  
  wordList = ["hot","dot","dog","lot","log"]  
    
  Output: 0  
    
  Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.  
    
  题目描述：找出一条从 beginWord 到 endWord 的最短路径，每次移动规定为改变一个字符，并且改变之后的字符串必须在 wordList 中。  
### solution    
```    
  
// 方法一   
Runtime: 224 ms, faster than 25.12% of Java online submissions for Word Ladder.  
Memory Usage: 37.9 MB, less than 99.44% of Java online submissions for Word Ladder.  
  
  
  class Solution {  
       public int ladderLength(String beginWord, String endWord, List<String> wordList) {  
          if(wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) {  
              return 0;  
          }  
          Queue<String> queue = new LinkedList<>();  
          queue.add(beginWord);  
          boolean[] mark = new boolean[wordList.size()];  
          int path = 0;  
          while(!queue.isEmpty()) {  
              path++;  
              int size = queue.size();  
              while(size-- > 0) {  
                  String poll = queue.poll();  
                  if(poll.equals(endWord)) {  
                      return path;  
                  }  
                  for(int i = 0; i < wordList.size(); i++) {  
                      String cur = wordList.get(i);  
                      if(!mark[i] && helper(cur, poll)) {  
                          queue.offer(cur);  
                          mark[i] = true;  
                      }  
                   }  
              }  
                
          }  
          return 0;  
    
      }  
    
      private boolean helper(String cur, String poll) {  
          if(cur.length() != poll.length()) {  
              return false;  
          }  
          boolean flag = false;  
          for(int i = 0; i < cur.length(); i++) {  
              if(cur.charAt(i) != poll.charAt(i)) {  
                  if(!flag) {  
                      flag = true;  
                  } else {  
                      return false;  
                  }  
              }    
          }  
          return flag;  
      }  
    
  }  
    
  // 方法二 优化  
  把String的关系转化用图来表示，可以省下很多时间  
    
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {  
      wordList.add(beginWord);  
      int N = wordList.size();  
      int start = N - 1;  
      int end = 0;  
      while (end < N && !wordList.get(end).equals(endWord)) {  
          end++;  
      }  
      if (end == N) {  
          return 0;  
      }  
      List<Integer>[] graphic = buildGraphic(wordList);  
      return getShortestPath(graphic, start, end);  
  }  
    
  private List<Integer>[] buildGraphic(List<String> wordList) {  
      int N = wordList.size();  
      List<Integer>[] graphic = new List[N];  
      for (int i = 0; i < N; i++) {  
          graphic[i] = new ArrayList<>();  
          for (int j = 0; j < N; j++) {  
              if (isConnect(wordList.get(i), wordList.get(j))) {  
                  graphic[i].add(j);  
              }  
          }  
      }  
      return graphic;  
  }  
    
  private boolean isConnect(String s1, String s2) {  
      int diffCnt = 0;  
      for (int i = 0; i < s1.length() && diffCnt <= 1; i++) {  
          if (s1.charAt(i) != s2.charAt(i)) {  
              diffCnt++;  
          }  
      }  
      return diffCnt == 1;  
  }  
    
  private int getShortestPath(List<Integer>[] graphic, int start, int end) {  
      Queue<Integer> queue = new LinkedList<>();  
      boolean[] marked = new boolean[graphic.length];  
      queue.add(start);  
      marked[start] = true;  
      int path = 1;  
      while (!queue.isEmpty()) {  
          int size = queue.size();  
          path++;  
          while (size-- > 0) {  
              int cur = queue.poll();  
              for (int next : graphic[cur]) {  
                  if (next == end) {  
                      return path;  
                  }  
                  if (marked[next]) {  
                      continue;  
                  }  
                  marked[next] = true;  
                  queue.add(next);  
              }  
          }  
      }  
      return 0;  
  }  
```    
    
### 个人解读    
  没有DP，想办法遍历。  
  最短路径问题，从beginWord开始扩散，使用BFS。  
    
tags:    
  -  BFS  
  -  图  
  -  预处理  
