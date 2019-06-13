### description    
You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.  
  
Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.  
  
Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.  
  
Example 1:  
Input: [[1,2], [2,3], [3,4]]  
Output: 2  
Explanation: The longest chain is [1,2] -> [3,4]  
Note:  
The number of given pairs will be in the range [1, 1000].  
  
一组整数对能够构成的最长链  
    
### solution    
```    
  class Solution {  
      public int findLongestChain(int[][] pairs) {  
          Arrays.sort(pairs, new Comparator<int[]>() {  
              @Override  
              public int compare(int[] o1, int[] o2) {  
                  if(o1[1] == o2[1]) {  
                      return o1[0] - o2[0];  
                  } else {  
                      return o1[1] - o2[1];  
                  }  
              }  
          });  
            
          int count = 0;  
          int last = Integer.MIN_VALUE;  
          for(int i = 0; i < pairs.length; i++) {  
              int[] curPair = pairs[i];  
              int p1 = curPair[0];  
              int p2 = curPair[1];  
              if(p1 > last) {  
                  count++;  
                  last = p2;  
              }  
          }  
          return count;  
      }  
  }  
```    
    
### 个人解读    
  类似于射气球的那个计算不重复区间最大个数。  
  将区间排序，然后用贪婪算法计算。  
  备注：刷题阶段看不懂别人的解法，并且自己已经有不错的解法时候，没必要强求去看懂别人的做法，可能绕了一堆弯，没必要看懂。  
    
tags:    
  -  贪婪算法  
  -  数组  
