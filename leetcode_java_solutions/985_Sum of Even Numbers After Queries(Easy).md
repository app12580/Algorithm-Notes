### description    
  We have an array A of integers, and an array queries of queries.  
    
  For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].  Then, the answer to the i-th query is the sum of the even values of A.  
    
  (Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)  
    
  Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.  
    
     
    
  Example 1:  
    
  Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]  
  Output: [8,6,2,4]  
  Explanation:   
  At the beginning, the array is [1,2,3,4].  
  After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.  
  After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.  
  After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.  
  After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.  
     
    
  Note:  
    
  1 <= A.length <= 10000  
  -10000 <= A[i] <= 10000  
  1 <= queries.length <= 10000  
  -10000 <= queries[i][0] <= 10000  
  0 <= queries[i][1] < A.length  
    
  把题目描述翻译成人话：  
  已知int[] A，对它执行queries操作，queries是一个int[i][2]数组，每次操作的含义：  
    val = queries[i][0], index = queries[i][1]   
    然后令A[index] += val  
  返回每次操作后A中所有的偶数和，返回值是Int[]形式。    
    
### solution    
```    
  class Solution {  
      public int[] sumEvenAfterQueries(int[] A, int[][] queries) {  
           int sum = 0;  
          for(int a: A) {  
              if(a % 2 == 0) {  
                  sum += a;  
              }  
          }  
          int[] res = new int[A.length];  
          int index = 0;  
    
          for(int[] q: queries) {  
              int val = q[0];  
              int i = q[1];  
              if(A[i] % 2 == 0) {  
                  sum -= A[i];  
              }  
              A[i] += val;  
              if(A[i] % 2 == 0) {  
                  sum += A[i];  
              }  
              res[index++] = sum;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  题目描述看不懂，需要看示例才知道要干啥。  
    
  动手原则，每次先减了再说。  
  按照步骤一步一步即可。  
    
tags:    
  -  数学  
  -  遍历  
