### description    
  You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even numbered jumps.  
    
  You may from index i jump forward to index j (with i < j) in the following way:  
    
  During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.  
  During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.  
  (It may be the case that for some index i, there are no legal jumps.)  
  A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once.)  
    
  Return the number of good starting indexes.  
    
     
    
  Example 1:  
    
  Input: [10,13,12,14,15]  
  Output: 2  
  Explanation:   
  From starting index i = 0, we can jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is greater or equal to A[0]), then we can't jump any more.  
  From starting index i = 1 and i = 2, we can jump to i = 3, then we can't jump any more.  
  From starting index i = 3, we can jump to i = 4, so we've reached the end.  
  From starting index i = 4, we've reached the end already.  
  In total, there are 2 different starting indexes (i = 3, i = 4) where we can reach the end with some number of jumps.  
  Example 2:  
    
  Input: [2,3,1,1,4]  
  Output: 3  
  Explanation:   
  From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:  
    
  During our 1st jump (odd numbered), we first jump to i = 1 because A[1] is the smallest value in (A[1], A[2], A[3], A[4]) that is greater than or equal to A[0].  
    
  During our 2nd jump (even numbered), we jump from i = 1 to i = 2 because A[2] is the largest value in (A[2], A[3], A[4]) that is less than or equal to A[1].  A[3] is also the largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3.  
    
  During our 3rd jump (odd numbered), we jump from i = 2 to i = 3 because A[3] is the smallest value in (A[3], A[4]) that is greater than or equal to A[2].  
    
  We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.  
    
  In a similar manner, we can deduce that:  
  From starting index i = 1, we jump to i = 4, so we reach the end.  
  From starting index i = 2, we jump to i = 3, and then we can't jump anymore.  
  From starting index i = 3, we jump to i = 4, so we reach the end.  
  From starting index i = 4, we are already at the end.  
  In total, there are 3 different starting indexes (i = 1, i = 3, i = 4) where we can reach the end with some number of jumps.  
  Example 3:  
    
  Input: [5,1,3,4,2]  
  Output: 3  
  Explanation:   
  We can reach the end from starting indexes 1, 2, and 4.  
     
    
  Note:  
    
  1 <= A.length <= 20000  
  0 <= A[i] < 100000  
### solution    
```    
// 方法一： TreeMap  
Runtime: 71 ms, faster than 30.27% of Java online submissions for Odd Even Jump.  
Memory Usage: 44.8 MB, less than 50.00% of Java online submissions for Odd Even Jump.  
  
  class Solution {  
      public int oddEvenJumps(int[] A) {  
          int N = A.length;  
          if (N <= 1) return N;  
          boolean[] odd = new boolean[N];  
          boolean[] even = new boolean[N];  
          odd[N-1] = even[N-1] = true;  
    
          TreeMap<Integer, Integer> vals = new TreeMap();  
          vals.put(A[N-1], N-1);  
          for (int i = N-2; i >= 0; --i) {  
              int v = A[i];  
              if (vals.containsKey(v)) {  
                    //如果后面有v，则会跳到该位置上  
                  odd[i] = even[vals.get(v)];  
                  even[i] = odd[vals.get(v)];  
              } else {  
                  Integer lower = vals.lowerKey(v);  
                  Integer higher = vals.higherKey(v);  
                    // 如果等于null ， 就是false  
                  if (lower != null)  
                      even[i] = odd[vals.get(lower)];  
                  if (higher != null) {  
                      odd[i] = even[vals.get(higher)];  
                  }  
              }  
              vals.put(v, i);  
          }  
    
          int ans = 0;  
          for (boolean b: odd)  
              if (b) ans++;  
          return ans;  
      }  
  }   
    
  // 方法二： 优化  
  Runtime: 55 ms, faster than 75.64% of Java online submissions for Odd Even Jump.  
  Memory Usage: 43.6 MB, less than 70.00% of Java online submissions for Odd Even Jump.  
    
  class Solution {  
      public int oddEvenJumps(int[] A) {  
          int n  = A.length, res = 1;  
          boolean[] higher = new boolean[n], lower = new boolean[n];  
          higher[n - 1] = lower[n - 1] = true;  
          TreeMap<Integer, Integer> map = new TreeMap<>();  
          map.put(A[n - 1], n - 1);  
          for (int i = n - 2; i >= 0; --i) {  
              Map.Entry hi = map.ceilingEntry(A[i]), lo = map.floorEntry(A[i]);  
              if (hi != null) higher[i] = lower[(int)hi.getValue()];  
              if (lo != null) lower[i] = higher[(int)lo.getValue()];  
              if (higher[i]) res++;  
              map.put(A[i], i);  
          }  
          return res;  
      }  
  }   
```    
    
### 个人解读    
  奇数跳时候是往高了跳，并且只能跳最小的那一个；偶数跳往低了跳，并且只能跳最大  
    
  思路一：  
  DP，存储每个节点奇数跳和偶数跳的情况，从后往前进行。但是问题是没次都要遍历后面的去找到最接近目标的值。  
  猜测：通过一个有序数据结构存起来。可以利用TreeMap来进行。  
    
  思路二：  
  两点优化:  
  1、在遍历内计算结果  
  2、使用entry  
    
tags:    
  -  动态规划  
