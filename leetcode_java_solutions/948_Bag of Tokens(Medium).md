### description      
  You have an initial power P, an initial score of 0 points, and a bag of tokens.    
      
  Each token can be used at most once, has a value token[i], and has potentially two ways to use it.    
      
  If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.    
  If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.    
  Return the largest number of points we can have after playing any number of tokens.    
      
       
      
  Example 1:    
      
  Input: tokens = [100], P = 50    
  Output: 0    
  Example 2:    
      
  Input: tokens = [100,200], P = 150    
  Output: 1    
  Example 3:    
      
  Input: tokens = [100,200,300,400], P = 200    
  Output: 2    
       
      
  Note:    
      
  tokens.length <= 1000    
  0 <= tokens[i] < 10000    
  0 <= P < 10000    
### solution      
```      
Runtime: 2 ms, faster than 99.75% of Java online submissions for Bag of Tokens.    
Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Bag of Tokens.    
    
  class Solution {    
      public int bagOfTokensScore(int[] tokens, int P) {    
          Arrays.sort(tokens);    
          int max = 0;    
          int res = 0;    
          int len = tokens.length;    
          int right = len;     
          int left = 0;    
          while(left < right) {    
              while(left < right && P >= tokens[left]) {    
                  P -= tokens[left++];    
                  res++;    
              }    
              max = Math.max(max, res);    
             if(res >= 1) {    
                  res--;    
                  P += tokens[--right];    
              } else {      //注意这个终止条件    
                  break;    
              }    
                  
          }    
          return max;    
      }    
  }    
```      
      
### 个人解读      
  token可以用来换分数，最终也是获取得分的手段。    
      
  首先令牌肯定是需要排序预处理的。然后开始遍历。    
  然后需要制定贪婪算法：    
  1、换点数操作，肯定是换点数最多的    
  2、问题在于用什么去换分。也是从低的开始换。    
      
  总结：确认好方向以后，只需要单向去调整即可，先尽可能的获取分数，然后再卖一分在去获取分数。重复进行下去。终止条件是两边指针相遇，或者无分可卖。    
      
tags:      
  -  贪婪算法    
