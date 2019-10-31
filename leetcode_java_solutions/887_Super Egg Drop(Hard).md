### description    
  You are given K eggs, and you have access to a building with N floors from 1 to N.   
    
  Each egg is identical in function, and if an egg breaks, you cannot drop it again.  
    
  You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.  
    
  Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).   
    
  Your goal is to know with certainty what the value of F is.  
    
  What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?  
    
     
    
  Example 1:  
    
  Input: K = 1, N = 2  
  Output: 2  
  Explanation:   
  Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.  
  Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.  
  If it didn't break, then we know with certainty F = 2.  
  Hence, we needed 2 moves in the worst case to know what F is with certainty.  
  Example 2:  
    
  Input: K = 2, N = 6  
  Output: 3  
  Example 3:  
    
  Input: K = 3, N = 14  
  Output: 4  
     
    
  Note:  
    
  1 <= K <= 100  
  1 <= N <= 10000  
### solution    
```    
  // 方法一： 自己写哒  
  Runtime: 1 ms, faster than 81.49% of Java online submissions for Super Egg Drop.  
  Memory Usage: 33.2 MB, less than 77.78% of Java online submissions for Super Egg Drop.  
    
  class Solution {  
      public int superEggDrop(int K, int N) {  
          if(K == 1) return N;  
          int len = (int) (Math.sqrt(N * 2) + 1);  
          int[][] dp = new int[K + 1][len + 1];  
          for(int i = 0; i <= len; i++) {  
              dp[1][i] = i;  
          }  
          for(int k = 2; k < K; k++) {  
              for(int i = 1; i <= len; i++) {  
                  dp[k][i] = dp[k-1][i-1] + 1 + dp[k][i-1];  
                  if(dp[k][i] >= N) break;  
              }  
          }  
    
          //下面开始正式k==K的情形  
          for(int i = 1; i <= len; i++) {  
              dp[K][i] = dp[K-1][i-1] + 1 + dp[K][i-1];  
              if(dp[K][i] >= N) return i;  
          }  
          return -1;  
      }  
  }  
    
  // 代码精简  
  Runtime: 8 ms, faster than 61.54% of Java online submissions for Super Egg Drop.  
  Memory Usage: 36.2 MB, less than 44.44% of Java online submissions for Super Egg Drop.  
  class Solution {  
        public int superEggDrop(int K, int N) {  
          int[][] dp = new int[N + 1][K + 1];  
          int m = 0;  
          while (dp[m][K] < N) {  
              ++m;  
              for (int k = 1; k <= K; ++k)  
                  dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;  
          }  
          return m;  
      }  
  }  
```    
    
### 个人解读    
  数学问题，需要理清题目描述。F表示鸡蛋的承受极限，范围[0,N]  
  如果只有一个蛋，那么只能从小到大一层层的试  
  如果有两个蛋的话，可以从中间开始，保证即使最坏的情况，两边的测试次数是差不多的。  
   K = 2, N = 6, answer = 3  
   123 4 56  
   如果4开始，如果破了，接下来还需要123  
   如果3开始，如果没破，下一步5即可。  
     
     
   需要从中找到数学规律：  
   有两种思路：第一种是干看着，只有input，然后找出一个规律用来描述进度。  
   另一种是带上结果，把结果当成一个变量一块讨论。本题选择后者  
     
   如果K==2的情况下，很容易判断行进路线。  
   假设结果是A，如果碎了，就要从当前阶段开始；如果没碎，就需要跳向下一个阶段  
   目标算法，就是每次放一个，如果碎了就在当前阶段找。  
   A + (A-1) + (A-2) + .... + 1 >= N  
   (A+1)*A/2 >=N  
     
   然后再想想K==3的情况，也是和==2的情况类似，先要找一个点，然后选择前面还是后面，只不过此时应该与k==2的结果有关，和不是之前的单纯的A-1，A-2这种  
     
     
   不等式是根据层数定的，所以第一个代数式是指第一次偏离的位置  
   res = A + 1  
   k2(A) + 1 + k2(A-1) + 1 + 。。。 + k2(1) + 1 + 1 >= N  
   这个式子的关键点在于，每次尝试，消耗的东西是那一次操作次数。  
   剩下的问题就是如何转化成代码了。  
     
  自己的思路是使用一个二维数组来记录这个信息。  
     
    
tags:    
  -  重点数学  
  -  动态规划  
