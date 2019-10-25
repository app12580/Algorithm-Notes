### description    
  A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.  
    
  For example, these are arithmetic sequences:  
    
  1, 3, 5, 7, 9  
  7, 7, 7, 7  
  3, -1, -5, -9  
  The following sequence is not arithmetic.  
    
  1, 1, 2, 5, 7  
     
  A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.  
    
  A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.  
    
  The function should return the number of arithmetic subsequence slices in the array A.  
    
  The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.  
    
     
  Example:  
    
  Input: [2, 4, 6, 8, 10]  
    
  Output: 7  
    
  Explanation:  
  All arithmetic subsequence slices are:  
  [2,4,6]  
  [4,6,8]  
  [6,8,10]  
  [2,4,6,8]  
  [4,6,8,10]  
  [2,4,6,8,10]  
  [2,6,10]  
### solution    
```    
  Runtime: 137 ms, faster than 68.28% of Java online submissions for Arithmetic Slices II - Subsequence.  
  Memory Usage: 64.3 MB, less than 100.00% of Java online submissions for Arithmetic Slices II - Subsequence.  
  class Solution {  
      public int numberOfArithmeticSlices(int[] A) {  
          int re = 0;  
          HashMap<Integer, Integer>[] maps = new HashMap[A.length];  
          for(int i=0; i<A.length; i++) {  
              maps[i] = new HashMap<>();  
              int num = A[i];  
              for(int j=0; j<i; j++) {  
                  if((long)num-A[j]>Integer.MAX_VALUE) continue;  
                  if((long)num-A[j]<Integer.MIN_VALUE) continue;  
                  int diff = num - A[j];  
                  int count = maps[j].getOrDefault(diff, 0);  
                  maps[i].put(diff, maps[i].getOrDefault(diff,0)+count+1);  
                  re += count;  
              }  
          }  
          return re;  
      }  
  }  
```    
    
### 个人解读    
  与[413](413_Arithmetic%20Slices%20(Medium).md)相比，那个题目是连续子区间，所以省了很多功夫，而本题目是  
    
  疑问一：重复的区间如何计数？多算一遍  
    
  首先思路是没错的，用一个数据结构存储每个数字为结尾的情况，需要存储的有公差和数量。  
  然而用什么数据结构是关键。  
  HashMap<Integer, Integer>[] map = new HashMap[len];  
  这种虽然少见，但很有用  
    
tags:    
  -  非连续子区间  
  -  区间数组  
