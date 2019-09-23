### description  
  Alice plays the following game, loosely based on the card game "21".
  
  Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.
  
  Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?
  
  Example 1:
  
  Input: N = 10, K = 1, W = 10
  Output: 1.00000
  Explanation:  Alice gets a single card, then stops.
  Example 2:
  
  Input: N = 6, K = 1, W = 10
  Output: 0.60000
  Explanation:  Alice gets a single card, then stops.
  In 6 out of W = 10 possibilities, she is at or below N = 6 points.
  Example 3:
  
  Input: N = 21, K = 17, W = 10
  Output: 0.73278
  Note:
  
  0 <= K <= N <= 10000
  1 <= W <= 10000
  Answers will be accepted as correct if they are within 10^-5 of the correct answer.
  The judging time limit has been reduced for this question.
### solution  
```  
  Runtime: 3 ms, faster than 98.68% of Java online submissions for New 21 Game.
  Memory Usage: 35.4 MB, less than 14.29% of Java online submissions for New 21 Game.
  
  class Solution {
       public double new21Game(int N, int K, int W) {
          if (K == 0 || N >= K + W) return 1;
          double dp[] = new double[N + 1],  Wsum = 1, res = 0;
          //Wsum 是i前面所有事件的概率总和
          dp[0] = 1;
          for (int i = 1; i <= N; ++i) {
              dp[i] = Wsum / W;
              if (i < K) {
                  Wsum += dp[i];
              } else {
                  res += dp[i];
              }
              if (i - W >= 0) { //这一行也是非常核心的一句，主要是if条件长什么样子
              //保证长度是W
              //这个长度W，是指最终能够到达K的那一部分
              //并且还有一个关键点，如果每一份称为一个示例，那么只有i前面W份里面有可能，并且每一份都是1/W
                  Wsum -= dp[i - W];
              }
          }
          return res;
      }
  }
  
```  
  
### 个人解读  
  这个题目看了好久，感觉读懂了以后会对概率论有更深的认识。
  
  总结：
  1、对于多个事件重复试验的情形，概率和可以比1高，也就是本题中Wsum中Wsum += dp[i];的做法
  2、这个题目稍加概括可以整出来一个新的概型出来。
  3、本题目中的Wsum和dp[i]比起概率来说，还要有其他的概念，类似理解成可能性成分，加起来的和并不是1。
    但是又和最终res不需要去做额外的除法矛盾了。
  
  本解法主要是Wsum的理解，
  
  
  
tags:  
  -  概率论
  -  重点数学
