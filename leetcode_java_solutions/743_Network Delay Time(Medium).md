### description    
  There are N network nodes, labelled 1 to N.  
    
  Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.  
    
  Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.  
    
     
    
  Example 1:  
    
    
    
  Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2  
  Output: 2  
     
    
  Note:  
    
  N will be in the range [1, 100].  
  K will be in the range [1, N].  
  The length of times will be in the range [1, 6000].  
  All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.  
### solution    
```    
  
Runtime: 30 ms, faster than 70.52% of Java online submissions for Network Delay Time.  
Memory Usage: 52.1 MB, less than 45.24% of Java online submissions for Network Delay Time.  
  
  
  class Solution {  
     public int networkDelayTime(int[][] times, int N, int K) {  
          Map<Integer, Map<Integer, Integer>> map = new HashMap<>();  
          for(int[] time: times) {  
              int src = time[0];  
              int dest = time[1];  
              int t = time[2];  
              if(!map.containsKey(src)) {  
                  map.put(src, new HashMap<Integer, Integer>());  
              }  
              Map<Integer, Integer> srcMap = map.get(src);  
              srcMap.put(dest, t);  
          }  
          int[] arrive = new int[N + 1];  
          Arrays.fill(arrive, -1);  
          arrive[K] = 0;  
          //从K开始  
          Queue<Integer> queue = new LinkedList<>();  
          queue.offer(K);  
          while(!queue.isEmpty()) {  
              int size = queue.size();  
              for(int i = 0; i < size; i++) {  
                  Integer poll = queue.poll();  
                  Map<Integer, Integer> queueMap = map.get(poll);  
                  if(queueMap == null) continue;  
                  int now = arrive[poll];  
                  for(Map.Entry<Integer, Integer> entry: queueMap.entrySet()) {  
                      int dest = entry.getKey();  
                      int time = entry.getValue();  
                      if(arrive[dest] != -1 && arrive[dest] <= now + time) {  
                          continue;  
                      }  
                      arrive[dest] = now + time;  
                      queue.offer(dest);  
                  }  
              }  
          }  
          int max = 0;  
          for(int i = 1; i<= N; i++) {  
              max = Math.max(max, arrive[i]);  
              if(arrive[i] == -1) {  
                  return -1;  
              }  
          }  
          return max;  
      }  
  }  
```    
    
### 个人解读    
  首先需要遍历一遍预处理，把东西全统计一遍，  
    
  层次遍历，用Map存储路径，用数组存储时间。  
  层次遍历的几个重点：如果时间更短的算过了，或者没有下一个路径。  
    
tags:    
  -  层次遍历  
