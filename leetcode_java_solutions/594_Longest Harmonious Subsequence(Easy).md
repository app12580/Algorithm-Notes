### description    
  We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.  
    
  Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.  
    
  Example 1:  
  Input: [1,3,2,2,5,2,3,7]  
  Output: 5  
  Explanation: The longest harmonious subsequence is [3,2,2,2,3].  
  Note: The length of the input array will not exceed 20,000.  
### solution    
```    
  class Solution {  
      public int findLHS(int[] nums) {  
          Map<Integer, Integer> countMap = new HashMap();  
          for(int num: nums) {  
              countMap.put(num, countMap.getOrDefault(num, 0) + 1);  
          }  
          int res = 0;  
          for(Map.Entry<Integer, Integer> entry: countMap.entrySet()) {  
              int key = entry.getKey();  
              if(countMap.containsKey(key + 1)) {  
                  res = Math.max(res, countMap.get(key) + countMap.get(key + 1));  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  找出数组中符合某种条件的子序列  
  这种问题的难点，主要是这个判断条件往往是需要一长串子序列共同判断的  
  常用的一种无脑做法是动态规划，但是往往效率不高，所以难点是要找到，那一个可以在遍历时候，存储的数据是什么，以及相应的判断方法，(有一种常见的就是长度为26的数组，表示26个字母相应特殊点的索引)。  
  中间存储数据，可以分为两类，一类是全范围存储结果(比如int[26]，比如全内容的hash表)，另一类是个别数据，比如只用当选循环单元i的某些信息(比如max)。  
  子序列问题又分为连续子序列和非连续子序列。  
  既然本题是非连续子序列问题，那么就把全部的num长度先取出来以后，然后再从头到尾挨个比较一遍就是了  
tags:    
  -  数组  
  -  子序列问题  
  -  非连续子序列  
