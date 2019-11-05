### description    
  Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)  
    
  Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).  
    
  When you get an instruction "A", your car does the following: position += speed, speed *= 2.  
    
  When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)  
    
  For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.  
    
  Now for some target position, say the length of the shortest sequence of instructions to get there.  
    
  Example 1:  
  Input:   
  target = 3  
  Output: 2  
  Explanation:   
  The shortest instruction sequence is "AA".  
  Your position goes from 0->1->3.  
  Example 2:  
  Input:   
  target = 6  
  Output: 5  
  Explanation:   
  The shortest instruction sequence is "AAARA".  
  Your position goes from 0->1->3->7->7->6.  
### solution    
```    
// 方法二： 动态规划  
Runtime: 4 ms, faster than 79.61% of Java online submissions for Race Car.  
Memory Usage: 33.2 MB, less than 66.67% of Java online submissions for Race Car.  
  
class Solution {  
    public int racecar(int target) {  
        int[] dp = new int[target + 3];  
        Arrays.fill(dp, Integer.MAX_VALUE);  
        dp[0] = 0; dp[1] = 1; dp[2] = 4;  
  
        for (int t = 3; t <= target; ++t) {  
            int k = 32 - Integer.numberOfLeadingZeros(t);  
            if (t == (1<<k) - 1) {  
                // 如果是 1 3 7 15这种的，可以直接知道步骤是k步  
                dp[t] = k;  
                continue;  
            }  
            // 对于数字101, 1<<k 是 1000, 1<<(k-1)是111
            // 这里的范围很关键、里面k的范围分别为： 
            for (int j = 0; j < k-1; ++j) {  
            // 情形一： 从原始出发，出发一段距离然后掉头到达点M，然后再加上(t-M)的数量 (在终点前掉头)
            // t - (1<<(k-1)) + (1<<j)   这个是A^k-1RA^jR的步骤，
            // t - (1<<(k-1)) + (1<<j)是指剩下的距离
                dp[t] = Math.min(dp[t], dp[t - (1<<(k-1)) + (1<<j)] + k-1 + j + 2);  
                
                
                if ((1<<k) - 1 - t < t)     // 小于t说明前面的那个已经有数据了  这种的是RA^K
                // 情形二： 从原始出发，走了K步，到达点N，然后计算N出发到达t的数量 A^KR，然后开始新的计算(在终点后掉头)
                    //根据前面的计算，A^K一定是会超过target的
                    dp[t] = Math.min(dp[t], dp[(1<<k) - 1 - t] + k + 1);  
            }  
        }  
  
        return dp[target];    
    }  
}  
   
   
    
  // 方法一： 最小路径和法  
  代码后面的有点看不懂它在说啥。。。  
  Runtime: 98 ms, faster than 49.62% of Java online submissions for Race Car.  
  Memory Usage: 36.9 MB, less than 33.33% of Java online submissions for Race Car.  
  class Solution {  
      public int racecar(int target) {  
          int K = 33 - Integer.numberOfLeadingZeros(target - 1);  //等于当前target的二进制位数+1  
          int barrier = 1 << K;       // target: 100 或者 11, 返回的barrier都是100  
          int[] dist = new int[2 * barrier + 1];  // [-barrier, barrier]  
          Arrays.fill(dist, Integer.MAX_VALUE);  
          dist[target] = 0;  
    
          PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> a.steps - b.steps);//steps从小到大  
          pq.offer(new Node(0, target));  
    
          while (!pq.isEmpty()) {  
              Node node = pq.poll();  
              int steps = node.steps, targ1 = node.target;  
              int mod = Math.floorMod(targ1, dist.length);  
              if (steps < dist[mod]) continue;    //如果当前到达mod的步数比厉害记录小，则跳过？  
    
              for (int k = 0; k <= K; ++k) {  
                  int walk = (1 << k) - 1;  
                  int targ2 = walk - targ1;  
                  int steps2 = steps + k + (targ2 != 0 ? 1 : 0);  
    
                  if (Math.abs(targ2) <= barrier && steps2 < dist[Math.floorMod(targ2, dist.length)]) {  
                      pq.offer(new Node(steps2, targ2));  
                      dist[Math.floorMod(targ2, dist.length)] = steps2;  
                  }  
              }  
          }  
    
          return dist[0];  
      }  
    
      class Node {  
          int steps, target;  
          Node(int s, int t) {  
              steps = s;  
              target = t;  
          }  
      }  
  }  
    
    
     
```    
    
### 个人解读    
  两个指令A和R，R是调转方向，A是不断加速。  
    
  因为倒车会原地停下来一格，所以尽可能的少倒车。  
    
  猜测一：通过一个Map记录所有的<距离， 指令数量>  
    
  贪婪算法： 遇到越过目标时候，必须立刻折返，这样才能最小  
    
  取余取模：  
  按照javadocs  
    
    
    
  // 官方解答： https://leetcode-cn.com/problems/race-car/solution/sai-che-by-leetcode/  
  官方解答也有点不说人话了。。。还是直接看代码把。总体思路还是差不多的  
    
    
  // 解法一：Dijkstra  
  如果参数的符号相同，则floorMod和％运算符的结果是相同的。  
    
  floorMod(4, 3) == 1;   and (4 % 3) == 1  
  如果参数的符号不同，则结果与％运算符不同。  
  取模(fllorMod)是为了保证结果为正，所以是另第二个参数为正  
    
  floorMod(+4, -3) == -2;   and (+4 % -3) == +1  
  floorMod(-4, +3) == +2;   and (-4 % +3) == -1  
  floorMod(-4, -3) == -1;   and (-4 % -3) == -1  
    
tags:    
  -  重点题目  
  -  图论  
  -  动态规划  
  -  贪婪算法  
  
    
