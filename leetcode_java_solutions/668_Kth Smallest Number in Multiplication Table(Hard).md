### description    
  Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?  
    
  Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.  
    
  Example 1:  
  Input: m = 3, n = 3, k = 5  
  Output:   
  Explanation:   
  The Multiplication Table:  
  1	2	3  
  2	4	6  
  3	6	9  
    
  The 5-th smallest number is 3 (1, 2, 2, 3, 3).  
  Example 2:  
  Input: m = 2, n = 3, k = 6  
  Output:   
  Explanation:   
  The Multiplication Table:  
  1	2	3  
  2	4	6  
    
  The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).  
  Note:  
  The m and n will be in the range [1, 30000].  
  The k will be in the range [1, m * n]  
### solution    
```    
Runtime: 10 ms, faster than 82.58% of Java online submissions for Kth Smallest Number in Multiplication Table.  
Memory Usage: 32.9 MB, less than 33.33% of Java online submissions for Kth Smallest Number in Multiplication Table.  
  
  
  class Solution {  
       public int findKthNumber(int m, int n, int k) {  
          int low = 1 , high = m * n + 1;  
    
          while (low < high) {  
              int mid = low + (high - low) / 2;  
              int c = count(mid, m, n);  
              //问题转化： c >= k的最左边一个。  
              if (c >= k) high = mid;  
              else low = mid + 1;  
          }  
    
          return high;  
      }  
    
      //return <= v的个数  
      private int count(int v, int m, int n) {  
          int count = 0;  
          for (int i = 1; i <= m; i++) {  
              int temp = Math.min(v / i , n);  
              count += temp;  
          }  
          return count;  
      }  
  }  
```    
    
### 个人解读    
      
完全不知所措啊。。。。。  
第一反应。。。就这也能用二分法？  
  
类似于那个什么最小sumAverage的那个题目。虽然不知道正向的结果是什么，但是如果给出某个数值，很容易知道它是第几个位置  
    
    1   2   3   4   5   6   7   8   9     
    2   4   6   8   10  12  14  16  18  
    3   6   9   12  15  18  21  24  27    
    4   8   12  16  20  24  28  32  36  
    5   10  15  20  25  30  35  40  45  
    6   12  18  24  30  36  42  48  54  
    7   14  21  28  35  42  49  56  63  
    
    
tags:    
  -  重点数学  
  -  数字逻辑  
  -  逆向思维  
  -  Kth问题  
