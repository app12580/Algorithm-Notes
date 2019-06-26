### description    
  Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.  
    
  Example 1:  
  Input: [3, 1, 4, 1, 5], k = 2  
  Output: 2  
  Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).  
  Although we have two 1s in the input, we should only return the number of unique pairs.  
  Example 2:  
  Input:[1, 2, 3, 4, 5], k = 1  
  Output: 4  
  Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).  
  Example 3:  
  Input: [1, 3, 1, 5, 4], k = 0  
  Output: 1  
  Explanation: There is one 0-diff pair in the array, (1, 1).  
  Note:  
  The pairs (i, j) and (j, i) count as the same pair.  
  The length of the array won't exceed 10,000.  
  All the integers in the given input belong to the range: [-1e7, 1e7].  
### solution    
```    
  class Solution {  
      public int findPairs(int[] nums, int k) {  
          if(k < 0) return 0;  
          int res = 0;  
          if(k == 0) {  
              Map<Integer, Integer> map = new HashMap<>();  
              for(int num: nums) {  
                   map.put(num, map.getOrDefault(num, 0) + 1);  
              }  
              for(Integer key: map.keySet()) {  
                  if(map.get(key) > 1) res++;  
              }  
          } else {  
              Set<Integer> set = new HashSet<>();  
              for(int num: nums) {  
                  set.add(num);  
              }  
              for(int num: set) {  
                  if(set.contains(num + k)) {  
                      res++;  
                  }  
              }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  要么预处理先排序，要么找个东西存储中间遍历结果。  
  注意k==0和k!=0的情况要分开讨论。  
  总想着要一次循环就把问题解决，然后看了一下参考答案发现，还是老老实实的两次循环把。  
    
    
  if(k < 0) return 0;  
  这种特殊的边界处理真的是从来不在题干里面说清楚的啊！！！    
      
tags:    
  -  数学  
  -  Hash表  
