### description    
  We are given an array A of N lowercase letter strings, all of the same length.  
    
  Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.  
    
  For example, if we have an array A = ["babca","bbazb"] and deletion indices {0, 1, 4}, then the final array after deletions is ["bc","az"].  
    
  Suppose we chose a set of deletion indices D such that after deletions, the final array has every element (row) in lexicographic order.  
    
  For clarity, A[0] is in lexicographic order (ie. A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]), A[1] is in lexicographic order (ie. A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]), and so on.  
    
  Return the minimum possible value of D.length.  
    
     
    
  Example 1:  
    
  Input: ["babca","bbazb"]  
  Output: 3  
  Explanation: After deleting columns 0, 1, and 4, the final array is A = ["bc", "az"].  
  Both these rows are individually in lexicographic order (ie. A[0][0] <= A[0][1] and A[1][0] <= A[1][1]).  
  Note that A[0] > A[1] - the array A isn't necessarily in lexicographic order.  
  Example 2:  
    
  Input: ["edcba"]  
  Output: 4  
  Explanation: If we delete less than 4 columns, the only row won't be lexicographically sorted.  
  Example 3:  
    
  Input: ["ghi","def","abc"]  
  Output: 0  
  Explanation: All rows are already lexicographically sorted.  
     
    
  Note:  
    
  1 <= A.length <= 100  
  1 <= A[i].length <= 100  
### solution    
```    
// 方法一： 逆向思维 + 动态规划  
Runtime: 9 ms, faster than 60.59% of Java online submissions for Delete Columns to Make Sorted III.  
Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Delete Columns to Make Sorted III.  
  
  class Solution {  
         //    dp[k] 表示在输入为 [row[k:] for row in A] 时保存的列数，那么 dp[k] 的递推式显而易见。  
  //    "babca","bbazb"  
      public int minDeletionSize(String[] A) {  
          int W = A[0].length();  
          int[] dp = new int[W];  //dp[i] 表示列数[i, W-1]范围内保留的列数  
          Arrays.fill(dp, 1);  
          for (int i = W-2; i >= 0; --i)  
              search: for (int j = i+1; j < W; ++j) {  
                  for (String row: A)  
                      if (row.charAt(i) > row.charAt(j))  
                          continue search;  
    
                  dp[i] = Math.max(dp[i], 1 + dp[j]);  
              }  
    
          int kept = 0;  
          for (int x: dp)  
              kept = Math.max(kept, x);  
          return W - kept;  
      }  
  }  
     
```    
    
### 个人解读    
  由于遇到不满足排序的时候，可以删除前面也可以删除后面，所以感觉Dp是一个方向。还有一种思路是每个字符的所有删减列出来，之后再汇总起来，但是因为可能性很多，所以效率太低。  
    
  知道删除了哪些列并不能对后续操作有帮助，但是如果知道保留哪些列，那么帮助就很大了  
    
tags:    
  -  动态规划  
  -  逆向思维  
