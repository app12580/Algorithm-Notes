### description    
  There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.  
    
  Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.  
    
  Example 1:  
  Input:   
  n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]  
  src = 0, dst = 2, k = 1  
  Output: 200  
  Explanation:   
  The graph looks like this:  
    
    
  The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.  
  Example 2:  
  Input:   
  n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]  
  src = 0, dst = 2, k = 0  
  Output: 500  
  Explanation:   
  The graph looks like this:  
    
    
  The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.  
  Note:  
    
  The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.  
  The size of flights will be in range [0, n * (n - 1) / 2].  
  The format of each flight will be (src, dst, price).  
  The price of each flight will be in the range [1, 10000].  
  k is in the range of [0, n - 1].  
  There will not be any duplicated flights or self cycles.  
### solution    
```    
Runtime: 20 ms, faster than 56.20% of Java online submissions for Cheapest Flights Within K Stops.  
Memory Usage: 40.3 MB, less than 12.96% of Java online submissions for Cheapest Flights Within K Stops.  
  
  
  class Solution {  
     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {  
          Map<Integer, Map<Integer, Integer>> map = new HashMap<>();  
          for(int[] f: flights) {  
              int u = f[0];  
              int v = f[1];  
              int w = f[2];  
              if(!map.containsKey(u)) {  
                  map.put(u, new HashMap<>());  
              }  
              map.get(u).put(v, w);  
          }  
          int[][][] dp = new int[n][n][K + 1];  
          int v = dfs(dp, src, dst, K, map);  
          return v;  
      }  
    
      private int dfs(int[][][] dp, int src, int dst, int k, Map<Integer, Map<Integer, Integer>> map) {  
          if(src == dst) return 0;  
          if(dp[src][dst][k] != 0) return dp[src][dst][k];  
          Map<Integer, Integer> ints = map.get(src);  
          if(ints == null) {  
              return -1;  
          }  
    
          if(k == 0 ) {  
              if(ints.containsKey(dst)) {  
                  dp[src][dst][k] = ints.get(dst);  
                  return ints.get(dst);  
              }  
              else return -1;  
          }  
          int res = Integer.MAX_VALUE;  
          for(Map.Entry<Integer, Integer> entry: ints.entrySet()) {  
              int nextDst = entry.getKey();  
              int nextVal = entry.getValue();  
              int v = dfs(dp, nextDst, dst, k - 1, map);  
              if(v != -1) {  
                  res = Math.min( res, nextVal + v);  
              }  
          }  
          dp[src][dst][k] = res == Integer.MAX_VALUE ? -1 : res;  
          return dp[src][dst][k];  
      }  
  }  
```    
    
### 个人解读    
  看到这个题目，联想到了：前面的悲观法求是否safe和求最大平均数和的题目。  
  分别代表了图的典型例题：图的递归和图的成环问题。  
    
  回归题目，本题基本上就是一个遍历算法模板了  
    
  总结：   
  1、通过1个三维int数组来存放中间结果  
  2、如果解决k-1与k的关系(k是至多而不是必须k个)，通过下列来解决  
  ```  
    if(src == dst) return 0;  
  ```  
  3、通过dfs的返回值来控制dfs的进度  
    
    
  ```  
    超时算法  
    class Solution {  
       public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {  
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();  
            for(int[] f: flights) {  
                int u = f[0];  
                int v = f[1];  
                int w = f[2];  
                if(!map.containsKey(u)) {  
                    map.put(u, new HashMap<>());  
                }  
                map.get(u).put(v, w);  
            }  
            int v = dfs(src, dst, K, map);  
            return v == 10000000 ? -1 : v;  
        }  
      
        private int dfs(int src, int dst, int k, Map<Integer, Map<Integer, Integer>> map) {  
                if(src == dst) return 0;  
            Map<Integer, Integer> ints = map.get(src);  
            if(ints == null) {  
                return 10000000;  
            }  
      
            if(k == 0 ) {  
                if(ints.containsKey(dst)) return ints.get(dst);  
                else return 10000000;  
            }  
            int res = Integer.MAX_VALUE;  
            for(Map.Entry<Integer, Integer> entry: ints.entrySet()) {  
                int nextDst = entry.getKey();  
                int nextVal = entry.getValue();  
                res = Math.min(res, nextVal + dfs(nextDst, dst, k - 1, map));  
            }  
            return res;  
        }  
      
    }  
  ```  
tags:    
  -  实际应用  
  -  DFS  
  -  多维中间结果  
