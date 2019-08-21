### description    
  In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.  
    
  What if we change the game so that players cannot re-use integers?  
    
  For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.  
    
  Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.  
    
  You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.  
    
  Example  
    
  Input:  
  maxChoosableInteger = 10  
  desiredTotal = 11  
    
  Output:  
  false  
    
  Explanation:  
  No matter which integer the first player choose, the first player will lose.  
  The first player can choose an integer from 1 up to 10.  
  If the first player choose 1, the second player can only choose integers from 2 up to 10.  
  The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.  
  Same with other integers chosen by the first player, the second player will always win.  
### solution    
```    
  
Runtime: 71 ms, faster than 61.83% of Java online submissions for Can I Win.  
Memory Usage: 46.3 MB, less than 48.28% of Java online submissions for Can I Win.  
  
  class Solution {  
       Map<Integer, Boolean> map;  
      boolean[] used;  
    
      public boolean canIWin(int maxChoosableInteger, int desiredTotal) {  
          int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;  
          if (sum < desiredTotal) return false;  
          if (desiredTotal <= 0) return true;  
    
          map = new HashMap();  
          used = new boolean[maxChoosableInteger + 1];  
          return helper(desiredTotal);  
      }  
    
      public boolean helper(int desiredTotal) {  
          if (desiredTotal <= 0) return false;  
          int key = format(used);  
          if (!map.containsKey(key)) {  
              // try every unchosen number as next step  
              for (int i = 1; i < used.length; i++) {  
                  if (!used[i]) {  
                      used[i] = true;  
                      // check whether this lead to a win (i.e. the other player lose)  
                      if (!helper(desiredTotal - i)) {  
                          map.put(key, true);  
                          used[i] = false;  
                          return true;  
                      }  
                      used[i] = false;  
                  }  
              }  
              map.put(key, false);  
          }  
          return map.get(key);  
      }  
    
      // transfer boolean[] to an Integer  
      public int format(boolean[] used) {  
          int num = 0;  
          for (boolean b : used) {  
              num <<= 1;  
              if (b) num |= 1;  
          }  
          return num;  
      }  
  }  
```    
    
### 个人解读    
  首先面临的第一个问题：是要通过数学方法确定唯一路线还是通过计算机的计算能力去遍历。  
    
  发现找不到好的数学方法，还是直接通过遍历去算把。  
    
  创建辅助函数，主要三个参数：先后手？可选数字，目标。  
    
  DFS题目，但是感觉第一次见到这种，和以往的都不太一样，但是又说不太好具体表现在哪里。  
    
  方法一解读：摒弃了最大值最小值的考量，全部使用遍历，唯一结果判断，当目标小于0时候，返回false，输了，原因上一个完成了。  
  返回true的情况，for循环，只要任意一个为false，则返回true，因为只要有一个是false，则说明当前赢了。  
    
    
tags:    
  -  重点DFS  
