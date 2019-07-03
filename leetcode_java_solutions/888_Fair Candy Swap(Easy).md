### description    
  Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.  
    
  Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)  
    
  Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.  
    
  If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.  
    
     
    
  Example 1:  
    
  Input: A = [1,1], B = [2,2]  
  Output: [1,2]  
  Example 2:  
    
  Input: A = [1,2], B = [2,3]  
  Output: [1,2]  
  Example 3:  
    
  Input: A = [2], B = [1,3]  
  Output: [2,3]  
  Example 4:  
    
  Input: A = [1,2,5], B = [2,4]  
  Output: [5,4]  
     
    
  Note:  
    
  1 <= A.length <= 10000  
  1 <= B.length <= 10000  
  1 <= A[i] <= 100000  
  1 <= B[i] <= 100000  
  It is guaranteed that Alice and Bob have different total amounts of candy.  
  It is guaranteed there exists an answer.  
### solution    
```    
  
Runtime: 15 ms, faster than 56.76% of Java online submissions for Fair Candy Swap.  
Memory Usage: 40.5 MB, less than 93.94% of Java online submissions for Fair Candy Swap.  
  
  class Solution {  
      public int[] fairCandySwap(int[] A, int[] B) {  
           Set<Integer> set = new HashSet<>();  
          int sum1 = 0;  
          int sum2 = 0;  
          for(int a: A) {  
              sum1 += a;  
              set.add(a);  
          }  
          for(int b: B) {  
              sum2 += b;  
          }  
          int need = (sum1 - sum2) / 2;  
          for(int b: B) {  
              int n = need + b;  
              if(set.contains(n)) {  
                  return new int[]{n, b};  
              }  
          }  
          return null;  
      }  
  }  
```    
    
### 个人解读    
  结果需要知道两个sum，另外需要一个东西去存储中间结果，不然很难找答案。  
  另外，预处理排序没什么卵用。  
  注意need需要除以2  
    
tags:    
  -  数学  
  -  Hash表  
