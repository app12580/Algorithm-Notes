### description    
  Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.  
    
     
    
  Example 1:  
    
  Input: A = [4,5,0,-2,-3,1], K = 5  
  Output: 7  
  Explanation: There are 7 subarrays with a sum divisible by K = 5:  
  [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]  
     
    
  Note:  
    
  1 <= A.length <= 30000  
  -10000 <= A[i] <= 10000  
  2 <= K <= 10000  
### solution    
```    
Runtime: 5 ms, faster than 79.58% of Java online submissions for Subarray Sums Divisible by K.  
Memory Usage: 42.9 MB, less than 52.63% of Java online submissions for Subarray Sums Divisible by K.  
  
  class Solution {  
     public int subarraysDivByK(int[] A, int K) {  
          int len = A.length;  
          int sum = 0;  
          int[] counts = new int[K];  
          int res = 0;  
    
          for(int i = 0; i < len; i++) {  
              sum += A[i];  
              int mod = (sum % K + K) % K;  //error: int mod = Math.abs(sum % K);  
              if(mod == 0) {  
                  res += counts[mod] + 1;  
              } else {  
                  res += counts[mod];  
              }  
              counts[mod]++;  
          }  
    
          return res;  
      }  
    
  }  
```    
    
### 个人解读    
  感觉这种问题没有任何的捷径，只能每种求和都要比较一下。需要注意的是不要出现重复的计算操作。  
  思路：统计sum[]求和，然后开始二维遍历。  
  然而TLE了，所以需要想办法变成O(N)的了。  
    
  思路二：每计算一个sum之后，用map存储sum%K的数量  
  之后根据count数量来增加res。  
  两点细节：  
  1、如果mod==0时候，需要额外+1  
  2、mod为负数时候的处理，不能直接abs，而是要+K  
    
  ```  
  TLE  
    public int subarraysDivByK(int[] A, int K) {  
          int len = A.length;  
          int[] sumArr = new int[len + 1];  
          int sum = 0;  
          for(int i = 0; i < len; i++) {  
              sum += A[i];  
              sumArr[i + 1] = sum;  
          }  
          int res = 0;  
          for(int i = 0; i < len; i++) {  
              for(int j = i + 1; j <= len; j++) {  
                  int cur = sumArr[j] - sumArr[i];  
                  if(cur % K == 0) {  
                      res++;  
                  }  
              }  
          }  
          return res;  
      }  
  ```  
    
    
tags:    
  -  数学  
  -  数字逻辑  
