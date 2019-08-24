### description    
  Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:  
    
  The number at the ith position is divisible by i.  
  i is divisible by the number at the ith position.  
     
    
  Now given N, how many beautiful arrangements can you construct?  
    
  Example 1:  
    
  Input: 2  
  Output: 2  
  Explanation:   
    
  The first beautiful arrangement is [1, 2]:  
    
  Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).  
    
  Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).  
    
  The second beautiful arrangement is [2, 1]:  
    
  Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).  
    
  Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.  
     
    
  Note:  
    
  N is a positive integer and will not exceed 15.  
### solution    
```    
  
Runtime: 54 ms, faster than 68.93% of Java online submissions for Beautiful Arrangement.  
Memory Usage: 33 MB, less than 5.26% of Java online submissions for Beautiful Arrangement.  
  
  
  class Solution {  
       int res = 0;  
    
      public int countArrangement(int N) {  
          int[] arr = new int[N + 1];  
          dfs(arr, 1, N);      
          return res;  
      }  
    
      private void dfs(int[] arr, int start, int N) {  
          if(start > N) {  
              return;  
          }  
          for(int i = 1; i <= N; i++) {  
              if(arr[i] != 0) {  
                  continue;  
              }  
              if(i % start == 0 || start % i == 0) {  
                  arr[i] = start;  
                  if(start == N) {  
                      res++;  
                  } else {  
                      dfs(arr, start + 1, N);  
                  }  
                    
              }  
              arr[i] = 0;  
    
          }  
      }  
  }  
```    
    
### 个人解读    
  这种题目如果没什么幺蛾子的话，就是需要dfs了。  
  dfs+回溯  
    
  典型的模板题目  
    
tags:    
  -  DFS   
  -  回溯   
