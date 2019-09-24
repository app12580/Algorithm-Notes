### description    
  Alice has a hand of cards, given as an array of integers.  
    
  Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.  
    
  Return true if and only if she can.  
    
     
    
  Example 1:  
    
  Input: hand = [1,2,3,6,2,3,4,7,8], W = 3  
  Output: true  
  Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].  
  Example 2:  
    
  Input: hand = [1,2,3,4,5], W = 4  
  Output: false  
  Explanation: Alice's hand can't be rearranged into groups of 4.  
     
    
  Note:  
    
  1 <= hand.length <= 10000  
  0 <= hand[i] <= 10^9  
  1 <= W <= hand.length  
### solution    
```    
Runtime: 48 ms, faster than 33.11% of Java online submissions for Hand of Straights.  
Memory Usage: 39.6 MB, less than 100.00% of Java online submissions for Hand of Straights.  
  
  class Solution {  
      public boolean isNStraightHand(int[] hand, int W) {  
          TreeMap<Integer, Integer> treeMap = new TreeMap<>();  
          for(int h: hand) {  
              treeMap.put(h, treeMap.getOrDefault(h, 0) + 1);  
          }  
          for(int key: treeMap.keySet()) {  
              if(treeMap.get(key) == 0) continue;  
              int val = treeMap.get(key);  
              for(int i = 1; i < W; i++) {  
                  if(treeMap.getOrDefault(key+i, 0) >= val) {  
                      treeMap.put(key+i, treeMap.get(key+i) - val);  
                  } else {  
                      return false;  
                  }  
              }  
          }  
          return true;  
      }  
  }  
    
```    
    
### 个人解读    
  类似于分水的DFS，但是好处在于顺子要求，其中可能蕴藏着一些规律。  
  至少最大值和最小值如果合格的话，一定在最两端。  
    
  印象里做过类似的题目，判断是否可以成为多个连续的序列。[659](659_Split%20Array%20into%20Consecutive%20Subsequences(Medium).md)  
    
  数据结构的选取很关键，本题目有大小关系，有数量统计，可以List<Pair>，也可以TreeMap  
    
tags:    
  -  TreeMap  
  -  数学  
