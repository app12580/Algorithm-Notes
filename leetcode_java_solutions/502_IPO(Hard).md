### description      
Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.    
    
You are given several projects. For each project i, it has a pure profit Pi and a minimum capital of Ci is needed to start the corresponding project. Initially, you have W capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.    
    
To sum up, pick a list of at most k distinct projects from given projects to maximize your final capital, and output your final maximized capital.    
    
Example 1:    
Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].    
    
Output: 4    
    
Explanation: Since your initial capital is 0, you can only start the project indexed 0.    
             After finishing it you will obtain profit 1 and your capital becomes 1.    
             With capital 1, you can either start the project indexed 1 or the project indexed 2.    
             Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.    
             Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.    
Note:    
You may assume all numbers in the input are non-negative integers.    
The length of Profits array and Capital array will not exceed 50,000.    
The answer is guaranteed to fit in a 32-bit signed integer.      
### solution      
```      
// 方法一： PriorityQueue  
Runtime: 55 ms, faster than 59.51% of Java online submissions for IPO.    
Memory Usage: 45.1 MB, less than 100.00% of Java online submissions for IPO.    
  class Solution {    
      public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {    
         PriorityQueue<int[]> pre = new PriorityQueue<int[]>((a1, a2) -> a1[0] - a2[0]);    
          for(int i = 0; i < Profits.length; i++) {    
              int c = Capital[i];    
              int p = Profits[i];    
              pre.add(new int[]{c, p});    
          }    
          PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());    
          for(int i = 0; i < k; i++) {    
              while(!pre.isEmpty() && pre.peek()[0] <= W) {    
                  queue.offer(pre.poll()[1]);    
              }    
              if(queue.isEmpty()) return W;    
              W += queue.poll();    
          }    
          return W;    
      }    
  }    
```      
      
### 个人解读      
  乍一看和背包问题很像，但因为对于cost的要求，导致无法直接任意的优化。    
      
  这种题一般DP、遍历、数学方法选一个。    
      
  思路一：    
  贪婪算法：在现有的资本下，尽可能的获取最大利润。数据结构选取TreeMap<Integer, PriorityQueue>    
  修正： 因为每次不是cost下，而是低于某cost的 所有在一块计算，所以需要一个全局的PriorityQUeue，TreeMap<Integer,List>即可。    
  TLE 。。。5w的数据长度。。。。。。    
      
  思路二：    
  思路一整体没有错，应该是数据结构优化    
  使用PriorityQueue代替TreeMap，treeMap相当于先分个组，然而没有那个必要    
      
  ```    
  TLE     
  class Solution {    
      public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {    
           TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();    
          for(int i = 0; i < Profits.length; i++) {    
              int c = Capital[i];    
              int p = Profits[i];    
              if(!treeMap.containsKey(c)) {    
                  treeMap.put(c, new ArrayList<>());    
              }    
              treeMap.get(c).add(p);    
          }    
          int startCost = 0;    
          PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());    
          for(int i = 0; i < k; i++) {    
              while(startCost <= W) {    
                  if(treeMap.containsKey(startCost)) {    
                      for(int num: treeMap.get(startCost)) {    
                          queue.offer(num);    
                      }    
                  }                    
                  startCost++;    
              }    
              if(queue.isEmpty()) return W;    
              W += queue.poll();    
          }    
          return W;    
      }    
  }    
  ```    
      
      
tags:      
  -  数据结构  
  -  优先队列  
