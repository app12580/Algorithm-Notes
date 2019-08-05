### description    
  In LOL world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition. Now, given the Teemo's attacking ascending time series towards Ashe and the poisoning time duration per Teemo's attacking, you need to output the total time that Ashe is in poisoned condition.  
    
  You may assume that Teemo attacks at the very beginning of a specific time point, and makes Ashe be in poisoned condition immediately.  
    
  Example 1:  
    
  Input: [1,4], 2  
  Output: 4  
  Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned immediately.   
  This poisoned status will last 2 seconds until the end of time point 2.   
  And at time point 4, Teemo attacks Ashe again, and causes Ashe to be in poisoned status for another 2 seconds.   
  So you finally need to output 4.  
     
    
  Example 2:  
    
  Input: [1,2], 2  
  Output: 3  
  Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned.   
  This poisoned status will last 2 seconds until the end of time point 2.   
  However, at the beginning of time point 2, Teemo attacks Ashe again who is already in poisoned status.   
  Since the poisoned status won't add up together, though the second poisoning attack will still work at time point 2, it will stop at the end of time point 3.   
  So you finally need to output 3.  
     
    
  Note:  
    
  You may assume the length of given time series array won't exceed 10000.  
  You may assume the numbers in the Teemo's attacking time series and his poisoning time duration per attacking are non-negative integers, which won't exceed 10,000,000.  
### solution    
```    
  
Runtime: 2 ms, faster than 91.74% of Java online submissions for Teemo Attacking.  
Memory Usage: 38.6 MB, less than 100.00% of Java online submissions for Teemo Attacking.  
  
  
  class Solution {  
      public int findPoisonedDuration(int[] timeSeries, int duration) {  
          if(duration == 0) {  
              return 0;  
          }  
          int res = 0;  
          int lastTime = -1;    //注意这里的-1，不能是0
          for(int time: timeSeries) {  
              if(time > lastTime) {  
                  res += duration;  
                  lastTime = time + duration - 1;  
              } else if(time + duration - 1 > lastTime) {  
                  res += time + duration - 1 - lastTime;  
                  lastTime = time + duration - 1;  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  类似于数组的curLen问题。本题需要控制好一个变量，来描述中毒状态，可以是最后中毒时间，然后每次更新中毒时候，就需要把时间加上去，这样就可以不必弄一个开始中毒事件的变量了。  
    
tags:    
  -  curLen问题  
