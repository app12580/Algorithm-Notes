### description    
  N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.  
    
  The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).  
    
  The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.  
    
  Example 1:  
    
  Input: row = [0, 2, 1, 3]  
  Output: 1  
  Explanation: We only need to swap the second (row[1]) and third (row[2]) person.  
  Example 2:  
    
  Input: row = [3, 2, 0, 1]  
  Output: 0  
  Explanation: All couples are already seated side by side.  
  Note:  
    
  len(row) is even and in the range of [4, 60].  
  row is guaranteed to be a permutation of 0...len(row)-1.  
    
### solution    
```    
// 方法一： 并查集  
Runtime: 0 ms, faster than 100.00% of Java online submissions for Couples Holding Hands.  
Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Couples Holding Hands.  
  class Solution {  
      private class UF {  
          private int[] parents;  
          public int count;  
          UF(int n) {  
              parents = new int[n];  
              for (int i = 0; i < n; i++) {  
                  parents[i] = i;  
              }  
              count = n;  
          }  
            
          private int find(int i) {  
              if (parents[i] == i) {  
                  return i;  
              }  
              parents[i] = find(parents[i]);  
              return parents[i];  
          }  
            
          public void union(int i, int j) {  
              int a = find(i);  
              int b = find(j);  
              if (a != b) {  
                  parents[a] = b;  
                  count--;  
              }  
          }  
      }  
        
        
      public int minSwapsCouples(int[] row) {  
          int N = row.length/ 2;  
          UF uf = new UF(N);  
          for (int i = 0; i < N; i++) {  
              int a = row[2*i];  
              int b = row[2*i + 1];  
              uf.union(a/2, b/2);  
          }  
          return N - uf.count;  
      }  
  }  
    
  // 方法二： 循环交换  
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Couples Holding Hands.  
  Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Couples Holding Hands.  
  class Solution {  
      public int minSwapsCouples(int[] row) {  
          int len = row.length;  
          int res = 0;  
          int[] pos = new int[len];  
          for(int i = 0; i < len; i++) {  
              pos[row[i]] = i;  
          }  
          for (int i = 0; i < len; i += 2) {  
              int cur = row[i];  
              int mate = cur ^ 1;  
              if(mate != row[i + 1]){  
                  swap(row, pos, i + 1, pos[mate]);  
                  res++;  
              }  
          }  
          return res;  
      }  
    
      private void swap(int[] arr, int[] pos, int i, int j) {  
          int t = arr[i];  
          arr[i] = arr[j];  
          arr[j] = t;  
         pos[arr[j]] = j;  
      }  
  }  
```    
    
### 个人解读    
  数学问题，遍历是不太靠谱的了，感觉要么贪婪要么DP  
    
  思路一：  
  感觉会存在那么个贪婪算法。  
  最前面的两个数字，与其全部换掉，不如只换掉一个。    
  ab....A.....B  
  aA....b.....B  
  cC.a..A..b..B  
  但即便这样，仍然是有两个数字奇数位还是偶数位？  
  需要DP或者回溯。回溯的话效率很差。  
  所以只能Dp了吧，大概。  
    
  总结：  
  1、首先想到并查集的做法  
  2、然后根据并查集，就会得到基于循环交换的贪婪算法  
    
   
    
tags:    
  -  并查集  
  -  贪婪算法  
  -  循环交换  
