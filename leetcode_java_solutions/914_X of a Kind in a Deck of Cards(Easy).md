### description    
  In a deck of cards, each card has an integer written on it.  
    
  Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:  
    
  Each group has exactly X cards.  
  All the cards in each group have the same integer.  
     
    
  Example 1:  
    
  Input: [1,2,3,4,4,3,2,1]  
  Output: true  
  Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]  
  Example 2:  
    
  Input: [1,1,1,2,2,2,3,3]  
  Output: false  
  Explanation: No possible partition.  
  Example 3:  
    
  Input: [1]  
  Output: false  
  Explanation: No possible partition.  
  Example 4:  
    
  Input: [1,1]  
  Output: true  
  Explanation: Possible partition [1,1]  
  Example 5:  
    
  Input: [1,1,2,2,2,2]  
  Output: true  
  Explanation: Possible partition [1,1],[2,2],[2,2]  
    
  能否把一个int数组，分成X个数字为一堆，使得恰好分好，其中X不能为1.  
### solution    
```    
  class Solution {  
       public boolean hasGroupsSizeX(int[] deck) {  
          Map<Integer, Integer> map = new HashMap<>();  
          for(int d: deck) {  
              map.put(d, map.getOrDefault(d, 0) + 1);  
          }  
          int res = -1;  
          for(int v: map.values()) {  
              if(res == -1) {  
                  res = v;  
                  if(res == 1) return false;        //注意这一行。  
              } else {  
                  res = gcd(res, v);  
                  if(res == 1) return false;  
              }  
          }  
          return true;  
      }  
    
      private int gcd(int a, int b) {  
          return b == 0 ? a : gcd(b, a % b);  
      }  
  }  
```    
    
### 个人解读    
  统计每个数字出现的次数，然后返回最大公约数 >= 2.  
    
tags:    
  -  数学  
  -  Hash表  
  -  最大公约数  
