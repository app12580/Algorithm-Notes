### description    
  Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)  
    
  Return the largest possible sum of the array after modifying it in this way.  
    
     
    
  Example 1:  
    
  Input: A = [4,2,3], K = 1  
  Output: 5  
  Explanation: Choose indices (1,) and A becomes [4,-2,3].  
  Example 2:  
    
  Input: A = [3,-1,0,2], K = 3  
  Output: 6  
  Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].  
  Example 3:  
    
  Input: A = [2,-3,-1,5,-4], K = 2  
  Output: 13  
  Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].  
     
    
  Note:  
    
  1 <= A.length <= 10000  
  1 <= K <= 10000  
  -100 <= A[i] <= 100  
    
    
### solution    
```    
  class Solution {  
      public int largestSumAfterKNegations(int[] A, int K) {  
          Arrays.sort(A);  
          int k = 0;  
          int index = 0;  
          while(k < K) {  
              if(index >= A.length) {  
                  index--;  
              }  
              int cur = A[index];  
              if(cur < 0) {  
                  A[index] = -A[index];  
                  index++;  
              } else if(cur == 0){  
                  break;  
              } else {  
                  //此时要么前面为负数，要么为-1  
                  if((K - k) % 2 == 0) {  
                      break;          
                  } else {  
                      //选择反转当前还是前一个  
                      if(index >0 && A[index - 1] < A[index]){  
                          A[index - 1] = -A[index - 1];  
                      } else {  
                          A[index] = -A[index];  
                      }  
                      break;  
                  }  
              }  
              k++;  
          }  
          int sum = 0;  
          for(int a: A) {  
              sum += a;  
          }  
          return sum;  
      }  
  }  
```    
    
### 个人解读    
  有正有负，需要很多次判断，先考虑动手原则可不可行(感觉没啥用)。  
  然后需要预处理，先将数组排序。  
    
  排序后再动手原则，两个指针：数组下标index，和K次计数器  
  如果cur<0 则执行反转操作，index++,k++。  
  如果cur>0 则分类讨论，此时可以削减K的次数  
    
  主要是要注意好每次遍历过程时候的细节  
    
    
    
tags:    
  -  预处理  
  -  遍历过程分析  
