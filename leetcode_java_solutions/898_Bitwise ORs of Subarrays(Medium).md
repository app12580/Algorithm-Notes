### description    
  We have an array A of non-negative integers.  
    
  For every (contiguous) subarray B = [A[i], A[i+1], ..., A[j]] (with i <= j), we take the bitwise OR of all the elements in B, obtaining a result A[i] | A[i+1] | ... | A[j].  
    
  Return the number of possible results.  (Results that occur more than once are only counted once in the final answer.)  
    
     
    
  Example 1:  
    
  Input: [0]  
  Output: 1  
  Explanation:   
  There is only one possible result: 0.  
  Example 2:  
    
  Input: [1,1,2]  
  Output: 3  
  Explanation:   
  The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].  
  These yield the results 1, 1, 2, 1, 3, 3.  
  There are 3 unique values, so the answer is 3.  
  Example 3:  
    
  Input: [1,2,4]  
  Output: 6  
  Explanation:   
  The possible results are 1, 2, 3, 4, 6, and 7.  
     
    
  Note:  
    
  1 <= A.length <= 50000  
  0 <= A[i] <= 10^9  
### solution    
```    
Runtime: 374 ms, faster than 11.59% of Java online submissions for Bitwise ORs of Subarrays.  
Memory Usage: 67.2 MB, less than 100.00% of Java online submissions for Bitwise ORs of Subarrays.  
  
  class Solution {  
      public int subarrayBitwiseORs(int[] A) {  
          Set<Integer> res = new HashSet<>();  
          Set<Integer> pre = new HashSet<>();  
          Set<Integer> cur;  
          for(int a: A) {  
              cur = new HashSet<>();  
              cur.add(a);  
              for(int p: pre) {  
                  cur.add(p | a);  
              }  
              res.addAll(cur);  
              pre = cur;  
          }  
          return res.size();  
      }  
  }  
```    
    
### 个人解读    
  这种数组中选取两点的问题：curLen、单调栈。很类似于[907](907_Sum%20of%20Subarray%20Minimums(Medium).md)  
  获取所有子区间的特征值。  
    
  总结：  
  单调栈法还是只用在含有最值的情况吧，别想太多骚操作，没必要。  
  类似于暴力法，每次遍历时候挨个比较，获取全部可能结果。需要定义一个set，用来存储前一个数字为结尾的区间的所有可能结果。  
    
tags:    
  -  暴力法  
