### description    
  Given a non-empty list of words, return the k most frequent elements.  
    
  Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.  
    
  Example 1:  
  Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2  
  Output: ["i", "love"]  
  Explanation: "i" and "love" are the two most frequent words.  
      Note that "i" comes before "love" due to a lower alphabetical order.  
  Example 2:  
  Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4  
  Output: ["the", "is", "sunny", "day"]  
  Explanation: "the", "is", "sunny" and "day" are the four most frequent words,  
      with the number of occurrence being 4, 3, 2 and 1 respectively.  
  Note:  
  You may assume k is always valid, 1 ≤ k ≤ number of unique elements.  
  Input words contain only lowercase letters.  
  Follow up:  
  Try to solve it in O(n log k) time and O(n) extra space.  
### solution    
```    
  class Solution {  
      public List<String> topKFrequent(String[] words, int k) {  
          List<String> list = new LinkedList<>();  
          Map<String, Integer> map = new HashMap<>();  
          for(String s: words) {  
              map.put(s, map.getOrDefault(s, 0) + 1);  
          }  
    
          PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(  
                  (a,b) -> {  
                      if(a.getValue() != b.getValue()) {  
                          return a.getValue() - b.getValue();  
                      }  
                      return b.getKey().compareTo(a.getKey());  
                  }  
          );  
          for(Map.Entry entry: map.entrySet()) {  
              pq.offer(entry);  
              if(pq.size() > k) {  
                  pq.poll();  
              }  
          }  
          while(!pq.isEmpty()) {  
              list.add(0, pq.poll().getKey());  
          }  
          return list;  
      }  
  }  
```    
    
### 个人解读    
  感觉可以流水账，  
  先排序，然后统计单词个数，通过合适的结构去存储。  
  反思：本题目只需要统计个数，所以可以省去排序的步骤。  
    
  双维度的Kth数据结构。  
    
tags:    
  -  多维排序  
  -  优先队列  
