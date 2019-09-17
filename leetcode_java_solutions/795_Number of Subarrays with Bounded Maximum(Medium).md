### description    
  We are given an array A of positive integers, and two positive integers L and R (L <= R).  
    
  Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.  
    
  Example :  
  Input:   
  A = [2, 1, 4, 3]  
  L = 2  
  R = 3  
  Output: 3  
  Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].  
  Note:  
    
  L, R  and A[i] will be an integer in the range [0, 10^9].  
  The length of A will be in the range of [1, 50000].  
### solution    
```    
//不服啊，为啥这方法效率还会低？  
Runtime: 7 ms, faster than 17.52% of Java online submissions for Number of Subarrays with Bounded Maximum.  
Memory Usage: 50 MB, less than 100.00% of Java online submissions for Number of Subarrays with Bounded Maximum.  
  
  class Solution {  
       public int numSubarrayBoundedMax(int[] A, int L, int R) {  
          int res = 0;  
          int preFlag = -1;       //-1 无 0小 1中  
          int preNum = 0;  
          int i = 0;  
          while(i < A.length) {  
              if(judge(A[i],L,R) == 2) {  
                  preFlag = -1;  
                  preNum = 0;  
              }  
              while(i<A.length - 1 && judge(A[i],L,R) == 2) i++;  
              if(i >= A.length) break;  
              int curFlag = judge(A[i], L, R);  
              int curNum = 1;  
              while(i<A.length - 1 && judge(A[i + 1],L,R) == curFlag) {  
                  i++;  
                  curNum++;  
              }  
              res += cal(preFlag, preNum, curFlag, curNum);  
              preFlag = curFlag;  
              preNum += curNum;  
              i++;  
          }  
          return res;  
      }  
    
      private int cal(int preFlag, int preNum, int curFlag, int curNum) {  
          if(preFlag == -1) {  
              if(curFlag == 1) {  
                  return sum(curNum);  
              }  else {  
                  return 0;  
              }  
          } else if(preFlag == 0) {  
              return preNum * curNum + sum(curNum);  
          } else {  
              //前面是中，当前是小，中的在之前加过了。  
              return preNum * curNum;  
          }  
      }  
    
      private int judge(int n, int L, int R) {  
          if(n < L) {  
              return 0;  
          } else if(n > R) {  
              return 2;  
          } else {  
              return 1;  
          }  
      }  
    
      private int sum(int n) {  
          return (1 + n) * n / 2;  
      }  
  }  
    
  // 方法二： 不服不行。。。  
  Runtime: 3 ms, faster than 96.90% of Java online submissions for Number of Subarrays with Bounded Maximum.  
  Memory Usage: 50.1 MB, less than 100.00% of Java online submissions for Number of Subarrays with Bounded Maximum.  
    
  核心： j表示左起第一个中小的坐标。  
  count表示 小中或者中的长度。这样之后  
  如果遇到中，就是更新count然后+count;  
  如果遇到小，只加count，不需要更新count;  
    
  精华就是把小中数列和中数列两种情况合并到一块了。  
    
  class Solution {  
      public int numSubarrayBoundedMax(int[] A, int L, int R) {  
          int res = 0, count = 0, i=0;  
          for(int j=0; j<A.length; j++) {  
              if(A[j]>=L && A[j]<=R) {    count = j-i+1;  res+=count; }  
              else if(A[j]<L) res+=count;  
              else {  i=j+1;  count=0;    }  
          }  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  数字分为三种，比范围小，比范围大，在范围中。  
  如果小的，可以挂靠到两边的。  
  如果大的，则两边全部切掉。  
  能否利用这一点，然后创建一个或多个合适的数组用来遍历。。  
    
  小中大大小小中中小  
    
  中：1  
  中中： 3  
  中小： 2  
  中小小： 3  
  中中小： 5  
  中中小小： 7  
  小中小： 4  
    
  使用栈，  
  如果中m小n，那么有 sum(m) 和 m * n  
    
  反思：少判断了一种情况中小中，这种时候第一次的中也是有效果的。  
  解决方法猜测：修改preFlag和preNum标记  
  而小中小的情况也是如此  
    
  ```  
   preNum = curNum;  
  ```  
  改成  
   ```  
     preNum += curNum;  
    ```  
    
tags:    
  -  重点数学  
  -  数组  
  -  优雅代码  
