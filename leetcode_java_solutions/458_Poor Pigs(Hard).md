### description    
  There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water. They all look identical. If a pig drinks the poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket is poisonous within one hour?  
    
  Answer this question, and write an algorithm for the general case.  
    
     
    
  General case:  
    
  If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the poisonous bucket within p minutes? There is exactly one bucket with poison.  
    
     
    
  Note:  
    
  A pig can be allowed to drink simultaneously on as many buckets as one would like, and the feeding takes no time.  
  After a pig has instantly finished drinking buckets, there has to be a cool down time of m minutes. During this time, only observation is allowed and no feedings at all.  
  Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).  
### solution    
```    
Runtime: 0 ms, faster than 100.00% of Java online submissions for Poor Pigs.  
Memory Usage: 33 MB, less than 100.00% of Java online submissions for Poor Pigs.  
  class Solution {  
     public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {  
          if(buckets <= 1) return 0;  
          int count = minutesToTest / minutesToDie + 1;  
          int res = 1;  
          int t = count;  
          while(t < buckets) {  
              t *= count;  
              res++;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  经典题目。 另外印象很深的是，曾经这个是easy题目。  
  采用标记法：  
  1、标记的是水  
  2、如何标记：  
  假设某一桶水，此时被2、3只猪吃了，另一桶是13  
  那么可以用011和101表示。  
  1表示吃了，0表示没吃。  
  3、即  
  2^res >= buckets / (minutesToTest / minutesToDie)的最小res值。  
    
  3、优化：  
  还可以更加复杂一些。假设有两轮,可以用011,012,022,021，这样比起前面的多出了012和021这两种标记  
    
tags:    
  -  数学  
  -  建模  
