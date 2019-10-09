### description    
  Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.  
    
  Return the least number of moves to make every value in A unique.  
    
     
    
  Example 1:  
    
  Input: [1,2,2]  
  Output: 1  
  Explanation:  After 1 move, the array could be [1, 2, 3].  
  Example 2:  
    
  Input: [3,2,1,2,1,7]  
  Output: 6  
  Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].  
  It can be shown with 5 or less moves that it is impossible for the array to have all unique values.  
     
    
  Note:  
    
  0 <= A.length <= 40000  
  0 <= A[i] < 40000  
### solution    
```    
Runtime: 19 ms, faster than 37.84% of Java online submissions for Minimum Increment to Make Array Unique.  
Memory Usage: 51.1 MB, less than 83.33% of Java online submissions for Minimum Increment to Make Array Unique.  
  
  class Solution {  
      public int minIncrementForUnique(int[] A) {  
          Arrays.sort(A);  
          int res = 0;  
          int count = 0;  
          int preVal = -1;  
          for(int i = 0; i < A.length; i++) {  
              int cur = A[i];  
              if(preVal == -1) {  
              } else if(count + preVal >= cur) {  
                  int gap = cur - preVal - 1;  
                  res += sum(gap) + (cur - preVal) * (count - gap);  
                  count -= gap;  
              } else if(count + preVal < cur) {  
                  res += sum(count);  
                  count = 0;  
              }  
              preVal = cur;  
              while(i < A.length - 1 && A[i] == A[i + 1]) {  
                  count++;  
                  i++;  
              }  
          }  
          return res + sum(count);  
      }  
    
      private int sum(int count) {  
          return (count + 1) * count / 2;  
      }  
  }  
```    
    
### 个人解读    
  由于遇到重复的数字时候，需要去找到比此大的最小数字，要么每个数字遇到的时候就单独去找；要么就所有数字混在一块，使用数学方法去统一计算。  
  思路一：先预处理排序，然后用两个变量：res和count来记录前面有多少个重复的数字。  
    
tags:    
  -  数学  
  -  数字逻辑  
