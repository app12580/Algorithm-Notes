### description    
  Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.  
    
  Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.  
    
  Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.  
    
  Return the minimum integer K such that she can eat all the bananas within H hours.  
    
     
    
  Example 1:  
    
  Input: piles = [3,6,7,11], H = 8  
  Output: 4  
  Example 2:  
    
  Input: piles = [30,11,23,4,20], H = 5  
  Output: 30  
  Example 3:  
    
  Input: piles = [30,11,23,4,20], H = 6  
  Output: 23  
     
    
  Note:  
    
  1 <= piles.length <= 10^4  
  piles.length <= H <= 10^9  
  1 <= piles[i] <= 10^9  
### solution    
```    
Runtime: 7 ms, faster than 96.66% of Java online submissions for Koko Eating Bananas.  
Memory Usage: 39 MB, less than 100.00% of Java online submissions for Koko Eating Bananas.  
  
  class Solution {  
        public int minEatingSpeed(int[] piles, int H) {  
          int l = 1;  
          int h = 1_000_000_000;  
          while(l < h) {  
              int m = l + (h - l) / 2;  
              if(check(piles, H, m)) {  
                  h = m;  
              } else {  
                  l = m + 1;  
              }  
          }  
          return l;  
      }  
    
      private boolean check(int[] piles, int h, int cur) {  
          int time = 0;  
          for(int p: piles) {  
              time += (p + cur - 1) / cur;  
          }  
          return time <= h;  
      }  
    
  }  
```    
    
### 个人解读    
  感觉是个纯数学问题，不大可能使用遍历。  
    
  首先可以比较H和数组长度。  
  想了一些可能性，比如Kth排序比较，最大值的二分之一、三分之一。然而没想到最终还是暴力法遍历，挨个试验一遍。  
    
    
    
tags:    
  -  重点数学  
