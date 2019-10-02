### description    
  Given an array of integers A with even length, return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.  
    
  Example 1:  
    
  Input: [3,1,3,6]  
  Output: false  
  Example 2:  
    
  Input: [2,1,2,6]  
  Output: false  
  Example 3:  
    
  Input: [4,-2,2,-4]  
  Output: true  
  Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].  
  Example 4:  
    
  Input: [1,2,4,16,8,4]  
  Output: false  
     
    
  Note:  
    
  0 <= A.length <= 30000  
  A.length is even  
  -100000 <= A[i] <= 100000  
### solution    
```    
Runtime: 41 ms, faster than 61.32% of Java online submissions for Array of Doubled Pairs.  
Memory Usage: 45.2 MB, less than 33.33% of Java online submissions for Array of Doubled Pairs.  
  
  class Solution {  
    public boolean canReorderDoubled(int[] A) {  
          Map<Integer, Integer> count = new TreeMap<>();  
          for (int a : A)  
              count.put(a, count.getOrDefault(a, 0) + 1);  
          for (int x : count.keySet()) {  
              if (count   .get(x) == 0) continue;  
              int want = x < 0 ? x / 2 : x * 2;  
              if (x < 0 && x % 2 != 0 || count.get(x) > count.getOrDefault(want, 0))  
                  return false;  
              count.put(want, count.get(want) - count.get(x));  
          }  
          return true;  
      }  
  }  
```    
    
### 个人解读    
  本题目的重点是要统计每个数字的个数，然后按照规律去分配。  
  问题在于，需要按顺序分配的话，需要记录排序，就要使用TreeMap，如果不事先排序，就需要每次操作的时候获取很多的信息才可以做到。  
  思路一：使用TreeMap，然后每次从小的开始处理。  
  遇到问题，需要按照绝对值进行排序，但是这样会导致-2与2冲突了。  
  绝对值问题： 如果是负数，就从最小开始；如果是正数，就从最大开始。  
    
   
    
tags:    
  -  TreeMap  
