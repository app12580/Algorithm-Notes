### description    
  Given an array A of 0s and 1s, consider N_i: the i-th subarray from A[0] to A[i] interpreted as a binary number (from most-significant-bit to least-significant-bit.)  
    
  Return a list of booleans answer, where answer[i] is true if and only if N_i is divisible by 5.  
    
  Example 1:  
    
  Input: [0,1,1]  
  Output: [true,false,false]  
  Explanation:   
  The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.  Only the first number is divisible by 5, so answer[0] is true.  
  Example 2:  
    
  Input: [1,1,1]  
  Output: [false,false,false]  
  Example 3:  
    
  Input: [0,1,1,1,1,1]  
  Output: [true,false,false,false,true,false]  
  Example 4:  
    
  Input: [1,1,1,0,1]  
  Output: [false,false,false,false,false]  
     
    
  Note:  
    
  1 <= A.length <= 30000  
  A[i] is 0 or 1  
### solution    
```    
  class Solution {  
      public List<Boolean> prefixesDivBy5(int[] A) {  
          int sum = 0;  
          List<Boolean> res = new ArrayList<>();  
          for(int num: A) {  
              sum = sum * 2 + num;  
              sum = sum % 5;  
              res.add(sum % 5 == 0);  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  依然是一个题目描述看不懂，只有看了示例才知道要干啥的题目。  
    
  题目很简单，但是示例竟然超限，不光超限，甚至连long也超了。。。。  
    
  ```  
  常规解法    
    public List<Boolean> prefixesDivBy5(int[] A) {  
          long sum = 0;  
          List<Boolean> res = new ArrayList<>();  
          for(int num: A) {  
              sum = sum * 2 + num;  
              res.add(sum % 5 == 0);  
          }  
          return res;  
      }  
  ```  
    
  想办法削减。因为sum过程中，5N的部分，无论后续怎么加，都不影响结果了。所以可以直接刨去。  
    
tags:    
  -  数学  
  -  超限  
  -  优化  
