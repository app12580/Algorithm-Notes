### description    
  Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.  
    
  Note:  
    
  If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].  
  All airports are represented by three capital letters (IATA code).  
  You may assume all tickets form at least one valid itinerary.  
  Example 1:  
    
  Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]  
  Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]  
  Example 2:  
    
  Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]  
  Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]  
  Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].  
               But it is larger in lexical order.  
### solution    
```    
  
Runtime: 5 ms, faster than 82.12% of Java online submissions for Reconstruct Itinerary.  
Memory Usage: 44 MB, less than 61.19% of Java online submissions for Reconstruct Itinerary.  
  
  class Solution {  
       public List<String> findItinerary(List<List<String>> tickets) {  
          List<String> res = new ArrayList<>();  
          Map<String, List<String>> map = new HashMap<>();  
          for(List<String> ticket: tickets) {  
              String s1 = ticket.get(0);  
              String s2 = ticket.get(1);  
              if(!map.containsKey(s1)) {  
                  map.put(s1, new ArrayList<String>());  
              }  
              List<String> desc = map.get(s1);  
              desc.add(s2);  
          }  
          for(List<String> list: map.values()) {  
              Collections.sort(list);  
          }  
          int len = tickets.size();  
          res.add("JFK");  
          dfs(res, map, "JFK", 0, len);  
    
          return res;  
      }  
    
      private boolean dfs(List<String> res, Map<String, List<String>> map, String start, int curLen, int len) {  
          if(curLen == len) {  
              return true;  
          }  
          List<String> strings = map.get(start);  
          if(strings == null || strings.size() == 0) {  
              return false;  
          }  
          int size = strings.size();  
          for(int i = 0; i < size; i++) {  
              String next = strings.get(i);  
              strings.remove(i);  
              res.add(next);  
              if(dfs(res, map, next, curLen+1, len)) {  
                  return true;  
              }  
              res.remove(res.size() - 1);  
              strings.add(next);  
              Collections.sort(strings);  
          }  
          return false;  
      }  
  }  
```    
    
### 个人解读    
  先预处理一下，通过一个Map<String,List>来存储每个地点的可行出度，然后使用DFS+回溯法，添加时候要排下序，这样第一个就是要的结果。  
  这种强硬调用排序的方法，竟然能够效率这么高。。。  
    
tags:    
  -  图  
  -  DFS  
  -  预处理、排序  
