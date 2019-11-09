### description    
  A car travels from a starting position to a destination which is target miles east of the starting position.  
    
  Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.  
    
  The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.  
    
  When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.  
    
  What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.  
    
  Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.  
    
     
    
  Example 1:  
    
  Input: target = 1, startFuel = 1, stations = []  
  Output: 0  
  Explanation: We can reach the target without refueling.  
  Example 2:  
    
  Input: target = 100, startFuel = 1, stations = [[10,100]]  
  Output: -1  
  Explanation: We can't reach the target (or even the first gas station).  
  Example 3:  
    
  Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]  
  Output: 2  
  Explanation:   
  We start with 10 liters of fuel.  
  We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.  
  Then, we drive from position 10 to position 60 (expending 50 liters of fuel),  
  and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.  
  We made 2 refueling stops along the way, so we return 2.  
     
    
  Note:  
    
  1 <= target, startFuel, stations[i][1] <= 10^9  
  0 <= stations.length <= 500  
  0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target  
    
  汽车从起点出发，每一公里耗费一点油，途径加油站，问最少加油次数  
    
### solution    
```    
// 方法一： 逆向思维，计算每个点出发时候需要的油耗： 通过pq计算需要加油次数  
执行用时 :7 ms , 在所有 java 提交中击败了 79.67% 的用户    
内存消耗 : 39.3 MB , 在所有 java 提交中击败了 98.31% 的用户  
  
  class Solution {  
         public int minRefuelStops(int target, int startFuel, int[][] stations) {  
          int res = 0, slen = stations.length;  
          PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());  
          int[] need = new int[slen + 1];  
          need[slen] = slen == 0 ? target : target - stations[slen - 1][0];  //这两行细节需要注意  
          if(slen > 0) need[0] = stations[0][0];  
          for(int i = 1; i < slen; i++) {     //第一个加油站 need[1] = statations[1] - stations[0];  
              need[i] = stations[i][0] - stations[i-1][0];  // need[i] 表示从stations[i - 1]这个加油站出发时候需要的油  
          }  
          for(int i = 0; i <= slen; i++) {  
              int curNeed = need[i];  //i表示从第i个加油站出发  
              while(!pq.isEmpty() && startFuel < curNeed) {  
                  //执行while操作，尽可能的从pq中加油，直到满足  
                  startFuel += pq.poll();  
                  res++;  
              }  
              if(startFuel < curNeed) return -1;  
              startFuel -= curNeed;  
              if(i != slen) pq.add(stations[i][1]);  
          }  
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  思路一： 问最少加油次数，那么就反应BFS  
  queue里面存放的是当前次数，可能会在不同的地方加油。  
  结果TLE了  
    
  BFS TLE   
  ```  
  class Solution {  
        public int minRefuelStops(int target, int startFuel, int[][] stations) {  
          int res = 0, slen = stations.length;  
    
          Queue<int[]> queue = new LinkedList<>();  
          Map<Integer, Integer> map = new HashMap<>();    //存储加油站索引  
          map.put(0, -1);  
          for (int i = 0; i < slen; i++) {  
              map.put(stations[i][0], i);  
          }  
          queue.offer(new int[]{0, startFuel});  
          while (!queue.isEmpty()) {  
              for (int i = queue.size(); i > 0; i--) {  
                  int[] poll = queue.poll();  
                  int pos = poll[0], restFuel = poll[1];  
                  if (restFuel + pos >= target) return res;  
                  int index = map.get(pos);  
                  for (int j = index + 1; j < slen; j++) {  
                      int next = stations[j][0];  
                      if(restFuel + pos >= next) {  
                          queue.offer(new int[]{next, restFuel - (next - pos) + stations[j][1]});  
                      }  
                  }  
              }  
              res++;  
          }  
          return -1;  
      }  
    
  }  
  ```  
    
  思路二：   
  想办法优化，因为全体都是线性计算的，没必要每个加油站都在里面测试，找最大的就行了。  
    
  这样不行的，因为虽然加油站1不如加油站2，但是好歹加了两次，不能只算第二次加油的  
    
  思路三：  
  因为思路二的失败，感觉满足逆向思维的条件，对未来的迷茫而导致不知道要那个。  
  从后往前考虑的话，可以知道在每个加油站时候，最低需要多少油。  
    
  1、构建数组int[][] : int[i][0]表示第i个加油站的位置 int[i][1]表示至少需要多少油。  
  2、然后从左往右遍历。用一个PQ来维护每一个加油站的加油数量。如果不够，就从pq中加油，同时res++。  
  3、修正一下，need不是到终点的距离，而是能够到达下一站的最低油耗。  
    
    
tags:    
  -  逆向思维  
  -  优先队列  
