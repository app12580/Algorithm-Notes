### description    
  In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.  
    
  Return the element repeated N times.  
    
     
    
  Example 1:  
    
  Input: [1,2,3,3]  
  Output: 3  
  Example 2:  
    
  Input: [2,1,2,5,3,2]  
  Output: 2  
  Example 3:  
    
  Input: [5,1,5,2,5,3,5,4]  
  Output: 5  
     
    
  Note:  
    
  4 <= A.length <= 10000  
  0 <= A[i] < 10000  
  A.length is even  
### solution    
```    
  class Solution {  
      public int repeatedNTimes(int[] A) {  
          Set<Integer> set = new HashSet<>();  
          for(int a: A) {  
              if(set.contains(a)) return a;  
              set.add(a);  
          }  
          return -1;  
      }  
  }  
    
  //方法二 优化  
  class Solution {  
      public int repeatedNTimes(int[] A) {  
          for (int i = 1; i < A.length; ++i) {  
              if (A[i-1] == A[i]) {  
                  return A[i];  
              }  
              if (i < A.length-2 && A[i-1] == A[i+2]) {  
                  return A[i-1];  
              }  
                
              if (i < A.length-1 && A[i-1] == A[i+1]) {  
                  return A[i-1];  
              }  
          }  
            
          return 0;  
      }  
  }  
```    
    
### 个人解读    
  通过hash表存储中间过程。效率还行。   
    
  方法二的优化：利用了一共2N个数字，里面有N+1个数字，相邻两个重复数字的最小值，即使完美分摊了，最接近的最大距离也就是2。  
    
tags:    
  -  重点数学  
  -  优化  
