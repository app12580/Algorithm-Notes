### description    
  Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.  
    
  If there is no non-empty subarray with sum at least K, return -1.  
    
     
    
  Example 1:  
    
  Input: A = [1], K = 1  
  Output: 1  
  Example 2:  
    
  Input: A = [1,2], K = 4  
  Output: -1  
  Example 3:  
    
  Input: A = [2,-1,2], K = 3  
  Output: 3  
     
    
  Note:  
    
  1 <= A.length <= 50000  
  -10 ^ 5 <= A[i] <= 10 ^ 5  
  1 <= K <= 10 ^ 9  
    
  返回符合条件的最小值：  
  某段连续子区间的长度，并且要求子区间的和大于等于K  
### solution    
```    
// 方法一： 通过Deque来实现滑动窗口  
Runtime: 26 ms, faster than 88.44% of Java online submissions for Shortest Subarray with Sum at Least K.  
Memory Usage: 54 MB, less than 14.29% of Java online submissions for Shortest Subarray with Sum at Least K.  
  
  public int shortestSubarray(int[] A, int K) {  
          int N = A.length;  
          long[] P = new long[N+1];  
          for (int i = 0; i < N; ++i)  
              P[i+1] = P[i] + (long) A[i];  
    
          // Want smallest y-x with P[y] - P[x] >= K  
          int ans = N+1; // N+1 is impossible  
          Deque<Integer> monoq = new LinkedList(); //opt(y) candidates, as indices of P  
    
          for (int y = 0; y < P.length; ++y) {  
              // Want opt(y) = largest x with P[x] <= P[y] - K;  
              // 把队列后面比当前大的，全弹出去？  
              //会变成一个单调增队列  
              while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()])  
                  monoq.removeLast();  
    
              // 如果队列中第一个元素和当前节点满足K的条件，那么弹出队列，同时比较ans  
              while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K)  
                  ans = Math.min(ans, y - monoq.removeFirst());  
    
              monoq.addLast(y);  
              // 这个循环里面相当于做了大量的筛选过程  
              // 1、首先筛选单调增队列  2、然后当前和first比较，开始筛选结果  
          }  
    
          return ans < N+1 ? ans : -1;  
      }  
  
```    
    
### 个人解读    
  思路一： 类似于curLen求最大子区间和的内容，然后遇到最大和以后，就递归子区间，继续计算。  
    
  怎么说呢，身为Har题目，最起码有个O(N)的算法才对吧  
    
  总结：  
  1、类似于滑动窗口，只不过不同于两个int，这里用了一个Deque，主要是可以存储更多内容，比如的单调栈信息  
    
tags:    
  -  滑动窗口进阶  
  -  双向队列  
