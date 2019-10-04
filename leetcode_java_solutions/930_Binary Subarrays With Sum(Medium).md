### description    
  In an array A of 0s and 1s, how many non-empty subarrays have sum S?  
    
     
    
  Example 1:  
    
  Input: A = [1,0,1,0,1], S = 2  
  Output: 4  
  Explanation:   
  The 4 subarrays are bolded below:  
  [1,0,1,0,1]  
  [1,0,1,0,1]  
  [1,0,1,0,1]  
  [1,0,1,0,1]  
     
    
  Note:  
    
  A.length <= 30000  
  0 <= S <= A.length  
  A[i] is either 0 or 1.  
### solution    
```    
  
Runtime: 2 ms, faster than 99.88% of Java online submissions for Binary Subarrays With Sum.  
Memory Usage: 40.5 MB, less than 50.00% of Java online submissions for Binary Subarrays With Sum.  
  
  class Solution {  
       public int numSubarraysWithSum(int[] A, int S) {  
          int i = 0, j = 0;  
          int len = A.length;  
          int sum = 0;  
          int res = 0;  
          int preZero = 1;  
          int nextZero = 1;  
           if(S == 0) {  
              for(; i < len; i++) {  
                  if(A[i] == 0) {  
                      res += preZero;  
                  }  
                  if(A[i] == 0) {  
                      preZero++;  
                  } else {  
                      preZero = 1;  
                  }  
              }  
              return res;  
          }  
          while(j < len ) {  
              //    [1,0,1,0,1], S = 2  
              while(j < len && sum < S) {  
                  sum += A[j];  
                  j++;  
              }  
              //j是sum为S的下一位  
              if(sum < S) break;  
              while(j < len && A[j] == 0) {  
                  j++;  
                  nextZero++;  
              }  
              //移动左端，修正preZero  
              while(i < j && A[i] == 0) {  
                  i++;  
                  preZero++;  
              }  
                 
              res += preZero * nextZero;  
              
                
              i++;  
              preZero = 1;  
              sum--;  
              nextZero=1;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  与[974](974_Subarray%20Sums%20Divisible%20by%20K(Medium).md)类似，然而因为974是modK的数量，所以那个可以累加求和然后看数量。  
  而本题目因为sum和为某一个值，所以不太适用。  
  思路： 滑动窗口。  
  因为0的问题需要算进结果里面，而直接内部while会导致处理麻烦，所以决定一个一个处理。然而纠结了一下还是打算内部while。  
    
  因为算例，内部while失败  
  ```  
  [0,0,0,0,0]  
  0  
  ```  
  这种情况下，每次slide一下都需要单独的修改的  
  最终决定，把S==0的单独拎出来计算。  
    
  总结：  
  各种打补丁，最终运行效率喜人。  
    
tags:    
  -  数学  
  -  curLen问题  
  -  滑动窗口  
