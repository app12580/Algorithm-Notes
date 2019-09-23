### description    
  N cars are going to the same destination along a one lane road.  The destination is target miles away.  
    
  Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.  
    
  A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.  
    
  The distance between these two cars is ignored - they are assumed to have the same position.  
    
  A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.  
    
  If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.  
    
    
  How many car fleets will arrive at the destination?  
    
     
    
  Example 1:  
    
  Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]  
  Output: 3  
  Explanation:  
  The cars starting at 10 and 8 become a fleet, meeting each other at 12.  
  The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.  
  The cars starting at 5 and 3 become a fleet, meeting each other at 6.  
  Note that no other cars meet these fleets before the destination, so the answer is 3.  
    
  Note:  
    
  0 <= N <= 10 ^ 4  
  0 < target <= 10 ^ 6  
  0 < speed[i] <= 10 ^ 6  
  0 <= position[i] < target  
  All initial positions are different.  
### solution    
```    
//方法一： 通过栈，然后挨个去判断  
这可怜的效率。。。  
Runtime: 69 ms, faster than 5.25% of Java online submissions for Car Fleet.  
Memory Usage: 39.3 MB, less than 40.00% of Java online submissions for Car Fleet.  
  
  import javafx.util.Pair;  
    
  class Solution {  
          public int carFleet(int target, int[] position, int[] speed) {  
          List<Pair<Integer, Integer>> list = new ArrayList<>();  
          for (int i = 0; i < position.length; i++) {  
              list.add(new Pair<>(position[i], speed[i]));  
          }  
          Collections.sort(list, Comparator.comparing(Pair::getKey));  
          Stack<Pair<Integer, Integer>> stack = new Stack<>();  
          int index = 0;  
          while (index < position.length) {  
              if (stack.isEmpty()) {  
                  stack.push(list.get(index));  
              } else {  
                  //开始进行while  
                  Pair<Integer, Integer> cur = list.get(index);  
                  while(!stack.isEmpty() && (check(stack.peek(), cur, target))) {  
                      stack.pop();  
                  }  
                  stack.push(cur);  
              }  
              index++;  
          }  
          return stack.size();  
      }  
    
      private boolean check(Pair<Integer, Integer> peek, Pair<Integer, Integer> cur, int target) {  
          int p1 = peek.getKey();  
          int s1 = peek.getValue();  
          int p2 = cur.getKey();  
          int s2 = cur.getValue();  
          double t1 = (double)(target - p1) / s1;  
          double t2 = (double)(target - p2) / s2;  
          return t1 <= t2;  
      }  
  }  
    
  // 方法二 把栈改成数组，然而并没有什么卵用。。。  
  Runtime: 60 ms, faster than 10.25% of Java online submissions for Car Fleet.  
  Memory Usage: 40.1 MB, less than 40.00% of Java online submissions for Car Fleet.  
    
  import javafx.util.Pair;  
    
  class Solution {  
           public int carFleet(int target, int[] position, int[] speed) {  
          List<Pair<Integer, Integer>> list = new ArrayList<>();  
          for (int i = 0; i < position.length; i++) {  
              list.add(new Pair<>(position[i], speed[i]));  
          }  
          Collections.sort(list, Comparator.comparing(Pair::getKey));  
          int res = 0;  
          for(int i = list.size() - 1; i >= 0; i--) {  
              Pair<Integer, Integer> cur = list.get(i);  
              while(i > 0 && check(cur, list.get(i - 1), target)) {  
                  i--;  
              }  
              res++;  
          }  
          return res;  
      }  
    
      private boolean check(Pair<Integer, Integer> peek, Pair<Integer, Integer> cur, int target) {  
          int p1 = peek.getKey();  
          int s1 = peek.getValue();  
          int p2 = cur.getKey();  
          int s2 = cur.getValue();  
          double t1 = (double)(target - p1) / s1;  
          double t2 = (double)(target - p2) / s2;  
          return t1 >= t2;  
      }  
    
  }  
    
  //方法三： 不用两个变量，而是直接把时间算出来，然后从后往前遍历，如果时间少则说明是同一个车队，否则，res++并且更新当前时间。  
  为了保证从后往前遍历，小技巧，把所有数字变成负数  
  class Solution {  
   public int carFleet(int target, int[] position, int[] speed) {  
          TreeMap<Integer, Double> map = new TreeMap<>();  
          for (int i = 0; i < position.length; i++) {  
              map.put(-position[i], (double)(target - position[i]) / speed[i]);  
          }  
    
          int res = 0;  
          double cur = 0;  
          for (double val : map.values()) {  
              if (val > cur) {  
                  cur = val;  
                  res++;  
              }  
          }  
    
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  这个题目首先需要判断两个车是否会相遇，然后需要判断先后顺序是否会影响，毕竟相遇后会减速  
    
  示例：  
  A快B慢C慢，AC、AB都会相遇，但是一旦BC特别慢，那么之后ABC并不会汇聚在一起。  
    
  猜测，感觉先预处理一下，然后从后往前处理。  
  难点：一个数组，全员都是动态变化，每一次变化都要比较两两之间的关系，就是一个O(n^2)的操作。  
    
  一点点分析吧，首先排序预处理是必然的，至少可以减少循环次数。  
  ABC三点啊，只需要比较AB、BC之间的关系就好了。如果AC能相遇，那么AB、BC至少有一组是能相遇的。   
  还有一个非常严重的问题，如果两车相遇，此时时间和空间都是小数会怎么办，除非说车队数目与相遇地点并不关键，但是不对啊，还是有关联的。  
  突然意识到，和多人联机游戏时候的算法可能有关联。  
    
  然后，会想到，介于小数的存在，会不会通过其他因素，利用数学方法直接算出来。其中关键一点是到达终点的时间。  
  距离长时间短和距离短时间长的必然是相同的车队。但问题还是来了，相遇顺序如何界定。  
    
  唉，可能是累了把，忽略了一个非常关键的事情，两车相遇，速度变成慢的，其实等价于： 两车相遇，快车消失了，并不影响慢车，直接把快车去掉即可。  
    
  1,2,3,4  
  12相遇，变成234  
  如果23相遇，变成134,3还需要再与1比较  
    
  方法一： 通过栈每个去判断，但是效率为啥这么低额。  
    
  反思与总结：  
  这个题目其实还挺简单的，只要看方法三即可。  
  问题在于对题目相同车队的满足条件要更清晰才行。还有行车顺序的问题。  
    
tags:    
  -  重点数学  
  -  实际应用  
