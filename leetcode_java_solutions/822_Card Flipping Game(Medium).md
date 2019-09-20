### description    
  On a table are N cards, with a positive integer printed on the front and back of each card (possibly different).  
    
  We flip any number of cards, and after we choose one card.   
    
  If the number X on the back of the chosen card is not on the front of any card, then this number X is good.  
    
  What is the smallest number that is good?  If no number is good, output 0.  
    
  Here, fronts[i] and backs[i] represent the number on the front and back of card i.   
    
  A flip swaps the front and back numbers, so the value on the front is now on the back and vice versa.  
    
  Example:  
    
  Input: fronts = [1,2,4,4,7], backs = [1,3,4,1,3]  
  Output: 2  
  Explanation: If we flip the second card, the fronts are [1,3,4,4,7] and the backs are [1,2,4,1,3].  
  We choose the second card, which has number 2 on the back, and it isn't on the front of any card, so 2 is good.  
     
    
  Note:  
    
  1 <= fronts.length == backs.length <= 1000.  
  1 <= fronts[i] <= 2000.  
  1 <= backs[i] <= 2000.  
### solution    
```    
  
Runtime: 7 ms, faster than 23.39% of Java online submissions for Card Flipping Game.  
Memory Usage: 44.7 MB, less than 33.33% of Java online submissions for Card Flipping Game.  
  
  
  class Solution {  
      public int flipgame(int[] fronts, int[] backs) {  
           Set<Integer> sameSet = new HashSet<>();  
          Set<Integer> allSet = new HashSet<>();  
          for(int i = 0; i < fronts.length; i++) {  
              int v1 = fronts[i];  
              int v2 = backs[i];  
              if(v1 == v2) {  
                  sameSet.add(v1);  
                  allSet.add(v1);  
              } else {  
                  allSet.add(v1);  
                  allSet.add(v2);  
              }  
          }  
          allSet.removeAll(sameSet);  
          if(allSet.size() == 0) return 0;  
          int res = Integer.MAX_VALUE;  
          for(int s: allSet) {  
              res = Math.min(res, s);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  数学问题，数字要满足某些特定要求，比如出现次数什么的，是否正反一致。  
  感觉只要不是正反一致的都OK啊。  
  结果还真的是这样的。。  
    
  其他方法优化就是把hash表改成2000长度的数组  
    
tags:    
  -  数学  
