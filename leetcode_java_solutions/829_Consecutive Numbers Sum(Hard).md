### description    
  Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?  
    
  Example 1:  
    
  Input: 5  
  Output: 2  
  Explanation: 5 = 5 = 2 + 3  
  Example 2:  
    
  Input: 9  
  Output: 3  
  Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4  
  Example 3:  
    
  Input: 15  
  Output: 4  
  Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5  
  Note: 1 <= N <= 10 ^ 9.  
### solution    
```    
// 找规律： 如果可以是n个连续数，那么N - (1+2+...+n) 必须可以被n整除  
Runtime: 4 ms, faster than 46.94% of Java online submissions for Consecutive Numbers Sum.  
Memory Usage: 33.4 MB, less than 9.09% of Java online submissions for Consecutive Numbers Sum.  
  class Solution {  
      public int consecutiveNumbersSum(int N) {  
          // 2N = k(2x + k + 1)  
          int res = 1;  
          for (int i = 2; i < Math.sqrt(2 * N); ++i) {  
              if ((N - i * (i - 1) / 2) % i == 0) ++res;  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
    
  // 方法一： 暴力法  
  ```  
  TLE 暴力法：  
  class Solution {  
      public int consecutiveNumbersSum(int N) {  
          int ans = 0;  
          for (int start = 1; start <= N; ++start) {  
              int target = N, x = start;  
              while (target > 0)  
                  target -= x++;  
              if (target == 0) ans++;  
          }  
          return ans;  
      }  
  }  
    
  ```  
    
  // 方法二根据等差数列求和  
  假设 k 个连续正整数的和为 NN，即 N = (x + 1) + (x + 2) + ... + (x + k)  
    ，即 2N = k(2x + k + 1)   
    遍历k[1,2N]，如果x能是整数，就说明有解  
     
   ```  
   TLE   
   class Solution {  
       public int consecutiveNumbersSum(int N) {  
           // 2N = k(2x + k + 1)  
           int ans = 0;  
           for (int k = 1; k <= 2*N; ++k)  
               if (2 * N % k == 0) {  
                   int y = 2 * N / k - k - 1;  
                   if (y % 2 == 0 && y >= 0)  
                       ans++;  
               }  
           return ans;  
       }  
   }  
   ```  
     
   // 进阶： 略  
   1。 k < 2x+k+1 所以 k < sqrt(2) * N  
     
   找规律  
   如果可以是n个连续数，那么N - (1+2+...+n) 必须可以被n整除  
     
    /*****************************  
           15 = 4 + 5 + 6  
              = 3 * 3 + 1 + 2 + 3  
              = n * 3 + ((n+1) * n)/2  
                
           Therefore, if with n consecutive numbers,   
           (N - ((n+1) * n)/2)%n == 0  
             
           **************************************/  
           int res = 1;  
           for (int n = 2; ((n+1) * n)/2 <= N; n++) {  
               if ((N - ((n+1) * n)/2)%n == 0) res++;  
           }  
           return res;  
     
   作者：georgeworkhard  
   链接：https://leetcode-cn.com/problems/consecutive-numbers-sum/solution/zhao-shu-zi-gui-lu-osqrt-n-by-georgeworkhard/  
   来源：力扣（LeetCode）  
   著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。  
    
tags:    
  -  重点数学  
  -  数字逻辑  
