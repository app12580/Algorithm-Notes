### description    
  Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.  
    
  As the answer can be very large, return it modulo 10^9 + 7.  
    
     
    
  Example 1:  
    
  Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8  
  Output: 20  
  Explanation:   
  Enumerating by the values (A[i], A[j], A[k]):  
  (1, 2, 5) occurs 8 times;  
  (1, 3, 4) occurs 8 times;  
  (2, 2, 4) occurs 2 times;  
  (2, 3, 3) occurs 2 times.  
  Example 2:  
    
  Input: A = [1,1,2,2,2,2], target = 5  
  Output: 12  
  Explanation:   
  A[i] = 1, A[j] = A[k] = 2 occurs 12 times:  
  We choose one 1 from [1,1] in 2 ways,  
  and two 2s from [2,2,2,2] in 6 ways.  
     
    
  Note:  
    
  3 <= A.length <= 3000  
  0 <= A[i] <= 100  
  0 <= target <= 300  
### solution    
```    
Runtime: 2 ms, faster than 98.25% of Java online submissions for 3Sum With Multiplicity.  
Memory Usage: 38 MB, less than 100.00% of Java online submissions for 3Sum With Multiplicity.  
  
  class Solution {  
       int big = (int) (1e9 + 7);  
    
      public int threeSumMulti(int[] A, int target) {  
          long[] c = new long[101];  
          for(int a: A) {  
              c[a]++;  
          }  
          long res = 0;  
          for(int i = 0; i <= 100; i++) {  
              for(int j = i; j <= 100; j++) {  
                  int k = target - i - j;  
                  if(k < 0 || k > 100) continue;  
                  if (i == j && j == k)  
                      res += c[i] * (c[i] - 1) * (c[i] - 2) / 6;  
                  else if (i == j)  
                      res += c[i] * (c[i] - 1) / 2 * c[k];  
                  else if (j < k)  
                      res += c[i] * c[j] * c[k];  
              }  
          }  
    
          return (int) (res % big);  
      }  
  }  
```    
    
### 个人解读    
  首先思考如果只有一个数字、两个数字求和是个什么情况。。。  
    
  遇到问题：  
  1、重复数字导致要除以一个数(排列组合中的分母)。  
  2、注意题目是有限数组  
   
  所以干脆的就直接每个数字挨个去计算。  
    
  本题目的核心点在于如何遍历时候规避相同数字的影响，一个较好的解决办法是用ijk三个数字分别计算，要满足i<=j<=k  
  代码里面i<j时候，只有k>j的时候才算进去。  
    
tags:    
  -  数组  
