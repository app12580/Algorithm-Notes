### description      
  Given an array of integers A, consider all non-empty subsequences of A.    
      
  For any sequence S, let the width of S be the difference between the maximum and minimum element of S.    
      
  Return the sum of the widths of all subsequences of A.     
      
  As the answer may be very large, return the answer modulo 10^9 + 7.    
      
       
      
  Example 1:    
      
  Input: [2,1,3]    
  Output: 6    
  Explanation:    
  Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].    
  The corresponding widths are 0, 0, 0, 1, 1, 2, 2.    
  The sum of these widths is 6.    
       
      
  Note:    
      
  1 <= A.length <= 20000    
  1 <= A[i] <= 20000    
### solution      
```      
// 方法一 ： 数学规律    
Runtime: 17 ms, faster than 98.95% of Java online submissions for Sum of Subsequence Widths.    
Memory Usage: 41.5 MB, less than 100.00% of Java online submissions for Sum of Subsequence Widths.    
    
  class Solution {    
        public int sumSubseqWidths(int[] A) {    
          Arrays.sort(A);    
          long c = 1, res = 0, mod = (long)1e9 + 7;    
          for (int i = 0; i < A.length; ++i, c = (c << 1) % mod)  //这里的c%mod很关键    
              res = (res + A[i] * c - A[A.length - i - 1] * c) % mod;    
          return (int)((res + mod) % mod);    
      
      }    
  }    
```      
      
### 个人解读      
  两种方式： DP 或者通过极值，在扫描时把结果算出来。    
      
  仔细观察会发现，主要是找出两个元素可以作为最大值和最小值。由此引出了第一个重要规律：    
  1、本题目打乱源数组顺序不影响结果。    
  2、然后想办法遍历，加上所有的最大值，减去所有的最小值。    
    https://leetcode.com/problems/sum-of-subsequence-widths/discuss/161267/C%2B%2BJava1-line-Python-Sort-and-One-Pass  
      
    The order in initial arrays doesn't matter,  
    my first intuition is to sort the array.  
      
    For A[i]:  
    There are i smaller numbers,  
    so there are 2 ^ i sequences in which A[i] is maximum.  
    we should do res += A[i] * (2 ^ i)  
      
    There are n - i - 1 bigger numbers,  
    so there are 2 ^ (n - i - 1) sequences in which A[i] is minimum.  
    we should do res -= A[i] * 2 ^ (n - i - 1)  
      
    Done.  
      
tags:      
  -  重点数学    
  -  大数统计    
  -  全部非连续子区间    
