### description    
  A sorted list A contains 1, plus some number of primes.  Then, for every p < q in the list, we consider the fraction p/q.  
    
  What is the K-th smallest fraction considered?  Return your answer as an array of ints, where answer[0] = p and answer[1] = q.  
    
  Examples:  
  Input: A = [1, 2, 3, 5], K = 3  
  Output: [2, 5]  
  Explanation:  
  The fractions to be considered in sorted order are:  
  1/5, 1/3, 2/5, 1/2, 3/5, 2/3.  
  The third fraction is 2/5.  
    
  Input: A = [1, 7], K = 1  
  Output: [1, 7]  
  Note:  
    
  A will have length between 2 and 2000.  
  Each A[i] will be between 1 and 30000.  
  K will be between 1 and A.length * (A.length - 1) / 2.  
### solution    
```    
// 方法一：  通过优先队列  
Runtime: 730 ms, faster than 16.07% of Java online submissions for K-th Smallest Prime Fraction.  
Memory Usage: 38.3 MB, less than 66.67% of Java online submissions for K-th Smallest Prime Fraction.  
  class Solution {  
     public int[] kthSmallestPrimeFraction(int[] A, int K) {  
          int len = A.length;  
          int[] arr = new int[len];   //表示分母为A[i]的时候， 分子的索引， 要求 arr[i] < i;  
          int left = A[0];  
          int right = A[len - 1];  
          PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> A[p1[0]] * A[p2[1]] - A[p1[1]] * A[p2[0]]);  
    
          for(int i = 1; i < len; i++) {  
              int l = arr[i]++;  
              int r = i;  
              pq.offer(new int[]{l, r});  
          }  
    
          while(K-- > 0){  
              int[] poll = pq.poll();  
              int l = poll[0];  
              int r = poll[1];  
              if(arr[r] < r) {  
                  pq.offer(new int[]{arr[r]++ ,r});  
              }  
    
              if(K == 0) {  
                  left = l;  
                  right = r;  
              }  
          }  
          return new int[]{A[left], A[right]};  
    
      }  
  }  
    
  // 方法二： PQ的优化  
  不通过arr标记，直接通过每次弹出来的p的左值进行比较  
  Runtime: 475 ms, faster than 46.47% of Java online submissions for K-th Smallest Prime Fraction.  
  Memory Usage: 38.5 MB, less than 66.67% of Java online submissions for K-th Smallest Prime Fraction.  
    
  class Solution {  
      public int[] kthSmallestPrimeFraction(int[] A, int K) {  
          int n = A.length;  
    
          PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {  
              public int compare(int[] a, int[] b) {  
                  return Integer.compare(A[a[0]] * A[n - 1 - b[1]], A[n - 1 - a[1]] * A[b[0]]);  
              }  
          });  
    
          for (int i = 0; i < n; i++) {  
              pq.offer(new int[] {i, 0});  
          }  
    
          while (--K > 0) {  
              int[] p = pq.poll();  
    
              if (++p[1] < n) {  
                  pq.offer(p);  
              }  
          }  
    
          return new int[] {A[pq.peek()[0]], A[n - 1 - pq.peek()[1]]};  
      }  
  }  
    
  // 方法三： 二分法、 效率最高  
  Runtime: 6 ms, faster than 75.49% of Java online submissions for K-th Smallest Prime Fraction.  
  Memory Usage: 39 MB, less than 66.67% of Java online submissions for K-th Smallest Prime Fraction.  
    
  class Solution {  
      public int[] kthSmallestPrimeFraction(int[] A, int K) {  
      double l = 0, r = 1;  
      int p = 0, q = 1;  
        
      for (int n = A.length, cnt = 0; true; cnt = 0, p = 0) {  
          double m = (l + r) / 2;  
            
          for (int i = 0, j = n - 1; i < n; i++) {  
              while (j >= 0 && A[i] > m * A[n - 1 - j]) j--;  
              cnt += (j + 1);  
                
              if (j >= 0 && p * A[n - 1 - j] < q * A[i]) {  // 最后一次的计数    
                  p = A[i];  
                  q = A[n - 1 - j];  
              }  
          }  
            
          if (cnt < K) {  
              l = m;  
          } else if (cnt > K) {  
              r = m;  
          } else {  
              return new int[] {p, q};  
          }  
      }  
  }  
  }  
```    
    
### 个人解读    
  思路：  
  1、用一个int[]标记每个位置的对应的索引，然后用一个PriorityQueue存储每个位置上的当前数值，之后循环k次  
  2、目标值问题，用二分法。  
    
  https://leetcode.com/problems/k-th-smallest-prime-fraction/submissions/  
  这里面写的很详细  
    
  这种问题二分法效率最高。  
    
tags:    
  -  目标值问题(Kth)  
  -  优先队列  
  -  矩阵  
