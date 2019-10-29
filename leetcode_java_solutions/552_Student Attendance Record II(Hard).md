### description    
  Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.  
    
  A student attendance record is a string that only contains the following three characters:  
    
  'A' : Absent.  
  'L' : Late.  
  'P' : Present.  
  A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).  
    
  Example 1:  
  Input: n = 2  
  Output: 8   
  Explanation:  
  There are 8 records with length 2 will be regarded as rewardable:  
  "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"  
  Only "AA" won't be regarded as rewardable owing to more than one absent times.   
  Note: The value of n won't exceed 100,000.  
### solution    
```    
Runtime: 45 ms, faster than 36.54% of Java online submissions for Student Attendance Record II.  
Memory Usage: 36.1 MB, less than 27.27% of Java online submissions for Student Attendance Record II.  
  
  class Solution {  
       public int checkRecord(int n) {  
          int m = 1000000007;  
          int[] A = new int [Math.max(n, 3)];  
          int[] P = new int [Math.max(n, 3)];  
          int[] L = new int [Math.max(n, 3)];  
    
          P[0] = 1;  
          L[0] = 1;  
          L[1] = 3;  
          A[0] = 1;  
          A[1] = 2;  
          A[2] = 4;  
    
          if(n == 1) return 3;  
    
          for(int i = 1; i < n; i++)  
          {  
              A[i - 1] %= m;  
              P[i - 1] %= m;  
              L[i - 1] %= m;  
    
              P[i] = ((A[i - 1] + P[i - 1]) % m + L[i - 1]) % m;  
    
              if(i > 1) L[i] = ((A[i - 1] + P[i - 1]) % m + (A[i - 2] + P[i - 2]) % m) % m;  
    
              if(i > 2) A[i] = ((A[i - 1] + A[i - 2]) % m + A[i - 3]) % m;  
          }  
    
          return ((A[n - 1] % m + P[n - 1] % m) % m + L[n - 1] % m) % m;  
      }  
  }  
```    
    
### 个人解读    
  不能出现两个A 或者连续三个L。  
  要么直接数学法一步算出来，要么是通过DP算出来，感觉DP比较靠谱一些啊。  
  需要对数据类型分类，好让dp[n+1]与dp[n]来联系起来  
  主要就是看n+1可以为ALP的个数  
  A:  前n个数只有没有A  
  L:  最后两位不是LL（可以转化）  
  P： n的全部  
  转化的思路需要严谨，变量宁可多，但是要足够充分  
  https://leetcode.com/problems/student-attendance-record-ii/discuss/101643/Share-my-O(n)-C%2B%2B-DP-solution-with-thinking-process-and-explanation  
  ```  
   A(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.  
   noAP(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.  
   noAL(n) = noAP(n - 1) + noAP(n - 2), n ≥ 3.  
   can be simplified to  
   // 原因： A(n) = noAP(n)  
   A(n) = A(n - 1) + A(n - 2) + A(n - 3), n ≥ 4.  
  ```  
    
   
    
tags:    
  -  重点数学  
  -  DP建模  
