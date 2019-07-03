### description    
  Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.  
    
  If it is impossible to form any triangle of non-zero area, return 0.  
    
     
    
  Example 1:  
    
  Input: [2,1,2]  
  Output: 5  
  Example 2:  
    
  Input: [1,2,1]  
  Output: 0  
  Example 3:  
    
  Input: [3,2,3,4]  
  Output: 10  
  Example 4:  
    
  Input: [3,6,2,3]  
  Output: 8  
     
    
  Note:  
    
  3 <= A.length <= 10000  
  1 <= A[i] <= 10^6  
    
    
### solution    
```    
Runtime: 9 ms, faster than 97.21% of Java online submissions for Largest Perimeter Triangle.
Memory Usage: 38.5 MB, less than 99.31% of Java online submissions for Largest Perimeter Triangle.

  class Solution {  
        public int largestPerimeter(int[] A) {  
          int res = 0;  
          Arrays.sort(A);  
          for(int i = A.length - 1; i >= 2; i --) {  
              if(check(A[i], A[i-1], A[i-2])) {  
                  return A[i] + A[i-1] + A[i -2];  
              }  
          }  
          return 0;  
    
      }  
    
      private boolean check(int i, int j, int k) {  
          return i + j > k && i + k > j && j + k > i;  
      }  
  }  
```    
    
### 个人解读    
  感觉可以用动手原则了。    
    
  ```  
  //超时了，被一个特别长的测试用例通不过。  
  class Solution {  
        public int largestPerimeter(int[] A) {  
          int res = 0;  
          for(int i = 0; i < A.length; i++) {  
              for(int j = i + 1; j < A.length; j++) {  
                  for(int k = j + 1; k < A.length; k++) {  
                      if(check(A[i],A[j],A[k])) {  
                          res = Math.max(res, A[i] + A[k] + A[j]);  
                      }  
                  }  
              }  
          }  
          return res;  
      }  
    
      private boolean check(int i, int j, int k) {  
          return i + j > k && i + k > j && j + k > i;  
      }  
  }  
  ```  
    
  如果先排序的话，然后从大到小，问题是如何控制ijk的顺序。  
  先排序，然后从最大的往下遍历，显示第一二三大，然后234，然后345.  
  证明： 如果有1，那么一定23，剩下略。  
    
tags:    
  -  数学  
  -  三角形  
  -  优化  
