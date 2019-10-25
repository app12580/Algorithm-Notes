### description    
  You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.  
    
  What is the maximum number of envelopes can you Russian doll? (put one inside other)  
    
  Note:  
  Rotation is not allowed.  
    
  Example:  
    
  Input: [[5,4],[6,4],[6,7],[2,3]]  
  Output: 3   
  Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).  
### solution    
```    
// 方法一: DFS  
Runtime: 200 ms, faster than 48.16% of Java online submissions for Russian Doll Envelopes.  
Memory Usage: 54.2 MB, less than 38.46% of Java online submissions for Russian Doll Envelopes.  
  
  class Solution {  
      public int maxEnvelopes(int[][] envelopes) {  
          Arrays.sort(envelopes, (o1, o2) -> {  
              //宽度从大到小，高度从大到小  
              return o2[0] != o1[0] ? o2[0] - o1[0] : o2[1] - o1[1];  
          });  
          int[] dp = new int[envelopes.length];  
          int res = 0;  
          for (int i = 0; i < envelopes.length; i++) {  
              res = Math.max(res, dfs(envelopes, i, dp));  
          }  
          return res;  
      }  
    
      // 以第index为起点的区间的套娃长度。  
      private int dfs(int[][] envelopes, int index, int[] dp) {  
          if (dp[index] > 0) return dp[index];  
          int next = 0;  
          int x = envelopes[index][0];  
          int y = envelopes[index][1];  
          for (int j = 0; j < index; j++) {  
              int[] nextArr = envelopes[j];  
              if (x < nextArr[0] && y < nextArr[1]) {  
                  next = Math.max(next, dfs(envelopes, j, dp));  
              }  
          }  
          dp[index] = next + 1;  
          return next + 1;  
      }  
  }  
```    
    
### 个人解读    
  联想到了那个矩阵内最长单调增序列长度的题目了，同样是需要递归，同样是需要把不同的数据组合在一起。  
    
  思路一：  
  1、先按照区间长度从大到小排序，然后开始遍历。O(n^2)  
  效率还行吧  
    
tags:    
  -  DFS  
