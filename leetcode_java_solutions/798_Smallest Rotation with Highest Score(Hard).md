### description    
   Given an array A, we may rotate it by a non-negative integer K so that the array becomes A[K], A[K+1], A{K+2], ... A[A.length - 1], A[0], A[1], ..., A[K-1].  Afterward, any entries that are less than or equal to their index are worth 1 point.   
    
  For example, if we have [2, 4, 1, 3, 0], and we rotate by K = 2, it becomes [1, 3, 0, 2, 4].  This is worth 3 points because 1 > 0 [no points], 3 > 1 [no points], 0 <= 2 [one point], 2 <= 3 [one point], 4 <= 4 [one point].  
    
  Over all possible rotations, return the rotation index K that corresponds to the highest score we could receive.  If there are multiple answers, return the smallest such index K.  
    
  Example 1:  
  Input: [2, 3, 1, 4, 0]  
  Output: 3  
  Explanation:    
  Scores for each K are listed below:   
  K = 0,  A = [2,3,1,4,0],    score 2  
  K = 1,  A = [3,1,4,0,2],    score 3  
  K = 2,  A = [1,4,0,2,3],    score 3  
  K = 3,  A = [4,0,2,3,1],    score 4  
  K = 4,  A = [0,2,3,1,4],    score 3  
  So we should choose K = 3, which has the highest score.  
    
     
    
  Example 2:  
  Input: [1, 3, 0, 2, 4]  
  Output: 0  
  Explanation:  A will always have 3 points no matter how it shifts.  
  So we will choose the smallest K, which is 0.  
  Note:  
    
  A will have length at most 20000.  
  A[i] will be in the range [0, A.length].  
    
  如果索引大于等于当前值，即可以得一分  
    
### solution    
```    
// 方法一： 数学  
Runtime: 3 ms, faster than 88.46% of Java online submissions for Smallest Rotation with Highest Score.  
Memory Usage: 40.8 MB, less than 100.00% of Java online submissions for Smallest Rotation with Highest Score.  
  
  class Solution {  
       public int bestRotation(int[] A) {  
          int N = A.length;  
          int change[] = new int[N];  
          for (int i = 0; i < N; ++i) change[(i - A[i] + 1 + N) % N] -= 1;  
          int max_i = 0;  
          for (int i = 1; i < N; ++i) {  
              change[i] += change[i - 1] + 1;  
              max_i = change[i] > change[max_i] ? i : max_i;  
          }  
          return max_i;  
      }  
  }  
```    
    
### 个人解读    
  需要想个办法，最好是一次遍历就能获取想要结果。  
    
  2, 3, 1, 4, 0  
    
  0, 1, 2, 3, 4  
  -2 -2 1  -1 4 : 2 分  
    
  4  0  1  2  3     // 31402  
  2  -3 0  -2 3  : 3 分  
    
  这样的看的话，没经过一次，后面的--，第一位是+K-1，根据这个的话需要O(N^2)  
    
  参考答案 分析；  
  每次k++，会在新的地方获得一分，然后其他地方会扣分。因此只需要记录扣分的时候，K是几，通过这样，来实时统计获得分数。  
    
  需要注意的关键点：  
  1、K是什么？  
  2、只需要返回K就好了，不用管这时候得多少分  
    
tags:    
  -  重点数学  
